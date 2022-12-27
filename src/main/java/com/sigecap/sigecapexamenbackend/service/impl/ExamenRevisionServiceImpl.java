package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntentoRespuesta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenInscripcionIntentoService;
import com.sigecap.sigecapexamenbackend.service.ExamenRevisionService;
import com.sigecap.sigecapexamenbackend.service.RespuestaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("examenRevisionService")
public class ExamenRevisionServiceImpl implements ExamenRevisionService {

    @Autowired
    private ExamenInscripcionIntentoService examenInscripcionIntentoService;

    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private ExamenSolicitudInscripcionRepository examenSolicitudInscripcionRepository;

    @Override
    public void revisarExamen(Long idExamenIntento) {

        ExamenSolicInsIntento intento = examenInscripcionIntentoService.getById(idExamenIntento);
        List<ExamenSolicInsIntentoRespuesta> respuestasIntento = intento.getDetalleRespuestas();

        Integer nota = 0;

        for(ExamenSolicInsIntentoRespuesta respuesta:respuestasIntento){
            Respuesta r = respuestaService.getByPreguntaAndRespuesta(respuesta.getIdPregunta(),respuesta.getIdRespuestaMarcada());
            if(r!=null){
                if(r.getRespuestaCorrecta().equals(Constantes.INDICADOR_RESPUESTA_CORRECTA)){
                    Integer puntaje = r.getPregunta().getPuntuacion().intValue();
                    nota = nota + puntaje;
                }
            }
        }

        Integer numeroIntento = 0;

        List<ExamenSolicInsIntento> examenesIntentos = examenInscripcionIntentoService.getByExamen(intento.getExamenSolicitudInscripcion().getIdExamenSolicitudInscripcion());


        if(examenesIntentos == null || examenesIntentos.size() == 1){
            numeroIntento = 1;
        }

        else{
            Integer ultimoIntento = examenInscripcionIntentoService.ultimoIntento(intento.getExamenSolicitudInscripcion().getIdExamenSolicitudInscripcion());
            numeroIntento = ultimoIntento + 1;
        }

        examenInscripcionIntentoService.updateNotaAndIntento(idExamenIntento,nota,numeroIntento);
        examenSolicitudInscripcionRepository.updateIntentoRealizado(intento.getExamenSolicitudInscripcion().getIdExamenSolicitudInscripcion(),numeroIntento);
    }
}
