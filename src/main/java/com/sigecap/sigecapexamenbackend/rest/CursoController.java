package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import com.sigecap.sigecapexamenbackend.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "/curso",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Curso>> getCurso(){
        try{
            return new ResponseEntity<>(cursoService.getAllActives(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
