package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "panier")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PANIER_GENERATOR")
    @SequenceGenerator(name = "PANIER_GENERATOR", sequenceName = "PANIER_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private float prixTotalHT;

    @Column
    private float prixTotalTTC;

    @Column
    private boolean estValide;

    @ManyToMany
    private List<Article> articles;

    @ManyToOne
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PrixPanierCategorie> prixPanierCategorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<PrixPanierCategorie> getPrixPanierCategorie() {
        return prixPanierCategorie;
    }

    public void setPrixPanierCategorie(List<PrixPanierCategorie> prixPanierCategorie) {
        this.prixPanierCategorie = prixPanierCategorie;
    }

    public float getPrixTotalHT() {
        return prixTotalHT;
    }

    public void setPrixTotalHT(float prixTotalHT) {
        this.prixTotalHT = prixTotalHT;
    }

    public float getPrixTotalTTC() {
        return prixTotalTTC;
    }

    public void setPrixTotalTTC(float prixTotalTTC) {
        this.prixTotalTTC = prixTotalTTC;
    }
}
