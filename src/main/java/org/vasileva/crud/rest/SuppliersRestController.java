package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.entity.Suppliers;
import org.vasileva.crud.service.SuppliersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suppliers/")
public class SuppliersRestController {

    @Autowired
    private SuppliersService suppliersService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suppliers> getSupplier (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersService.getById(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suppliers> saveSupplier (@RequestBody @Valid Suppliers supplier) {
        HttpHeaders headers = new HttpHeaders();
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        suppliersService.save(supplier);
        return new ResponseEntity<>(supplier, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suppliers> updateSupplier (@RequestBody @Valid Suppliers supplier) {
        HttpHeaders headers = new HttpHeaders();
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        suppliersService.save(supplier);
        return new ResponseEntity<>(supplier, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suppliers> deleteSupplier (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersService.getById(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        suppliersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Suppliers>> getAllSuppliers() {
        List<Suppliers> suppliers = this.suppliersService.getAll();

        if (suppliers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
}
