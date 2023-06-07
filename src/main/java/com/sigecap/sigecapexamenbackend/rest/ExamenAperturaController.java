package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.*;
import com.sigecap.sigecapexamenbackend.model.entity.*;
import com.sigecap.sigecapexamenbackend.repository.AsistenciaSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicInscripcionIntentoRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.*;
import com.sigecap.sigecapexamenbackend.util.CargarRecurso;
import com.sigecap.sigecapexamenbackend.util.Print;
import com.sigecap.sigecapexamenbackend.util.RespuestaApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ExamenAperturaController {

    private static final Logger logger = LoggerFactory.getLogger(ExamenAperturaController.class);
    @Value("${ruta.guardar.archivos}")
    private String rutaGuardaArchivo;

    @Autowired
    private ExamenAperturaService examenAperturaService;

    @Autowired
    private ExamenSolicInscripcionIntentoRepository examenSolicInscripcionIntentoRepository;

    @Autowired
    private SolicitudInscripcionDetalleService solicitudInscripcionDetalleService;

    @Autowired
    private AsistenciaSolicitudInscripcionRepository asistenciaSolicitudInscripcionRepository;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private ExamenJdbcRepository examenJdbcRepository;




    @GetMapping(value = "/examen-apertura", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExamenApertura>> getAll() {
        try {
            return new ResponseEntity<>(examenAperturaService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/examen-apertura/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamenApertura> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(examenAperturaService.getById(id), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
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
            logger.error(ex.getMessage());
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
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/examen-apertura/cerrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> updateState(@RequestParam(name = "id", required = true) String id) {
        try {
            examenAperturaService.cerrarExamen(id, new Date());
            return new ResponseEntity<>(new RespuestaApi("OK", null, null), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
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
            logger.error(ex.getMessage());
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
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/examen-apertura/validar-inicio-examen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> validarInicioExamen(@RequestBody ExamenParticipanteDTO examenParticipanteDTO){
            examenAperturaService.validarInicioExamen(examenParticipanteDTO);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }

    @PostMapping(value = "/examen-apertura/validar-encuesta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> validarEncuesta(@RequestBody CursosDisponibleExamenAlumnoDTO cursosDisponibleExamenAlumnoDTO){
        examenAperturaService.validarEncuesta(cursosDisponibleExamenAlumnoDTO);
        return new ResponseEntity<>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }

    @PostMapping(value = "/examen-apertura/exportParticipantesGrupo")
    public ResponseEntity<InputStreamSource> exportarExcelVentas(@RequestBody ConsultaAsistenciaParticipanteDTO parms){
        try {

            ByteArrayInputStream stream = examenAperturaService.exportReporteParticipantes(parms);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-participantes-grupo.xls");
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/examen-apertura/nuevosInscritos")
    public ResponseEntity<RespuestaApi> nuevosInscritos(@RequestBody ConsultaAsistenciaParticipanteDTO parms){
            examenAperturaService.nuevosInscritos(parms);
            return new ResponseEntity<>(new RespuestaApi("OK",null,null),HttpStatus.OK);
    }

    @GetMapping(value = "/examen-apertura/descargarExamen/{idSidEaIntento}/{IdSid}/{IdExamen}")
    public ResponseEntity<byte[]> descargarExamen(HttpServletResponse response,@PathVariable(name = "idSidEaIntento" ) Long idSidEaIntento,
                                                  @PathVariable(name = "IdSid" ) String IdSid,
                                                    @PathVariable(name = "IdExamen" ) String IdExamen) {
        try{

            String tipoReporte = "1";

            ExamenSolicInsIntento objSidEai = new ExamenSolicInsIntento();
            objSidEai = examenSolicInscripcionIntentoRepository.findById(idSidEaIntento).orElse(null);
            List<ExamenSolicInsIntentoRespuesta> listSidEaiR = new ArrayList<>();
            listSidEaiR = objSidEai.getDetalleRespuestas();


            SolicitudInscripcionDetalle objSid = new SolicitudInscripcionDetalle();
            objSid = solicitudInscripcionDetalleService.consultarSolicitudDetallePorId(IdSid);
            List<AsistenciaSolicitudInscripcion> listSidA = new ArrayList<>();
            listSidA = asistenciaSolicitudInscripcionRepository.getByIdSolicitudInscripcionDetalle(IdSid);
            Long idSida = new Long(0);
            if(listSidA.size() > 0 ) {
                idSida = listSidA.get(0).getIdSolicitudAsistencia();
            }

            String nombreParticipante = objSid.getParticipante().getNombres() + " " + objSid.getParticipante().getApellidoPaterno() + " " + objSid.getParticipante().getApellidoMaterno();

            Entidad objEntidadCliente = new Entidad();
            objEntidadCliente = entidadService.consultarEntidadPorIdEntidad(objSid.getSolicitudInscripcion().getIdEntidadCliente());
            String nombreCliente = "";
            if(objEntidadCliente.getTipoPersona().equals("J")) {
                nombreCliente = objEntidadCliente.getRazonSocial();
            }else {
                nombreCliente = objEntidadCliente.getNombres() + " " + objEntidadCliente.getApellidoPaterno() + " " + objEntidadCliente.getApellidoMaterno();
            }



            List<PreguntasPorExamenDTO> listExamenPreguntaRespuesta = new ArrayList<>();
            listExamenPreguntaRespuesta = examenJdbcRepository.getPreguntasPorExamen(IdExamen);

            Collections.sort(listExamenPreguntaRespuesta,
                    new Comparator<PreguntasPorExamenDTO>() {
                        @Override
                        public int compare(PreguntasPorExamenDTO o1, PreguntasPorExamenDTO o2) {
                            return new String(o1.getEnunciadoPregunta().toString())
                                    .compareTo(new String(o2
                                            .getEnunciadoPregunta().toString()));
                        }
                    });

            String examen = "";

            String noPregunta = "";
            String noRespuesta = "";
            int indicePregunta = 1;
            int indiceRespuesta = 0;
            String letras[] = {"a","b","c","d","e","f","g","h","i","j"};

            for(PreguntasPorExamenDTO obj: listExamenPreguntaRespuesta) {

                for(ExamenSolicInsIntentoRespuesta objR: listSidEaiR) {
                    if(objR.getIdPregunta().equals(obj.getIdPregunta())) {
                        if(!noPregunta.equals(obj.getEnunciadoPregunta())) {
                            noPregunta = obj.getEnunciadoPregunta();
                            examen =  examen + "\n<style forecolor=\'black\' size='10' >"  + indicePregunta + ") " + noPregunta + "</style>\n\n";
                            indicePregunta++;
                            indiceRespuesta = 0;
                        }
                        noRespuesta = obj.getEnunciadoRespuesta();
                        examen = examen + "<style forecolor=\'black\' size='8' >" + letras[indiceRespuesta] + ")" + noRespuesta + "</style>";
                        if(obj.getInRespuestaCorrecta().equals("S")) {
                            examen = examen + "<style backcolor=\'yellow\' size='8'> Correcto</style>";
                        }
                        if(objR.getIdRespuestaMarcada().equals(obj.getIdRespuesta())) {
                            examen = examen + "<style backcolor=\'green\' size='8'> Marcado</style>\n";
                        }
                        else {
                            examen = examen + "\n";
                        }

                        indiceRespuesta++;
                    }
                }

            }



            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("parNombreCurso", objSid.getSolicitudInscripcion().getCursoGrupo().getCurso().getNombreCurso());
            parametros.put("parRutaLogo", CargarRecurso.cargarImagen("logo.jpg"));

            parametros.put("parCliente", nombreCliente);
            parametros.put("parRutaFirma", rutaGuardaArchivo + "/FIRMAS/" + idSida + "/" + objSid.getParticipante().getNumeroDocumento() + ".png");
            parametros.put("SUBREPORT_DIR", rutaGuardaArchivo + "/subreporte/");
            parametros.put("parParticipante",  nombreParticipante);

            SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
            parametros.put("parFechaInicioExamen", sdfDDMMYYYY.format( objSidEai.getFechaInicio() ));
            SimpleDateFormat sdfHHMI = new SimpleDateFormat("HH:mm");
            parametros.put("parHoraInicioExamen", sdfHHMI.format( objSidEai.getFechaInicio() ));
            parametros.put("parHoraTerminoExamen", sdfHHMI.format( objSidEai.getFechaTermino() ));

            parametros.put("parNuDocuParticipante", objSid.getParticipante().getNumeroDocumento());
            parametros.put("parNota", objSidEai.getNota().intValue());


            parametros.put("parExamen", examen);

            String nombreReporteJasper = "";
            String nombreReporte = "";


            nombreReporteJasper = "sgeParticipantePorExamen.jasper";
            nombreReporte = objSid.getParticipante().getNombres() + " " + objSid.getParticipante().getApellidoPaterno() + " " + objSid.getParticipante().getApellidoMaterno();


            Print objPrint = new Print();
            byte[] byteArray = objPrint.imprimirBoleta(CargarRecurso.cargarReporte(nombreReporteJasper),response, parametros, null,tipoReporte,nombreReporte,false,"");

            return new ResponseEntity<byte[]>(byteArray,HttpStatus.OK);

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }


}
