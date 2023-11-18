package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.Client;
import nextu.ilalic.jevendstout.entity.DTO.*;
import nextu.ilalic.jevendstout.entity.Devis;
import nextu.ilalic.jevendstout.entity.Panier;
import nextu.ilalic.jevendstout.mapper.CategorieMapper;
import nextu.ilalic.jevendstout.mapper.DevisMapper;
import nextu.ilalic.jevendstout.repository.DevisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DevisServiceImplTest {
    @Mock
    private DevisRepository devisRepository;

    @InjectMocks
    private DevisServiceImpl devisService;

    @Mock
    private DevisMapper devisMapper;

    @Mock
    private CommercialServiceImpl commercialService;

    @Mock
    private CategorieMapper categorieMapper;

    @Mock
    private BanqueServiceImpl banqueService;

    @Test
    public void testAddDevis_Success() {
        // Given
        DevisDTO devisDTO = new DevisDTO();

        // When
        when(devisMapper.devisDTOToDevis(devisDTO)).thenReturn(null);

        // Then
        String result = devisService.addDevis(devisDTO);

        verify(devisRepository, times(1)).save(any());
        assertEquals("Le devis a bien été enregistré", result);
    }

    @Test
    public void testAddClient_Failure() {
        // Given
        DevisDTO devisDTO = new DevisDTO();

        // When
        when(devisMapper.devisDTOToDevis(devisDTO)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = devisService.addDevis(devisDTO);

        verify(devisRepository, never()).save(any());
        assertEquals("ne erreur est survenue lors de la validation d'un devis : Mocked exception", result);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Devis> devis = new ArrayList<>();
        devis.add(new Devis());

        List<DevisDTO> devisDTOS = new ArrayList<>();
        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setId(1L);
        devisDTOS.add(devisDTO);

        // When
        when(devisRepository.findAll()).thenReturn(devis);
        when(devisMapper.devissToDevisDTOs(devis)).thenReturn(devisDTOS);

        List<DevisDTO> result = devisService.getAll();

        // Then
        assertEquals(devisDTOS.get(0).getId(), result.get(0).getId());
        verify(devisRepository, times(1)).findAll();
    }

    @Test
    public void testGetByNom() {
        // Given
        String nomClient = "TestClient";

        List<Devis> deviss = new ArrayList<>();
        Devis devis = new Devis();
        Panier panier = new Panier();
        Client client = new Client();
        client.setNom(nomClient);
        panier.setClient(client);
        devis.setPanier(panier);
        deviss.add(devis);

        List<DevisDTO> devisDTOS = new ArrayList<>();
        DevisDTO devisDTO = new DevisDTO();
        PanierDTO panierDTO = new PanierDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom(nomClient);
        panierDTO.setClient(clientDTO);
        devisDTO.setPanier(panierDTO);
        devisDTOS.add(devisDTO);

        // When
        when(devisRepository.findAllByPanierClientNom(nomClient)).thenReturn(deviss);
        when(devisMapper.devissToDevisDTOs(deviss)).thenReturn(devisDTOS);

        List<DevisDTO> result = devisService.getByNomClient(nomClient);

        // Then
        assertEquals(devisDTOS.get(0).getPanier().getClient().getNom(), result.get(0).getPanier().getClient().getNom());
        verify(devisRepository, times(1)).findAllByPanierClientNom(nomClient);
    }

    @Test
    public void testGetById() {
        // Given
        Long id = 1L;
        Devis devis = new Devis();
        devis.setId(id);

        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setId(id);

        // When
        when(devisRepository.findById(id)).thenReturn(Optional.of(devis));
        when(devisMapper.devisToDevisDTO(devis)).thenReturn(devisDTO);

        DevisDTO result = devisService.getById(id);

        // Then
        assertEquals(devisDTO.getId(), result.getId());
        verify(devisRepository, times(1)).findById(id);
    }

    @Test
    public void testGetDevisForDirector() {
        // Given
        PanierDTO panierDTO = new PanierDTO();
        panierDTO.setPrixTotalHT(50000);

        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setPanier(panierDTO);
        List<DevisDTO> devisDTOS = new ArrayList<>();
        devisDTOS.add(devisDTO);

        // When
        when(devisRepository.findAllByPanierPrixTotalHTGreaterThan(10000)).thenReturn(Collections.emptyList());
        when(devisMapper.devissToDevisDTOs(any())).thenReturn(devisDTOS);

        // Then
        List<DevisDTO> result = devisService.getDevisForDirector();

        assertTrue(result.get(0).getPanier().getPrixTotalHT() > 10000);
    }

    @Test
    public void testGetDevisForCommercial() {
        // Given
        Long commercialId = 1L;

        CommercialDTO commercialDTO = new CommercialDTO();
        CategorieDTO categorieDTO1 = new CategorieDTO();
        categorieDTO1.setId(1L);
        categorieDTO1.setNom("TestCategorie1");
        List<CategorieDTO> categorieDTOList = new ArrayList<>();
        categorieDTOList.add(categorieDTO1);
        commercialDTO.setCategorieDTOS(categorieDTOList);

        PanierDTO panierDTO = new PanierDTO();
        panierDTO.setPrixTotalHT(1000);

        List<PrixPanierCategorieDTO> prixPanierCategorieDTOS = new ArrayList<>();
        PrixPanierCategorieDTO prixPanierCategorieDTO1 = new PrixPanierCategorieDTO();
        prixPanierCategorieDTO1.setId(1L);
        prixPanierCategorieDTO1.setPrix(10);
        prixPanierCategorieDTO1.setCategorieDTO(categorieDTO1);

        PrixPanierCategorieDTO prixPanierCategorieDTO2 = new PrixPanierCategorieDTO();
        prixPanierCategorieDTO2.setId(2L);
        prixPanierCategorieDTO2.setPrix(0);
        CategorieDTO categorieDTO2 = new CategorieDTO();
        categorieDTO2.setId(15L);
        categorieDTO2.setNom("TestCategorie2");
        prixPanierCategorieDTO2.setCategorieDTO(categorieDTO2);

        prixPanierCategorieDTOS.add(prixPanierCategorieDTO1);
        prixPanierCategorieDTOS.add(prixPanierCategorieDTO2);

        panierDTO.setPrixPanierCategories(prixPanierCategorieDTOS);

        DevisDTO devisDTO1 = new DevisDTO();
        devisDTO1.setId(1L);
        devisDTO1.setPanier(panierDTO);

        List<DevisDTO> devisDTOS = new ArrayList<>();
        devisDTOS.add(devisDTO1);

        // When
        when(commercialService.getCommercialById(commercialId)).thenReturn(commercialDTO);
        when(categorieMapper.categorieDTOsToCategories(anyList())).thenReturn(Collections.emptyList());
        when(devisRepository.findByCategoriesInCommercial(anyList())).thenReturn(Collections.emptyList());
        when(devisMapper.devissToDevisDTOs(any())).thenReturn(devisDTOS);

        // Then
        List<DevisDTO> result = devisService.getDevisForCommercial(commercialId);

        assertEquals(devisDTOS.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void testPayerDevis_Success() {
        // Given
        Long devisId = 1L;

        Devis devis = new Devis();

        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setEstValide(true);
        PanierDTO panierDTO = new PanierDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("TestClient");
        panierDTO.setClient(clientDTO);
        devisDTO.setPanier(panierDTO);

        // When
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));
        when(devisMapper.devisToDevisDTO(any())).thenReturn(devisDTO);

        when(banqueService.payer()).thenReturn(true);

        // Then
        String result = devisService.payerDevis(devisId);

        assertEquals("Le devis 1 a été payé par le client TestClient - Devis modifié", result);
    }

    @Test
    public void testPayerDevis_Failure_BanqueKO() {
        // Given
        Long devisId = 1L;

        Devis devis = new Devis();

        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setEstValide(true);

        // When
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));
        when(devisMapper.devisToDevisDTO(any())).thenReturn(devisDTO);

        when(banqueService.payer()).thenReturn(false);

        // Then
        String result = devisService.payerDevis(devisId);

        assertEquals("Le devis 1 n'a pas pu être payé à cause d'une erreur de la banque", result);
    }

    @Test
    public void testPayerDevis_Failure_DevisNonValide() {
        // Given
        Long devisId = 1L;

        Devis devis = new Devis();

        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setEstValide(false);

        // When
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));
        when(devisMapper.devisToDevisDTO(any())).thenReturn(devisDTO);

        // Then
        String result = devisService.payerDevis(devisId);

        assertEquals("Veuillez attendre qu'un commercial valide le devis avant de pouvoir le payer", result);
    }

    @Test
    public void testPayerDevis_Failure_Failure() {
        // Given
        Long devisId = 1L;

        // When
        when(devisRepository.findById(devisId)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = devisService.payerDevis(devisId);

        assertEquals("Une erreur est survenue lors du paieement du devis : Mocked exception", result);
    }

    @Test
    public void testValiderDevis_Success() {
        // Given
        Long devisId = 1L;

        DevisDTO devisDTO = new DevisDTO();
        Devis devis = new Devis();

        // When
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));
        when(devisMapper.devisToDevisDTO(any())).thenReturn(devisDTO);

        // Then
        String result = devisService.validerDevis(devisId);

        assertEquals("Valider devis - Devis modifié", result);
    }

    @Test
    public void testValiderDevis_Failure() {
        // Given
        Long devisId = 1L;

        // When
        when(devisRepository.findById(devisId)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = devisService.validerDevis(devisId);

        assertEquals("Une erreur est survenue lors de la validation d'un devis : Mocked exception", result);
    }

    @Test
    public void testValiderMultipleDevis_Success() {
        // Given
        CommercialDTO commercialDTO = new CommercialDTO();
        commercialDTO.setEstDirecteur(true);

        ValiderMultipleDevisDTO validerMultipleDevisDTO = new ValiderMultipleDevisDTO();
        validerMultipleDevisDTO.setIdCommercial(1L);
        validerMultipleDevisDTO.setIdDevisList(Arrays.asList(1L, 2L));

        DevisDTO devisDTO1 = new DevisDTO();
        devisDTO1.setId(1L);
        DevisDTO devisDTO2 = new DevisDTO();
        devisDTO1.setId(2L);

        Devis devis1 = new Devis();
        devis1.setId(1L);
        Devis devis2 = new Devis();
        devis2.setId(2L);

        // When
        when(commercialService.getCommercialById(validerMultipleDevisDTO.getIdCommercial())).thenReturn(commercialDTO);
        when(devisRepository.findById(1L)).thenReturn(Optional.of(devis1));
        when(devisRepository.findById(2L)).thenReturn(Optional.of(devis2));
        when(devisMapper.devisToDevisDTO(devis1)).thenReturn(devisDTO1);
        when(devisMapper.devisToDevisDTO(devis2)).thenReturn(devisDTO2);

        // Then
        String result = devisService.validerMutlipleDevis(validerMultipleDevisDTO);

        assertEquals("Les devis 1, 2 ont été validés", result);
    }

    @Test
    public void testValiderMultipleDevis_Failure_PasDirecteur() {
        // Given
        CommercialDTO commercialDTO = new CommercialDTO();
        commercialDTO.setEstDirecteur(false);

        ValiderMultipleDevisDTO validerMultipleDevisDTO = new ValiderMultipleDevisDTO();
        validerMultipleDevisDTO.setIdCommercial(1L);

        // When
        when(commercialService.getCommercialById(validerMultipleDevisDTO.getIdCommercial())).thenReturn(commercialDTO);

        // Then
        String result = devisService.validerMutlipleDevis(validerMultipleDevisDTO);

        assertEquals("Seul un directeur peut valider plusieurs devis en même temps.", result);
    }

    @Test
    public void testValiderMultipleDevis_Failure() {
        // Given
        ValiderMultipleDevisDTO validerMultipleDevisDTO = new ValiderMultipleDevisDTO();
        validerMultipleDevisDTO.setIdCommercial(1L);

        // When
        when(commercialService.getCommercialById(validerMultipleDevisDTO.getIdCommercial())).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = devisService.validerMutlipleDevis(validerMultipleDevisDTO);

        assertEquals("Une erreur est survenue lors de la validation de plusieurs devis : Mocked exception", result);
    }
}
