package nextu.ilalic.jevendstout.entity.DTO;

import java.util.List;

public class CommercialDTO {

    private Long id;

    private String nom;

    private boolean estDirecteur;

    private List<CategorieDTO> categorieDTOS;

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

    public List<CategorieDTO> getCategorieDTOS() {
        return categorieDTOS;
    }

    public void setCategorieDTOS(List<CategorieDTO> categorieDTOS) {
        this.categorieDTOS = categorieDTOS;
    }
}
