package nextu.ilalic.jevendstout.service;

import nextu.ilalic.jevendstout.entity.DTO.AddCategoriesToCommercialDTO;
import nextu.ilalic.jevendstout.entity.DTO.CommercialDTO;

import java.util.List;

public interface CommercialService {
    /**
     * Enregistre un commercial en bdd
     *
     * @param commercialDTO commercial à enregistrer
     * @return message de confirmation
     */
    String addCommercial(CommercialDTO commercialDTO);

    /**
     * Enregistre un commercial en bdd
     *
     * @param commercialDTO commercial à enregistrer
     * @return message de confirmation
     */
    String addCommerciaux(List<CommercialDTO> commercialDTOs);

    /**
     * Récuperer tous les commerciaux en bdd
     *
     * @return liste des commerciaux dto
     */
    List<CommercialDTO> getAll();

    /**
     * Récuperer un commercial avec son nom
     *
     * @param nom nom du commercial
     * @return commercial dto
     */
    CommercialDTO getCommercialByNom(String nom);

    /**
     * Récuperer un commercial avec son id
     *
     * @param id id du commercial
     * @return commercial dto
     */
    CommercialDTO getCommercialById(Long id);

    /**
     * Ajoute des catégories au commercial
     *
     * @param addCategoriesToCommercialDTO dto contenant l'id du commercial ainsi que les ids des catégories
     * @return message de confirmation
     */
    String addCategories(AddCategoriesToCommercialDTO addCategoriesToCommercialDTO);
}
