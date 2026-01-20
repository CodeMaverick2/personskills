package com.example.personskills.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Person in the system.
 * A person can have multiple skills associated with them.
 */
@Entity
@Data
@Table(name = "persons")
public final class Person {

    /**
     * Unique identifier for the person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the person.
     */
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    /**
     * Age of the person.
     */
    @Min(value = 0, message = "Age must be positive")
    @Column(nullable = false)
    private Integer age;

    /**
     * List of skills possessed by the person.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    /**
     * Helper method to add a skill to this person.
     *
     * @param skill the skill to add
     */
    public void addSkill(final Skill skill) {
        skills.add(skill);
        skill.setPerson(this);
    }

    /**
     * Helper method to remove a skill from this person.
     *
     * @param skill the skill to remove
     */
    public void removeSkill(final Skill skill) {
        skills.remove(skill);
        skill.setPerson(null);
    }
}

