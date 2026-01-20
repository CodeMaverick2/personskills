package com.example.personskills.repository;

import com.example.personskills.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Skill entity.
 * Provides CRUD operations and custom queries for Skill entities.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    /**
     * Finds all skills with their associated person eagerly loaded.
     *
     * @return list of skills with person data
     */
    @Query("SELECT s FROM Skill s INNER JOIN s.person p")
    List<Skill> findAllWithPerson();

    /**
     * Finds all skills belonging to a specific person.
     *
     * @param personId the ID of the person
     * @return list of skills for the person
     */
    List<Skill> findByPersonId(Long personId);
}
