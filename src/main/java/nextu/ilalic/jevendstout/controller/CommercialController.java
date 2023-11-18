package nextu.ilalic.jevendstout.controller;

import nextu.ilalic.jevendstout.entity.DTO.AddCategoriesToCommercialDTO;
import nextu.ilalic.jevendstout.entity.DTO.CommercialDTO;
import nextu.ilalic.jevendstout.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercial/")
public class CommercialController {

    @Autowired
    private CommercialService commercialService;

    /**
     * Récuperer tous les commerciaux en bdd
     *
     * @return liste des commerciaux dto
     */
    @GetMapping("all")
    public List<CommercialDTO> getCommercials() {
        return commercialService.getAll();
    }

    /**
     * Récuperer un commercial avec son nom
     *
     * @param nom nom du commercial
     * @return commercial dto
     */
    @GetMapping("byName")
    public CommercialDTO getCommercialByNom(@RequestParam String nom) {
        return commercialService.getCommercialByNom(nom);
    }

    /**
     * Enregistre un commercial en bdd
     *
     * @param commercialDTO commercial à enregistrer
     * @return message de confirmation
     */
    @PostMapping("add")
    public String addCommercial(@RequestBody CommercialDTO commercialDTO) {
        return commercialService.addCommercial(commercialDTO);
    }

    /**
     * Enregistre plusieurs commerciaux en bdd
     *
     * @param commercialDTOs commerciaux à enregistrer
     * @return message de confirmation
     */
    @PostMapping("adds")
    public String addCommerciaux(@RequestBody List<CommercialDTO> commercialDTOs) {
        return commercialService.addCommerciaux(commercialDTOs);
    }

    /**
     * Ajoute des catégories au commercial
     *
     * @param addCategoriesToCommercialDTO dto contenant l'id du commercial ainsi que les ids des catégories
     * @return message de confirmation
     */
    @PutMapping("addCategories")
    public String addCategories(@RequestBody AddCategoriesToCommercialDTO addCategoriesToCommercialDTO) {
        return commercialService.addCategories(addCategoriesToCommercialDTO);
    }
}
