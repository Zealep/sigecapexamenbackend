package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.AsistenciaSolicitudInscripcion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AsistenciaSolicitudInscripcionRepository  extends CrudRepository<AsistenciaSolicitudInscripcion,Long> {

    @Query("update AsistenciaSolicitudInscripcion c set c.realizoFirma=?2 , c.fechaFirma=?3 where c.idSolicitudAsistencia=?1")
    @Modifying
    void updateFirmaAsistencia(Long idSolicitudAsistencia, String indicador, Date fechaFirma);

    @Query("select c from AsistenciaSolicitudInscripcion c  where c.idSolicitudInscripcionDetalle=?1")
    List<AsistenciaSolicitudInscripcion> getByIdSolicitudInscripcionDetalle(String idSolicitudDetalleInscripcion);
}
