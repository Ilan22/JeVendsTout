package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.Commercial;
import nextu.ilalic.jevendstout.entity.DTO.AddCategoriesToCommercialDTO;
import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;
import nextu.ilalic.jevendstout.entity.DTO.CommercialDTO;
import nextu.ilalic.jevendstout.mapper.CommercialMapper;
import nextu.ilalic.jevendstout.repository.CommercialRepository;
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
public class CommercialServiceImplTest {
    @Mock
    private CommercialRepository commercialRepository;

    @InjectMocks
    private CommercialServiceImpl commercialService;

    @Mock
    private CommercialMapper commercialMapper;

    @Mock
    private CategorieServiceImpl categorieService;

    @Test
    public void testAddCommerciaux_Success() {
        // Given
        List<CommercialDTO> commercialDTOS = new ArrayList<>();

        // When
        when(commercialMapper.commercialDTOsToCommercials(commercialDTOS)).thenReturn(Collections.emptyList());

        // Then
        String result = commercialService.addCommerciaux(commercialDTOS);

        verify(commercialRepository, times(1)).saveAll(any());
        assertEquals("Les commerciaux ont bien été enregistrés", result);
    }

    @Test
    public void testAddCommerciaux_Failure() {
        // Given
        List<CommercialDTO> commercialDTOS = new ArrayList<>();

        // When
        when(commercialMapper.commercialDTOsToCommercials(commercialDTOS)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = commercialService.addCommerciaux(commercialDTOS);

        verify(commercialRepository, never()).saveAll(any());
        assertEquals("Une erreur est survenue lors de l'enregistrement des commerciaux : Mocked exception", result);
    }

    @Test
    public void testAddCommercial_Success() {
        // Given
        CommercialDTO commercialDTO = new CommercialDTO();

        // When
        when(commercialMapper.commercialDTOToCommercial(commercialDTO)).thenReturn(null);

        // Then
        String result = commercialService.addCommercial(commercialDTO);

        verify(commercialRepository, times(1)).save(any());
        assertEquals("Le commercial a bien été enregistré", result);
    }

    @Test
    public void testAddCommercial_Failure() {
        // Given
        CommercialDTO commercialDTO = new CommercialDTO();

        // When
        when(commercialMapper.commercialDTOToCommercial(commercialDTO)).thenThrow(new RuntimeException("Mocked exception"));

        // Then
        String result = commercialService.addCommercial(commercialDTO);

        verify(commercialRepository, never()).save(any());
        assertEquals("Une erreur est survenue lors de l'enregistrement du commercial : Mocked exception", result);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Commercial> commercials = new ArrayList<>();
        commercials.add(new Commercial());

        List<CommercialDTO> commercialDTOS = new ArrayList<>();
        CommercialDTO commercialDTO = new CommercialDTO();
        commercialDTO.setNom("TestCommercial");
        commercialDTOS.add(commercialDTO);

        // When
        when(commercialRepository.findAll()).thenReturn(commercials);
        when(commercialMapper.commercialsToCommercialDTOs(commercials)).thenReturn(commercialDTOS);

        List<CommercialDTO> result = commercialService.getAll();

        // Then
        assertEquals(commercialDTOS.get(0).getNom(), result.get(0).getNom());
        verify(commercialRepository, times(1)).findAll();
    }

    @Test
    public void testGetByNom() {
        // Given
        String nom = "TestCommercial";
        Commercial commercial = new Commercial();
        commercial.setNom(nom);

        CommercialDTO commercialDTO = new CommercialDTO();
        commercialDTO.setNom(nom);

        // When
        when(commercialRepository.findCommercialByNom(nom)).thenReturn(commercial);
        when(commercialMapper.commercialToCommercialDTO(commercial)).thenReturn(commercialDTO);

        CommercialDTO result = commercialService.getCommercialByNom(nom);

        // Then
        assertEquals(commercialDTO.getNom(), result.getNom());
        verify(commercialRepository, times(1)).findCommercialByNom(nom);
    }

    @Test
    public void testGetById() {
        // Given
        Long id = 1L;
        Commercial commercial = new Commercial();
        commercial.setId(id);

        CommercialDTO commercialDTO = new CommercialDTO();
        commercialDTO.setId(id);

        // When
        when(commercialRepository.findById(id)).thenReturn(Optional.of(commercial));
        when(commercialMapper.commercialToCommercialDTO(commercial)).thenReturn(commercialDTO);

        CommercialDTO result = commercialService.getCommercialById(id);

        // Then
        assertEquals(commercialDTO.getId(), result.getId());
        verify(commercialRepository, times(1)).findById(id);
    }

    @Test
    public void testAddCategories_Success() {
        // Given
        Commercial commercial = new Commercial();
        commercial.setId(1L);
        commercial.setNom("Nom Commercial");
        commercial.setEstDirecteur(true);
        commercial.setCategories(new ArrayList<>());

        AddCategoriesToCommercialDTO addCategoriesToCommercialDTO = new AddCategoriesToCommercialDTO();
        addCategoriesToCommercialDTO.setIdCommercial(1L);
        addCategoriesToCommercialDTO.setCategoriesIdList(List.of(1L, 2L));

        CategorieDTO categorieDTO1 = new CategorieDTO();
        categorieDTO1.setId(1L);
        categorieDTO1.setNom("Nom Catégorie 1");

        CategorieDTO categorieDTO2 = new CategorieDTO();
        categorieDTO2.setId(2L);
        categorieDTO2.setNom("Nom Catégorie 2");

        // When
        when(commercialRepository.findById(1L)).thenReturn(Optional.of(commercial));
        when(commercialMapper.commercialToCommercialDTO(commercial)).thenReturn(new CommercialDTO());

        when(categorieService.getById(1L)).thenReturn(categorieDTO1);
        when(categorieService.getById(2L)).thenReturn(categorieDTO2);

        // Then
        String result = commercialService.addCategories(addCategoriesToCommercialDTO);

        verify(commercialRepository, times(1)).findById(1L);
        verify(commercialMapper, times(1)).commercialToCommercialDTO(commercial);
        verify(categorieService, times(2)).getById(any());

        assertEquals("Ajout de catégories au commercial - Commercial modifié", result);
    }
}
