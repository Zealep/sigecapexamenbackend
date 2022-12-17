package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenSolicInsIntento;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicInscripcionIntentoRepository;
import com.sigecap.sigecapexamenbackend.repository.ExamenSolicitudInscripcionRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenInscripcionIntentoService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("examenInscripcionIntentoService")
public class ExamenInscripcionIntentoServiceImpl implements ExamenInscripcionIntentoService {

    @Autowired
    ExamenSolicInscripcionIntentoRepository examenSolicInscripcionIntentoRepository;

    @Override
    public List<ExamenSolicInsIntento> getAll() {
        return (List<ExamenSolicInsIntento>) examenSolicInscripcionIntentoRepository.findAll();
    }

    @Override
    @Transactional
    public ExamenSolicInsIntento save(ExamenSolicInsIntento ex) {
        ex.setEstado(Constantes.ESTADO_ACTIVO);
        ex.getDetalleRespuestas().forEach(x -> {
            x.setExamenSolicInsIntento(ex);
        });
        return examenSolicInscripcionIntentoRepository.save(ex);
    }
}
