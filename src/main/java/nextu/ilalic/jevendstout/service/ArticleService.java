package nextu.ilalic.jevendstout.service;

import nextu.ilalic.jevendstout.entity.DTO.ArticleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    /**
     * Enregistre des articles.json en bdd
     *
     * @param articleDTOs articles.json à enregistrer
     * @return message de confirmation
     */
    String addArticles(List<ArticleDTO> articleDTOs);

    /**
     * Récuperer tous les articles.json en bdd
     *
     * @return liste des articles.json dto
     */
    List<ArticleDTO> getAll();

    /**
     * Récuperer un article avec son nom
     *
     * @param nom nom de l'article
     * @return dto de l'article
     */
    ArticleDTO getByNom(String nom);

    /**
     * Récuperer les articles.json liés à une catégorie
     *
     * @param categorieId l'id de la catégorie
     * @return liste dto des articles.json d'une catégorie
     */
    List<ArticleDTO> getByCategorieId(Long categorieId);
}
