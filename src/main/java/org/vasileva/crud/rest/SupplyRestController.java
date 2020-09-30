package org.vasileva.crud.rest;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.dto.SupplyDto;
import org.vasileva.crud.entity.Supply;
import org.vasileva.crud.mapper.SupplyMapper;
import org.vasileva.crud.service.SupplyService;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Application", tags = {"Supply RestController"})
@RestController
@RequestMapping("/supply/")
public class SupplyRestController {

    @Autowired
    private SupplyService supplyService;
    @Autowired
    private SupplyMapper supplyMapper;

    @ApiOperation(value = "View a supply selected by id", response = SupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> getSupply(@ApiParam(value = "ID of a supply")
                                               @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyService.getById(id);
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplyMapper.toSupplyDto(supply), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a supply", response = SupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to add the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to add is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> saveSupply(@ApiParam(value = "A JSON value representing a supply.")
                                                @RequestBody @Valid SupplyDto supplyDto) {
        HttpHeaders headers = new HttpHeaders();
        if (supplyDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyMapper.toSupply(supplyDto);
        supplyService.save(supply);
        return new ResponseEntity<>(supplyMapper.toSupplyDto(supply), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a supply", response = SupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    @PutMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> updateSupply(@ApiParam(value = "A JSON value representing a supply.")
                                                  @RequestBody @Valid SupplyDto supplyDetailsDto,
                                                  @ApiParam(value = "ID of a supply you want to update")
                                                  @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (supplyDetailsDto == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyService.getById(id);
        Supply supplyDetails = supplyMapper.toSupply(supplyDetailsDto);
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supply.setDateOfSupply(supplyDetails.getDateOfSupply());
        supply.setDishesSupplies(supplyDetails.getDishesSupplies());
        supply.setSupplier(supplyDetails.getSupplier());

        supplyService.save(supply);
        return new ResponseEntity<>(supplyMapper.toSupplyDto(supply), headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a supply")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The resource was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> deleteSupply(@ApiParam(value = "ID of a supply you want to delete")
                                                  @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyService.getById(id);
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "View a list of available supplies", response = SupplyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SupplyDto>> getAllSupplies() {
        List<Supply> supplies = this.supplyService.getAll();

        if (supplies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplyMapper.toListSupplyDto(supplies), HttpStatus.OK);
    }
}
