package nextu.ilalic.jevendstout.repository;

import nextu.ilalic.jevendstout.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Récuperer un article avec son nom
     * @param nom nom de l'article
     * @return entité article
     */
    Article findByNom(String nom);

    /**
     * Récuperer les articles.json d'une catégories
     * @param categorieId id de la catégorie
     * @return liste d'entité article
     */
    List<Article> findAllByCategorieId(Long categorieId);
}
