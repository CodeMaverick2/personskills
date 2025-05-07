package com.example.personskills.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @Min(value = 0, message = "Age must be positive")
    @Column(nullable = false)
    private Integer age;
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();
    
    // Helper method to add skill
    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setPerson(this);
    }
    
    // Helper method to remove skill
    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.setPerson(null);
    }
} 