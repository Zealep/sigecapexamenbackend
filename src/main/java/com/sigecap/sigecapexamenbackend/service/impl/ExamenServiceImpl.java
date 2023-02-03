package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.*;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.ExamenRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenJdbcRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenParticipanteJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("examenService")
public class ExamenServiceImpl implements ExamenService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private ExamenJdbcRepository examenJdbcRepository;

    @Autowired
    private ExamenParticipanteJdbcRepository examenParticipanteJdbcRepository;

    @Override
    public List<Examen> getAllActives() {
        return examenRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Examen> getByCurso(String idCurso) {
        return examenRepository.getByCurso(Constantes.ESTADO_ACTIVO,idCurso);
    }

    @Override
    public List<Examen> getAll() {
        return (List<Examen>) examenRepository.findAll();
    }

    @Override
    public List<Examen> listBandeja(BandejaExamenInDTO bandejaExamenInDTO) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Examen> cq = cb.createQuery(Examen.class);

        Root<Examen> examen = cq.from(Examen.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bandejaExamenInDTO.getIdCurso() != null && !bandejaExamenInDTO.getIdCurso().equals("")) {
            predicates.add(cb.equal(examen.get("curso").get("idCurso"), bandejaExamenInDTO.getIdCurso()));
        }

        if (bandejaExamenInDTO.getNombreExamen() != null && !bandejaExamenInDTO.getNombreExamen().equals("")) {
            predicates.add(cb.like(examen.get("nombreExamen"), "%" + bandejaExamenInDTO.getNombreExamen() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public Examen getById(String id) {
        return examenRepository.findById(id).orElse(null);
    }

    @Override
    public Examen save(Examen p) {
        if(p.getIdExamen()==null) {
            String pk = examenRepository.generatePrimaryKey(Constantes.TABLE_EXAMEN, Constantes.ID_TABLE_EXAMEN);
            p.setIdExamen(pk);
        }

        p.getDetalleExamen().forEach(x -> {
            x.setExamen(p);
        });

        return examenRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(String id) {
        examenRepository.deleteLogicById(id,Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public void updateState(String id, String state) {
        this.examenRepository.deleteLogicById(id,state);
    }

    @Override
    public List<CursosDisponibleExamenAlumnoDTO> listBandejaExamenesCursoPorAlumno(String id) {

        List<CursosDisponibleExamenAlumnoDTO> examenesCurso = examenJdbcRepository.getBandejaPorAlumno(id);

       /*
        Map<String,List<BandejaExamenPorAlumnoDTO>> result = examenes.stream().collect(Collectors.groupingBy(BandejaExamenPorAlumnoDTO::getIdCurso));

        List<BandejaCursoExamenPorAlumnoDTO> examenesCurso = new ArrayList<>();
        for(Map.Entry<String,List<BandejaExamenPorAlumnoDTO>> entry:result.entrySet()){
            BandejaCursoExamenPorAlumnoDTO cursoExamen = new BandejaCursoExamenPorAlumnoDTO();
            cursoExamen.setIdCurso(entry.getKey());
            cursoExamen.setExamanes(entry.getValue());
            examenesCurso.add(cursoExamen);
        }
        */


        return examenesCurso;
    }

    @Override
    public List<ExamenParticipanteDTO> getExamenesParticipante(String idCursoGrupo, String idSolicitudInscripcionDetalle) {
        List<ExamenParticipanteDTO> examenesParticipante = this.examenParticipanteJdbcRepository.getExamenesParticipante(idCursoGrupo,idSolicitudInscripcionDetalle);

        if(examenesParticipante!=null){
            for(ExamenParticipanteDTO e:examenesParticipante){
                List<IntentoExamenDTO> intentos = this.examenParticipanteJdbcRepository.getIntentoExamen(e.getIdSidExamen());
                e.setIntentosExamen(intentos);
            }
        return examenesParticipante;
    }
    else{
        return null;
    }

    }

    @Override
    public List<IntentoExamenDTO> getIntentoExamen(Long idSidExamen) {
        return null;
    }
}
