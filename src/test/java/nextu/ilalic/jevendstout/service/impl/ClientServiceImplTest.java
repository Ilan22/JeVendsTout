package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.Client;
import nextu.ilalic.jevendstout.entity.DTO.ClientDTO;
import nextu.ilalic.jevendstout.mapper.ClientMapper;
import nextu.ilalic.jevendstout.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientMapper clientMapper;

    @Test
    public void testAddClients_Success() {
        // Given
        List<ClientDTO> clientDTOS = new ArrayList<>();

        // When
        when(clientMapper.clientDTOsToClients(clientDTOS)).thenReturn(Collections.emptyList());

        // Then
        String result = clientService.addClients(clientDTOS);

        verify(clientRepository, times(1)).saveAll(any());
        assertEquals("Les clients ont bien été enregistrés", result);
    }

    @Test
    public void testAddClients_Failure() {
        // Given
        List<ClientDTO> clientDTOS = new ArrayList<>();

        // When
        when(clientMapper.clientDTOsToClients(clientDTOS)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = clientService.addClients(clientDTOS);

        verify(clientRepository, never()).saveAll(any());
        assertEquals("Une erreur est survenue lors de l'enregistrement de plusieurs clients : Mocked exception", result);
    }

    @Test
    public void testAddClient_Success() {
        // Given
        ClientDTO clientDTO = new ClientDTO();

        // When
        when(clientMapper.clientDTOToClient(clientDTO)).thenReturn(null);

        // Then
        String result = clientService.addClient(clientDTO);

        verify(clientRepository, times(1)).save(any());
        assertEquals("Le client a bien été enregistré", result);
    }

    @Test
    public void testAddClient_Failure() {
        // Given
        ClientDTO clientDTO = new ClientDTO();

        // When
        when(clientMapper.clientDTOToClient(clientDTO)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = clientService.addClient(clientDTO);

        verify(clientRepository, never()).save(any());
        assertEquals("Une erreur est survenue lors de l'enregistrement du client : Mocked exception", result);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());

        List<ClientDTO> clientDTOS = new ArrayList<>();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("TestClient");
        clientDTOS.add(clientDTO);

        // When
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientMapper.clientsToClientDTOs(clients)).thenReturn(clientDTOS);

        List<ClientDTO> result = clientService.getAll();

        // Then
        assertEquals(clientDTOS.get(0).getNom(), result.get(0).getNom());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetByNom() {
        // Given
        String nom = "TestClient";
        Client client = new Client();
        client.setNom(nom);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom(nom);

        // When
        when(clientRepository.findClientByNom(nom)).thenReturn(client);
        when(clientMapper.clientToClientDTO(client)).thenReturn(clientDTO);

        ClientDTO result = clientService.getClientByNom(nom);

        // Then
        assertEquals(clientDTO.getNom(), result.getNom());
        verify(clientRepository, times(1)).findClientByNom(nom);
    }
}
