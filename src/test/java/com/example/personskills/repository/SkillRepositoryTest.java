package com.example.personskills.repository;

import com.example.personskills.model.Person;
import com.example.personskills.model.Skill;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkillRepositoryTest {

    @Test
    public void testSkillCreation() {
        // Simple unit test - no Spring, no database
        Skill skill = new Skill();
        skill.setSkillName("Java");
        skill.setLevel(5);
        
        assertNotNull(skill);
        assertEquals("Java", skill.getSkillName());
        assertEquals(5, skill.getLevel());
    }

    @Test
    public void testSkillWithPerson() {
        Person person = new Person();
        person.setName("John Doe");

        Skill skill = new Skill();
        skill.setSkillName("Python");
        skill.setPerson(person);
        
        assertNotNull(skill);
        assertEquals("Python", skill.getSkillName());
        assertEquals(person, skill.getPerson());
    }
} 