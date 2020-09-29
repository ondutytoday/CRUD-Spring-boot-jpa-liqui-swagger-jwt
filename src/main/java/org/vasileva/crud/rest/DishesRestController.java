package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.dto.DishesDto;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.mapper.DishesMapper;
import org.vasileva.crud.service.DishesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dishes/")
public class DishesRestController {

    @Autowired
    private DishesService dishesService;
    @Autowired
    private DishesMapper dishesMapper;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> getDish(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesMapper.toDishesDto(dish), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> saveDish(@RequestBody @Valid DishesDto dishDto) {
        HttpHeaders headers = new HttpHeaders();
        if (dishDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesMapper.toDishes(dishDto);
        dishesService.save(dish);
        return new ResponseEntity<>(dishesMapper.toDishesDto(dish), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> updateDish(@RequestBody @Valid DishesDto dishDetailsDto, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (dishDetailsDto == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        Dishes dishDetails = dishesMapper.toDishes(dishDetailsDto);
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
        return new ResponseEntity<>(dishesMapper.toDishesDto(dish), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> deleteDish(@PathVariable("id") Long id) {
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
    public ResponseEntity<List<DishesDto>> getAllDishes() {
        List<Dishes> dishes = this.dishesService.getAll();

        if (dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dishesMapper.toListDishesDto(dishes), HttpStatus.OK);
    }
}
