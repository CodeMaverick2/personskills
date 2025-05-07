package com.example.personskills.repository;

import com.example.personskills.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    @Query("SELECT s FROM Skill s INNER JOIN s.person p")
    List<Skill> findAllWithPerson();
    
    List<Skill> findByPersonId(Long personId);
} 