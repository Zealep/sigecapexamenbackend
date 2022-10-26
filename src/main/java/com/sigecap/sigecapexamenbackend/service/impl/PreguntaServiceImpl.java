package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.repository.PreguntaRepository;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

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
    public Pregunta save(Pregunta p) {
        if(p.getIdPregunta()==null) {
            String pk = preguntaRepository.generatePrimaryKey(Constantes.TABLE_PREGUNTA, Constantes.ID_TABLE_PREGUNTA);
            p.setIdPregunta(pk);
        }
        return preguntaRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(String id) {
        preguntaRepository.deleteLogicById(id,Constantes.ESTADO_INACTIVO);
    }
}
