package nextu.ilalic.jevendstout.repository;

import nextu.ilalic.jevendstout.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    /**
     * Récuperer tous les paniers d'un client
     * @param clientNom nom du cient
     * @return liste d'entité panier
     */
    List<Panier> findAllByClientNom(String clientNom);
}
