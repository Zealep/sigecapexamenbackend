package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.CursosAsincronosDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import com.sigecap.sigecapexamenbackend.service.CursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CursoController {

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "/curso",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Curso>> getCurso(){
        try{
            return new ResponseEntity<>(cursoService.getAllActives(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/curso/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> getById(@PathVariable String id){
        try{
            return new ResponseEntity<>(cursoService.getById(id), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/curso/asincrono/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CursosAsincronosDTO>> getCursosAsincronosByUsuario(@PathVariable String id){
        try{
            return new ResponseEntity<>(cursoService.getCursosAsincronos(id), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
