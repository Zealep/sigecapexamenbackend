package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaRespuestasInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;

import java.io.IOException;
import java.util.List;

public interface ExamenService {

    List<Examen> getAllActives();

    List<Examen> getByCurso(String idCurso);
    List<Examen> getAll();

    List<Examen> listBandeja(BandejaExamenInDTO bandejaExamenInDTO);

    Examen getById(String id);
    Examen save(Examen p);
    void delete(String id);
    void updateState(String id,String  state);

}
