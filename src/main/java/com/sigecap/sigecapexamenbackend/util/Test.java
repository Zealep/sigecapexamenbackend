package com.sigecap.sigecapexamenbackend.util;

import com.sigecap.sigecapexamenbackend.service.impl.PreguntaServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws IOException {
        final String SAMPLE_CSV_FILE_PATH = "src/main/resources/static/masivo-sigecap.csv";
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("id_curso", "id_tipo_pregunta", "enunciado", "retroalimentacion","puntuacion")
                        .withIgnoreHeaderCase()
                        .withDelimiter(';')
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accediendo a los valores por el nombre asignado en cada columna
                String idCurso = csvRecord.get("id_curso");
                String tipoPregunta = csvRecord.get("id_tipo_pregunta");
                String enunciado = csvRecord.get("enunciado");
                String retroalimentacion = csvRecord.get("retroalimentacion");
                String puntuacion = csvRecord.get("puntuacion");
                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("IdCurso : " + idCurso);
                System.out.println("TipoPregunta : " + tipoPregunta);
                System.out.println("Enunciado : " + enunciado);
                System.out.println("Retroalimentacion : " + retroalimentacion);
                System.out.println("Puntuacion : " + puntuacion);
                System.out.println("---------------\n\n");
            }
        }


    }
}
