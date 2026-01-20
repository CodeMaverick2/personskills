package com.example.personskills.controller;

import com.example.personskills.model.Skill;
import com.example.personskills.service.PersonService;
import com.example.personskills.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for managing Skill entities.
 * Handles web requests for skill CRUD operations.
 */
@Controller
@RequestMapping("/skills")
public final class SkillController {

    /**
     * Service for skill business logic.
     */
    private final SkillService skillService;

    /**
     * Service for person business logic.
     */
    private final PersonService personService;

    /**
     * Constructor for SkillController.
     *
     * @param skillSvc  the skill service
     * @param personSvc the person service
     */
    @Autowired
    public SkillController(final SkillService skillSvc,
            final PersonService personSvc) {
        this.skillService = skillSvc;
        this.personService = personSvc;
    }

    /**
     * Displays list of all skills.
     *
     * @param model the model for view
     * @return view name
     */
    @GetMapping
    public String listSkills(final Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        return "listSkills";
    }

    /**
     * Shows form to add a new skill.
     *
     * @param model the model for view
     * @return view name
     */
    @GetMapping("/add")
    public String showAddForm(final Model model) {
        model.addAttribute("skill", new Skill());
        model.addAttribute("persons", personService.getAllPersons());
        return "addSkill";
    }

    /**
     * Processes form submission to add a new skill.
     *
     * @param skill              the skill to add
     * @param result             validation result
     * @param redirectAttributes attributes for redirect
     * @return redirect path
     */
    @PostMapping("/add")
    public String addSkill(
            @Valid @ModelAttribute("skill") final Skill skill,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addSkill";
        }
        skillService.addSkill(skill);
        redirectAttributes.addFlashAttribute("successMessage",
                "Skill added successfully!");
        return "redirect:/skills";
    }

    /**
     * Shows form to edit an existing skill.
     *
     * @param id    the skill ID
     * @param model the model for view
     * @return view name
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable final Long id,
            final Model model) {
        model.addAttribute("skill", skillService.getSkillById(id));
        model.addAttribute("persons", personService.getAllPersons());
        return "updateSkill";
    }

    /**
     * Processes form submission to update a skill.
     *
     * @param id                 the skill ID
     * @param skill              the updated skill data
     * @param result             validation result
     * @param redirectAttributes attributes for redirect
     * @return redirect path
     */
    @PostMapping("/edit/{id}")
    public String updateSkill(
            @PathVariable final Long id,
            @Valid @ModelAttribute("skill") final Skill skill,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "updateSkill";
        }
        skillService.updateSkill(id, skill);
        redirectAttributes.addFlashAttribute("successMessage",
                "Skill updated successfully!");
        return "redirect:/skills";
    }

    /**
     * Deletes a skill by ID.
     *
     * @param id                 the skill ID
     * @param redirectAttributes attributes for redirect
     * @return redirect path
     */
    @GetMapping("/delete/{id}")
    public String deleteSkill(
            @PathVariable final Long id,
            final RedirectAttributes redirectAttributes) {
        skillService.deleteSkill(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Skill deleted successfully!");
        return "redirect:/skills";
    }
}
