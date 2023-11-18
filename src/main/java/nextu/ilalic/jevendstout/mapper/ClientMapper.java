package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.Client;
import nextu.ilalic.jevendstout.entity.DTO.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientMapper {

    public List<ClientDTO> clientsToClientDTOs(List<Client> clients) {
        if (clients == null) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>(clients.size());
        for (Client client : clients) {
            list.add(clientToClientDTO(client));
        }

        return list;
    }

    public List<Client> clientDTOsToClients(List<ClientDTO> clientDTOs) {
        if (clientDTOs == null) {
            return null;
        }

        List<Client> list = new ArrayList<Client>(clientDTOs.size());
        for (ClientDTO clientDTO : clientDTOs) {
            list.add(clientDTOToClient(clientDTO));
        }

        return list;
    }

    public Client clientDTOToClient(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();

        client.setId(clientDTO.getId());
        client.setNom(clientDTO.getNom());

        return client;
    }

    public ClientDTO clientToClientDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setNom(client.getNom());

        return clientDTO;
    }
}
