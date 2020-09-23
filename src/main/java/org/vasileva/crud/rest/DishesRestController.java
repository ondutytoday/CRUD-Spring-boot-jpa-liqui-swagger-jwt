package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.service.DishesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dishes/")
public class DishesRestController {

    @Autowired
    private DishesService dishesService;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> getDish(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> saveDish(@RequestBody @Valid Dishes dish) {
        HttpHeaders headers = new HttpHeaders();
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dishesService.save(dish);
        return new ResponseEntity<>(dish, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> updateDish(@RequestBody @Valid Dishes dishDetails, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (dishDetails == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dish.setBalance(dishDetails.getBalance());
        dish.setCalories(dishDetails.getCalories());
        dish.setDishesSupplies(dishDetails.getDishesSupplies());
        dish.setDishName(dishDetails.getDishName());
        dish.setOrdersOfDishes(dishDetails.getOrdersOfDishes());
        dish.setPrice(dishDetails.getPrice());

        dishesService.save(dish);
        return new ResponseEntity<>(dish, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> deleteDish(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dishes>> getAllDishes() {
        List<Dishes> dishes = this.dishesService.getAll();

        if (dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}
