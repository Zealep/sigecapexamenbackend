package com.sigecap.sigecapexamenbackend.rest;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteInscritoDto;
import com.sigecap.sigecapexamenbackend.service.ParticipanteDtoService;
import com.sigecap.sigecapexamenbackend.service.ParticipanteService;



@RestController
@RequestMapping("/participante")
public class ParticipanteController {
		
	@Autowired
	ParticipanteService participanteService;
	
	@Autowired
	ParticipanteDtoService participanteInscritoService;
	

	@PostMapping(value = "/consultarParticipantesInscritos")
	@ResponseBody
	public List<ParticipanteInscritoDto> consultaRegistroParticipante(@RequestBody String jsonString,HttpServletRequest srv) {
		List<ParticipanteInscritoDto> jqTable = new ArrayList<>();
		try {
			
			
			JSONObject jsonObject = new JSONObject(jsonString);
			
			String parIdCurso = jsonObject.getString("parIdCurso");
			String parIdCursoGrupo = jsonObject.getString("parIdCursoGrupo");
			String parFecFinGrupoRi = jsonObject.getString("parFecFinGrupoRi");
			String parFecFinGrupoRf = jsonObject.getString("parFecFinGrupoRf");
			String parIdEntidadCliente = jsonObject.getString("parIdEntidadCliente");
			
			// Defino objetos
			List<ParticipanteInscritoDto> listParticipantesInscritos = new ArrayList<>();
			
			
			jqTable = participanteInscritoService.getListParticipantesInscritosPorCriterios(parIdCurso,parIdCursoGrupo,parFecFinGrupoRi,parFecFinGrupoRf,parIdEntidadCliente);


		  	    	
	    	
			return listParticipantesInscritos;
		}catch (Exception ex) {
			System.out.print(ex.getMessage());
			return jqTable;
		}
	}
	
	/*
	@PostMapping(value = "/descargarReporteParticipantes")
    public void descargarReporteParticipantes(
    		HttpServletRequest request,
			HttpServletResponse response) {
		try{
			
			String tipoReporte = "2";
			String parIdCurso = request.getParameter("parIdCursoReporte");
			String parIdCursoGrupo = request.getParameter("parIdCursoGrupoReporte");
			String parFecFinGrupoRi = request.getParameter("parFecFinGrupoRiReporte");
			String parFecFinGrupoRf = request.getParameter("parFecFinGrupoRfReporte");
			String parIdEntidadCliente = request.getParameter("parIdEntidadClienteReporte");
			List<ParticipanteInscritoDto> listParticipantesInscritos = new ArrayList<>();
			listParticipantesInscritos = participanteInscritoService.getListParticipantesInscritosPorCriterios(parIdCurso,parIdCursoGrupo,parFecFinGrupoRi,parFecFinGrupoRf,parIdEntidadCliente);
			
			List<ParticipanteInscritoDto> listParticipantesReporte = new ArrayList<>();
			int i=0;
			for(ParticipanteInscritoDto obj: listParticipantesInscritos) {
				i = i + 1;
				obj.setOrdenMostrar(i);
				listParticipantesReporte.add(obj);
			}
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("parIdCurso", parIdCurso);
			parametros.put("parIdCursoGrupo", parIdCursoGrupo);
			parametros.put("parRutaLogo", CargarRecurso.cargarImagen("logo.jpg"));
			String nombreReporteJasper = "sigecapReporteParticipantesInscritos.jasper";
		
			Print.imprimirBoleta(
					CargarRecurso.cargarReporte(nombreReporteJasper),
					response, parametros, listParticipantesReporte,tipoReporte,"F02-R-CAP REPORTE PARTICIPANTES",true,"");
	        

		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
    }
*/
}
