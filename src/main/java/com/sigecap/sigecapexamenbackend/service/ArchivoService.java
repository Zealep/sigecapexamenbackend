package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Archivo;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoService {

    Archivo save(Archivo a, MultipartFile file);

    byte[] getImage(Archivo a,String icurso);

    byte[] getFirma(String idDocumento);

    Archivo findByIdDocumento(String idDocumento);


}
