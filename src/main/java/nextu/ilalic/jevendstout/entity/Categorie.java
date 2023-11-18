package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIE_GENERATOR")
    @SequenceGenerator(name = "CATEGORIE_GENERATOR", sequenceName = "CATEGORIE_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private String nom;

    @ManyToMany(mappedBy = "categories")
    private List<Commercial> commerciaux = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Commercial> getCommerciaux() {
        return commerciaux;
    }

    public void setCommerciaux(List<Commercial> commerciaux) {
        this.commerciaux = commerciaux;
    }
}
