package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;

import java.util.List;

public interface ExamenInscripcionIntentoService {

    List<ExamenSolicInsIntento> getAll();

    List<ExamenSolicInsIntento> getByExamen(Long idExamenSolicitud);
    ExamenSolicInsIntento save(ExamenSolicInsIntento ex);

    ExamenSolicInsIntento getById(Long id);

    void updateNotaAndIntento(Long id,Double nota,Integer intento);

    Integer ultimoIntento(Long idExamenSolicitud);
}
