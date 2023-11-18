package nextu.ilalic.jevendstout.entity.DTO;

import jakarta.persistence.Column;

import java.util.List;

public class PanierDTO {

    private Long id;

    @Column
    private float prixTotalHT;

    @Column
    private float prixTotalTTC;

    private boolean estValide;

    private List<ArticleDTO> articles;

    private ClientDTO client;

    private List<PrixPanierCategorieDTO> prixPanierCategories;

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

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<PrixPanierCategorieDTO> getPrixPanierCategories() {
        return prixPanierCategories;
    }

    public void setPrixPanierCategories(List<PrixPanierCategorieDTO> prixPanierCategories) {
        this.prixPanierCategories = prixPanierCategories;
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
