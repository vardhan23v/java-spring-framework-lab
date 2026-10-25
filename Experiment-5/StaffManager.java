package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer with an extra exists() helper for error handling.
 */
@Service
public class StaffManager {

    @Autowired
    private StaffRepository repository;

    public List<Staff> getAllStaff()        { return repository.findAll();            }
    public Staff getStaffById(Long id)     { return repository.findById(id).get();   }
    public void  saveStaff(Staff staff)    { repository.save(staff);                 }
    public void  deleteStaff(Long id)      { repository.deleteById(id);              }
    public boolean exists(Long id)         { return repository.existsById(id);       }
}
