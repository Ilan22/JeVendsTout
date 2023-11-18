package nextu.ilalic.jevendstout.repository;

import nextu.ilalic.jevendstout.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialRepository extends JpaRepository<Commercial, Long> {
    /**
     * Récuperer un commercial avec son nom
     * @param nom nom du commercial
     * @return entité commercial
     */
    Commercial findCommercialByNom(String nom);
}
