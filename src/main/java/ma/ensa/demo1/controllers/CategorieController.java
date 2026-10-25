package ma.ensa.demo1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ma.ensa.demo1.repository.CategorieRepository;

@Controller
public class CategorieController {

    private CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/categories")
    public String afficherCategories(Model model) {

        model.addAttribute("categories", categorieRepository.findAll());

        return "categories";
    }
}
