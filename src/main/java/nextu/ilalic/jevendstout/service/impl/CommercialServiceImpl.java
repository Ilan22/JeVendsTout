package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.*;
import nextu.ilalic.jevendstout.mapper.CommercialMapper;
import nextu.ilalic.jevendstout.repository.CommercialRepository;
import nextu.ilalic.jevendstout.service.CategorieService;
import nextu.ilalic.jevendstout.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommercialServiceImpl implements CommercialService {

    @Autowired
    private CommercialRepository commercialRepository;

    @Autowired
    private CommercialMapper commercialMapper;

    @Autowired
    private CategorieService categorieService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addCommercial(CommercialDTO commercial) {
        try {
            commercialRepository.save(commercialMapper.commercialDTOToCommercial(commercial));
            return "Le commercial a bien été enregistré";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement du commercial : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addCommerciaux(List<CommercialDTO> commercialDTOs) {
        try {
            for (CommercialDTO commercialDTO : commercialDTOs){
                for (CategorieDTO categorieDTO : commercialDTO.getCategorieDTOS()){
                    categorieDTO.setId(categorieService.getByNom(categorieDTO.getNom()).getId());
                }
            }
            commercialRepository.saveAll(commercialMapper.commercialDTOsToCommercials(commercialDTOs));
            return "Les commerciaux ont bien été enregistrés";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement des commerciaux : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommercialDTO> getAll() {
        return commercialMapper.commercialsToCommercialDTOs(commercialRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommercialDTO getCommercialByNom(String nom) {
        return commercialMapper.commercialToCommercialDTO(commercialRepository.findCommercialByNom(nom));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommercialDTO getCommercialById(Long id) {
        return commercialMapper.commercialToCommercialDTO(commercialRepository.findById(id).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addCategories(AddCategoriesToCommercialDTO addCategoriesToCommercialDTO) {
        try {
            CommercialDTO commercialDTO = commercialMapper.commercialToCommercialDTO(commercialRepository.findById(addCategoriesToCommercialDTO.getIdCommercial()).get());
            List<CategorieDTO> categorieDTOList = new ArrayList<>();
            for (Long id : addCategoriesToCommercialDTO.getCategoriesIdList()) {
                CategorieDTO categorieDTO = categorieService.getById(id);
                categorieDTOList.add(categorieDTO);
            }
            commercialDTO.setCategorieDTOS(categorieDTOList);
            return "Ajout de catégories au commercial - " + updateCommercial(commercialDTO);
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'ajout des catégories au commercial : " + e.getMessage();
        }
    }

    /**
     * Met à jour le commercial
     * @param commercialDTO dto contenant les nouvelles valeurs
     * @return message de confirmation
     */
    private String updateCommercial(CommercialDTO commercialDTO) {
        commercialRepository.save(commercialMapper.commercialDTOToCommercial(commercialDTO));
        return "Commercial modifié";
    }
}
