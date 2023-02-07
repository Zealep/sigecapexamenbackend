package com.sigecap.sigecapexamenbackend.service.impl;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
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
import org.apache.commons.io.input.BOMInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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


        File outputFile = new File("file_utf8.csv");
        CharsetDetector detector = new CharsetDetector();
        byte[] inputBytes = file.getBytes();
        detector.setText(inputBytes);
        CharsetMatch match = detector.detect();
        String encoding = match.getName();


        try (Reader reader = new InputStreamReader(file.getInputStream(), encoding)) {
            try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }
        }

        try (
                final Reader reader = new InputStreamReader(new FileInputStream(outputFile), StandardCharsets.UTF_8);
                CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT
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
                    Pregunta p = preguntaService.saveMasivo(pregunta);
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
                    Respuesta res = respuestaService.saveMasivo(r);
                    String ultimaRes = res.getIdRespuesta();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
