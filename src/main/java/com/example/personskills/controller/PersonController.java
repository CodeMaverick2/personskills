package com.example.personskills.controller;

import com.example.personskills.model.Person;
import com.example.personskills.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "listPersons";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @PostMapping("/add")
    public String addPerson(@Valid @ModelAttribute("person") Person person,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addPerson";
        }
        personService.addPerson(person);
        redirectAttributes.addFlashAttribute("successMessage", "Person added successfully!");
        return "redirect:/persons";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));
        return "updatePerson";
    }

    @PostMapping("/edit/{id}")
    public String updatePerson(@PathVariable Long id,
                             @Valid @ModelAttribute("person") Person person,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "updatePerson";
        }
        personService.updatePerson(id, person);
        redirectAttributes.addFlashAttribute("successMessage", "Person updated successfully!");
        return "redirect:/persons";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        personService.deletePerson(id);
        redirectAttributes.addFlashAttribute("successMessage", "Person deleted successfully!");
        return "redirect:/persons";
    }
} 