package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Archivo;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArchivoRepository extends CrudRepository<Archivo,String> {
    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);

    @Query("select c from Archivo c where c.estado=?1 and c.idDocumento=?2")
    Archivo getByIdDocumento(String estado, String idDocumento);

    @Query("select c from Archivo c where c.estado=?1 and c.idDocumento=?2 and c.nombre =?3 ")
    Archivo getByIdDocumentoAndNombre(String estado, String idDocumento,String nombre);


}
