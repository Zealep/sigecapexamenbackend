package com.sigecap.sigecapexamenbackend.model.dto;

public class PreguntasPorExamenDTO {

    private String idTipoPregunta;
    private String nombreTipoPregunta;
    private String idPregunta;
    private String enunciadoPregunta;
    private String retroAlimentacionPregunta;
    private String idRespuesta;
    private String enunciadoRespuesta;
    private String retroAlimentacionRespuesta;
    private String inRespuestaCorrecta;


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

    public String getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(String idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getEnunciadoRespuesta() {
        return enunciadoRespuesta;
    }

    public void setEnunciadoRespuesta(String enunciadoRespuesta) {
        this.enunciadoRespuesta = enunciadoRespuesta;
    }

    public String getRetroAlimentacionRespuesta() {
        return retroAlimentacionRespuesta;
    }

    public void setRetroAlimentacionRespuesta(String retroAlimentacionRespuesta) {
        this.retroAlimentacionRespuesta = retroAlimentacionRespuesta;
    }

    public String getInRespuestaCorrecta() {
        return inRespuestaCorrecta;
    }

    public void setInRespuestaCorrecta(String inRespuestaCorrecta) {
        this.inRespuestaCorrecta = inRespuestaCorrecta;
    }
}
