package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.CursoGrupo;
import com.sigecap.sigecapexamenbackend.service.CursoGrupoService;
import com.sigecap.sigecapexamenbackend.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CursoGrupoController {

    @Autowired
    private CursoGrupoService cursoGrupoService;

    @GetMapping(value = "/curso-grupo",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CursoGrupo>> getActives(){
        try{
            return new ResponseEntity<>(cursoGrupoService.getAllActives(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/curso-grupo/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CursoGrupo> getById(@PathVariable String id){
        try{
            return new ResponseEntity<>(cursoGrupoService.getById(id), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/curso-grupo/curso/{curso}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CursoGrupo>> getActivesByCurso(@PathVariable String curso){
        try{
            return new ResponseEntity<>(cursoGrupoService.getByIdCurso(curso), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
