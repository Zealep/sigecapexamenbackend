package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Archivo;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoService {

    Archivo save(Archivo a, MultipartFile file);
}
