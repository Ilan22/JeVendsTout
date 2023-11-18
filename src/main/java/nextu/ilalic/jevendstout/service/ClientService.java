package nextu.ilalic.jevendstout.service;

import nextu.ilalic.jevendstout.entity.DTO.ClientDTO;

import java.util.List;

public interface ClientService {
    /**
     * Enregistre un client en bdd
     *
     * @param client client à enregistrer
     * @return message de confirmation
     */
    String addClient(ClientDTO client);

    /**
     * Enregistre plusieurs clients en bdd
     *
     * @param clients clients à enregistrer
     * @return message de confirmation
     */
    String addClients(List<ClientDTO> clients);

    /**
     * Récuperer tous les clients en bdd
     *
     * @return liste des clients dto
     */
    List<ClientDTO> getAll();

    /**
     * Récuperer un client avec son nom
     *
     * @param nom nom du client
     * @return client dto
     */
    ClientDTO getClientByNom(String nom);
}
