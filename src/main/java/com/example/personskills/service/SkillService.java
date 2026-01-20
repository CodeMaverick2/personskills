package com.example.personskills.service;

import com.example.personskills.model.Skill;
import com.example.personskills.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing Skill entities.
 * Provides business logic for skill operations.
 */
@Service
@Transactional
public final class SkillService {

    /**
     * Repository for skill data access.
     */
    private final SkillRepository skillRepository;

    /**
     * Constructor for SkillService.
     *
     * @param repository the skill repository
     */
    @Autowired
    public SkillService(final SkillRepository repository) {
        this.skillRepository = repository;
    }

    /**
     * Adds a new skill to the system.
     *
     * @param skill the skill to add
     * @return the saved skill
     */
    public Skill addSkill(final Skill skill) {
        return skillRepository.save(skill);
    }

    /**
     * Retrieves all skills with their associated persons.
     *
     * @return list of all skills
     */
    public List<Skill> getAllSkills() {
        return skillRepository.findAllWithPerson();
    }

    /**
     * Retrieves all skills for a specific person.
     *
     * @param personId the ID of the person
     * @return list of skills for the person
     */
    public List<Skill> getSkillsByPersonId(final Long personId) {
        return skillRepository.findByPersonId(personId);
    }

    /**
     * Retrieves a skill by its ID.
     *
     * @param id the skill ID
     * @return the skill
     * @throws EntityNotFoundException if skill not found
     */
    public Skill getSkillById(final Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Skill not found with id: " + id));
    }

    /**
     * Updates an existing skill.
     *
     * @param id           the skill ID
     * @param skillDetails the updated skill details
     * @return the updated skill
     */
    public Skill updateSkill(final Long id, final Skill skillDetails) {
        Skill skill = getSkillById(id);
        skill.setSkillName(skillDetails.getSkillName());
        skill.setLevel(skillDetails.getLevel());
        return skillRepository.save(skill);
    }

    /**
     * Deletes a skill by its ID.
     *
     * @param id the skill ID
     * @throws EntityNotFoundException if skill not found
     */
    public void deleteSkill(final Long id) {
        if (!skillRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }
}
