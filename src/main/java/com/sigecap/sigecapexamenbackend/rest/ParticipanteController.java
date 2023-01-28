package com.sigecap.sigecapexamenbackend.rest;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sigecap.sigecapexamenbackend.repository.ParticipanteInscritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	ParticipanteInscritoRepository participanteInscritoRepository;
	
	@Autowired
	ParticipanteDtoService participanteInscritoService;

	@GetMapping(value = "/consultarAlumnosPorGrupo")
	public  ResponseEntity<List<ParticipanteInscritoDto>>consultarAlumnosPorGrupo(@RequestParam(name = "curso")String curso,@RequestParam(name = "grupo")String grupo){
		try{
			return new ResponseEntity<>(participanteInscritoService.getListParticipanteByIdCursoAndIdCursoGrupo(curso,grupo), HttpStatus.OK);
		}catch (Exception ex){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping(value = "/consultarParticipantesInscritos")
	public ResponseEntity<List<ParticipanteInscritoDto>> consultaRegistroParticipante(@RequestBody String jsonString) {
		List<ParticipanteInscritoDto> jqTable = new ArrayList<>();
		try {
			
			
			JSONObject jsonObject = new JSONObject(jsonString);
			
			String parIdCurso = jsonObject.getString("parIdCurso");
			String parIdCursoGrupo = jsonObject.getString("parIdCursoGrupo");


			return new ResponseEntity<>(participanteInscritoService.getListParticipantesInscritosPorCriterios(parIdCurso,parIdCursoGrupo,"","",""), HttpStatus.OK);



		}catch (Exception ex) {
			System.out.print(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping(value = "/consultarAsistenciaParticipantesInscritos")
	public ResponseEntity<List<ParticipanteInscritoDto>> consultaAsistenciaParticipante(@RequestBody String jsonString) {
		List<ParticipanteInscritoDto> jqTable = new ArrayList<>();
		try {


			JSONObject jsonObject = new JSONObject(jsonString);

			String parIdCurso = jsonObject.getString("parIdCurso");
			String parIdCursoGrupo = jsonObject.getString("parIdCursoGrupo");


			return new ResponseEntity<>(participanteInscritoRepository.getAsistenciaParticipantesCursoGrupo(parIdCursoGrupo), HttpStatus.OK);



		}catch (Exception ex) {
			System.out.print(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

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
