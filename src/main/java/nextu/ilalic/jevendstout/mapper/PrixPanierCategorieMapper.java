package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.DTO.PrixPanierCategorieDTO;
import nextu.ilalic.jevendstout.entity.PrixPanierCategorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrixPanierCategorieMapper {

    @Autowired
    private CategorieMapper categorieMapper;

    public List<PrixPanierCategorieDTO> prixPanierCategoriesToPrixPanierCategorieDTOs(List<PrixPanierCategorie> prixPanierCategories) {
        if (prixPanierCategories == null) {
            return null;
        }

        List<PrixPanierCategorieDTO> prixPanierCategorieDTOS = new ArrayList<PrixPanierCategorieDTO>(prixPanierCategories.size());
        for (PrixPanierCategorie prixPanierCategorie : prixPanierCategories) {
            prixPanierCategorieDTOS.add(prixPanierCategorieToPrixPanierCategorieDTO(prixPanierCategorie));
        }

        return prixPanierCategorieDTOS;
    }

    public List<PrixPanierCategorie> prixPanierCategorieDTOsToPrixPanierCategories(List<PrixPanierCategorieDTO> prixPanierCategorieDTOs) {
        if (prixPanierCategorieDTOs == null) {
            return null;
        }

        List<PrixPanierCategorie> prixPanierCategories = new ArrayList<PrixPanierCategorie>(prixPanierCategorieDTOs.size());
        for (PrixPanierCategorieDTO prixPanierCategorieDTO : prixPanierCategorieDTOs) {
            prixPanierCategories.add(prixPanierCategorieDTOToPrixPanierCategorie(prixPanierCategorieDTO));
        }

        return prixPanierCategories;
    }

    public PrixPanierCategorie prixPanierCategorieDTOToPrixPanierCategorie(PrixPanierCategorieDTO prixPanierCategorieDTO) {
        if (prixPanierCategorieDTO == null) {
            return null;
        }

        PrixPanierCategorie prixPanierCategorie = new PrixPanierCategorie();

        prixPanierCategorie.setId(prixPanierCategorieDTO.getId());
        prixPanierCategorie.setPrix(prixPanierCategorieDTO.getPrix());
        prixPanierCategorie.setCategorie(categorieMapper.categorieDTOToCategorie(prixPanierCategorieDTO.getCategorieDTO()));

        return prixPanierCategorie;
    }

    public PrixPanierCategorieDTO prixPanierCategorieToPrixPanierCategorieDTO(PrixPanierCategorie prixPanierCategorie) {
        if (prixPanierCategorie == null) {
            return null;
        }

        PrixPanierCategorieDTO prixPanierCategorieDTO = new PrixPanierCategorieDTO();

        prixPanierCategorieDTO.setId(prixPanierCategorie.getId());
        prixPanierCategorieDTO.setPrix(prixPanierCategorie.getPrix());
        prixPanierCategorieDTO.setCategorieDTO(categorieMapper.categorieToCategorieDTO(prixPanierCategorie.getCategorie()));

        return prixPanierCategorieDTO;
    }
}
