package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.exception.BusinessException;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaRespuestasInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.RespuestaRepository;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.service.RespuestaService;
import com.sigecap.sigecapexamenbackend.util.BusinessMsgError;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.Util;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private PreguntaService preguntaService;

    @Override
    public List<Respuesta> getAllActives() {
        return respuestaRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Respuesta> getAll() {
        return (List<Respuesta>) respuestaRepository.findAll();
    }

    @Override
    public Respuesta getById(String id) {
        return respuestaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Respuesta> getByPregunta(String id) {
        return respuestaRepository.getByPregunta(id, Constantes.ESTADO_ACTIVO);
    }

    @Override
    public Respuesta getByPreguntaAndRespuesta(String idPregunta, String idRespuesta) {
        return respuestaRepository.getByPreguntaAndRespuesta(idPregunta,idRespuesta,Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Respuesta> listBandeja(BandejaRespuestasInDTO bandejaRespuestasInDTO) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Respuesta> cq = cb.createQuery(Respuesta.class);

        Root<Respuesta> respuesta = cq.from(Respuesta.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bandejaRespuestasInDTO.getIdPregunta() != null && !bandejaRespuestasInDTO.getIdPregunta().equals("")) {
            predicates.add(cb.equal(respuesta.get("pregunta").get("idPregunta"), bandejaRespuestasInDTO.getIdPregunta()));
        }

        if (bandejaRespuestasInDTO.getNombreRespuesta() != null && !bandejaRespuestasInDTO.getNombreRespuesta().equals("")) {
            predicates.add(cb.like(respuesta.get("enunciadoTexto"), "%" + bandejaRespuestasInDTO.getNombreRespuesta() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public Respuesta save(Respuesta p) throws IOException {
        validarRespuesta(p);
        if (p.getIdRespuesta() == null) {
            String pk = respuestaRepository.generatePrimaryKey(Constantes.TABLE_RESPUESTA, Constantes.ID_TABLE_RESPUESTA);
            p.setIdRespuesta(pk);
        }
        String enunciandoTexto = Util.convertHtmlToString(p.getEnunciado());
        p.setEnunciadoTexto(enunciandoTexto);
        return respuestaRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(String id) {
        respuestaRepository.deleteLogicById(id, Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public void updateState(String id, String state) {
        this.respuestaRepository.deleteLogicById(id,state);
    }


    private void validarRespuesta(Respuesta respuesta) throws IOException {

        Pregunta p = preguntaService.getById(respuesta.getPregunta().getIdPregunta());
        String enunciadoTexto = Util.convertHtmlToString(respuesta.getEnunciado());
        respuesta.setEnunciadoTexto(enunciadoTexto);

        if (p != null) {
            if (p.getTipoPregunta().getIdTipoPregunta().equals(Constantes.OPCION_UNICA)) {
                if (respuesta.getIdRespuesta() == null) {
                    respuestaCorrectaRepetida(p, respuesta);
                } else {
                    respuestaCorrectaRepetidaEditar(p, respuesta);
                }
            }
            if (p.getTipoPregunta().getIdTipoPregunta().equals(Constantes.OPCION_VERDADERO_FALSO)) {
                if(respuesta.getIdRespuesta() == null){
                    respuestaVerdaderoFalsoRepetida(p, respuesta);
                    respuestaCorrectaRepetida(p, respuesta);
                }
                else{
                    respuestaCorrectaRepetidaEditar(p, respuesta);
                }

            }
            if (p.getTipoPregunta().getIdTipoPregunta().equals(Constantes.OPCION_NUMERICA)) {

                boolean result = NumberUtils.isCreatable(enunciadoTexto);
                if (!result) {
                    throw new BusinessException(BusinessMsgError.ERROR_NO_NUMERICO);
                }
                if (respuesta.getIdRespuesta() == null) {
                    respuestaCorrectaRepetida(p, respuesta);
                } else {
                    respuestaCorrectaRepetidaEditar(p, respuesta);
                }
            }

            if (p.getTipoPregunta().getIdTipoPregunta().equals(Constantes.OPCION_RESPUESTA)) {
                throw new BusinessException(BusinessMsgError.ERROR_SIN_RESPUESTA);
            }
        }
    }

    private void respuestaCorrectaRepetida(Pregunta p, Respuesta r) {

        if (r.getRespuestaCorrecta().equals(Constantes.INDICADOR_RESPUESTA_CORRECTA)) {
            List<Respuesta> respuestas = respuestaRepository.getByPregunta(p.getIdPregunta(), Constantes.ESTADO_ACTIVO);
            boolean exist = respuestas.stream().anyMatch(s -> s.getRespuestaCorrecta().equals(Constantes.INDICADOR_RESPUESTA_CORRECTA));
            if (exist)
                throw new BusinessException(BusinessMsgError.ERROR_EXISTE_RESPUESTA_CORRECTA);
        }
    }

    private void respuestaCorrectaRepetidaEditar(Pregunta p, Respuesta r) {

        if (r.getRespuestaCorrecta().equalsIgnoreCase(Constantes.INDICADOR_RESPUESTA_CORRECTA)) {
            List<Respuesta> respuestas = respuestaRepository.getByPregunta(p.getIdPregunta(), Constantes.ESTADO_ACTIVO);
            String idCorrecto = "";
            for (Respuesta re : respuestas) {
                if (re.getRespuestaCorrecta().equalsIgnoreCase(Constantes.INDICADOR_RESPUESTA_CORRECTA)) {
                    idCorrecto = re.getIdRespuesta();
                }
            }
            if (!idCorrecto.equals("")) {
                if (!idCorrecto.equalsIgnoreCase(r.getIdRespuesta())) {
                    throw new BusinessException(BusinessMsgError.ERROR_EXISTE_RESPUESTA_CORRECTA);
                }
            }

        }
    }

    private void respuestaVerdaderoFalsoRepetida(Pregunta p, Respuesta r) {
        List<Respuesta> respuestas = respuestaRepository.getByPregunta(p.getIdPregunta(), Constantes.ESTADO_ACTIVO);
        if (r.getEnunciadoTexto().equalsIgnoreCase(Constantes.RESPUESTA_VERDADERO)) {
            boolean exist = respuestas.stream().anyMatch(s -> s.getEnunciadoTexto().equalsIgnoreCase(Constantes.RESPUESTA_VERDADERO));
            if (exist)
                throw new BusinessException(BusinessMsgError.ERROR_VERDADERO_REPETIDO);
        }

        if (r.getEnunciadoTexto().equalsIgnoreCase(Constantes.RESPUESTA_FALSO)) {
            boolean exist = respuestas.stream().anyMatch(s -> s.getEnunciadoTexto().equalsIgnoreCase(Constantes.RESPUESTA_FALSO));
            if (exist)
                throw new BusinessException(BusinessMsgError.ERROR_FALSO_REPETIDO);
        }

    }
}
