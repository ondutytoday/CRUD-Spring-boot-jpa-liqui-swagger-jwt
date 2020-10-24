package org.vasileva.crud.rest;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.dto.StaffDto;
import org.vasileva.crud.entity.Staff;
import org.vasileva.crud.mapper.StaffMapper;
import org.vasileva.crud.service.StaffService;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Application", tags = {"Staff RestController"})
@RestController
@RequestMapping("/staff/")
public class StaffRestController {

    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @Autowired
    public StaffRestController(StaffService staffService, StaffMapper staffMapper) {
        this.staffService = staffService;
        this.staffMapper = staffMapper;
    }

    @ApiOperation(value = "View an employee selected by id", response = StaffDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> getStaff(@ApiParam(value = "ID of an employee")
                                             @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffService.getById(id);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffMapper.toStaffDto(staff), HttpStatus.OK);
    }

    @ApiOperation(value = "Add an employee", response = StaffDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to add the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to add is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> saveStaff(@ApiParam(value = "A JSON value representing an employee.")
                                              @RequestBody @Valid StaffDto staffDto) {
        HttpHeaders headers = new HttpHeaders();
        if (staffDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffMapper.toStaff(staffDto);
        staffService.save(staff);
        return new ResponseEntity<>(staffMapper.toStaffDto(staff), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an employee", response = StaffDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> updateStaff(@ApiParam(value = "A JSON value representing an employee.")
                                                @RequestBody @Valid StaffDto staffDetailsDto,
                                                @ApiParam(value = "ID of an employee you want to update")
                                                @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (staffDetailsDto == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffService.getById(id);
        Staff staffDetails = staffMapper.toStaff(staffDetailsDto);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        staff.setDateOfBirth(staffDetails.getDateOfBirth());
        staff.setGender(staffDetails.getGender());
        staff.setHomeAddress(staffDetails.getHomeAddress());
        staff.setSurname(staffDetails.getSurname());
        staff.setName(staffDetails.getName());
        staff.setPatronymic(staffDetails.getPatronymic());
        staff.setPassport(staffDetails.getPassport());
        staff.setPosition(staffDetails.getPosition());
        staff.setSalary(staffDetails.getSalary());
        staff.setOrders(staffDetails.getOrders());
        staffService.save(staff);
        return new ResponseEntity<>(staffMapper.toStaffDto(staff), headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The resource was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> deleteStaff(@ApiParam(value = "ID of an employee you want to delete")
                                                @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffService.getById(id);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        staffService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "View a list of available staff", response = StaffDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        List<Staff> staff = this.staffService.getAll();

        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffMapper.toListStaffDto(staff), HttpStatus.OK);
    }
}
