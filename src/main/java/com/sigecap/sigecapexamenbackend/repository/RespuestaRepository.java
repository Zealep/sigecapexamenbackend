package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RespuestaRepository extends CrudRepository<Respuesta,String> {

    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);

    @Query("select c from Respuesta c where c.estado=?1")
    List<Respuesta> getAllActive(String estado);

    @Query("select c from Respuesta c where c.pregunta.idPregunta=?1 and c.estado=?2")
    List<Respuesta> getByPregunta(String id,String estado);


    @Query("update Respuesta c set c.estado=?2 where c.idRespuesta=?1")
    @Modifying
    void deleteLogicById(String id,String estado);

}
