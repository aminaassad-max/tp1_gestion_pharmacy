package ma.ensa.demo1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ma.ensa.demo1.entity.Categorie;
import ma.ensa.demo1.repository.CategorieRepository;


@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategorieRestController {


    private final CategorieRepository categorieRepository;


    public CategorieRestController(CategorieRepository categorieRepository){

        this.categorieRepository = categorieRepository;
    }



    @GetMapping
    public List<Categorie> getCategories(){

        return categorieRepository.findAll();
    }


    @GetMapping("/{id}")
    public Categorie getCategorie(@PathVariable Long id){

        return categorieRepository.findById(id)
                .orElse(null);
    }


    @PostMapping
    public Categorie ajouterCategorie(
            @RequestBody Categorie categorie){

        categorie.setId(null);
        return categorieRepository.save(categorie);
    }

    @PutMapping("/{id}")
    public Categorie modifierCategorie(
            @PathVariable Long id,
            @RequestBody Categorie nouvelleCategorie){

        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Catégorie introuvable"));
        categorie.setNom(nouvelleCategorie.getNom());
        categorie.setDescription(nouvelleCategorie.getDescription());
        return categorieRepository.save(categorie);
    }


    @DeleteMapping("/{id}")
    public void supprimerCategorie(@PathVariable Long id){

        categorieRepository.deleteById(id);
    }

}