package com.sigecap.sigecapexamenbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CargaMasivaService {

    void cargarPreguntasYRespuestas(MultipartFile file) throws IOException;
}
