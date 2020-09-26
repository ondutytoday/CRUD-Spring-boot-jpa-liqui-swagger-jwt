package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.entity.DishesSupply;
import org.vasileva.crud.service.DishesSupplyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dishes_supply/")
public class DishesSupplyRestController {

    @Autowired
    private DishesSupplyService dishesSupplyService;

    @GetMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupply> getDishSupply (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishesSupply = dishesSupplyService.getById(id);
        if (dishesSupply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesSupply, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupply> saveDishSupply (@RequestBody @Valid DishesSupply dishSupply) {
        HttpHeaders headers = new HttpHeaders();
        if (dishSupply == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dishesSupplyService.save(dishSupply);
        return new ResponseEntity<>(dishSupply, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupply> updateDishSupply (@RequestBody @Valid DishesSupply dishSupply) {
        HttpHeaders headers = new HttpHeaders();
        if (dishSupply == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dishesSupplyService.save(dishSupply);
        return new ResponseEntity<>(dishSupply, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupply> deleteDishSupply (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishSupply = dishesSupplyService.getById(id);
        if (dishSupply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishesSupplyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishesSupply>> getAllDishesSupply() {
        List<DishesSupply> dishesSupply = this.dishesSupplyService.getAll();

        if (dishesSupply.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesSupply, HttpStatus.OK);
    }
}
