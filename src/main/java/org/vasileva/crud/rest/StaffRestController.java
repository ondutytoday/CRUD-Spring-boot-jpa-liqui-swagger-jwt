package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.entity.Staff;
import org.vasileva.crud.service.StaffService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/staff/")
public class StaffRestController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Staff> getStaff (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staff = staffService.getById(id);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Staff> saveStaff (@RequestBody @Valid Staff staff) {
        HttpHeaders headers = new HttpHeaders();
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        staffService.save(staff);
        return new ResponseEntity<>(staff, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Staff> updateStaff (@RequestBody @Valid Staff staff) {
        HttpHeaders headers = new HttpHeaders();
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        staffService.save(staff);
        return new ResponseEntity<>(staff, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Staff> deleteStaff (@PathVariable("id") Long id) {
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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staff = this.staffService.getAll();

        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
}
