package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntentoRespuesta;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicitudInscripcion;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenInscripcionIntentoService;
import com.sigecap.sigecapexamenbackend.service.ExamenRevisionService;
import com.sigecap.sigecapexamenbackend.service.RespuestaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
    public void revisarExamen(Long idExamenIntento) {


                ExamenSolicInsIntento intento = examenInscripcionIntentoService.getById(idExamenIntento);
                ExamenSolicitudInscripcion solicitudInscripcion = examenSolicitudInscripcionRepository.findById(intento.getExamenSolicitudInscripcion().getIdExamenSolicitudInscripcion()).orElse(null);
                List<ExamenSolicInsIntentoRespuesta> respuestasIntento = intento.getDetalleRespuestas();

                String tipoExamen = solicitudInscripcion.getExamenApertura().getExamen().getTipoExamen().getIdTipoExamen();

                Double nota = 0.0;

                if(tipoExamen.equals(Constantes.TIPO_EXAMEN_FIJO)){

                    for(ExamenSolicInsIntentoRespuesta respuesta:respuestasIntento){
                        Respuesta r = respuestaService.getByPreguntaAndRespuesta(respuesta.getIdPregunta(),respuesta.getIdRespuestaMarcada());
                        if(r!=null){
                            if(r.getRespuestaCorrecta().equals(Constantes.INDICADOR_RESPUESTA_CORRECTA)){
                                Double puntuacion = r.getPregunta().getPuntuacion().doubleValue();
                                nota = nota + puntuacion;
                            }
                        }
                    }
                }

                if(tipoExamen.equals(Constantes.TIPO_EXAMEN_BALOTARIO)){
                    BigDecimal puntaje = new BigDecimal("20").divide(new BigDecimal(solicitudInscripcion.getExamenApertura().getExamen().getCantidadPreguntas()));

                    for(ExamenSolicInsIntentoRespuesta respuesta:respuestasIntento) {
                        Respuesta r = respuestaService.getByPreguntaAndRespuesta(respuesta.getIdPregunta(),respuesta.getIdRespuestaMarcada());
                        if(r!=null){
                            if(r.getRespuestaCorrecta().equals(Constantes.INDICADOR_RESPUESTA_CORRECTA)){
                                nota = nota +  puntaje.doubleValue();;
                            }
                        }
                    }
                }


                Integer numeroIntento = 0;

                List<ExamenSolicInsIntento> examenesIntentos = examenInscripcionIntentoService.getByExamen(solicitudInscripcion.getIdExamenSolicitudInscripcion());


                if(examenesIntentos == null || examenesIntentos.size() == 1){
                    numeroIntento = 1;
                }

                else{
                    Integer ultimoIntento = examenInscripcionIntentoService.ultimoIntento(solicitudInscripcion.getIdExamenSolicitudInscripcion());
                    numeroIntento = ultimoIntento + 1;
                }

                examenInscripcionIntentoService.updateNotaAndIntento(idExamenIntento,nota,numeroIntento);
                examenInscripcionIntentoService.updateEstado(idExamenIntento,Constantes.EXAMEN_REALIZADO);
                examenSolicitudInscripcionRepository.updateIntentoRealizado(intento.getExamenSolicitudInscripcion().getIdExamenSolicitudInscripcion(),numeroIntento,"S");


    }
}
