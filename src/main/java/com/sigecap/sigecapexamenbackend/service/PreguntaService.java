package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaPreguntaInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.EncuestaPreguntaDTO;
import com.sigecap.sigecapexamenbackend.model.dto.ExamenPreguntaDTO;
import com.sigecap.sigecapexamenbackend.model.dto.PreguntasPorExamenDTO;
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

    List<Pregunta> getByCursoAndTipoPreguntaAndEstado(String idCurso,String idTipoPregunta,String estado);

    Pregunta save(Pregunta p) throws IOException;

    Pregunta saveMasivo(Pregunta p) throws IOException;
    void delete(String id);

    void updateState(String id,String  state);

    List<ExamenPreguntaDTO> getPreguntasyRespuestasPorExamen(String idExamen);

    List<EncuestaPreguntaDTO> getPreguntasyRespuestasPorEncuesta();

    List<Pregunta> listBandeja(BandejaPreguntaInDTO bandejaPreguntaInDTO);
}
