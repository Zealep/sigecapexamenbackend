package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ExamenAperturaRepository extends CrudRepository<ExamenApertura,String> {


    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);

    @Query("select c from ExamenApertura c where c.estado <> ?1")
    List<ExamenApertura> getAllActive(String estado);

    @Query("select c from ExamenApertura c where c.estado<> ?1 and c.examen.idExamen=?2")
    List<ExamenApertura> getByExamen(String estado,String idExamen);

    @Query("update ExamenApertura c set c.estado=?2 where c.idExamenApertura=?1")
    @Modifying
    void deleteLogicById(String id,String estado);

    @Query("update ExamenApertura c set c.estado=?2 , c.fechaHoraCierre=?3 where c.idExamenApertura=?1")
    @Modifying
    void cerrar(String id, String estado, Date fechaCierre);

    @Query(nativeQuery = true ,value= "update sgc_tz_solicitud_inscripcion_detalle c set c.in_asistio =?2 where c.id_solicitud_inscripcion_detalle=?1")
    @Modifying
    void updateAsistencia(String id,String estado);
}
