package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.EncuestaInscripcionRespuestasDTO;
import com.sigecap.sigecapexamenbackend.model.entity.EncuestaInscripcionRespuesta;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntentoRespuesta;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.repository.EncuestaInscripcionRespuestaRepository;
import com.sigecap.sigecapexamenbackend.service.EncuestaInscripcionRespuestaService;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("encuestaInscripcionRespuestaService")
public class EncuestaInscripcionRespuestaServiceImpl implements EncuestaInscripcionRespuestaService {

    @Autowired
    private EncuestaInscripcionRespuestaRepository encuestaInscripcionRespuestaRepository;

    @Autowired
    private PreguntaService preguntaService;


    @Override
    @Transactional
    public void save(EncuestaInscripcionRespuestasDTO s) {

        s.getRespuestasEncuesta().stream().forEach(x->{
            if(x.getRespuesta() == null){
                x.setRespuesta(Constantes.RESPUESTA_VACIA);
            }
            encuestaInscripcionRespuestaRepository.save(x);
        });

        //Registro de comentarios

        List<Pregunta> p = preguntaService.getByCursoAndTipoPreguntaAndEstado(Constantes.ID_CURSO,Constantes.TIPO_PREGUNTA_COMENTARIO,Constantes.ESTADO_INACTIVO);

        if(p.size()>0){
            Pregunta pre = p.get(0);
            EncuestaInscripcionRespuesta ea = new EncuestaInscripcionRespuesta();
            ea.setIdSolicitudInscripcionDetalle(s.getIdSolicitudInscripcion());
            ea.setPregunta(pre.getIdPregunta());
            ea.setRespuestaIngresada(s.getComentarios());
            encuestaInscripcionRespuestaRepository.save(ea);
        }

        encuestaInscripcionRespuestaRepository.updateEncuesta(s.getIdSolicitudInscripcion(),Constantes.IND_REALIZO_ENCUESTA);

    }
}
