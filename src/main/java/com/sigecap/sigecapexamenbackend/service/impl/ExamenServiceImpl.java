package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaRespuestasInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.Respuesta;
import com.sigecap.sigecapexamenbackend.repository.ExamenRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import com.sigecap.sigecapexamenbackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("examenService")
public class ExamenServiceImpl implements ExamenService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public List<Examen> getAllActives() {
        return examenRepository.getAllActive(Constantes.ESTADO_ACTIVO);
    }

    @Override
    public List<Examen> getByCurso(String idCurso) {
        return examenRepository.getByCurso(Constantes.ESTADO_ACTIVO,idCurso);
    }

    @Override
    public List<Examen> getAll() {
        return (List<Examen>) examenRepository.findAll();
    }

    @Override
    public List<Examen> listBandeja(BandejaExamenInDTO bandejaExamenInDTO) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Examen> cq = cb.createQuery(Examen.class);

        Root<Examen> examen = cq.from(Examen.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bandejaExamenInDTO.getIdCurso() != null && !bandejaExamenInDTO.getIdCurso().equals("")) {
            predicates.add(cb.equal(examen.get("curso").get("idCurso"), bandejaExamenInDTO.getIdCurso()));
        }

        if (bandejaExamenInDTO.getNombreExamen() != null && !bandejaExamenInDTO.getNombreExamen().equals("")) {
            predicates.add(cb.like(examen.get("nombreExamen"), "%" + bandejaExamenInDTO.getNombreExamen() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public Examen getById(String id) {
        return examenRepository.findById(id).orElse(null);
    }

    @Override
    public Examen save(Examen p) {
        if(p.getIdExamen()==null) {
            String pk = examenRepository.generatePrimaryKey(Constantes.TABLE_EXAMEN, Constantes.ID_TABLE_EXAMEN);
            p.setIdExamen(pk);
        }

        p.getDetalleExamen().forEach(x -> {
            x.setExamen(p);
        });

        return examenRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(String id) {
        examenRepository.deleteLogicById(id,Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public void updateState(String id, String state) {
        this.examenRepository.deleteLogicById(id,state);
    }
}
