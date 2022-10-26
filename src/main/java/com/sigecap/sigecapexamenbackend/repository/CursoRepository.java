package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoRepository extends CrudRepository<Curso,String> {

    @Query("select c from Curso c where c.estado=?1")
    List<Curso> getAllActive(String estado);

}
