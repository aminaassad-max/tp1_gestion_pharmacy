package ma.ensa.demo1.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ma.ensa.demo1.entity.Categorie;
import ma.ensa.demo1.entity.DetailProduit;
import ma.ensa.demo1.entity.Fournisseur;
import ma.ensa.demo1.entity.Produit;
import ma.ensa.demo1.entity.Tag;
import ma.ensa.demo1.repository.CategorieRepository;
import ma.ensa.demo1.repository.DetailProduitRepository;
import ma.ensa.demo1.repository.FournisseurRepository;
import ma.ensa.demo1.repository.ProduitRepository;
import ma.ensa.demo1.repository.TagRepository;

@RestController
@RequestMapping("/api/produits")
public class ProduitRestController {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final FournisseurRepository fournisseurRepository;
    private final DetailProduitRepository detailProduitRepository;
    private final TagRepository tagRepository;

    public ProduitRestController(
            ProduitRepository produitRepository,
            CategorieRepository categorieRepository,
            FournisseurRepository fournisseurRepository,
            DetailProduitRepository detailProduitRepository,
            TagRepository tagRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.detailProduitRepository = detailProduitRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable Long id) {
        return findProduit(id);
    }

    @PostMapping
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        produit.setId(null);
        setExistingRelations(produit);
        Produit savedProduit = produitRepository.save(produit);
        return ResponseEntity.created(URI.create("/api/produits/" + savedProduit.getId()))
                .body(savedProduit);
    }

    @PutMapping("/{id}")
    public Produit modifierProduit(@PathVariable Long id, @RequestBody Produit nouveauProduit) {
        Produit produit = findProduit(id);
        setExistingRelations(nouveauProduit);

        produit.setNom(nouveauProduit.getNom());
        produit.setDescription(nouveauProduit.getDescription());
        produit.setPrix(nouveauProduit.getPrix());
        produit.setCodeBarre(nouveauProduit.getCodeBarre());
        produit.setDateExpiration(nouveauProduit.getDateExpiration());

        produit.setCategorie(nouveauProduit.getCategorie());
        produit.setFournisseur(nouveauProduit.getFournisseur());
        produit.setDetail(nouveauProduit.getDetail());
        produit.setTags(nouveauProduit.getTags());

        return produitRepository.save(produit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable Long id) {
        produitRepository.delete(findProduit(id));
        return ResponseEntity.noContent().build();
    }

    private Produit findProduit(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable"));
    }

    private void setExistingRelations(Produit produit) {
        if (produit.getCategorie() != null) {
            Long categorieId = requireRelationId(produit.getCategorie().getId(), "Catégorie");
            Categorie categorie = categorieRepository.findById(categorieId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie introuvable"));
            produit.setCategorie(categorie);
        }
        if (produit.getFournisseur() != null) {
            Long fournisseurId = requireRelationId(produit.getFournisseur().getId(), "Fournisseur");
            Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur introuvable"));
            produit.setFournisseur(fournisseur);
        }
        if (produit.getDetail() != null) {
            Long detailId = requireRelationId(produit.getDetail().getId(), "Détail");
            DetailProduit detail = detailProduitRepository.findById(detailId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Détail introuvable"));
            produit.setDetail(detail);
        }
        if (produit.getTags() != null) {
            List<Tag> tags = produit.getTags().stream()
                    .map(tag -> tagRepository.findById(requireRelationId(tag.getId(), "Tag"))
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag introuvable")))
                    .toList();
            produit.setTags(tags);
        }
    }

    private Long requireRelationId(Long id, String relation) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID manquant pour " + relation);
        }
        return id;
    }
}