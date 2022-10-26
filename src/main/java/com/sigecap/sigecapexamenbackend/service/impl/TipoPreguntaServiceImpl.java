package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import com.sigecap.sigecapexamenbackend.repository.TipoPreguntaRepository;
import com.sigecap.sigecapexamenbackend.service.TipoPreguntaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPreguntaServiceImpl implements TipoPreguntaService {

    @Autowired
    private TipoPreguntaRepository tipoPreguntaRepository;

    @Override
    public List<TipoPregunta> getAllActives() {
        return tipoPreguntaRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<TipoPregunta> getAll() {
        return (List<TipoPregunta>) tipoPreguntaRepository.findAll();
    }
}
