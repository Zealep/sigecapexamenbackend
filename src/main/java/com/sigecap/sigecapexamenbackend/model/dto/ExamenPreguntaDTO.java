package com.sigecap.sigecapexamenbackend.model.dto;

import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;

import java.util.List;

public class ExamenPreguntaDTO {

    private String idPregunta;
    private String enunciadoPregunta;
    private String retroAlimentacionPregunta;
    private String idTipoPregunta;
    private String nombreTipoPregunta;
    private List<ExamenRespuestaDTO> respuestas;

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEnunciadoPregunta() {
        return enunciadoPregunta;
    }

    public void setEnunciadoPregunta(String enunciadoPregunta) {
        this.enunciadoPregunta = enunciadoPregunta;
    }

    public String getRetroAlimentacionPregunta() {
        return retroAlimentacionPregunta;
    }

    public void setRetroAlimentacionPregunta(String retroAlimentacionPregunta) {
        this.retroAlimentacionPregunta = retroAlimentacionPregunta;
    }

    public String getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(String idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public String getNombreTipoPregunta() {
        return nombreTipoPregunta;
    }

    public void setNombreTipoPregunta(String nombreTipoPregunta) {
        this.nombreTipoPregunta = nombreTipoPregunta;
    }

    public List<ExamenRespuestaDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<ExamenRespuestaDTO> respuestas) {
        this.respuestas = respuestas;
    }
}
