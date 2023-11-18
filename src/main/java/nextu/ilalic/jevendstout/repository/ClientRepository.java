package nextu.ilalic.jevendstout.repository;

import nextu.ilalic.jevendstout.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Récuperer un client avec son nom
     * @param nom nom du client
     * @return entité client
     */
    Client findClientByNom(String nom);
}
