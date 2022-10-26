package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Long id);

    List<Usuario> findAll();

    List<Usuario> findAllActives();

    Usuario save(Usuario c);

    Usuario update(Usuario c);

    void deleteById(Long id);

    boolean isExist(Usuario c);

}
