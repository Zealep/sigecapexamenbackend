package com.sigecap.sigecapexamenbackend.repository;

import com.sigecap.sigecapexamenbackend.model.entity.Entidad;
import com.sigecap.sigecapexamenbackend.model.entity.TipoDocumentosIdentidad;
import com.sigecap.sigecapexamenbackend.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad,Long> {


	@Query("select e from Entidad e where e.estado = 'ACT' and e.tipoEntidad.nombreTipoEntidad = 'CLIENTE' order by e.apellidoPaterno,e.apellidoMaterno,e.nombres,e.razonSocial asc")
	List<Entidad> getAllEntidades();
	
	@Query("select e from Entidad e where e.estado = 'ACT' and e.tipoEntidad.nombreTipoEntidad = 'CLIENTE' and e.idEntidad = ?1")
	List<Entidad> getEntidadByIdEntidadCliente(String idEntidadCliente);
	
	
	@Query("select e from Entidad e where e.estado = 'ACT' and e.tipoEntidad.idTipoEntidad = ?1 order by e.razonSocial,e.nombres,e.apellidoPaterno,e.apellidoMaterno")
	List<Entidad> getAllEntidadesByIdTipoEntidad(String idTipoEntidad);
	
	@Query("select e from Entidad e where e.estado = 'ACT' and e.tipoPersona = ?1 order by e.razonSocial,e.nombres,e.apellidoPaterno,e.apellidoMaterno")
	List<Entidad> getAllEntidadesByIdTipoPersona(String tipoPersona);
	
	@Query("select e from Entidad e where e.estado = 'ACT' and e.tipoEntidad.idTipoEntidad = ?1 and e.tipoPersona = ?2 order by razonSocial asc")
	List<Entidad> getAllEntidadesByIdTipoEntidadAndTipoPersona(String idTipoEntidad,String tipoPersona);
	
	@Query("select e from Entidad e left join EmpresaResponsable er on e.idEntidad = er.idEntidadResponsable where e.estado = 'ACT' and e.tipoEntidad.idTipoEntidad = ?1 and e.tipoPersona = ?2 and (er.idEntidadResponsable is null or er.estado = 'INA') order by e.apellidoPaterno,e.apellidoMaterno,e.nombres asc")
	List<Entidad> getAllEntidadesByIdTipoEntidadAndTipoPersonaNoResponsable(String idTipoEntidad,String tipoPersona);
	
	@Query("select e from Entidad e inner join EmpresaResponsable er on e.idEntidad = er.idEntidadResponsable where er.idEntidadEmpresa = ?1 and e.estado = 'ACT' and er.estado = 'ACT' and e.tipoPersona = 'N' order by e.apellidoPaterno,e.apellidoMaterno,e.nombres asc")
	List<Entidad> getAllEntidadesResponsableByIdEntidadCliente(String idEntidadCliente);
	
    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKeyUsuario(String tabla,String campo);
    
    Entidad findByNumeroDocumento(String numeroDocumento);
    
    Entidad findByTipoDocumentosIdentidadAndNumeroDocumentoAndEstado(TipoDocumentosIdentidad tipoDocumentosIdentidad, String numeroDocumento, String estado);
    
    Entidad findByIdEntidad(String idEntidad);
    
    Entidad findByUsuario(Usuario objUsuario);
    
    Entidad findByCorreoElectronico(String correoElectronico);
}
