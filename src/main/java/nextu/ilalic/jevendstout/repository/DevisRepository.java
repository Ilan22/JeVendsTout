package nextu.ilalic.jevendstout.repository;

import nextu.ilalic.jevendstout.entity.Categorie;
import nextu.ilalic.jevendstout.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    /**
     * Récuperer tous les devis d'un client
     * @param nomClient nom du client
     * @return liste d'entité devis
     */
    List<Devis> findAllByPanierClientNom(String nomClient);

    /**
     * Récuperer tous les devis d'un client dont le prixtotal du panier est supérieur à "prix"
     * @param prix minimum de prix à récuperer
     * @return liste d'entité devis
     */
    List<Devis> findAllByPanierPrixTotalHTGreaterThan(float prix);

    /**
     * Récuperer tous les devis dont un des articles.json correspond à une des catégorie du commercial
     * @param categories catégorie à rechercher
     * @return liste d'entité devis
     */
    @Query("SELECT DISTINCT d FROM Devis d " +
            "JOIN d.panier.articles a " +
            "WHERE a.categorie IN :categories " +
            "AND d.panier.prixTotalHT < 10000")
    List<Devis> findByCategoriesInCommercial(@Param("categories") List<Categorie> categories);
}
