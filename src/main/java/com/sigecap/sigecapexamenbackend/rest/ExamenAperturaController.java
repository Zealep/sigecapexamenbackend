package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaAperturaInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenPorAlumnoDTO;
import com.sigecap.sigecapexamenbackend.model.dto.ExamenParticipanteDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicitudInscripcion;
import com.sigecap.sigecapexamenbackend.service.ExamenAperturaService;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.util.RespuestaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ExamenAperturaController {

    @Autowired
    private ExamenAperturaService examenAperturaService;

    @GetMapping(value = "/examen-apertura", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExamenApertura>> getAll() {
        try {
            return new ResponseEntity<>(examenAperturaService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/examen-apertura/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamenApertura> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(examenAperturaService.getById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/examen-apertura", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamenApertura> save(@RequestBody ExamenApertura examenApertura) {
        try {
            return new ResponseEntity<>(examenAperturaService.save(examenApertura), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/examen-apertura/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> deleteById(@PathVariable String id) {
        try {
            examenAperturaService.delete(id);
            return new ResponseEntity<>(new RespuestaApi("OK", null, null), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/examen-apertura/bandeja", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExamenApertura>> bucarBandeja(@RequestBody BandejaAperturaInDTO bandejaAperturaInDTO) {
        return new ResponseEntity<>(examenAperturaService.listBandeja(bandejaAperturaInDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/examen-apertura/{id}/{state}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> updateState(@PathVariable String id, @PathVariable String state) {
        try {
            examenAperturaService.updateState(id, state);
            return new ResponseEntity<>(new RespuestaApi("OK", null, null), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/examen-apertura/cerrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> updateState(@RequestParam(name = "id", required = true) String id) {
        try {
            examenAperturaService.cerrarExamen(id, new Date());
            return new ResponseEntity<>(new RespuestaApi("OK", null, null), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/examen-apertura/notificar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> notificarParticipantes(@RequestParam(name = "idApertura") String idApertura,
                                                               @RequestParam(name = "curso") String curso, @RequestParam(name = "grupo") String grupo) {
        try {
            examenAperturaService.notificarParticipantes(idApertura, curso, grupo);
            return new ResponseEntity<>(new RespuestaApi("OK", null, null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/examen-apertura/asistencia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrarAsistencia(@RequestParam(name = "dni") String dni,
                                                            @RequestParam(name = "curso") String curso, @RequestParam(name = "grupo") String grupo) {
        examenAperturaService.registrarAsistencia(dni, curso, grupo);
        return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }

    @GetMapping(value = "/examen-apertura/inscripcion/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamenSolicitudInscripcion> getExamenInscripcionById(@PathVariable(name = "id") Long id){
        try {
            return new ResponseEntity<ExamenSolicitudInscripcion>(examenAperturaService.getExamenInscripcionById(id),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/examen-apertura/validar-inicio-examen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> validarInicioExamen(@RequestBody ExamenParticipanteDTO examenParticipanteDTO){
            examenAperturaService.validarInicioExamen(examenParticipanteDTO);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }

    @PostMapping(value = "/examen-apertura/validar-encuesta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> validarEncuesta(@RequestBody ExamenParticipanteDTO bandejaExamenPorAlumnoDTO){
        examenAperturaService.validarEncuesta(bandejaExamenPorAlumnoDTO);
        return new ResponseEntity<>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }


}
