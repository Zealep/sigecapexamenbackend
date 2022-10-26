package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.repository.CursoRepository;
import com.sigecap.sigecapexamenbackend.service.CursoService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> getAllActives() {
        return cursoRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Curso> getAll() {
        return (List<Curso>) cursoRepository.findAll();
    }
}
