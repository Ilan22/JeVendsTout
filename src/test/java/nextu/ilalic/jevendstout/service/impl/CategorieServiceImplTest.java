package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.Categorie;
import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;
import nextu.ilalic.jevendstout.mapper.CategorieMapper;
import nextu.ilalic.jevendstout.repository.CategorieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CategorieServiceImplTest {
    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieServiceImpl categorieService;

    @Mock
    private CategorieMapper categorieMapper;

    @Test
    public void testAddCategories_Success() {
        // Given
        List<CategorieDTO> categorieDTOs = new ArrayList<>();

        // When
        when(categorieMapper.categorieDTOsToCategories(categorieDTOs)).thenReturn(Collections.emptyList());

        // Then
        String result = categorieService.addCategories(categorieDTOs);

        verify(categorieRepository, times(1)).saveAll(any());
        assertEquals("Les catégories ont bien été enregistrées", result);
    }

    @Test
    public void testAddCategories_Failure() {
        // Given
        List<CategorieDTO> categorieDTOs = new ArrayList<>();

        // When
        when(categorieMapper.categorieDTOsToCategories(categorieDTOs)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = categorieService.addCategories(categorieDTOs);

        verify(categorieRepository, never()).saveAll(any());
        assertEquals("Une erreur est survenue lors de l'enregistrement des catégories : Mocked exception", result);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie());

        List<CategorieDTO> categorieDTOList = new ArrayList<>();
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setId(1L);
        categorieDTO.setNom("TestCategorie");
        categorieDTOList.add(categorieDTO);

        // When
        when(categorieRepository.findAll()).thenReturn(categories);
        when(categorieMapper.categoriesToCategorieDTOs(categories)).thenReturn(categorieDTOList);

        List<CategorieDTO> result = categorieService.getAll();

        // Then
        assertEquals(categorieDTOList.get(0).getNom(), result.get(0).getNom());
        verify(categorieRepository, times(1)).findAll();
    }

    @Test
    public void testGetByNom() {
        // Given
        String nom = "TestCategorie";
        Categorie categorie = new Categorie();
        categorie.setNom(nom);

        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setNom(nom);

        // When
        when(categorieRepository.findByNom(nom)).thenReturn(categorie);
        when(categorieMapper.categorieToCategorieDTO(categorie)).thenReturn(categorieDTO);

        CategorieDTO result = categorieService.getByNom(nom);

        // Then
        assertEquals(categorieDTO.getNom(), result.getNom());
        verify(categorieRepository, times(1)).findByNom(nom);
    }

    @Test
    public void testGetById() {
        // Given
        Long id = 1L;
        Categorie categorie = new Categorie();
        categorie.setId(id);

        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setId(id);

        // When
        when(categorieRepository.findById(id)).thenReturn(Optional.of(categorie));
        when(categorieMapper.categorieToCategorieDTO(categorie)).thenReturn(categorieDTO);

        CategorieDTO result = categorieService.getById(id);

        // Then
        assertEquals(categorieDTO.getId(), result.getId());
        verify(categorieRepository, times(1)).findById(id);
    }
}
