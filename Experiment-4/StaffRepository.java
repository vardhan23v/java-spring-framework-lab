package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository – no implementation needed.
 * JpaRepository provides findAll, findById, save, deleteById, etc.
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
