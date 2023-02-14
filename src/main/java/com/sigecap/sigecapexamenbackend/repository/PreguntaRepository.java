package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PreguntaRepository extends CrudRepository<Pregunta,String> {

    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);

    @Query("select c from Pregunta c where c.estado=?1")
    List<Pregunta> getAllActive(String estado);

    @Query("select c from Pregunta c where c.curso.idCurso=?1 and c.estado=?2")
    List<Pregunta> getByCurso(String idCurso,String estado);

    @Query("select c from Pregunta c where c.tipoPregunta.idTipoPregunta=?1 and c.estado=?2")
    List<Pregunta> getByTipoPregunta(String idTipoPregunta,String estado);

    @Query("select c from Pregunta c where c.curso.idCurso=?1 and c.tipoPregunta.idTipoPregunta=?2 and c.estado=?3")
    List<Pregunta> getByCursoAndTipoPregunta(String idCurso,String idTipoPregunta,String estado);

    @Query("select c from Pregunta c where c.curso.idCurso=?1 and c.tipoPregunta.idTipoPregunta=?2 and c.estado=?3")
    List<Pregunta> getByCursoAndTipoPreguntaAndEstado(String idCurso,String idTipoPregunta,String estado);

    @Query("update Pregunta c set c.estado=?2 where c.idPregunta=?1")
    @Modifying
    void deleteLogicById(String idPregunta,String estado);

}
