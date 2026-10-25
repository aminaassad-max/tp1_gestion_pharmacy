package ma.ensa.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.demo1.entity.Produit;

public interface ProduitRepository 
        extends JpaRepository<Produit, Long> {

}
