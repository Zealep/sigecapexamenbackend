package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.AsistenciaSolicitudInscripcion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AsistenciaSolicitudInscripcionRepository  extends CrudRepository<AsistenciaSolicitudInscripcion,Long> {

    @Query("update AsistenciaSolicitudInscripcion c set c.realizoFirma=?2 where c.idSolicitudAsistencia=?1")
    @Modifying
    void updateFirmaAsistencia(Long idSolicitudAsistencia,String indicador);
}
