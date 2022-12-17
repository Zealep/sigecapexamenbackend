package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;

import java.util.List;

public interface ExamenInscripcionIntentoService {

    List<ExamenSolicInsIntento> getAll();
    ExamenSolicInsIntento save(ExamenSolicInsIntento ex);
}
