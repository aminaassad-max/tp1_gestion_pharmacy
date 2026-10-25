package ma.ensa.demo1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ma.ensa.demo1.repository.FournisseurRepository;


@Controller
public class FournisseurController {

    private FournisseurRepository fournisseurRepository;

    public FournisseurController(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("/fournisseurs")
    public String afficherFournisseurs(Model model){

        model.addAttribute("fournisseurs",
                fournisseurRepository.findAll());

        return "fournisseurs";
    }
}
