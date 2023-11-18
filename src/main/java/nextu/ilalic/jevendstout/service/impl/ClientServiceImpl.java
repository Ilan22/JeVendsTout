package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.ClientDTO;
import nextu.ilalic.jevendstout.mapper.ClientMapper;
import nextu.ilalic.jevendstout.repository.ClientRepository;
import nextu.ilalic.jevendstout.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addClient(ClientDTO client) {
        try {
            clientRepository.save(clientMapper.clientDTOToClient(client));
            return "Le client a bien été enregistré";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement du client : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addClients(List<ClientDTO> clients) {
        try {
            clientRepository.saveAll(clientMapper.clientDTOsToClients(clients));
            return "Les clients ont bien été enregistrés";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement de plusieurs clients : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientDTO> getAll() {
        return clientMapper.clientsToClientDTOs(clientRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientDTO getClientByNom(String nom) {
        return clientMapper.clientToClientDTO(clientRepository.findClientByNom(nom));
    }
}
