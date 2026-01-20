package com.example.personskills.service;

import com.example.personskills.model.Person;
import com.example.personskills.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing Person entities.
 * Provides business logic for person operations.
 */
@Service
@Transactional
public final class PersonService {

    /**
     * Repository for person data access.
     */
    private final PersonRepository personRepository;

    /**
     * Constructor for PersonService.
     *
     * @param repository the person repository
     */
    @Autowired
    public PersonService(final PersonRepository repository) {
        this.personRepository = repository;
    }

    /**
     * Adds a new person to the system.
     *
     * @param person the person to add
     * @return the saved person
     */
    public Person addPerson(final Person person) {
        return personRepository.save(person);
    }

    /**
     * Retrieves all persons from the system.
     *
     * @return list of all persons
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Retrieves a person by their ID.
     *
     * @param id the person ID
     * @return the person
     * @throws EntityNotFoundException if person not found
     */
    public Person getPersonById(final Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Person not found with id: " + id));
    }

    /**
     * Updates an existing person.
     *
     * @param id            the person ID
     * @param personDetails the updated person details
     * @return the updated person
     */
    public Person updatePerson(final Long id,
            final Person personDetails) {
        Person person = getPersonById(id);
        person.setName(personDetails.getName());
        person.setAge(personDetails.getAge());
        return personRepository.save(person);
    }

    /**
     * Deletes a person by their ID.
     *
     * @param id the person ID
     * @throws EntityNotFoundException if person not found
     */
    public void deletePerson(final Long id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Person not found with id: " + id);
        }
        personRepository.deleteById(id);
    }
}
