package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.ExamenPreguntaDTO;
import com.sigecap.sigecapexamenbackend.model.dto.ExamenRespuestaDTO;
import com.sigecap.sigecapexamenbackend.model.dto.PreguntasPorExamenDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.repository.PreguntaRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private ExamenJdbcRepository examenJdbcRepository;

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

        return preguntaPorExamen;
    }
}
