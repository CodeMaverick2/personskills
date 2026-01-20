package com.example.personskills.controller;

import com.example.personskills.model.Person;
import com.example.personskills.service.PersonService;
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
 * Controller for managing Person entities.
 * Handles web requests for person CRUD operations.
 */
@Controller
@RequestMapping("/persons")
public final class PersonController {

    /**
     * Service for person business logic.
     */
    private final PersonService personService;

    /**
     * Constructor for PersonController.
     *
     * @param service the person service
     */
    @Autowired
    public PersonController(final PersonService service) {
        this.personService = service;
    }

    /**
     * Displays list of all persons.
     *
     * @param model the model for view
     * @return view name
     */
    @GetMapping
    public String listPersons(final Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "listPersons";
    }

    /**
     * Shows form to add a new person.
     *
     * @param model the model for view
     * @return view name
     */
    @GetMapping("/add")
    public String showAddForm(final Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    /**
     * Processes form submission to add a new person.
     *
     * @param person             the person to add
     * @param result             validation result
     * @param redirectAttributes attributes for redirect
     * @return redirect path
     */
    @PostMapping("/add")
    public String addPerson(
            @Valid @ModelAttribute("person") final Person person,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addPerson";
        }
        personService.addPerson(person);
        redirectAttributes.addFlashAttribute("successMessage",
                "Person added successfully!");
        return "redirect:/persons";
    }

    /**
     * Shows form to edit an existing person.
     *
     * @param id    the person ID
     * @param model the model for view
     * @return view name
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable final Long id,
            final Model model) {
        model.addAttribute("person", personService.getPersonById(id));
        return "updatePerson";
    }

    /**
     * Processes form submission to update a person.
     *
     * @param id                 the person ID
     * @param person             the updated person data
     * @param result             validation result
     * @param redirectAttributes attributes for redirect
     * @return redirect path
     */
    @PostMapping("/edit/{id}")
    public String updatePerson(
            @PathVariable final Long id,
            @Valid @ModelAttribute("person") final Person person,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "updatePerson";
        }
        personService.updatePerson(id, person);
        redirectAttributes.addFlashAttribute("successMessage",
                "Person updated successfully!");
        return "redirect:/persons";
    }

    /**
     * Deletes a person by ID.
     *
     * @param id                 the person ID
     * @param redirectAttributes attributes for redirect
     * @return redirect path
     */
    @GetMapping("/delete/{id}")
    public String deletePerson(
            @PathVariable final Long id,
            final RedirectAttributes redirectAttributes) {
        personService.deletePerson(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Person deleted successfully!");
        return "redirect:/persons";
    }
}
