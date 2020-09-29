package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.dto.DishesSupplyDto;
import org.vasileva.crud.entity.DishesSupply;
import org.vasileva.crud.mapper.DishesSupplyMapper;
import org.vasileva.crud.service.DishesSupplyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dishes_supply/")
public class DishesSupplyRestController {

    @Autowired
    private DishesSupplyService dishesSupplyService;
    @Autowired
    private DishesSupplyMapper dishesSupplyMapper;

    @GetMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> getDishSupply (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishesSupply = dishesSupplyService.getById(id);
        if (dishesSupply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesSupplyMapper.toDishesSupplyDto(dishesSupply), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> saveDishSupply (@RequestBody @Valid DishesSupplyDto dishesSupplyDto) {
        HttpHeaders headers = new HttpHeaders();
        if (dishesSupplyDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishSupply = dishesSupplyMapper.toDishesSupply(dishesSupplyDto);
        dishesSupplyService.save(dishSupply);
        return new ResponseEntity<>(dishesSupplyMapper.toDishesSupplyDto(dishSupply), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> updateDishSupply (@RequestBody @Valid DishesSupplyDto dishesSupplyDetailsDto, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (dishesSupplyDetailsDto == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishSupply = dishesSupplyService.getById(id);
        DishesSupply dishSupplyDetails = dishesSupplyMapper.toDishesSupply(dishesSupplyDetailsDto);

        if (dishSupply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dishSupply.setDish(dishSupplyDetails.getDish());
        dishSupply.setPrice(dishSupplyDetails.getPrice());
        dishSupply.setQuantity(dishSupplyDetails.getQuantity());
        dishSupply.setSupply(dishSupplyDetails.getSupply());

        dishesSupplyService.save(dishSupply);
        return new ResponseEntity<>(dishesSupplyMapper.toDishesSupplyDto(dishSupply), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> deleteDishSupply (@PathVariable("id") Long id) {
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
    public ResponseEntity<List<DishesSupplyDto>> getAllDishesSupply() {
        List<DishesSupply> dishesSupply = this.dishesSupplyService.getAll();

        if (dishesSupply.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesSupplyMapper.toListDishesSupplyDto(dishesSupply), HttpStatus.OK);
    }
}
