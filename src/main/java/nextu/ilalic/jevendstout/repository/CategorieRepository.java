package nextu.ilalic.jevendstout.repository;

import nextu.ilalic.jevendstout.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByNom(String nom);
}
