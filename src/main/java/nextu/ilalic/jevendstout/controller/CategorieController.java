package nextu.ilalic.jevendstout.controller;

import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;
import nextu.ilalic.jevendstout.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorie/")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    /**
     * Récuperer toutes les catégories en bdd
     *
     * @return liste des catégories dto
     */
    @GetMapping("all")
    public List<CategorieDTO> getCategories() {
        return categorieService.getAll();
    }

    /**
     * Enregistre des catégories en bdd
     *
     * @param categorieDTOs catégories à enregistrer
     * @return message de confirmation
     */
    @PostMapping("add")
    public String addCategories(@RequestBody List<CategorieDTO> categorieDTOs) {
        return categorieService.addCategories(categorieDTOs);
    }
}
