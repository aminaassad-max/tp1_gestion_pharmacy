package ma.ensa.demo1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ma.ensa.demo1.repository.DetailProduitRepository;


@Controller
public class DetailProduitController {

    private DetailProduitRepository detailProduitRepository;

    public DetailProduitController(DetailProduitRepository detailProduitRepository) {
        this.detailProduitRepository = detailProduitRepository;
    }

    @GetMapping("/details")
    public String afficherDetails(Model model){

        model.addAttribute("details", detailProduitRepository.findAll());

        return "details";
    }
}