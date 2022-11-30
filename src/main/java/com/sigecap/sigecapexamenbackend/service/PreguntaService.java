package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;

import java.io.IOException;
import java.util.List;

public interface PreguntaService {

    List<Pregunta> getAllActives();
    List<Pregunta> getAll();
    Pregunta getById(String id);
    List<Pregunta> getByCurso(String idCurso);
    List<Pregunta> getByTipoPregunta(String idTipoPregunta);
    List<Pregunta> getByCursoAndTipoPregunta(String idCurso,String idTipoPregunta);
    Pregunta save(Pregunta p) throws IOException;
    void delete(String id);

    void updateState(String id,String  state);

}
