package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamenRepository extends CrudRepository<Examen,String> {


    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);

    @Query("select c from Examen c where c.estado=?1")
    List<Examen> getAllActive(String estado);

    @Query("select c from Examen c where c.estado=?1 and c.curso.idCurso=?2")
    List<Examen> getByCurso(String estado,String curso);

    @Query("update Examen c set c.estado=?2 where c.idExamen=?1")
    @Modifying
    void deleteLogicById(String id,String estado);

}
