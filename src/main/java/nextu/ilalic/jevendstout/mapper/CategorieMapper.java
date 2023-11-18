package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.Categorie;
import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieMapper {
    public List<Categorie> categorieDTOsToCategories(List<CategorieDTO> categorieDTOs) {
        if (categorieDTOs == null) {
            return null;
        }

        List<Categorie> list = new ArrayList<Categorie>(categorieDTOs.size());
        for (CategorieDTO categorieDTO : categorieDTOs) {
            list.add(categorieDTOToCategorie(categorieDTO));
        }

        return list;
    }

    public List<CategorieDTO> categoriesToCategorieDTOs(List<Categorie> categories) {
        if (categories == null) {
            return null;
        }

        List<CategorieDTO> list = new ArrayList<CategorieDTO>(categories.size());
        for (Categorie categorie : categories) {
            list.add(categorieToCategorieDTO(categorie));
        }

        return list;
    }

    public Categorie categorieDTOToCategorie(CategorieDTO categorieDTO) {
        if (categorieDTO == null) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setId(categorieDTO.getId());
        categorie.setNom(categorieDTO.getNom());

        return categorie;
    }

    public CategorieDTO categorieToCategorieDTO(Categorie categorie) {
        if (categorie == null) {
            return null;
        }

        CategorieDTO categorieDTO = new CategorieDTO();

        categorieDTO.setId(categorie.getId());
        categorieDTO.setNom(categorie.getNom());

        return categorieDTO;
    }
}
