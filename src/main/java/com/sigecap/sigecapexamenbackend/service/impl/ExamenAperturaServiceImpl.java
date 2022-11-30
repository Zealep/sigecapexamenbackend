package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaAperturaInDTO;
import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenInDTO;
import com.sigecap.sigecapexamenbackend.model.entity.Examen;
import com.sigecap.sigecapexamenbackend.model.entity.ExamenApertura;
import com.sigecap.sigecapexamenbackend.repository.ExamenAperturaRepository;
import com.sigecap.sigecapexamenbackend.repository.ExamenRepository;
import com.sigecap.sigecapexamenbackend.service.ExamenAperturaService;
import com.sigecap.sigecapexamenbackend.service.ExamenService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
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
import java.util.Date;
import java.util.List;

@Service("examenAperturaService")
public class ExamenAperturaServiceImpl implements ExamenAperturaService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ExamenAperturaRepository examenAperturaRepository;

    @Override
    public List<ExamenApertura> getAllActives() {
        return examenAperturaRepository.getAllActive(Constantes.ESTADO_INACTIVO);
    }

    @Override
    public List<ExamenApertura> getAll() {
        return (List<ExamenApertura>) examenAperturaRepository.findAll();
    }

    @Override
    public List<ExamenApertura> listBandeja(BandejaAperturaInDTO bandejaAperturaInDTO) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExamenApertura> cq = cb.createQuery(ExamenApertura.class);

        Root<ExamenApertura> examenAperturaRoot  = cq.from(ExamenApertura.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bandejaAperturaInDTO.getIdExamen() != null && !bandejaAperturaInDTO.getIdExamen().equals("")) {
            predicates.add(cb.equal(examenAperturaRoot.get("examen").get("idExamen"), bandejaAperturaInDTO.getIdExamen()));
        }

        if (bandejaAperturaInDTO.getIdCursoGrupo() != null && !bandejaAperturaInDTO.getIdCursoGrupo().equals("")) {
            predicates.add(cb.equal(examenAperturaRoot.get("cursoGrupo").get("idCursoGrupo"), bandejaAperturaInDTO.getIdCursoGrupo()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public ExamenApertura getById(String id) {
        return examenAperturaRepository.findById(id).orElse(null);
    }

    @Override
    public ExamenApertura save(ExamenApertura p) {
        if(p.getIdExamenApertura()==null) {
            String pk = examenAperturaRepository.generatePrimaryKey(Constantes.TABLE_EXAMEN_APERTURA, Constantes.ID_TABLE_EXAMEN_APERTURA);
            p.setIdExamenApertura(pk);
        }

        return examenAperturaRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(String id) {
        examenAperturaRepository.deleteLogicById(id,Constantes.ESTADO_INACTIVO);
    }

    @Override
    @Transactional
    public void updateState(String id, String state) {
        this.examenAperturaRepository.deleteLogicById(id,state);
    }

    @Override
    @Transactional
    public void cerrarExamen(String id, Date fechaCierre) {
        this.examenAperturaRepository.cerrar(id,Constantes.CERRADO_APERTURA_EXAMEN,fechaCierre);
    }
}
