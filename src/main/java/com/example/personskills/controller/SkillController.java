package com.example.personskills.controller;

import com.example.personskills.model.Skill;
import com.example.personskills.service.PersonService;
import com.example.personskills.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;
    private final PersonService personService;

    @Autowired
    public SkillController(SkillService skillService, PersonService personService) {
        this.skillService = skillService;
        this.personService = personService;
    }

    @GetMapping
    public String listSkills(Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        return "listSkills";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("skill", new Skill());
        model.addAttribute("persons", personService.getAllPersons());
        return "addSkill";
    }

    @PostMapping("/add")
    public String addSkill(@Valid @ModelAttribute("skill") Skill skill,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addSkill";
        }
        skillService.addSkill(skill);
        redirectAttributes.addFlashAttribute("successMessage", "Skill added successfully!");
        return "redirect:/skills";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("skill", skillService.getSkillById(id));
        model.addAttribute("persons", personService.getAllPersons());
        return "updateSkill";
    }

    @PostMapping("/edit/{id}")
    public String updateSkill(@PathVariable Long id,
                            @Valid @ModelAttribute("skill") Skill skill,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "updateSkill";
        }
        skillService.updateSkill(id, skill);
        redirectAttributes.addFlashAttribute("successMessage", "Skill updated successfully!");
        return "redirect:/skills";
    }

    @GetMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        skillService.deleteSkill(id);
        redirectAttributes.addFlashAttribute("successMessage", "Skill deleted successfully!");
        return "redirect:/skills";
    }
} 