package nextu.ilalic.jevendstout.controller;

import nextu.ilalic.jevendstout.entity.DTO.AddArticlesDTO;
import nextu.ilalic.jevendstout.entity.DTO.PanierDTO;
import nextu.ilalic.jevendstout.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/panier/")
public class PanierController {

    @Autowired
    private PanierService panierService;

    /**
     * Récuperer tous les paniers
     *
     * @return list panier dto
     */
    @GetMapping("all")
    public List<PanierDTO> getPaniers() {
        return panierService.getAll();
    }

    /**
     * Récuperer un panier avec son id
     *
     * @param idPanier id du panier
     * @return panier dto
     */
    @GetMapping("byId")
    public PanierDTO getPanierById(@RequestParam Long idPanier) {
        return panierService.getByIdPanier(idPanier);
    }

    /**
     * Récuperer un panier avec le nom de son client
     *
     * @param nomClient nom du client
     * @return panier dto
     */
    @GetMapping("byNomCient")
    public List<PanierDTO> getPanierByNomClient(@RequestParam String nomClient) {
        return panierService.getByNomClient(nomClient);
    }

    /**
     * Enregistre un panier en bdd
     *
     * @param panierDTO panier à enregistrer
     * @return message de confirmation
     */
    @PostMapping("add")
    public String addPanier(@RequestBody PanierDTO panierDTO) {
        return panierService.addPanier(panierDTO);
    }

    /**
     * Ajouter des articles.json au panier
     *
     * @param addArticlesDTO dto contenant les noms des articles.json ainsi que l'id du panier
     * @return message de confirmation
     */
    @PutMapping("addArticles")
    public String addArticles(@RequestBody AddArticlesDTO addArticlesDTO) {
        return panierService.addArticles(addArticlesDTO.getNomArticles(), addArticlesDTO.getIdPanier());
    }

    /**
     * Valider le panier
     *
     * @param idPanier id du panier
     * @return message de confirmation
     */
    @PutMapping("valider")
    public String validerPanier(@RequestBody Long idPanier) {
        return panierService.validerPanier(idPanier);
    }

    /**
     * Effectuer la tarification du panier
     *
     * @param idPanier id du panier
     * @return message de confirmation
     */
    @PutMapping("setTarif")
    public String setTarif(@RequestBody Long idPanier) {
        return panierService.setTarif(idPanier);
    }
}
