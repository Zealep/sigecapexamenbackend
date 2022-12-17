package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaAperturaInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteInscritoDto;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicitudInscripcion;

import java.util.Date;
import java.util.List;

public interface ExamenAperturaService {

    List<ExamenApertura> getAllActives();
    List<ExamenApertura> getAll();

    List<ExamenApertura> listBandeja(BandejaAperturaInDTO bandejaAperturaInDTO);

    ExamenApertura getById(String id);
    ExamenApertura save(ExamenApertura p);
    void delete(String id);
    void updateState(String id,String  state);

    void cerrarExamen(String id, Date fechaCierre);

    void notificarParticipantes(String idApertura,String idCurso,String idGrupo);

    void registrarAsistencia(String dni,String idCurso,String idGrupo);

    ExamenSolicitudInscripcion getExamenInscripcionById(Long id);


}
