package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.Archivo;
import com.sigecap.sigecapexamenbackend.repository.ArchivoRepository;
import com.sigecap.sigecapexamenbackend.service.ArchivoService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service("archivoService")
public class ArchivoServiceImpl implements ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Value("${url-path-base-attachment}")
    private String URL_PATH_BASE_ATTACHMENT;

    @Value("${url-path-base}")
    private String URL_PATH_BASE;

    @Override
    public Archivo save(Archivo a, MultipartFile file) {
        if(a.getIdArchivo()==null) {
            String pk = archivoRepository.generatePrimaryKey(Constantes.TABLA_ARCHIVO, Constantes.ID_TABLA_ARCHIVO);
            a.setIdArchivo(pk);
        }
        a.setEstado(Constantes.ESTADO_ACTIVO);


        try {
            String url = "/"+"SIDXEA-"+a.getIdDocumento();
            Path path = Paths.get(URL_PATH_BASE_ATTACHMENT+url);
            boolean dirExist = Files.exists(path);
            if (!dirExist) {
                Files.createDirectories(path);
            }

            Path nameImage = Paths.get(a.getNombre()+".png");
            Path targetLocation = path.resolve(nameImage);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            a.setFechaCreacion(new Date());
            a.setPeso(String.valueOf(file.getSize()) + " KB");
            archivoRepository.save(a);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return archivoRepository.save(a);
    }

    @Override
    public byte[] getImage(Archivo a, String idCurso) {
        try {
            String url = "/CURSOS/" + idCurso + "/" + a.getIdArchivo() + "/" + a.getNombre();
            String path = URL_PATH_BASE + url;
            return FileUtils.readFileToByteArray(new File(FilenameUtils.separatorsToSystem(path)));
        }catch (IOException i){
            return null;
        }
    }
}
