package com.sigecap.sigecapexamenbackend.rest;

import com.sigecap.sigecapexamenbackend.model.dto.ItemMenuDTO;
import com.sigecap.sigecapexamenbackend.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);


    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "/menu/{usuario}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemMenuDTO>> getMenu(@PathVariable String usuario){
        try{
            return new ResponseEntity<>(menuService.getMenu(usuario), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
