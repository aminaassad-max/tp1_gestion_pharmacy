package ma.ensa.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.demo1.entity.DetailProduit;

public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {

}
