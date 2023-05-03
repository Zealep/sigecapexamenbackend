package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;
import com.sigecap.sigecapexamenbackend.service.ExamenInscripcionIntentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamenIntentoController {

    private static final Logger logger = LoggerFactory.getLogger(ExamenIntentoController.class);


    @Autowired
    private ExamenInscripcionIntentoService examenInscripcionIntentoService;

    @PostMapping(value = "/examen-intento",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamenSolicInsIntento> save(@RequestBody ExamenSolicInsIntento examenSolicInsIntento){
        try{
            return new ResponseEntity<>(examenInscripcionIntentoService.save(examenSolicInsIntento), HttpStatus.CREATED);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
