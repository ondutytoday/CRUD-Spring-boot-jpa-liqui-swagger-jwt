package org.vasileva.crud.rest;

import io.swagger.annotations.*;
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

@Api(value = "CRUD Application", tags = {"Suppliers RestController"})
@RestController
@RequestMapping("/suppliers/")
public class SuppliersRestController {

    private final SuppliersService suppliersService;
    private final SuppliersMapper suppliersMapper;

    @Autowired
    public SuppliersRestController(SuppliersService suppliersService, SuppliersMapper suppliersMapper) {
        this.suppliersService = suppliersService;
        this.suppliersMapper = suppliersMapper;
    }

    @ApiOperation(value = "View a supplier selected by id", response = SuppliersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "_{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> getSupplier(@ApiParam(value = "ID of a supplier")
                                                    @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersService.getById(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suppliersMapper.toSuppliersDto(supplier), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a supplier", response = SuppliersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to add the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to add is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> saveSupplier(@ApiParam(value = "A JSON value representing a supplier.")
                                                     @RequestBody @Valid SuppliersDto suppliersDto) {
        HttpHeaders headers = new HttpHeaders();
        if (suppliersDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Suppliers supplier = suppliersMapper.toSuppliers(suppliersDto);
        suppliersService.save(supplier);
        return new ResponseEntity<>(suppliersMapper.toSuppliersDto(supplier), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a supplier", response = SuppliersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> updateSupplier(@ApiParam(value = "A JSON value representing a supplier.")
                                                       @RequestBody @Valid SuppliersDto supplierDetailsDto,
                                                       @ApiParam(value = "ID of a supplier you want to update")
                                                       @PathVariable("id") Long id) {
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

    @ApiOperation(value = "Delete a supplier")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The resource was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersDto> deleteSupplier(@ApiParam(value = "ID of a supplier you want to delete")
                                                       @PathVariable("id") Long id) {
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

    @ApiOperation(value = "View a list of available suppliers", response = SuppliersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SuppliersDto>> getAllSuppliers() {
        List<Suppliers> suppliers = this.suppliersService.getAll();

        if (suppliers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suppliersMapper.toListSuppliersDto(suppliers), HttpStatus.OK);
    }
}
