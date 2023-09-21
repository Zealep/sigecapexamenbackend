package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.dto.CursosAsincronosDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Curso;

import java.util.List;

public interface CursoService {

    List<Curso> getAllActives();
    List<Curso> getAll();

    Curso getById(String id);

    List<CursosAsincronosDTO> getCursosAsincronos(String idUsuario);

}
