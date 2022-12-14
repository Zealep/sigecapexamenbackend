package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenPorAlumnoDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaRespuestasInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @Autowired
    private ExamenJdbcRepository examenJdbcRepository;

    @GetMapping(value = "/examen",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Examen>> getAll(){
        try{
            return new ResponseEntity<>(examenService.getAll(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/examen/curso/{curso}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Examen>> getAllByCurso(@PathVariable String curso){
        try{
            return new ResponseEntity<>(examenService.getByCurso(curso), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/examen/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Examen> getById(@PathVariable String id){
        try{
            return new ResponseEntity<>(examenService.getById(id), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/examen",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Examen> save(@RequestBody Examen examen){
        try{
            return new ResponseEntity<>(examenService.save(examen), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/examen/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> deleteById(@PathVariable String id){
        try{
            examenService.delete(id);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/examen/bandeja",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Examen>> bucarBandeja(@RequestBody BandejaExamenInDTO bandejaExamenInDTO) {
        return new ResponseEntity<>(examenService.listBandeja(bandejaExamenInDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/examen/{id}/{state}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> updateState(@PathVariable String id,@PathVariable String state){
        try{
            examenService.updateState(id,state);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/examen/bandeja-alumno/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BandejaExamenPorAlumnoDTO>> getBandejaExamenPorAlumno(@PathVariable(name = "usuario") String usuario){
        try{
            List<BandejaExamenPorAlumnoDTO> examenes = examenJdbcRepository.getBandejaPorAlumno(usuario);
            return new ResponseEntity<>(examenes, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
