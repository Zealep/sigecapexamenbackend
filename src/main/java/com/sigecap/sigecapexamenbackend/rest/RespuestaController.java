package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.exception.BusinessException;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaRespuestasInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.service.RespuestaService;
import com.sigecap.sigecapexamenbackend.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping(value = "/respuesta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Respuesta>> getRespuesta() {
        try {
            return new ResponseEntity<>(respuestaService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/respuesta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta> getRespuestaById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(respuestaService.getById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/respuesta/pregunta/{idPregunta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Respuesta>> getRespuestaByPregunta(@PathVariable String idPregunta) {
        try {
            return new ResponseEntity<>(respuestaService.getByPregunta(idPregunta), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/respuesta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta> getRespuesta(@RequestBody Respuesta respuesta) throws Exception{
            return new ResponseEntity<>(respuestaService.save(respuesta), HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/respuesta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> getRespuesta(@PathVariable String id) {
        try {
            respuestaService.delete(id);
            return new ResponseEntity<>(new RespuestaApi("OK", null, null), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/respuesta/bandeja",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Respuesta>> bucarBandejaVentas(@RequestBody BandejaRespuestasInDTO bandejaRespuestasInDTO) {
        return new ResponseEntity<>(respuestaService.listBandeja(bandejaRespuestasInDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/respuesta/{id}/{state}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> updateState(@PathVariable String id,@PathVariable String state){
        try{
            respuestaService.updateState(id,state);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
