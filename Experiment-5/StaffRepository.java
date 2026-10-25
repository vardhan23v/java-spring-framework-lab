package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Experiment 5 - Spring Data JPA Repository for Staff entity
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
