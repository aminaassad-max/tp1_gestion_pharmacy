package ma.ensa.demo1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ma.ensa.demo1.entity.Fournisseur;
import ma.ensa.demo1.repository.FournisseurRepository;


@RestController
@RequestMapping("/api/fournisseurs")
@CrossOrigin("*")
public class FournisseurRestController {


    private final FournisseurRepository fournisseurRepository;


    public FournisseurRestController(
            FournisseurRepository fournisseurRepository){

        this.fournisseurRepository = fournisseurRepository;
    }



    @GetMapping
    public List<Fournisseur> getFournisseurs(){

        return fournisseurRepository.findAll();
    }



    @GetMapping("/{id}")
    public Fournisseur getFournisseur(@PathVariable Long id){

        return fournisseurRepository.findById(id)
                .orElse(null);
    }



    @PostMapping
    public Fournisseur ajouterFournisseur(
            @RequestBody Fournisseur fournisseur){

        fournisseur.setId(null);
        return fournisseurRepository.save(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur modifierFournisseur(
            @PathVariable Long id,
            @RequestBody Fournisseur nouveauFournisseur){

        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Fournisseur introuvable"));
        fournisseur.setNom(nouveauFournisseur.getNom());
        fournisseur.setContact(nouveauFournisseur.getContact());
        fournisseur.setAdresse(nouveauFournisseur.getAdresse());
        return fournisseurRepository.save(fournisseur);
    }


    @DeleteMapping("/{id}")
    public void supprimerFournisseur(@PathVariable Long id){

        fournisseurRepository.deleteById(id);
    }

}