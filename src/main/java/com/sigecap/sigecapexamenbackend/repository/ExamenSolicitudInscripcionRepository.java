package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicitudInscripcion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExamenSolicitudInscripcionRepository extends CrudRepository<ExamenSolicitudInscripcion,Long> {

    @Query("update ExamenSolicitudInscripcion c set c.numeroIntentoRealizado=?2 where c.idExamenSolicitudInscripcion=?1")
    @Modifying
    void updateIntentoRealizado(Long idExamenSolicitud,Integer intento);

}
