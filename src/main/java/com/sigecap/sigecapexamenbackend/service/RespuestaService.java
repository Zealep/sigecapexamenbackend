package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaRespuestasInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;

import java.io.IOException;
import java.util.List;

public interface RespuestaService {

    List<Respuesta> getAllActives();
    List<Respuesta> getAll();
    Respuesta getById(String id);
    List<Respuesta> getByPregunta(String id);

    Respuesta getByPreguntaAndRespuesta(String idPregunta,String idRespuesta);

    List<Respuesta> listBandeja(BandejaRespuestasInDTO bandejaRespuestasInDTO);
    
    Respuesta save(Respuesta p) throws Exception;
    Respuesta saveMasivo(Respuesta p) throws Exception;

    void delete(String id);

    void updateState(String id,String  state);


}
