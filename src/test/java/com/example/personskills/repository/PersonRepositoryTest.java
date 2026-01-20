package com.example.personskills.repository;

import com.example.personskills.model.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonRepositoryTest {

    @Test
    public void testPersonCreation() {
        // Simple unit test - no Spring, no database
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        assertNotNull(person);
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    public void testPersonDefaultValues() {
        Person person = new Person();
        
        assertNotNull(person);
        assertNotNull(person.getSkills());
    }
} 