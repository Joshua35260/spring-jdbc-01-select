package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class SchoolController {

    private SchoolRepository repository = new SchoolRepository();

    @GetMapping("/schools")
    public String getAll(Model model) {

        model.addAttribute("schools", repository.findAll());

        return "school_get_all";
    }

    @GetMapping("/school")
    public String getById(Model model, @RequestParam Long id) {

        model.addAttribute("school", repository.findById(id));

        return "school_get";
    }

    @GetMapping("/schools/search")
    public String getByCountry(Model model, @RequestParam String country) {

        model.addAttribute("schools", repository.findByCountry(country));

        return "school_get_all";
    }

    @PostMapping("/school/create")
    public String postSchool(Model model,
    @RequestParam String name,
    @RequestParam Long capacity,
    @RequestParam String country
    ) {
        model.addAttribute("school", repository.save(name, capacity, country));
        return "school_get";
    }

    @GetMapping("/school/update")
    public String updateSchool(Model model, @RequestParam Long id) {

        model.addAttribute("school", repository.findById(id));

        return "school_update";
    }

    @PostMapping("/school/update")
    public String updateSchool(Model model,
    @RequestParam Long id,
    @RequestParam String name,
    @RequestParam Long capacity,
    @RequestParam String country
    ) {
        model.addAttribute("school", repository.update(id, name, capacity, country));
        return "school_get";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(Model model, @RequestParam Long id) {

        repository.deleteById(id);

        return "redirect:/schools";
    }
}
