package nextu.ilalic.jevendstout.entity.DTO;

import java.util.List;

public class AddArticlesDTO {
    List<String> nomArticles;

    Long idPanier;

    public List<String> getNomArticles() {
        return nomArticles;
    }

    public void setNomArticles(List<String> nomArticles) {
        this.nomArticles = nomArticles;
    }

    public Long getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Long idPanier) {
        this.idPanier = idPanier;
    }
}
