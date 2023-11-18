package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.*;
import nextu.ilalic.jevendstout.entity.Panier;
import nextu.ilalic.jevendstout.mapper.PanierMapper;
import nextu.ilalic.jevendstout.repository.PanierRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PanierServiceImplTest {
    @Mock
    private PanierRepository panierRepository;

    @InjectMocks
    private PanierServiceImpl panierService;

    @Mock
    private PanierMapper panierMapper;

    @Mock
    private ArticleServiceImpl articleService;

    @Mock
    private ClientServiceImpl clientService;

    @Mock
    private DevisServiceImpl devisService;

    @Mock
    private TarificationServiceImpl tarificationService;

    @Test
    public void testAddPanier_Success() {
        // Given
        PanierDTO panierDTO = new PanierDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("TestClient");
        panierDTO.setClient(clientDTO);

        // When
        when(panierMapper.panierDTOToPanier(panierDTO)).thenReturn(null);

        // Then
        String result = panierService.addPanier(panierDTO);

        verify(panierRepository, times(1)).save(any());
        assertEquals("Le panier a bien été enregistré", result);
    }

    @Test
    public void testAddPanier_Failure() {
        // Given
        PanierDTO panierDTO = new PanierDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("TestClient");
        panierDTO.setClient(clientDTO);

        // When
        when(panierMapper.panierDTOToPanier(panierDTO)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = panierService.addPanier(panierDTO);

        verify(panierRepository, never()).save(any());
        assertEquals("Une erreur est survenue lors de l'enregistrement du panier : Mocked exception", result);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Panier> paniers = new ArrayList<>();
        paniers.add(new Panier());

        List<PanierDTO> panierDTOS = new ArrayList<>();
        PanierDTO panierDTO = new PanierDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("TestClient");
        panierDTO.setClient(clientDTO);
        panierDTOS.add(panierDTO);

        // When
        when(panierRepository.findAll()).thenReturn(paniers);
        when(panierMapper.paniersToPanierDTOs(paniers)).thenReturn(panierDTOS);

        List<PanierDTO> result = panierService.getAll();

        // Then
        assertEquals(panierDTOS.get(0).getClient().getNom(), result.get(0).getClient().getNom());
        verify(panierRepository, times(1)).findAll();
    }

    @Test
    public void getByNomClient() {
        // Given
        String nomClient = "TestClient";
        List<Panier> paniers = new ArrayList<>();

        List<PanierDTO> panierDTOs = new ArrayList<>();
        PanierDTO panierDTO = new PanierDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom(nomClient);
        panierDTO.setClient(clientDTO);
        panierDTOs.add(panierDTO);

        // When
        when(panierRepository.findAllByClientNom(nomClient)).thenReturn(paniers);
        when(panierMapper.paniersToPanierDTOs(paniers)).thenReturn(panierDTOs);

        List<PanierDTO> result = panierService.getByNomClient(nomClient);

        // Then
        assertEquals(panierDTOs.get(0).getClient().getNom(), result.get(0).getClient().getNom());
        verify(panierRepository, times(1)).findAllByClientNom(nomClient);
    }

    @Test
    public void testGetById() {
        // Given
        Long id = 1L;
        Panier panier = new Panier();

        PanierDTO panierDTO = new PanierDTO();
        panierDTO.setId(id);

        // When
        when(panierRepository.findById(id)).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(panier)).thenReturn(panierDTO);

        PanierDTO result = panierService.getByIdPanier(id);

        // Then
        assertEquals(panierDTO.getId(), result.getId());
        verify(panierRepository, times(1)).findById(id);
    }

    @Test
    public void testAddArticles_Success() {
        // Given
        List<String> nomArticles = List.of("NomArticle1", "NomArticle2");
        Long idPanier = 1L;

        Panier panier = new Panier();
        panier.setId(idPanier);
        panier.setArticles(new ArrayList<>());

        PanierDTO panierDTO = new PanierDTO();
        panierDTO.setId(idPanier);
        panierDTO.setArticles(new ArrayList<>());

        ArticleDTO articleDTO1 = new ArticleDTO();
        articleDTO1.setNom("NomArticle1");
        ArticleDTO articleDTO2 = new ArticleDTO();
        articleDTO2.setNom("NomArticle2");

        // When
        when(panierRepository.findById(idPanier)).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(any())).thenReturn(panierDTO);

        when(articleService.getByNom("NomArticle1")).thenReturn(articleDTO1);
        when(articleService.getByNom("NomArticle2")).thenReturn(articleDTO2);

        // Then
        String result = panierService.addArticles(nomArticles, idPanier);

        verify(panierRepository, times(1)).findById(idPanier);
        assertEquals("Ajout d'articles au panier - Panier modifié", result);
    }

    @Test
    public void testAddArticles_Failure_ArticleNotFound() {
        // Given
        List<String> nomArticles = List.of("NomArticle1", "NomArticle2");
        Long idPanier = 1L;

        Panier panier = new Panier();
        panier.setId(idPanier);
        panier.setArticles(new ArrayList<>());

        // When
        when(panierRepository.findById(idPanier)).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(any())).thenReturn(new PanierDTO());

        when(articleService.getByNom(anyString())).thenReturn(null);

        // Then
        String result = panierService.addArticles(nomArticles, idPanier);

        verify(panierRepository, times(1)).findById(idPanier);
        verify(panierMapper, times(1)).panierToPanierDTO(any());
        verify(articleService, times(1)).getByNom(any());

        assertEquals("Une erreur est survenue lors de l'ajout d'articles au panier : Cannot invoke \"java.util.List.add(Object)\" because \"articleDTOS\" is null", result);
    }

    @Test
    public void testValiderPanier_Success() {
        // Given
        Long idPanier = 1L;

        Panier panier = new Panier();

        PanierDTO panierDTO = new PanierDTO();
        panierDTO.setPrixTotalHT(1);

        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setPanier(panierDTO);

        // When
        when(panierRepository.findById(any())).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(panier)).thenReturn(panierDTO);

        // Then
        String result = panierService.validerPanier(idPanier);

        verify(devisService, times(1)).addDevis(any());
        assertEquals("Valider panier - Création du devis.. - Panier modifié", result);
    }

    @Test
    public void testValiderPanier_Failure_prixTotalHTIsZero() {
        // Given
        Long idPanier = 1L;

        Panier panier = new Panier();

        PanierDTO panierDTO = new PanierDTO();
        panierDTO.setPrixTotalHT(0);

        // When
        when(panierRepository.findById(any())).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(panier)).thenReturn(panierDTO);

        // Then
        String result = panierService.validerPanier(idPanier);

        assertEquals("Valider panier - Veuillez faire tarifer le panier avant de le valider", result);
    }

    @Test
    public void testValiderPanier_Failure() {
        // Given
        Long idPanier = 1L;

        Panier panier = new Panier();

        // When
        when(panierRepository.findById(any())).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(panier)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = panierService.validerPanier(idPanier);

        assertEquals("Une erreur est survenue lors de la validation du panier : Mocked exception", result);
    }

    @Test
    public void testSetTarif_Success() {
        // Given
        Long idPanier = 1L;

        PanierDTO panierDTO = new PanierDTO();
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setNom("TestArticles");
        articleDTO.setId(1L);
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setNom("TestCategorie");
        categorieDTO.setId(1L);
        articleDTO.setCategorie(categorieDTO);
        articleDTOList.add(articleDTO);
        panierDTO.setArticles(articleDTOList);

        Panier panier = new Panier();

        // When
        when(panierRepository.findById(idPanier)).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(panier)).thenReturn(panierDTO);

        when(tarificationService.getTarif()).thenReturn(50);

        // Then
        String result = panierService.setTarif(idPanier);

        assertEquals("Le prix du panier est de 50.0€ HT soit 60.000004€ TTC - Panier modifié", result);
    }

    @Test
    public void testSetTarif_Failure() {
        // Given
        Long idPanier = 1L;

        Panier panier = new Panier();

        // When
        when(panierRepository.findById(any())).thenReturn(Optional.of(panier));
        when(panierMapper.panierToPanierDTO(panier)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = panierService.setTarif(idPanier);

        assertEquals("Une erreur est survenue lors de la tarification du panier : Mocked exception", result);
    }
}
