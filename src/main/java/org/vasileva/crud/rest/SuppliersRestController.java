package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.dto.SuppliersDto;
import org.vasileva.crud.entity.Suppliers;
import org.vasileva.crud.mapper.SuppliersMapper;
import org.vasileva.crud.service.SuppliersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suppliers/")
public class SuppliersRestController {

    @Autowired
    private SuppliersService suppliersService;
    @Autowired
    private SuppliersMapper suppliersMapper;

    @GetMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> getSupplier (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersService.getById(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suppliersMapper.toSuppliersDto(supplier), HttpStatus.OK);
    }

    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> saveSupplier (@RequestBody @Valid SuppliersDto suppliersDto) {
        HttpHeaders headers = new HttpHeaders();
        if (suppliersDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersMapper.toSuppliers(suppliersDto);
        suppliersService.save(supplier);
        return new ResponseEntity<>(suppliersMapper.toSuppliersDto(supplier), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> updateSupplier (@RequestBody @Valid SuppliersDto supplierDetailsDto, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (supplierDetailsDto == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersService.getById(id);
        Suppliers supplierDetails = suppliersMapper.toSuppliers(supplierDetailsDto);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setInformation(supplierDetails.getInformation());
        supplier.setInn(supplierDetails.getInn());
        supplier.setPhoneNumber(supplierDetails.getPhoneNumber());
        supplier.setSupplierAddress(supplierDetails.getSupplierAddress());
        supplier.setSupplierName(supplierDetails.getSupplierName());
        supplier.setSupplies(supplierDetails.getSupplies());
        suppliersService.save(supplier);
        return new ResponseEntity<>(suppliersMapper.toSuppliersDto(supplier), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> deleteSupplier (@PathVariable("id") Long id) {
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

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SuppliersDto>> getAllSuppliers() {
        List<Suppliers> suppliers = this.suppliersService.getAll();

        if (suppliers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suppliersMapper.toListSuppliersDto(suppliers), HttpStatus.OK);
    }
}
