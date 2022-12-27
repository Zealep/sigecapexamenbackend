package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.exception.BusinessException;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaAperturaInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenPorAlumnoDTO;
import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteInscritoDto;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicitudInscripcion;
import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.repository.ExamenAperturaRepository;
import com.sigecap.sigecapexamenbackend.repository.ExamenRepository;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.repository.ParticipanteInscritoRepository;
import com.sigecap.sigecapexamenbackend.service.EmailService;
import com.sigecap.sigecapexamenbackend.service.ExamenAperturaService;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.service.ParticipanteDtoService;
import com.sigecap.sigecapexamenbackend.util.BusinessMsgError;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("examenAperturaService")
public class ExamenAperturaServiceImpl implements ExamenAperturaService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ExamenAperturaRepository examenAperturaRepository;

    @Autowired
    ParticipanteDtoService participanteInscritoService;

    @Autowired
    EmailService emailService;

    @Autowired
    ParticipanteInscritoRepository participanteInscritoRepository;

    @Autowired
    ExamenSolicitudInscripcionRepository examenSolicitudInscripcionRepository;

    @Override
    public List<ExamenApertura> getAllActives() {
        return examenAperturaRepository.getAllActive(Constantes.ESTADO_INACTIVO);
    }

    @Override
    public List<ExamenApertura> getAll() {
        return (List<ExamenApertura>) examenAperturaRepository.findAll();
    }

    @Override
    public List<ExamenApertura> listBandeja(BandejaAperturaInDTO bandejaAperturaInDTO) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExamenApertura> cq = cb.createQuery(ExamenApertura.class);

        Root<ExamenApertura> examenAperturaRoot = cq.from(ExamenApertura.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bandejaAperturaInDTO.getIdExamen() != null && !bandejaAperturaInDTO.getIdExamen().equals("")) {
            predicates.add(cb.equal(examenAperturaRoot.get("examen").get("idExamen"), bandejaAperturaInDTO.getIdExamen()));
        }

        if (bandejaAperturaInDTO.getIdCursoGrupo() != null && !bandejaAperturaInDTO.getIdCursoGrupo().equals("")) {
            predicates.add(cb.equal(examenAperturaRoot.get("cursoGrupo").get("idCursoGrupo"), bandejaAperturaInDTO.getIdCursoGrupo()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public ExamenApertura getById(String id) {
        return examenAperturaRepository.findById(id).orElse(null);
    }

    @Override
    public ExamenApertura save(ExamenApertura p) {
        boolean save = false;
        if (p.getIdExamenApertura() == null) {
            String pk = examenAperturaRepository.generatePrimaryKey(Constantes.TABLE_EXAMEN_APERTURA, Constantes.ID_TABLE_EXAMEN_APERTURA);
            p.setIdExamenApertura(pk);
            save = true;
        }
        ExamenApertura exa = examenAperturaRepository.save(p);
        if(save){
            this.guardarRelacionPorcadaAlumno(exa);
        }
        return exa;
    }

    @Override
    @Transactional
    public void delete(String id) {
        examenAperturaRepository.deleteLogicById(id, Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public void updateState(String id, String state) {
        this.examenAperturaRepository.deleteLogicById(id, state);
    }

    @Override
    @Transactional
    public void cerrarExamen(String id, Date fechaCierre) {
        this.examenAperturaRepository.cerrar(id, Constantes.CERRADO_APERTURA_EXAMEN, fechaCierre);
    }

    @Override
    public void notificarParticipantes(String idExamenApertura, String idCurso, String idGrupo) {

        ExamenApertura apertura = getById(idExamenApertura);
        List<ParticipanteInscritoDto> participantes = participanteInscritoService.getListParticipantesInscritosPorCriterios(idCurso, idGrupo, "", "", "");


        SimpleDateFormat formatFechaInicio = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

        String fechaInicio = formatFechaInicio.format(apertura.getFechaHoraApertura());
        String horaInicio = formatHora.format(apertura.getFechaHoraApertura());
        String nombreCurso = apertura.getExamen().getCurso().getNombreCurso();
        String duracion = String.valueOf(apertura.getTiempoDuracion());
        String intentos = String.valueOf(apertura.getNumeroIntentos());

        String subject = "SIGECAP - Aviso de examen " + nombreCurso;
        participantes.stream().forEach(x -> {
            String text = "<HTML><body>"
                    + "<p>Estimado<br>"
                    + "Sírvase en considerar la siguiente información:<br><br><b>"
                    + "Tiene programado un examen del curso:" + nombreCurso + "</b><br><br>"

                    + "<dd><b>Fecha: " + fechaInicio + "</b><br>"
                    + "<b>Hora: " + horaInicio + "</b><br>"
                    + "<b>Número de intentos: " + intentos + "</b><br>"
                    + "<b>Duración: " + duracion + " minutos</b><br><br>"


                    + "QUEDO ATENTA ANTE CUALQUIER INQUIETUD.<br><br>"
                    + "AREA DE CAPACITACION - JS CONSULTING<br>"
                    + "CEL: 932524891</i></p><br><br>"
                    + "<p>Las respuestas a este mensaje no se entregarán y no llegarán al Administrador del SIGECAP, le rogamos no responda. </p>"
                    + "</body></HTML>";
            emailService.sendMailHtml(x.getCorreo(), subject, text);
        });
    }

    @Override
    @Transactional
    public void registrarAsistencia(String dni, String idCurso, String idGrupo) {
        List<ParticipanteInscritoDto> participantes = participanteInscritoService.getListParticipantesInscritosPorCriterios(idCurso, idGrupo, "", "", "");
        ParticipanteInscritoDto p = participantes.stream().filter(x -> dni.equals(x.getNumeroDocumento()))
                .findAny()
                .orElse(null);

        if (p != null) {
            examenAperturaRepository.updateAsistencia(p.getIdSolicitudInscripcionDetalle(), Constantes.ASISTIO_EXAMEN);
        } else {
            throw new BusinessException(BusinessMsgError.ERROR_NO_SE_ENCUENTRA_PARTICIPANTE_EXAMEN);
        }


    }

    @Override
    public ExamenSolicitudInscripcion getExamenInscripcionById(Long id) {
        return examenSolicitudInscripcionRepository.findById(id).orElse(null);
    }

    @Override
    public void validarInicioExamen(BandejaExamenPorAlumnoDTO bandejaExamenPorAlumnoDTO) {
        if(bandejaExamenPorAlumnoDTO.getIndicadorEncuesta().equals("S")){
            if(bandejaExamenPorAlumnoDTO.getIndicadorRealizoEncuesta() == null || bandejaExamenPorAlumnoDTO.getIndicadorRealizoEncuesta().equals("") || bandejaExamenPorAlumnoDTO.getIndicadorAsistio().equals("N") ){
                throw new BusinessException(BusinessMsgError.ERROR_NO_REALIZO_ENCUESTA);
            }
        }

        if(bandejaExamenPorAlumnoDTO.getIndicadorAsistio() == null || bandejaExamenPorAlumnoDTO.getIndicadorAsistio().equals("") || bandejaExamenPorAlumnoDTO.getIndicadorAsistio().equals("N")){
            throw new BusinessException(BusinessMsgError.ERROR_NO_REALIZO_ASISTENCIA);
        }

        if(new Date().toInstant().isBefore(bandejaExamenPorAlumnoDTO.getFechaHoraApertura().toInstant())){
            throw new BusinessException(BusinessMsgError.ERROR_FECHA_INICIO_EXAMEN);
        }

        if(new Date().toInstant().isAfter(bandejaExamenPorAlumnoDTO.getFechaHoraCierre().toInstant())){
            throw new BusinessException(BusinessMsgError.ERROR_FECHA_FIN_EXAMEN);
        }

        ExamenSolicitudInscripcion ea = examenSolicitudInscripcionRepository.findById(bandejaExamenPorAlumnoDTO.getIdSidExamen()).orElse(null);

        Integer intentosRealizados = ea.getNumeroIntentoRealizado();
        Integer intentosPermitidos = ea.getExamenApertura().getNumeroIntentos();
        if(intentosRealizados != null){
            if(intentosRealizados>=intentosPermitidos){
                throw new BusinessException(BusinessMsgError.ERROR_NUMEROS_INTENTOS_);
            }
        }
    }

    private void guardarRelacionPorcadaAlumno(ExamenApertura examenApertura) {
        List<ParticipanteInscritoDto> participanteInscritoDtos = participanteInscritoRepository.getListParticipantesInscritosPorCriterios("", examenApertura.getCursoGrupo().getIdCursoGrupo(), "", "", "");
        participanteInscritoDtos.stream().forEach(x -> {
            ExamenSolicitudInscripcion ex = new ExamenSolicitudInscripcion();
            ex.setExamenApertura(examenApertura);
            ex.setIndicadorAsistio("N");
            ex.setIdSolicitudInscripcionDetalle(x.getIdSolicitudInscripcionDetalle());
            ex.setIndicadorRealizoExamen("N");
            ex.setNumeroIntentoRealizado(0);
            ex.setEstado(Constantes.ESTADO_ACTIVO);
            examenSolicitudInscripcionRepository.save(ex);
        });
    }
}
