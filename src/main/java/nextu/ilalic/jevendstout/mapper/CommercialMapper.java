package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.Commercial;
import nextu.ilalic.jevendstout.entity.DTO.CommercialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommercialMapper {

    @Autowired
    private CategorieMapper categorieMapper;

    public List<CommercialDTO> commercialsToCommercialDTOs(List<Commercial> commercials) {
        if (commercials == null) {
            return null;
        }

        List<CommercialDTO> list = new ArrayList<CommercialDTO>(commercials.size());
        for (Commercial commercial : commercials) {
            list.add(commercialToCommercialDTO(commercial));
        }

        return list;
    }

    public List<Commercial> commercialDTOsToCommercials(List<CommercialDTO> commercialDTOs) {
        if (commercialDTOs == null) {
            return null;
        }

        List<Commercial> list = new ArrayList<Commercial>(commercialDTOs.size());
        for (CommercialDTO commercialDTO : commercialDTOs) {
            list.add(commercialDTOToCommercial(commercialDTO));
        }

        return list;
    }

    public Commercial commercialDTOToCommercial(CommercialDTO commercialDTO) {
        if (commercialDTO == null) {
            return null;
        }

        Commercial commercial = new Commercial();

        commercial.setId(commercialDTO.getId());
        commercial.setNom(commercialDTO.getNom());
        commercial.setEstDirecteur(commercialDTO.isEstDirecteur());
        commercial.setCategories(categorieMapper.categorieDTOsToCategories(commercialDTO.getCategorieDTOS()));

        return commercial;
    }

    public CommercialDTO commercialToCommercialDTO(Commercial commercial) {
        if (commercial == null) {
            return null;
        }

        CommercialDTO commercialDTO = new CommercialDTO();

        commercialDTO.setId(commercial.getId());
        commercialDTO.setNom(commercial.getNom());
        commercialDTO.setEstDirecteur(commercial.isEstDirecteur());
        commercialDTO.setCategorieDTOS(categorieMapper.categoriesToCategorieDTOs(commercial.getCategories()));

        return commercialDTO;
    }
}
