package com.sigecap.sigecapexamenbackend.service.impl;

import com.sigecap.sigecapexamenbackend.model.dto.ItemMenuDTO;
import com.sigecap.sigecapexamenbackend.repository.jdbc.MenuJdbcRepository;
import com.sigecap.sigecapexamenbackend.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuJdbcRepository menuJdbcRepository;

    public MenuServiceImpl(MenuJdbcRepository menuJdbcRepository) {
        this.menuJdbcRepository = menuJdbcRepository;
    }

    @Override
    public List<ItemMenuDTO> getMenu(String usuario, String rol) {

        List<ItemMenuDTO> items = menuJdbcRepository.getMenuUsuario(usuario,rol).stream().map(x ->{
            ItemMenuDTO i = new ItemMenuDTO();
            i.setLabel(x.getNombreLargo());
            i.setLink(x.getUrl());
            return i;
        }).collect(Collectors.toList());

        return items;
    }
}
