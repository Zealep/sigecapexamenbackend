package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.ItemMenuDTO;
import com.sigecap.sigecapexamenbackend.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "/menu/{usuario}/{rol}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemMenuDTO>> getMenu(@PathVariable String usuario, @PathVariable String rol){
        try{
            return new ResponseEntity<>(menuService.getMenu(usuario,rol), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
