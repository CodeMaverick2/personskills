package com.example.personskills.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Entity class representing a Skill in the system.
 * A skill belongs to a person and has a name and proficiency level.
 */
@Entity
@Data
@Table(name = "skills")
public class Skill {

    /**
     * Unique identifier for the skill.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the skill.
     */
    @NotBlank(message = "Skill name is required")
    @Column(nullable = false)
    private String skillName;

    /**
     * Proficiency level of the skill.
     */
    @NotNull(message = "Skill level is required")
    @Column(nullable = false)
    private Integer level;

    /**
     * The person who possesses this skill.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
