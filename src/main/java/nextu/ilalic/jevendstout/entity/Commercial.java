package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "commercial")
public class Commercial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMERCIAL_GENERATOR")
    @SequenceGenerator(name = "COMMERCIAL_GENERATOR", sequenceName = "COMMERCIAL_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private String nom;

    @Column
    private boolean estDirecteur;

    @ManyToMany
    private List<Categorie> categories;

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

    public boolean isEstDirecteur() {
        return estDirecteur;
    }

    public void setEstDirecteur(boolean estDirecteur) {
        this.estDirecteur = estDirecteur;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }
}
