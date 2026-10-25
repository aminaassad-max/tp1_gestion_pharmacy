package ma.ensa.demo1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Commande {

    @Id
    private Long id;

    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}