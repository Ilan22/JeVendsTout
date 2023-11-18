package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_GENERATOR")
    @SequenceGenerator(name = "ARTICLE_GENERATOR", sequenceName = "ARTICLE_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String nom;

    @ManyToOne
    private Categorie categorie;

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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
