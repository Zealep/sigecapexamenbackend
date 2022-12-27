package com.sigecap.sigecapexamenbackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigecap.sigecapexamenbackend.model.entity.Archivo;
import com.sigecap.sigecapexamenbackend.service.ArchivoService;
import com.sigecap.sigecapexamenbackend.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @PostMapping(value = "/archivo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrar(@RequestParam(value="file") MultipartFile file, @RequestParam(value="archivo") String archivo) {
        try {
            ObjectMapper obj = new ObjectMapper();
            Archivo img = obj.readValue(archivo,Archivo.class);
            Archivo i = archivoService.save(img,file);
            return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK", i.getIdArchivo(),""), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
