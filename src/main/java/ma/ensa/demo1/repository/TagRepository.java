package ma.ensa.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.demo1.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
