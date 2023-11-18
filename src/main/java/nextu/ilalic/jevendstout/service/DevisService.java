package nextu.ilalic.jevendstout.service;

import nextu.ilalic.jevendstout.entity.DTO.DevisDTO;
import nextu.ilalic.jevendstout.entity.DTO.ValiderMultipleDevisDTO;

import java.util.List;

public interface DevisService {
    /**
     * Enregistre un devis en bdd
     *
     * @param devisDTO devis à enregistrer
     * @return message de confirmation
     */
    String addDevis(DevisDTO devisDTO);

    /**
     * Valide le devis
     *
     * @param idDevis id du devis à valider
     * @return message de confirmation
     */
    String validerDevis(Long idDevis);

    /**
     * Valider plusieurs devis
     *
     * @param validerMultipleDevisDTO dto contenant les ids des devis à valider
     *                                ainsi que l'id du commercial qui veut les valider
     *                                si il n'est pas directeur il n'a pas le droit d'en valider plusieurs
     * @return message de confirmation
     */
    String validerMutlipleDevis(ValiderMultipleDevisDTO validerMultipleDevisDTO);

    /**
     * Récuperer tous les devis
     *
     * @return liste devis dto
     */
    List<DevisDTO> getAll();

    /**
     * Récuperer un devis avec à son id
     *
     * @param id id du devis
     * @return dto du devis
     */
    DevisDTO getById(Long id);

    /**
     * Récuperer les devis d'un client
     *
     * @param nomClient nom du client dont on cherche les devis
     * @return liste devis dto
     */
    List<DevisDTO> getByNomClient(String nomClient);

    /**
     * Payer un devis
     *
     * @param id id du devis à payer
     * @return message de confirmation
     */
    String payerDevis(Long id);

    /**
     * Récuperer les devis du directeur : prixTotal > 10000€
     *
     * @return list devis dto
     */
    List<DevisDTO> getDevisForDirector();

    /**
     * Récuperer les devis d'un commercial : prixTotal < 10000€
     * et le prixTotal des catégories qu'il gère est le plus elevé
     *
     * @param id id du commercial
     * @return liste devis dto
     */
    List<DevisDTO> getDevisForCommercial(Long id);
}
