package ma.ensa.demo1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ma.ensa.demo1.repository.ProduitRepository;

@Controller
public class ProduitController {

    private ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/produits")
    public String afficherProduits(Model model) {

        model.addAttribute("produits", produitRepository.findAll());

        return "produits";
    }
}