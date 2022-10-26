package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;

import java.util.List;

public interface CursoService {

    List<Curso> getAllActives();
    List<Curso> getAll();

}
