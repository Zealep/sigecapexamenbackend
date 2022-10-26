package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;

import java.util.List;

public interface TipoPreguntaService {

    List<TipoPregunta> getAllActives();
    List<TipoPregunta> getAll();
}
