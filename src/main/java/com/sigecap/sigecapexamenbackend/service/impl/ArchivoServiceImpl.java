package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.Archivo;
import com.sigecap.sigecapexamenbackend.repository.ArchivoRepository;
import com.sigecap.sigecapexamenbackend.repository.AsistenciaSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.repository.jdbc.ExamenParticipanteJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.ArchivoService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private static final Logger logger = LoggerFactory.getLogger(ArchivoServiceImpl.class);


    @Autowired
    private ArchivoRepository archivoRepository;

    @Value("${url-path-base-attachment}")
    private String URL_PATH_BASE_ATTACHMENT;

    @Value("${url-path-base}")
    private String URL_PATH_BASE;

    @Autowired
    private AsistenciaSolicitudInscripcionRepository asistenciaSolicitudInscripcionRepository;

    @Override
    @Transactional
    public Archivo save(Archivo a, MultipartFile file) {

        Archivo ar = archivoRepository.getByIdDocumentoAndNombre(Constantes.ESTADO_ACTIVO,a.getIdDocumento(),a.getNombre());

        if(ar==null) {
            String pk = archivoRepository.generatePrimaryKey(Constantes.TABLA_ARCHIVO, Constantes.ID_TABLA_ARCHIVO);
            a.setIdArchivo(pk);
        }
        else{
            a.setIdArchivo(ar.getIdArchivo());
        }

        a.setEstado(Constantes.ESTADO_ACTIVO);

        try {
            String url = "/FIRMAS/"+a.getIdDocumento();
            Path path = Paths.get(URL_PATH_BASE+url);
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
            asistenciaSolicitudInscripcionRepository.updateFirmaAsistencia(Long.parseLong(a.getIdDocumento()),Constantes.SI_FIRMO_EXAMEN,new Date());

        } catch (IOException e) {
            logger.error(e.getMessage());
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
            logger.error(i.getMessage());
            return null;
        }
    }

    @Override
    public byte[] getFirma(String idDocumento) {
        try {

            Archivo a = this.findByIdDocumento(idDocumento);
            if(a!=null){
                String url = "/FIRMAS/" + a.getIdDocumento() + "/" + a.getNombre()+".png";
                String path = URL_PATH_BASE + url;
                return FileUtils.readFileToByteArray(new File(FilenameUtils.separatorsToSystem(path)));
            }else{
                return null;
            }

        }catch (IOException i){
            logger.error(i.getMessage());
            return null;
        }
    }

    @Override
    public Archivo findByIdDocumento(String idDocumento) {
        return archivoRepository.getByIdDocumento(Constantes.ESTADO_ACTIVO,idDocumento);
    }
}
