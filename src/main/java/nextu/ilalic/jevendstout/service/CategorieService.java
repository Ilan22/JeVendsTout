package nextu.ilalic.jevendstout.service;

import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;

import java.util.List;

public interface CategorieService {
    /**
     * Enregistre des catégories en bdd
     *
     * @param categorieDTOs catégories à enregistrer
     * @return message de confirmation
     */
    String addCategories(List<CategorieDTO> categorieDTOs);

    /**
     * Récuperer toutes les catégories en bdd
     *
     * @return liste des catégories dto
     */
    List<CategorieDTO> getAll();

    /**
     * Récuperer une catégorie avec son id
     *
     * @param id id de la catégorie
     * @return categorie dto
     */
    CategorieDTO getById(Long id);

    /**
     * Récuperer une catégorie avec son nom
     *
     * @param nom nom de la catégorie
     * @return categorie dto
     */
    CategorieDTO getByNom(String nom);
}
