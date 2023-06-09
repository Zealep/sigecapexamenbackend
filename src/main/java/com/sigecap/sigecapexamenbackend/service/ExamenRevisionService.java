package com.sigecap.sigecapexamenbackend.service;

public interface ExamenRevisionService {

    void revisarExamen(Long idExamenIntento);

    void recalcularNota(Long idExamenIntento);
}
