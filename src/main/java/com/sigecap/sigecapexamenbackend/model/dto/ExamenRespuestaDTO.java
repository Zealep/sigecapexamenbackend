package com.sigecap.sigecapexamenbackend.model.dto;

public class ExamenRespuestaDTO
{
    private String idRespuesta;
    private String enunciadoRespuesta;
    private String retroAlimentacionRespuesta;

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
}
