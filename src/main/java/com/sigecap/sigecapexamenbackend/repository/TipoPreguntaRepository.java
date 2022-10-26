package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TipoPreguntaRepository extends CrudRepository<TipoPregunta,String> {

    @Query("select c from TipoPregunta c where c.estado=?1")
    List<TipoPregunta> getAllActive(String estado);
}
