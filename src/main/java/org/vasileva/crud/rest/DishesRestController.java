package org.vasileva.crud.rest;

import io.swagger.annotations.*;
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


@Api(value = "CRUD Application", tags = {"Dishes RestController"})
@RestController
@RequestMapping("/dishes/")
public class DishesRestController {


    private final DishesService dishesService;
    private final DishesMapper dishesMapper;

    @Autowired
    public DishesRestController(DishesService dishesService, DishesMapper dishesMapper) {
        this.dishesService = dishesService;
        this.dishesMapper = dishesMapper;
    }

    @ApiOperation(value = "View a dish selected by id", response = DishesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> getDish(@ApiParam(value = "ID of a dish")
                                             @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesService.getById(id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesMapper.toDishesDto(dish), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a dish to menu", response = DishesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to add the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to add is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> saveDish(@ApiParam(value = "A JSON value representing a dish.")
                                              @RequestBody @Valid DishesDto dishDto) {
        HttpHeaders headers = new HttpHeaders();
        if (dishDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dishes dish = dishesMapper.toDishes(dishDto);
        dishesService.save(dish);
        return new ResponseEntity<>(dishesMapper.toDishesDto(dish), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a dish", response = DishesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> updateDish(@ApiParam(value = "A JSON value representing a dish.")
                                                @RequestBody @Valid DishesDto dishDetailsDto,
                                                @ApiParam(value = "ID of a dish you want to update")
                                                @PathVariable("id") Long id) {
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

    @ApiOperation(value = "Delete a dish from menu")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The resource was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesDto> deleteDish(@ApiParam(value = "ID of a dish you want to delete")
                                                @PathVariable("id") Long id) {
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

    @ApiOperation(value = "View a list of available dishes", response = DishesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishesDto>> getAllDishes() {
        List<Dishes> dishes = this.dishesService.getAll();

        if (dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dishesMapper.toListDishesDto(dishes), HttpStatus.OK);
    }
}
