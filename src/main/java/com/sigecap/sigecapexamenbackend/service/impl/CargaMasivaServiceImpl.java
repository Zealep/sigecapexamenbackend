package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.Curso;
import com.sigecap.sigecapexamenbackend.model.entity.Pregunta;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.model.entity.TipoPregunta;
import com.sigecap.sigecapexamenbackend.service.CargaMasivaService;
import com.sigecap.sigecapexamenbackend.service.PreguntaService;
import com.sigecap.sigecapexamenbackend.service.RespuestaService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.Util;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("cargaMasivaService")
public class CargaMasivaServiceImpl implements CargaMasivaService {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private RespuestaService respuestaService;

    @Override
    public void cargarPreguntasYRespuestas(MultipartFile file) throws IOException {

        try (
                CSVParser csvParser = new CSVParser(new InputStreamReader(file.getInputStream()), CSVFormat.DEFAULT
                        .withHeader("id_curso", "id_tipo_pregunta", "enunciado", "retroalimentacion","puntuacion")
                        .withIgnoreHeaderCase()
                        .withDelimiter(';')
                        .withTrim());
        ) {

            String ultimoIdPregunta = "";
            for (CSVRecord csvRecord : csvParser) {
                // Accediendo a los valores por el nombre asignado en cada columna
                String idCurso = csvRecord.get("id_curso");
                String tipoPregunta = csvRecord.get("id_tipo_pregunta");
                String enunciado = csvRecord.get("enunciado");
                String retroalimentacion = csvRecord.get("retroalimentacion");
                String puntuacion = csvRecord.get("puntuacion");

                if(csvRecord.getRecordNumber()== 1L){
                    continue;
                }

                if(!idCurso.equals("") && idCurso!=null){
                    Pregunta pregunta = new Pregunta();
                    Curso curso = new Curso();
                    TipoPregunta tipo = new TipoPregunta();
                    curso.setIdCurso(idCurso);
                    tipo.setIdTipoPregunta(tipoPregunta);
                    pregunta.setCurso(curso);
                    pregunta.setTipoPregunta(tipo);
                    String enunciadoTexto = Util.convertHtmlToString(enunciado);
                    pregunta.setNombrePregunta(enunciadoTexto);
                    pregunta.setEnunciado(enunciado);
                    pregunta.setEnunciadoTexto(enunciadoTexto);
                    pregunta.setRetroalimentacion(retroalimentacion);
                    pregunta.setPuntuacion(new BigDecimal(puntuacion));
                    pregunta.setEstado(Constantes.ESTADO_ACTIVO);
                    Pregunta p = preguntaService.save(pregunta);
                    ultimoIdPregunta = p.getIdPregunta();
                }
                else{
                    Respuesta r = new Respuesta();
                    Pregunta p = new Pregunta();
                    p.setIdPregunta(ultimoIdPregunta);
                    r.setPregunta(p);
                    r.setEnunciado(enunciado);
                    r.setEnunciadoTexto( Util.convertHtmlToString(enunciado));
                    r.setRetroAlimentacion(retroalimentacion);
                    r.setEstado(Constantes.ESTADO_ACTIVO);
                    if(puntuacion.equals("S"))
                        r.setRespuestaCorrecta("S");
                    else{
                        r.setRespuestaCorrecta("N");
                    }
                    Respuesta res = respuestaService.save(r);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
