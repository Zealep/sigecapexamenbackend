package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.CursoGrupo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoGrupoRepository extends CrudRepository<CursoGrupo,String> {

    @Query("select c from CursoGrupo c where c.estado=?1")
    List<CursoGrupo> getAllActive(String estado);

    @Query("select c from CursoGrupo c where c.estado=?1 and c.curso.idCurso=?2 order by c.fechaInicio desc")
    List<CursoGrupo> getCursoGrupoByIdCurso(String estado,String id);

}
