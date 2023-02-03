package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicitudInscripcion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamenSolicInscripcionIntentoRepository extends CrudRepository<ExamenSolicInsIntento,Long> {

    @Query("update ExamenSolicInsIntento c set c.nota=?2, c.numeroIntento=?3 where c.idExamSoliInscIntento=?1")
    @Modifying
    void updateNotaAndIntento(Long id,Double nota,Integer intento);

    @Query("update ExamenSolicInsIntento c set c.estado=?2 where c.idExamSoliInscIntento=?1")
    @Modifying
    void updateEstado(Long id,String estado);

    @Query("select c from ExamenSolicInsIntento c where c.examenSolicitudInscripcion.idExamenSolicitudInscripcion = ?1")
    List<ExamenSolicInsIntento> findByExamen(Long idExamen);

    @Query("select max(c.numeroIntento) from ExamenSolicInsIntento c where c.examenSolicitudInscripcion.idExamenSolicitudInscripcion = ?1")
    Integer ultimoIntento(Long idExamen);


}
