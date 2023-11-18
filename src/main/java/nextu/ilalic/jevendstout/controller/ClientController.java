package nextu.ilalic.jevendstout.controller;

import nextu.ilalic.jevendstout.entity.DTO.ClientDTO;
import nextu.ilalic.jevendstout.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Récuperer tous les clients en bdd
     *
     * @return liste des clients dto
     */
    @GetMapping("all")
    public List<ClientDTO> getClients() {
        return clientService.getAll();
    }

    /**
     * Récuperer un client avec son nom
     *
     * @param nom nom du client
     * @return client dto
     */
    @GetMapping("byName")
    public ClientDTO getClientByNom(@RequestParam String nom) {
        return clientService.getClientByNom(nom);
    }

    /**
     * Enregistre un client en bdd
     *
     * @param clientDTO client à enregistrer
     * @return message de confirmation
     */
    @PostMapping("add")
    public String addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    /**
     * Enregistre plusieurs clients en bdd
     *
     * @param clientDTOs clients à enregistrer
     * @return message de confirmation
     */
    @PostMapping("adds")
    public String addClients(@RequestBody List<ClientDTO> clientDTOs) {
        return clientService.addClients(clientDTOs);
    }
}
