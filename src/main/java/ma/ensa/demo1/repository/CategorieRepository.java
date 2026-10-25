package ma.ensa.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.demo1.entity.Categorie;

public interface CategorieRepository 
        extends JpaRepository<Categorie, Long> {

}
