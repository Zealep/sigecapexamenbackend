package com.sigecap.sigecapexamenbackend.util;

import com.sigecap.sigecapexamenbackend.service.impl.PreguntaServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws IOException {
        Double nota = 0.0;

        BigDecimal puntaje = new BigDecimal("20").divide(new BigDecimal(40));

        nota = nota + puntaje.doubleValue();

    }
}
