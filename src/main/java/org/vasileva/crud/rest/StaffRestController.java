package org.vasileva.crud.rest;

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

@RestController
@RequestMapping("/staff/")
public class StaffRestController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffMapper staffMapper;

    @GetMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> getStaff (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffService.getById(id);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffMapper.toStaffDto(staff), HttpStatus.OK);
    }

    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> saveStaff (@RequestBody @Valid StaffDto staffDto) {
        HttpHeaders headers = new HttpHeaders();
        if (staffDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffMapper.toStaff(staffDto);
        staffService.save(staff);
        return new ResponseEntity<>(staffMapper.toStaffDto(staff), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> updateStaff (@RequestBody @Valid StaffDto staffDetailsDto, @PathVariable("id") Long id ) {
        HttpHeaders headers = new HttpHeaders();
        if (staffDetailsDto == null|| id == null) {
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

    @DeleteMapping(value = "delete/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDto> deleteStaff (@PathVariable("id") Long id) {
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

    @GetMapping(value = "list",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        List<Staff> staff = this.staffService.getAll();

        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffMapper.toListStaffDto(staff), HttpStatus.OK);
    }
}
