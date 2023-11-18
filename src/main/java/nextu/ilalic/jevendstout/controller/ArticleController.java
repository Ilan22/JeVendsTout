package nextu.ilalic.jevendstout.controller;

import nextu.ilalic.jevendstout.entity.DTO.ArticleDTO;
import nextu.ilalic.jevendstout.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Récuperer tous les articles.json en bdd
     *
     * @return liste des articles.json dto
     */
    @GetMapping
    public List<ArticleDTO> getArticles() {
        return articleService.getAll();
    }

    /**
     * Récuperer un article avec son nom
     *
     * @param nom nom de l'article
     * @return dto de l'article
     */
    @GetMapping("byNom")
    public ArticleDTO getArticleByNom(@RequestParam String nom) {
        return articleService.getByNom(nom);
    }

    /**
     * Récuperer les articles.json liés à une catégorie
     *
     * @param categorieId l'id de la catégorie
     * @return liste dto des articles.json d'une catégorie
     */
    @GetMapping("byCategorieId")
    public List<ArticleDTO> getArticlesByCategorieId(@RequestParam Long categorieId) {
        return articleService.getByCategorieId(categorieId);
    }

    /**
     * Enregistre des articles.json en bdd
     *
     * @param articleDTOS articles.json à enregistrer
     * @return message de confirmation
     */
    @PostMapping("add")
    public String addArticles(@RequestBody List<ArticleDTO> articleDTOS) {
        return articleService.addArticles(articleDTOS);
    }
}
