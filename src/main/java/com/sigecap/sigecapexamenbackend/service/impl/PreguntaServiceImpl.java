package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.*;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.PreguntaRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.EncuestaJdbcRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private ExamenJdbcRepository examenJdbcRepository;

    @Autowired
    private EncuestaJdbcRepository encuestaJdbcRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Pregunta> getAllActives() {
        return preguntaRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Pregunta> getAll() {
        return (List<Pregunta>) preguntaRepository.findAll();
    }

    @Override
    public Pregunta getById(String id) {
        return preguntaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pregunta> getByCurso(String idCurso) {
        return preguntaRepository.getByCurso(idCurso,Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Pregunta> getByTipoPregunta(String idTipoPregunta) {
        return preguntaRepository.getByTipoPregunta(idTipoPregunta,Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Pregunta> getByCursoAndTipoPregunta(String idCurso, String idTipoPregunta) {
        return preguntaRepository.getByCursoAndTipoPregunta(idCurso,idTipoPregunta,Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Pregunta> getByCursoAndTipoPreguntaAndEstado(String idCurso, String idTipoPregunta, String estado) {
        return preguntaRepository.getByCursoAndTipoPreguntaAndEstado(idCurso,idTipoPregunta,estado);
    }

    @Override
    public Pregunta save(Pregunta p) throws IOException {

        if(p.getIdPregunta()==null) {
            String pk = preguntaRepository.generatePrimaryKey(Constantes.TABLE_PREGUNTA, Constantes.ID_TABLE_PREGUNTA);
            p.setIdPregunta(pk);
        }
        String enunciadoTexto = Util.convertHtmlToString(p.getEnunciado());
        p.setEnunciadoTexto(enunciadoTexto);
        return preguntaRepository.save(p);
    }

    @Override
    @Transactional
    public Pregunta saveMasivo(Pregunta p) throws IOException {

        if(p.getIdPregunta()==null) {
            String pk = preguntaRepository.generatePrimaryKey(Constantes.TABLE_PREGUNTA, Constantes.ID_TABLE_PREGUNTA);
            p.setIdPregunta(pk);
        }

        String enunciadoTexto = Util.convertHtmlToString(p.getEnunciado());
        p.setEnunciadoTexto(enunciadoTexto);
        return preguntaRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(String id) {
        preguntaRepository.deleteLogicById(id,Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public void updateState(String id, String state) {
        preguntaRepository.deleteLogicById(id,state);
    }

    @Override
    public List<ExamenPreguntaDTO> getPreguntasyRespuestasPorExamen(String idExamen) {
        List<PreguntasPorExamenDTO> preguntasExamen = examenJdbcRepository.getPreguntasPorExamen(idExamen);
        Map<String,List<PreguntasPorExamenDTO>> result = preguntasExamen.stream().collect(Collectors.groupingBy(PreguntasPorExamenDTO::getIdPregunta));

        List<ExamenPreguntaDTO> preguntaPorExamen = new ArrayList<>();
        for (Map.Entry<String,List<PreguntasPorExamenDTO>> entry : result.entrySet()) {

            ExamenPreguntaDTO pregunta = new ExamenPreguntaDTO();

            if(entry.getValue().size()>0){
                pregunta.setIdPregunta(entry.getValue().get(0).getIdPregunta());
                pregunta.setEnunciadoPregunta(entry.getValue().get(0).getEnunciadoPregunta());
                pregunta.setRetroAlimentacionPregunta(entry.getValue().get(0).getRetroAlimentacionPregunta());
                pregunta.setIdTipoPregunta(entry.getValue().get(0).getIdTipoPregunta());
                pregunta.setNombreTipoPregunta(entry.getValue().get(0).getNombreTipoPregunta());
            }
            else{
                break;
            }

            List<ExamenRespuestaDTO> respuestasPorPregunta = new ArrayList<>();
            for(PreguntasPorExamenDTO p: entry.getValue()){
              ExamenRespuestaDTO res = new ExamenRespuestaDTO();
              res.setIdRespuesta(p.getIdRespuesta());
              res.setEnunciadoRespuesta(p.getEnunciadoRespuesta());
              res.setRetroAlimentacionRespuesta(p.getRetroAlimentacionRespuesta());
              respuestasPorPregunta.add(res);
            }
            pregunta.setRespuestas(respuestasPorPregunta);
            preguntaPorExamen.add(pregunta);
        }

        if(preguntaPorExamen.size()>0){
            Collections.shuffle(preguntaPorExamen);
        }


        /*
        if(preguntaPorExamen.size()>0){
            Random random = new Random();
            int n = preguntaPorExamen.size();

            for (int i = 0; i < n; i++) {
                int randomIndex = i + random.nextInt(n - i);
                ExamenPreguntaDTO temp = preguntaPorExamen.get(randomIndex);
                preguntaPorExamen.set(randomIndex, preguntaPorExamen.get(i));
                preguntaPorExamen.set(i, temp);
            }

        }
        */
        return preguntaPorExamen;
    }

    @Override
    public List<EncuestaPreguntaDTO> getPreguntasyRespuestasPorEncuesta() {
        List<PreguntasPorEncuestaDTO> preguntasEncuesta = encuestaJdbcRepository.getPreguntasPorEncuesta();
        Map<String,List<PreguntasPorEncuestaDTO>> result = preguntasEncuesta.stream().collect(Collectors.groupingBy(PreguntasPorEncuestaDTO::getIdPregunta));

        List<EncuestaPreguntaDTO> preguntaPorEncuesta = new ArrayList<>();
        for (Map.Entry<String,List<PreguntasPorEncuestaDTO>> entry : result.entrySet()) {

            EncuestaPreguntaDTO pregunta = new EncuestaPreguntaDTO();

            if(entry.getValue().size()>0){
                pregunta.setIdPregunta(entry.getValue().get(0).getIdPregunta());
                pregunta.setEnunciadoPregunta(entry.getValue().get(0).getEnunciadoPregunta());
                pregunta.setNombrePregunta(entry.getValue().get(0).getNombrePregunta());
                pregunta.setRetroAlimentacionPregunta(entry.getValue().get(0).getRetroAlimentacionPregunta());
                pregunta.setIdTipoPregunta(entry.getValue().get(0).getIdTipoPregunta());
                pregunta.setNombreTipoPregunta(entry.getValue().get(0).getNombreTipoPregunta());
            }
            else{
                break;
            }

            List<EncuestaRespuestaDTO> respuestasPorPregunta = new ArrayList<>();
            for(PreguntasPorEncuestaDTO p: entry.getValue()){
                EncuestaRespuestaDTO res = new EncuestaRespuestaDTO();
                res.setIdRespuesta(p.getIdRespuesta());
                res.setEnunciadoRespuesta(p.getEnunciadoRespuesta());
                res.setRetroAlimentacionRespuesta(p.getRetroAlimentacionRespuesta());
                respuestasPorPregunta.add(res);
            }
            pregunta.setRespuestas(respuestasPorPregunta);
            preguntaPorEncuesta.add(pregunta);
        }

        return preguntaPorEncuesta;


    }

    @Override
    public List<Pregunta> listBandeja(BandejaPreguntaInDTO bandejaPreguntaInDTO) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pregunta> cq = cb.createQuery(Pregunta.class);

        Root<Pregunta> preguntaRoot = cq.from(Pregunta.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bandejaPreguntaInDTO.getIdCurso() != null && !bandejaPreguntaInDTO.getIdCurso().equals("")) {
            predicates.add(cb.equal(preguntaRoot.get("curso").get("idCurso"), bandejaPreguntaInDTO.getIdCurso()));
        }

        if (bandejaPreguntaInDTO.getNombrePregunta() != null && !bandejaPreguntaInDTO.getNombrePregunta().equals("")) {
            predicates.add(cb.like(preguntaRoot.get("enunciadoTexto"), "%" + bandejaPreguntaInDTO.getNombrePregunta() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
