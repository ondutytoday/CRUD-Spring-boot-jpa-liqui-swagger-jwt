package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.service.DishesService;

@RestController
@RequestMapping("/dishes")
public class DishesRestController {

    @Autowired
    private DishesService dishesService;

    public ResponseEntity<Dishes> getDish (Long dishId) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
