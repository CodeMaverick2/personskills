package com.example.personskills.service;

import com.example.personskills.model.Person;
import com.example.personskills.model.Skill;
import com.example.personskills.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenAddSkill_thenReturnSavedSkill() {
        // given
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        Skill skill = new Skill();
        skill.setSkillName("Java");
        skill.setLevel(5);
        skill.setPerson(person);

        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        // when
        Skill savedSkill = skillService.addSkill(skill);

        // then
        assertThat(savedSkill).isNotNull();
        assertThat(savedSkill.getSkillName()).isEqualTo("Java");
        assertThat(savedSkill.getLevel()).isEqualTo(5);
        assertThat(savedSkill.getPerson()).isEqualTo(person);
        verify(skillRepository, times(1)).save(any(Skill.class));
    }

    @Test
    void whenGetAllSkills_thenReturnListOfSkills() {
        // given
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        Skill skill1 = new Skill();
        skill1.setSkillName("Java");
        skill1.setLevel(5);
        skill1.setPerson(person);

        Skill skill2 = new Skill();
        skill2.setSkillName("Python");
        skill2.setLevel(4);
        skill2.setPerson(person);

        when(skillRepository.findAllWithPerson()).thenReturn(Arrays.asList(skill1, skill2));

        // when
        List<Skill> skills = skillService.getAllSkills();

        // then
        assertThat(skills).hasSize(2);
        assertThat(skills.get(0).getSkillName()).isEqualTo("Java");
        assertThat(skills.get(1).getSkillName()).isEqualTo("Python");
        verify(skillRepository, times(1)).findAllWithPerson();
    }

    @Test
    void whenGetSkillById_thenReturnSkill() {
        // given
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        Skill skill = new Skill();
        skill.setSkillName("Java");
        skill.setLevel(5);
        skill.setPerson(person);

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));

        // when
        Skill found = skillService.getSkillById(1L);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getSkillName()).isEqualTo("Java");
        assertThat(found.getLevel()).isEqualTo(5);
        assertThat(found.getPerson()).isEqualTo(person);
        verify(skillRepository, times(1)).findById(1L);
    }

    @Test
    void whenGetSkillByIdNotFound_thenThrowException() {
        // given
        when(skillRepository.findById(1L)).thenReturn(Optional.empty());

        // when/then
        assertThatThrownBy(() -> skillService.getSkillById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Skill not found with id: 1");
    }

    @Test
    void whenUpdateSkill_thenReturnUpdatedSkill() {
        // given
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        Skill existingSkill = new Skill();
        existingSkill.setSkillName("Java");
        existingSkill.setLevel(5);
        existingSkill.setPerson(person);

        Skill updatedSkill = new Skill();
        updatedSkill.setSkillName("Java Advanced");
        updatedSkill.setLevel(8);
        updatedSkill.setPerson(person);

        when(skillRepository.findById(1L)).thenReturn(Optional.of(existingSkill));
        when(skillRepository.save(any(Skill.class))).thenReturn(updatedSkill);

        // when
        Skill result = skillService.updateSkill(1L, updatedSkill);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getSkillName()).isEqualTo("Java Advanced");
        assertThat(result.getLevel()).isEqualTo(8);
        verify(skillRepository, times(1)).findById(1L);
        verify(skillRepository, times(1)).save(any(Skill.class));
    }
} 