package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.Article;
import nextu.ilalic.jevendstout.entity.DTO.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleMapper {

    @Autowired
    private CategorieMapper categorieMapper;

    public List<Article> articleDTOsToArticles(List<ArticleDTO> articleDTOs) {
        if (articleDTOs == null) {
            return null;
        }

        List<Article> list = new ArrayList<Article>(articleDTOs.size());
        for (ArticleDTO articleDTO : articleDTOs) {
            list.add(articleDTOToArticle(articleDTO));
        }

        return list;
    }

    public List<ArticleDTO> articlesToArticleDTOs(List<Article> articles) {
        if (articles == null) {
            return null;
        }

        List<ArticleDTO> list = new ArrayList<ArticleDTO>(articles.size());
        for (Article article : articles) {
            list.add(articleToArticleDTO(article));
        }

        return list;
    }

    public Article articleDTOToArticle(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }

        Article article = new Article();

        article.setId(articleDTO.getId());
        article.setNom(articleDTO.getNom());
        article.setCategorie(categorieMapper.categorieDTOToCategorie(articleDTO.getCategorie()));

        return article;
    }

    public ArticleDTO articleToArticleDTO(Article article) {
        if (article == null) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setId(article.getId());
        articleDTO.setNom(article.getNom());
        articleDTO.setCategorie(categorieMapper.categorieToCategorieDTO(article.getCategorie()));


        return articleDTO;
    }
}
