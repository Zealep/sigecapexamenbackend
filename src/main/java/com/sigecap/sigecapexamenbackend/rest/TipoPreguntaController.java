package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.ItemMenuDTO;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import com.sigecap.sigecapexamenbackend.service.TipoPreguntaService;
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
@RequestMapping
public class TipoPreguntaController {

    private static final Logger logger = LoggerFactory.getLogger(TipoPreguntaController.class);


    @Autowired
    private TipoPreguntaService tipoPreguntaService;

    @GetMapping(value = "/tipoPregunta",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoPregunta>> getTipoPregunta(){
        try{
            return new ResponseEntity<>(tipoPreguntaService.getAllActives(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
