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

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> getDish (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> saveDish (@RequestBody @Valid Dishes dish) {
        HttpHeaders headers = new HttpHeaders();
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dishesService.save(dish);
        return new ResponseEntity<>(dish, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> updateDish (@RequestBody @Valid Dishes dish) {
        HttpHeaders headers = new HttpHeaders();
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dishesService.save(dish);
        return new ResponseEntity<>(dish, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dishes> deleteDish (@PathVariable("id") Long id) {
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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dishes>> getAllCustomers() {
        List<Dishes> dishes = this.dishesService.getAll();

        if (dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}
