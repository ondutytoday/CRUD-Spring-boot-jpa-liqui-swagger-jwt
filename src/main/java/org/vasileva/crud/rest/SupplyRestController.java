package org.vasileva.crud.rest;

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

@RestController
@RequestMapping("/supply/")
public class SupplyRestController {

    @Autowired
    private SupplyService supplyService;
    @Autowired
    private SupplyMapper supplyMapper;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> getSupply (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyService.getById(id);
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplyMapper.toSupplyDto(supply), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> saveSupply (@RequestBody @Valid SupplyDto supplyDto) {
        HttpHeaders headers = new HttpHeaders();
        if (supplyDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyMapper.toSupply(supplyDto);
        supplyService.save(supply);
        return new ResponseEntity<>(supplyMapper.toSupplyDto(supply), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> updateSupply (@RequestBody @Valid SupplyDto supplyDetailsDto, @PathVariable("id") Long id) {
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

    @DeleteMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyDto> deleteSupply (@PathVariable("id") Long id) {
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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SupplyDto>> getAllSupplies() {
        List<Supply> supplies = this.supplyService.getAll();

        if (supplies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplyMapper.toListSupplyDto(supplies), HttpStatus.OK);
    }
}
