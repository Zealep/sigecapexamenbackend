package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @GetMapping(value = "/pregunta",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregunta>> getPregunta(){
        try{
            return new ResponseEntity<>(preguntaService.getAll(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pregunta/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregunta> getPreguntaById(@PathVariable String id){
        try{
            return new ResponseEntity<>(preguntaService.getById(id), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pregunta/curso/{idCurso}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregunta>> getPreguntaByCurso(@PathVariable String idCurso){
        try{
            return new ResponseEntity<>(preguntaService.getByCurso(idCurso), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pregunta/tipoPregunta/{idTipoPregunta}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregunta>> getPreguntaByTipoPregunta(@PathVariable String idTipoPregunta){
        try{
            return new ResponseEntity<>(preguntaService.getByTipoPregunta(idTipoPregunta), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pregunta/curso/{idCurso}/tipoPregunta/{idTipoPregunta}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregunta>> getPreguntaByCursoAndTipoPregunta(@PathVariable String idCurso,@PathVariable String idTipoPregunta){
        try{
            return new ResponseEntity<>(preguntaService.getByCursoAndTipoPregunta(idCurso,idTipoPregunta), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/pregunta",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregunta> getPregunta(@RequestBody Pregunta pregunta){
        try{
            return new ResponseEntity<>(preguntaService.save(pregunta), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/pregunta/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> delete(@PathVariable String id){
        try{
            preguntaService.delete(id);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/pregunta/{id}/{state}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> updateState(@PathVariable String id,@PathVariable String state){
        try{
            preguntaService.updateState(id,state);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
