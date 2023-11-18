package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.ArticleDTO;
import nextu.ilalic.jevendstout.mapper.ArticleMapper;
import nextu.ilalic.jevendstout.repository.ArticleRepository;
import nextu.ilalic.jevendstout.service.ArticleService;
import nextu.ilalic.jevendstout.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategorieService categorieService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addArticles(List<ArticleDTO> articleDTOs) {
        try {
            for (ArticleDTO articleDTO : articleDTOs){
                articleDTO.getCategorie().setId(categorieService.getByNom(articleDTO.getCategorie().getNom()).getId());
            }
            articleRepository.saveAll(articleMapper.articleDTOsToArticles(articleDTOs));
            return "Les articles ont bien été enregistrés";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement des articles : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleDTO> getAll() {
        return articleMapper.articlesToArticleDTOs(articleRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleDTO getByNom(String nom) {
        return articleMapper.articleToArticleDTO(articleRepository.findByNom(nom));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleDTO> getByCategorieId(Long categorieId) {
        return articleMapper.articlesToArticleDTOs(articleRepository.findAllByCategorieId(categorieId));
    }
}
