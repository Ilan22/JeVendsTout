package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prix_panier_categorie")
public class PrixPanierCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIX_PANIER_CATEGORIE_GENERATOR")
    @SequenceGenerator(name = "PRIX_PANIER_CATEGORIE_GENERATOR", sequenceName = "PRIX_PANIER_CATEGORIE_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private int prix;

    @ManyToOne
    private Categorie categorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
