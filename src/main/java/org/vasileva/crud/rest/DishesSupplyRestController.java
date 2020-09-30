package org.vasileva.crud.rest;

import io.swagger.annotations.*;
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

@Api(value = "CRUD Application", tags = {"DishesSupply RestController"})
@RestController
@RequestMapping("/dishes_supply/")
public class DishesSupplyRestController {

    @Autowired
    private DishesSupplyService dishesSupplyService;
    @Autowired
    private DishesSupplyMapper dishesSupplyMapper;

    @ApiOperation(value = "View a dishSupply selected by id", response = DishesSupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> getDishSupply(@ApiParam(value = "ID of a dishSupply")
                                                         @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishesSupply = dishesSupplyService.getById(id);
        if (dishesSupply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesSupplyMapper.toDishesSupplyDto(dishesSupply), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a dishSupply", response = DishesSupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to add the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to add is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> saveDishSupply(@ApiParam(value = "A JSON value representing a dishSupply.")
                                                          @RequestBody @Valid DishesSupplyDto dishesSupplyDto) {
        HttpHeaders headers = new HttpHeaders();
        if (dishesSupplyDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DishesSupply dishSupply = dishesSupplyMapper.toDishesSupply(dishesSupplyDto);
        dishesSupplyService.save(dishSupply);
        return new ResponseEntity<>(dishesSupplyMapper.toDishesSupplyDto(dishSupply), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a dishSupply", response = DishesSupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    @PutMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> updateDishSupply(@ApiParam(value = "A JSON value representing a dishSupply.")
                                                            @RequestBody @Valid DishesSupplyDto dishesSupplyDetailsDto,
                                                            @ApiParam(value = "ID of a dishSupply you want to update")
                                                            @PathVariable("id") Long id) {
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

    @ApiOperation(value = "Delete a dishSupply")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The resource was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishesSupplyDto> deleteDishSupply(@ApiParam(value = "ID of a dishSupply you want to delete")
                                                            @PathVariable("id") Long id) {
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

    @ApiOperation(value = "View a list of available dishSupplies", response = DishesSupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishesSupplyDto>> getAllDishesSupply() {
        List<DishesSupply> dishesSupply = this.dishesSupplyService.getAll();

        if (dishesSupply.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishesSupplyMapper.toListDishesSupplyDto(dishesSupply), HttpStatus.OK);
    }
}
