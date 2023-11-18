package nextu.ilalic.jevendstout.entity.DTO;

public class PrixPanierCategorieDTO {

    private Long id;

    private int prix;

    private CategorieDTO categorieDTO;

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

    public CategorieDTO getCategorieDTO() {
        return categorieDTO;
    }

    public void setCategorieDTO(CategorieDTO categorieDTO) {
        this.categorieDTO = categorieDTO;
    }
}
