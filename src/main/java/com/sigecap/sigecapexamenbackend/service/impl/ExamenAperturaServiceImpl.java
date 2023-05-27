package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.exception.BusinessException;
import com.sigecap.sigecapexamenbackend.model.dto.*;
import com.sigecap.sigecapexamenbackend.model.entity.*;
import com.sigecap.sigecapexamenbackend.repository.*;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenAperturaJdbcRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenParticipanteJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.EmailService;
import com.sigecap.sigecapexamenbackend.service.ExamenAperturaService;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.service.ParticipanteDtoService;
import com.sigecap.sigecapexamenbackend.util.BusinessMsgError;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("examenAperturaService")
public class ExamenAperturaServiceImpl implements ExamenAperturaService {

    private static final Logger logger = LoggerFactory.getLogger(ExamenAperturaServiceImpl.class);


    @PersistenceContext
    EntityManager em;

    @Autowired
    private ExamenAperturaRepository examenAperturaRepository;

    @Autowired
    private ParticipanteDtoService participanteInscritoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ParticipanteInscritoRepository participanteInscritoRepository;

    @Autowired
    private ExamenSolicitudInscripcionRepository examenSolicitudInscripcionRepository;

    @Autowired
    private AsistenciaSolicitudInscripcionRepository asistenciaSolicitudInscripcionRepository;

    @Autowired
    private ExamenParticipanteJdbcRepository examenParticipanteJdbcRepository;

    @Autowired
    private ExamenAperturaJdbcRepository examenAperturaJdbcRepository;

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
        cq.orderBy(cb.desc(examenAperturaRoot.get("fechaHoraApertura")));

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
        List<ParticipanteInscritoDto> participantes = participanteInscritoRepository.getAsistenciaParticipantesCursoGrupo( idGrupo);
        ParticipanteInscritoDto p = participantes.stream().filter(x -> dni.equals(x.getNumeroDocumento()))
                .findAny()
                .orElse(null);

        if (p != null) {
            examenAperturaRepository.updateAsistencia(p.getIdSolicitudInscripcionDetalle(), Constantes.ASISTIO_EXAMEN);
            AsistenciaSolicitudInscripcion a = new AsistenciaSolicitudInscripcion(p.getIdSolicitudInscripcionDetalle(),new Date(),Constantes.NO_FIRMO_EXAMEN);
            asistenciaSolicitudInscripcionRepository.save(a);
        } else {
            throw new BusinessException(BusinessMsgError.ERROR_NO_SE_ENCUENTRA_PARTICIPANTE_EXAMEN);
        }


    }

    @Override
    public ExamenSolicitudInscripcion getExamenInscripcionById(Long id) {
        return examenSolicitudInscripcionRepository.findById(id).orElse(null);
    }

    @Override
    public void validarInicioExamen(ExamenParticipanteDTO examenParticipanteDTO) {

        ExamenSolicitudInscripcion esi = this.getExamenInscripcionById(examenParticipanteDTO.getIdSidExamen());
        List<FirmaExamenDTO> firma = examenParticipanteJdbcRepository.getFirmaExamen(examenParticipanteDTO.getIdSolicitudInscripcionDetalle());

        if(firma.get(0).getRealizoFirma().equals("N")){
            throw new BusinessException(BusinessMsgError.ERROR_NO_FIRMO);
        }

        if(esi.getIndicadorAsistio() == null || esi.getIndicadorAsistio().equals("") || esi.getIndicadorAsistio().equals("NO")){
            throw new BusinessException(BusinessMsgError.ERROR_NO_REALIZO_ASISTENCIA);
        }

        if(new Date().toInstant().isBefore(esi.getExamenApertura().getFechaHoraApertura().toInstant())){
            throw new BusinessException(BusinessMsgError.ERROR_FECHA_INICIO_EXAMEN);
        }

        if(new Date().toInstant().isAfter(esi.getExamenApertura().getFechaHoraCierre().toInstant())){
            throw new BusinessException(BusinessMsgError.ERROR_FECHA_FIN_EXAMEN);
        }

        if(esi.getNumeroIntentoRealizado() >= esi.getExamenApertura().getNumeroIntentos()){
                throw new BusinessException(BusinessMsgError.ERROR_NUMEROS_INTENTOS_);
        }
    }

    @Override
    public void validarEncuesta(CursosDisponibleExamenAlumnoDTO cursosDisponibleExamenAlumnoDTO) {
        if(cursosDisponibleExamenAlumnoDTO.getIndicadorRealizarEncuesta().equals("S")){
            if(cursosDisponibleExamenAlumnoDTO.getIndicadorRealizoEncuesta() == null || cursosDisponibleExamenAlumnoDTO.getIndicadorRealizoEncuesta().equals("")|| cursosDisponibleExamenAlumnoDTO.getIndicadorRealizoEncuesta().equals("N")){
                throw new BusinessException(BusinessMsgError.ERROR_NO_REALIZO_ENCUESTA);
            }
        }
    }

    @Override
    public ByteArrayInputStream exportReporteParticipantes(ConsultaAsistenciaParticipanteDTO consultaAsistenciaParticipanteDTO) {
        try {

            List<ParticipanteInscritoDto> list = participanteInscritoRepository.getAsistenciaParticipantesCursoGrupoyNotas(consultaAsistenciaParticipanteDTO.getParIdCursoGrupo(),consultaAsistenciaParticipanteDTO.getParIdExamenApertura());

            if(list.size()==0){
                return null;
            }

            String[] headers = {"Participante","DNI" ,"Empresa","Unidad","Asistencia","Nota Maxima"};

            Workbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            CellStyle headerStyle = ExcelUtil.headersStyle(workbook);
            CellStyle rowStyle = ExcelUtil.rowsStyle(workbook);

            Sheet sheet = workbook.createSheet("Reporte de participantes inscritos en el grupo");
            sheet.setDefaultColumnWidth(20);


            //titutlo

            Row row = sheet.createRow(0);

            Cell cell = row.createCell(0);
            cell.setCellValue("Curso:  " + list.get(0).getNombreCurso());
            cell.setCellStyle(headerStyle);

            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("Grupo:  " + list.get(0).getNombreGrupo());
            cell.setCellStyle(headerStyle);

            row = sheet.createRow(3);

            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);

            }

            int initRow = 4;
            for (ParticipanteInscritoDto p : list) {
                row = sheet.createRow(initRow);
                row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));

                ExcelUtil.createStringCell(p.getApellidoPaterno() + " "+ p.getApellidoMaterno()+ " "+p.getNombres(),row,0,rowStyle);
                ExcelUtil.createStringCell(p.getNumeroDocumento(),row,1,rowStyle);
                ExcelUtil.createStringCell(p.getNombreEmpresa(),row,2,rowStyle);
                ExcelUtil.createStringCell(p.getUnidad(),row,3,rowStyle);
                ExcelUtil.createStringCell(p.getAsistio(),row,4,rowStyle);
                ExcelUtil.createDoubleCell(p.getNotaMaxima(),row,5,rowStyle);

                row.setRowStyle(rowStyle);
                initRow++;
            }

            workbook.write(stream);
            workbook.close();
            return new ByteArrayInputStream(stream.toByteArray());
        }catch(Exception ex){
            logger.error(ex.getMessage());
            return null;

        }
    }

    @Override
    public void nuevosInscritos(ConsultaAsistenciaParticipanteDTO consultaAsistenciaParticipanteDTO) {
        List<String> nuevosInscritos = examenAperturaJdbcRepository.getNuevosInscritos(consultaAsistenciaParticipanteDTO.getParIdCursoGrupo(),consultaAsistenciaParticipanteDTO.getParIdExamenApertura());
            if(nuevosInscritos == null || nuevosInscritos.isEmpty()){
                throw new BusinessException(BusinessMsgError.ERROR_NUEVOS_INSCRITOS);
            }
            nuevosInscritos.stream().forEach(x->{
                ExamenSolicitudInscripcion ex = new ExamenSolicitudInscripcion();
                ExamenApertura e = new ExamenApertura();
                e.setIdExamenApertura(consultaAsistenciaParticipanteDTO.getParIdExamenApertura());
                ex.setExamenApertura(e);
                ex.setIndicadorAsistio("N");
                ex.setIdSolicitudInscripcionDetalle(x);
                ex.setIndicadorRealizoExamen("N");
                ex.setNumeroIntentoRealizado(0);
                ex.setEstado(Constantes.POR_INICIAR);
                examenSolicitudInscripcionRepository.save(ex);
            });
    }

    private void guardarRelacionPorcadaAlumno(ExamenApertura examenApertura) {
        List<ParticipanteInscritoDto> participanteInscritoDtos = participanteInscritoRepository.getAsistenciaParticipantesCursoGrupo(examenApertura.getCursoGrupo().getIdCursoGrupo());
        participanteInscritoDtos.stream().forEach(x -> {
            ExamenSolicitudInscripcion ex = new ExamenSolicitudInscripcion();
            ex.setExamenApertura(examenApertura);
            ex.setIndicadorAsistio("N");
            ex.setIdSolicitudInscripcionDetalle(x.getIdSolicitudInscripcionDetalle());
            ex.setIndicadorRealizoExamen("N");
            ex.setNumeroIntentoRealizado(0);
            ex.setEstado(Constantes.POR_INICIAR);
            examenSolicitudInscripcionRepository.save(ex);
        });
    }
}
