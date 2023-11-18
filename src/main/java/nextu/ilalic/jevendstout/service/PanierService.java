package nextu.ilalic.jevendstout.service;

import nextu.ilalic.jevendstout.entity.DTO.PanierDTO;

import java.util.List;

public interface PanierService {
    /**
     * Enregistre un panier en bdd
     *
     * @param panierDTO panier à enregistrer
     * @return message de confirmation
     */
    String addPanier(PanierDTO panierDTO);

    /**
     * Ajouter des articles.json au panier
     *
     * @param articlesNoms nom des articles.json à ajouter
     * @param idPanier     id du panier
     * @return message de confirmation
     */
    String addArticles(List<String> articlesNoms, Long idPanier);

    /**
     * Valider le panier
     *
     * @param idPanier id du panier
     * @return message de confirmation
     */
    String validerPanier(Long idPanier);

    /**
     * Effectuer la tarification du panier
     *
     * @param idPanier id du panier
     * @return message de confirmation
     */
    String setTarif(Long idPanier);

    /**
     * Récuperer tous les paniers
     *
     * @return list panier dto
     */
    List<PanierDTO> getAll();

    /**
     * Récuperer un panier avec son id
     *
     * @param idPanier id du panier
     * @return panier dto
     */
    PanierDTO getByIdPanier(Long idPanier);

    /**
     * Récuperer un panier avec le nom de son client
     *
     * @param nomClient nom du client
     * @return panier dto
     */
    List<PanierDTO> getByNomClient(String nomClient);
}
