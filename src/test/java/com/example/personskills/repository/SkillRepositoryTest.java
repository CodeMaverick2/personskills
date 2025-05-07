package com.example.personskills.repository;

import com.example.personskills.model.Person;
import com.example.personskills.model.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SkillRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    public void whenSaveSkill_thenReturnSavedSkill() {
        // given
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        entityManager.persist(person);

        Skill skill = new Skill();
        skill.setSkillName("Java");
        skill.setLevel(5);
        skill.setPerson(person);

        // when
        Skill savedSkill = skillRepository.save(skill);

        // then
        assertThat(savedSkill).isNotNull();
        assertThat(savedSkill.getId()).isNotNull();
        assertThat(savedSkill.getSkillName()).isEqualTo("Java");
        assertThat(savedSkill.getLevel()).isEqualTo(5);
        assertThat(savedSkill.getPerson()).isEqualTo(person);
    }

    @Test
    public void whenFindAllWithPerson_thenReturnSkillsWithPersons() {
        // given
        Person person = new Person();
        person.setName("Jane Doe");
        person.setAge(25);
        entityManager.persist(person);

        Skill skill1 = new Skill();
        skill1.setSkillName("Python");
        skill1.setLevel(4);
        skill1.setPerson(person);
        entityManager.persist(skill1);

        Skill skill2 = new Skill();
        skill2.setSkillName("JavaScript");
        skill2.setLevel(3);
        skill2.setPerson(person);
        entityManager.persist(skill2);

        entityManager.flush();

        // when
        List<Skill> skills = skillRepository.findAllWithPerson();

        // then
        assertThat(skills).hasSize(2);
        assertThat(skills.get(0).getPerson()).isNotNull();
        assertThat(skills.get(1).getPerson()).isNotNull();
    }
} 