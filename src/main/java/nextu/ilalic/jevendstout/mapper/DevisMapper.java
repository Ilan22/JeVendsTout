package nextu.ilalic.jevendstout.mapper;

import nextu.ilalic.jevendstout.entity.DTO.DevisDTO;
import nextu.ilalic.jevendstout.entity.Devis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevisMapper {

    @Autowired
    private PanierMapper panierMapper;

    public List<Devis> devisDTOsToDeviss(List<DevisDTO> devisDTOs) {
        if (devisDTOs == null) {
            return null;
        }

        List<Devis> list = new ArrayList<Devis>(devisDTOs.size());
        for (DevisDTO devisDTO : devisDTOs) {
            list.add(devisDTOToDevis(devisDTO));
        }

        return list;
    }

    public List<DevisDTO> devissToDevisDTOs(List<Devis> deviss) {
        if (deviss == null) {
            return null;
        }

        List<DevisDTO> list = new ArrayList<DevisDTO>(deviss.size());
        for (Devis devis : deviss) {
            list.add(devisToDevisDTO(devis));
        }

        return list;
    }

    public Devis devisDTOToDevis(DevisDTO devisDTO) {
        if (devisDTO == null) {
            return null;
        }

        Devis devis = new Devis();

        devis.setId(devisDTO.getId());
        devis.setEstValide(devisDTO.isEstValide());
        devis.setPanier(panierMapper.panierDTOToPanier(devisDTO.getPanier()));
        devis.setEstPaye(devisDTO.isEstPaye());

        return devis;
    }

    public DevisDTO devisToDevisDTO(Devis devis) {
        if (devis == null) {
            return null;
        }

        DevisDTO devisDTO = new DevisDTO();

        devisDTO.setId(devis.getId());
        devisDTO.setEstValide(devis.isEstValide());
        devisDTO.setPanier(panierMapper.panierToPanierDTO(devis.getPanier()));
        devisDTO.setEstPaye(devis.isEstPaye());

        return devisDTO;
    }
}
