package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "SELECT count(*) FROM sge_tz_examen_apertura ea " +
            "inner join sge_tz_sid_por_ea sid_ea on ea.id_examen_apertura = sid_ea.id_examen_apertura " +
            "inner join sge_tz_sid_por_ea_intento sid_ea_i on  sid_ea.id_sid_examen = sid_ea_i.id_sid_examen " +
            "where id_examen =:idExamen",nativeQuery = true)
    Integer validateEditarExamen(@Param("idExamen")String idExamen);

}
