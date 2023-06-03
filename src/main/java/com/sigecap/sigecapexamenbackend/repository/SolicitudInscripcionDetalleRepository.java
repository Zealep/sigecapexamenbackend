package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.model.entity.SolicitudInscripcion;
import com.sigecap.sigecapexamenbackend.model.entity.SolicitudInscripcionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface SolicitudInscripcionDetalleRepository extends JpaRepository<SolicitudInscripcionDetalle,Long> {

	
	
    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);
    
    SolicitudInscripcionDetalle findByIdSolicitudInscripcionDetalle(String idSolicitudInscripcion);
    
    SolicitudInscripcionDetalle findByCodigoCertificado(String codigoCertificado);
    
    SolicitudInscripcionDetalle findBySolicitudInscripcionAndParticipante(SolicitudInscripcion solicitudInscripcio, Participante objParticipante);
    
    List<SolicitudInscripcionDetalle> findByParticipante(Participante objParticipante);
    
    List<SolicitudInscripcionDetalle> findBySolicitudInscripcion(SolicitudInscripcion solicitudInscripcion);
    

    
}
