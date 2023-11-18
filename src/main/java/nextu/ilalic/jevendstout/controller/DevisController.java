package nextu.ilalic.jevendstout.controller;

import nextu.ilalic.jevendstout.entity.DTO.DevisDTO;
import nextu.ilalic.jevendstout.entity.DTO.ValiderMultipleDevisDTO;
import nextu.ilalic.jevendstout.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devis/")
public class DevisController {

    @Autowired
    private DevisService devisService;

    /**
     * Récuperer tous les devis
     *
     * @return liste devis dto
     */
    @GetMapping("all")
    public List<DevisDTO> getDevis() {
        return devisService.getAll();
    }

    /**
     * Récuperer un devis avec à son id
     *
     * @param idDevis id du devis
     * @return dto du devis
     */
    @GetMapping("byId")
    public DevisDTO getDevisById(@RequestParam Long idDevis) {
        return devisService.getById(idDevis);
    }

    /**
     * Récuperer les devis d'un client
     *
     * @param nomClient nom du client dont on cherche les devis
     * @return liste devis dto
     */
    @GetMapping("byNomClient")
    public List<DevisDTO> getDevisByNomClient(@RequestParam String nomClient) {
        return devisService.getByNomClient(nomClient);
    }

    /**
     * Récuperer les devis du directeur : prixTotal > 10000€
     *
     * @return list devis dto
     */
    @GetMapping("forDirector")
    public List<DevisDTO> getDevisForDirector() {
        return devisService.getDevisForDirector();
    }

    /**
     * Récuperer les devis d'un commercial : prixTotal < 10000€
     * et le prixTotal des catégories qu'il gère est le plus elevé
     *
     * @param idCommercial id du commercial
     * @return liste devis dto
     */
    @GetMapping("forCommercial")
    public List<DevisDTO> getDevisForCommercial(@RequestParam Long idCommercial) {
        return devisService.getDevisForCommercial(idCommercial);
    }

    /**
     * Valide le devis
     *
     * @param idDevis id du devis à valider
     * @return message de confirmation
     */
    @PutMapping("valider")
    public String validerDevis(@RequestBody Long idDevis) {
        return devisService.validerDevis(idDevis);
    }

    /**
     * Valider plusieurs devis
     *
     * @param validerMultipleDevisDTO dto contenant les ids des devis à valider
     *                                ainsi que l'id du commercial qui veut les valider
     *                                si il n'est pas directeur il n'a pas le droit d'en valider plusieurs
     * @return message de confirmation
     */
    @PutMapping("validerMultiple")
    public String validerMultipleDevis(@RequestBody ValiderMultipleDevisDTO validerMultipleDevisDTO) {
        return devisService.validerMutlipleDevis(validerMultipleDevisDTO);
    }

    /**
     * Payer un devis
     *
     * @param idDevis id du devis à payer
     * @return message de confirmation
     */
    @PutMapping("payer")
    public String payerDevis(@RequestBody Long idDevis) {
        return devisService.payerDevis(idDevis);
    }
}
