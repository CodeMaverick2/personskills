package com.example.personskills.repository;

import com.example.personskills.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenSavePerson_thenReturnSavedPerson() {
        // given
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        // when
        Person savedPerson = personRepository.save(person);

        // then
        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getId()).isNotNull();
        assertThat(savedPerson.getName()).isEqualTo("John Doe");
        assertThat(savedPerson.getAge()).isEqualTo(30);
    }

    @Test
    public void whenFindById_thenReturnPerson() {
        // given
        Person person = new Person();
        person.setName("Jane Doe");
        person.setAge(25);
        entityManager.persist(person);
        entityManager.flush();

        // when
        Person found = personRepository.findById(person.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Jane Doe");
        assertThat(found.getAge()).isEqualTo(25);
    }
} 