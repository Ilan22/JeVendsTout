package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.DTO.PanierDTO;
import nextu.ilalic.jevendstout.entity.Panier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PanierMapper {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PrixPanierCategorieMapper prixPanierCategorieMapper;

    public List<PanierDTO> paniersToPanierDTOs(List<Panier> paniers) {
        if (paniers == null) {
            return null;
        }

        List<PanierDTO> panierDTOS = new ArrayList<PanierDTO>(paniers.size());
        for (Panier panier : paniers) {
            panierDTOS.add(panierToPanierDTO(panier));
        }

        return panierDTOS;
    }

    public Panier panierDTOToPanier(PanierDTO panierDTO) {
        if (panierDTO == null) {
            return null;
        }

        Panier panier = new Panier();

        panier.setId(panierDTO.getId());
        panier.setPrixTotalHT(panierDTO.getPrixTotalHT());
        panier.setPrixTotalTTC(panierDTO.getPrixTotalTTC());
        panier.setEstValide(panierDTO.isEstValide());
        panier.setArticles(articleMapper.articleDTOsToArticles(panierDTO.getArticles()));
        panier.setClient(clientMapper.clientDTOToClient(panierDTO.getClient()));
        panier.setPrixPanierCategorie(prixPanierCategorieMapper.prixPanierCategorieDTOsToPrixPanierCategories(panierDTO.getPrixPanierCategories()));

        return panier;
    }

    public PanierDTO panierToPanierDTO(Panier panier) {
        if (panier == null) {
            return null;
        }

        PanierDTO panierDTO = new PanierDTO();

        panierDTO.setId(panier.getId());
        panierDTO.setPrixTotalHT(panier.getPrixTotalHT());
        panierDTO.setPrixTotalTTC(panier.getPrixTotalTTC());
        panierDTO.setEstValide(panier.isEstValide());
        panierDTO.setArticles(articleMapper.articlesToArticleDTOs(panier.getArticles()));
        panierDTO.setClient(clientMapper.clientToClientDTO(panier.getClient()));
        panierDTO.setPrixPanierCategories(prixPanierCategorieMapper.prixPanierCategoriesToPrixPanierCategorieDTOs(panier.getPrixPanierCategorie()));

        return panierDTO;
    }
}
