package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.CursoGrupo;
import com.sigecap.sigecapexamenbackend.repository.CursoGrupoRepository;
import com.sigecap.sigecapexamenbackend.repository.CursoRepository;
import com.sigecap.sigecapexamenbackend.service.CursoGrupoService;
import com.sigecap.sigecapexamenbackend.service.CursoService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cursoGrupoService")
public class CursoGrupoServiceImpl implements CursoGrupoService {

    @Autowired
    private CursoGrupoRepository cursoGrupoRepository;

    @Override
    public List<CursoGrupo> getAllActives() {
        return cursoGrupoRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<CursoGrupo> getAll() {
        return (List<CursoGrupo>) cursoGrupoRepository.findAll();
    }

    @Override
    public List<CursoGrupo> getByIdCurso(String id) {
        return cursoGrupoRepository.getCursoGrupoByIdCurso(Constantes.ESTADO_ACTIVO,id);
    }

    @Override
    public CursoGrupo getById(String id) {
        return cursoGrupoRepository.findById(id).orElse(null);
    }
}
