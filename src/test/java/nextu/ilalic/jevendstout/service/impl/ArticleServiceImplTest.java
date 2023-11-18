package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.Article;
import nextu.ilalic.jevendstout.entity.DTO.ArticleDTO;
import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;
import nextu.ilalic.jevendstout.mapper.ArticleMapper;
import nextu.ilalic.jevendstout.repository.ArticleRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {
    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CategorieServiceImpl categorieService;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleMapper articleMapper;

    @Test
    public void testAddArticles_Success() {
        // Given
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setNom("Test");
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setNom("TestCategorie");
        articleDTO.setCategorie(categorieDTO);
        articleDTOList.add(articleDTO);

        CategorieDTO categorieReturn = new CategorieDTO();
        categorieReturn.setId(1L);

        // When
        when(categorieService.getByNom("TestCategorie")).thenReturn(categorieReturn);
        when(articleRepository.saveAll(anyList())).thenReturn(Collections.emptyList());

        String result = articleService.addArticles(articleDTOList);

        // Then
        assertEquals("Les articles ont bien été enregistrés", result);
        verify(articleRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testAddArticles_Failure() {
        // Given
        List<ArticleDTO> articleDTOs = new ArrayList<>();
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setNom("Test");
        articleDTOs.add(articleDTO);
        // Pas de catégorie pour générer une exception

        // When
        String result = articleService.addArticles(articleDTOs);

        // Then
        assertTrue(result.startsWith("Une erreur est survenue lors de l'enregistrement des articles"));
        verify(articleRepository, times(0)).saveAll(any());
    }

    @Test
    public void testGetAll() {
        // Given
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());

        List<ArticleDTO> expectedArticleDTOs = new ArrayList<>();
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1L);
        articleDTO.setNom("TestArticle");
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setId(1L);
        categorieDTO.setNom("TestCategorie");
        articleDTO.setCategorie(categorieDTO);
        expectedArticleDTOs.add(articleDTO);

        // When
        when(articleRepository.findAll()).thenReturn(articles);
        when(articleMapper.articlesToArticleDTOs(articles)).thenReturn(expectedArticleDTOs);

        List<ArticleDTO> result = articleService.getAll();

        // Then
        assertEquals(expectedArticleDTOs.get(0).getNom(), result.get(0).getNom());
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    public void testGetByNom() {
        // Given
        String nom = "TestArticle";
        Article article = new Article();
        article.setNom(nom);

        ArticleDTO expectedArticleDTO = new ArticleDTO();
        expectedArticleDTO.setNom(nom);

        // When
        when(articleRepository.findByNom(nom)).thenReturn(article);
        when(articleMapper.articleToArticleDTO(article)).thenReturn(expectedArticleDTO);

        ArticleDTO result = articleService.getByNom(nom);

        // Then
        assertEquals(expectedArticleDTO.getNom(), result.getNom());
        verify(articleRepository, times(1)).findByNom(nom);
    }

    @Test
    public void testGetByCategorieId() {
        // Given
        Long categorieId = 1L;

        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());

        List<ArticleDTO> expectedArticleDTOs = new ArrayList<>();
        expectedArticleDTOs.add(new ArticleDTO());
        expectedArticleDTOs.add(new ArticleDTO());

        // When
        when(articleRepository.findAllByCategorieId(categorieId)).thenReturn(articles);
        when(articleMapper.articlesToArticleDTOs(articles)).thenReturn(expectedArticleDTOs);

        List<ArticleDTO> result = articleService.getByCategorieId(categorieId);

        // Then
        assertEquals(expectedArticleDTOs.get(0).getId(), result.get(0).getId());
        verify(articleRepository, times(1)).findAllByCategorieId(categorieId);
    }
}
