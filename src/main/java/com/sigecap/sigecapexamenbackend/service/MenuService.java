package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.dto.ItemMenuDTO;

import java.util.List;

public interface MenuService {

    List<ItemMenuDTO> getMenu(String usuario);

}
