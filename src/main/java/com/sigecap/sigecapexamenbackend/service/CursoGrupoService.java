package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.CursoGrupo;

import java.util.List;

public interface CursoGrupoService {

    List<CursoGrupo> getAllActives();
    List<CursoGrupo> getAll();
    List<CursoGrupo> getByIdCurso(String id);
    CursoGrupo getById(String id);

}
