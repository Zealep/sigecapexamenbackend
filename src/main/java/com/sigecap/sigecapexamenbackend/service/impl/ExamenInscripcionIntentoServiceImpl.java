package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicInscripcionIntentoRepository;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenInscripcionIntentoService;
import com.sigecap.sigecapexamenbackend.service.ExamenRevisionService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("examenInscripcionIntentoService")
public class ExamenInscripcionIntentoServiceImpl implements ExamenInscripcionIntentoService {

    @Autowired
    ExamenSolicInscripcionIntentoRepository examenSolicInscripcionIntentoRepository;

    @Autowired
    ExamenRevisionService examenRevisionService;

    @Override
    public List<ExamenSolicInsIntento> getAll() {
        return (List<ExamenSolicInsIntento>) examenSolicInscripcionIntentoRepository.findAll();
    }

    @Override
    public List<ExamenSolicInsIntento> getByExamen(Long idExamenSolicitud) {
        return examenSolicInscripcionIntentoRepository.findByExamen(idExamenSolicitud);
    }

    @Override
    @Transactional
    public ExamenSolicInsIntento save(ExamenSolicInsIntento ex) {

        ex.setEstado(Constantes.ESTADO_ACTIVO);
        ex.getDetalleRespuestas().forEach(x -> {
            if(x.getIdRespuestaMarcada().equals("")|| x.getIdRespuestaMarcada() == null){
                x.setIdRespuestaMarcada(Constantes.RESPUESTA_VACIA);
            }
            x.setExamenSolicInsIntento(ex);
        });

        ExamenSolicInsIntento examenSolicInsIntento = examenSolicInscripcionIntentoRepository.save(ex);
        examenRevisionService.revisarExamen(examenSolicInsIntento.getIdExamSoliInscIntento());
        return examenSolicInsIntento;
    }

    @Override
    public ExamenSolicInsIntento getById(Long id) {
        return examenSolicInscripcionIntentoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateNotaAndIntento(Long id, Double nota, Integer intento) {
        examenSolicInscripcionIntentoRepository.updateNotaAndIntento(id,nota,intento);
    }

    @Override
    @Transactional
    public void updateEstado(Long id, String estado) {
        examenSolicInscripcionIntentoRepository.updateEstado(id,estado);

    }

    @Override
    public Integer ultimoIntento(Long idExamenSolicitud) {
        return examenSolicInscripcionIntentoRepository.ultimoIntento(idExamenSolicitud);
    }
}
