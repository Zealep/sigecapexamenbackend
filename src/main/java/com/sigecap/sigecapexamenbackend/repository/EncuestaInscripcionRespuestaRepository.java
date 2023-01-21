package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.EncuestaInscripcionRespuesta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EncuestaInscripcionRespuestaRepository extends CrudRepository<EncuestaInscripcionRespuesta,Long> {

    @Query(nativeQuery = true ,value= "update id_solicitud_inscripcion_detalle c set c.in_realizo_encuesta =?2 where c.id_solicitud_inscripcion_detalle=?1")
    @Modifying
    void updateEncuesta(String id,String estado);
}
