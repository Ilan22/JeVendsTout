package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.CategorieDTO;
import nextu.ilalic.jevendstout.mapper.CategorieMapper;
import nextu.ilalic.jevendstout.repository.CategorieRepository;
import nextu.ilalic.jevendstout.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private CategorieMapper categorieMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addCategories(List<CategorieDTO> categorieDTOs) {
        try {
            categorieRepository.saveAll(categorieMapper.categorieDTOsToCategories(categorieDTOs));
            return "Les catégories ont bien été enregistrées";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement des catégories : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategorieDTO> getAll() {
        return categorieMapper.categoriesToCategorieDTOs(categorieRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategorieDTO getById(Long id) {
        return categorieMapper.categorieToCategorieDTO(categorieRepository.findById(id).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategorieDTO getByNom(String nom) {
        return categorieMapper.categorieToCategorieDTO(categorieRepository.findByNom(nom));
    }
}
