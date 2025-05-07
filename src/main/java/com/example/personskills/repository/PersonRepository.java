package com.example.personskills.repository;

import com.example.personskills.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // Basic CRUD operations are automatically provided by JpaRepository
} 