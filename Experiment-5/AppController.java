package com.example.demo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Experiment 5 - RESTful Controller for CRUD operations on Staff/Mobile resources.
 * Supports GET (all & by id), POST, PUT, DELETE with HTTP status codes and error handling.
 */
@RestController
@RequestMapping("/api/staff")
public class AppController {

    @Autowired
    private StaffManager manager;

    // GET /api/staff - Retrieve all records
    @GetMapping
    public List<Staff> listAllStaff() {
        return manager.getAllStaff();
    }

    // GET /api/staff/{id} - Retrieve single record with 404 handling
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable Long id) {
        try {
            Staff staff = manager.getStaffById(id);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /api/staff - Create new record
    @PostMapping
    public ResponseEntity<String> saveStaff(@RequestBody Staff staff) {
        manager.saveStaff(staff);
        return new ResponseEntity<>("Record created successfully", HttpStatus.CREATED);
    }

    // PUT /api/staff - Update existing record
    @PutMapping
    public ResponseEntity<String> updateStaff(@RequestBody Staff staff) {
        if (staff.getId() != null && manager.exists(staff.getId())) {
            manager.saveStaff(staff);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Record not found to update", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/staff/{id} - Delete record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        if (manager.exists(id)) {
            manager.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
