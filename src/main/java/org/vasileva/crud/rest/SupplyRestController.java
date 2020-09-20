package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.entity.Supply;
import org.vasileva.crud.service.SupplyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/supply/")
public class SupplyRestController {

    @Autowired
    private SupplyService supplyService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supply> getSupply (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supply supply = supplyService.getById(id);
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supply, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supply> saveSupply (@RequestBody @Valid Supply supply) {
        HttpHeaders headers = new HttpHeaders();
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        supplyService.save(supply);
        return new ResponseEntity<>(supply, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supply> updateSupply (@RequestBody @Valid Supply supply) {
        HttpHeaders headers = new HttpHeaders();
        if (supply == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        supplyService.save(supply);
        return new ResponseEntity<>(supply, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supply> deleteSupply (@PathVariable("id") Long id) {
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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Supply>> getAllSupplies() {
        List<Supply> supplies = this.supplyService.getAll();

        if (supplies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }
}
