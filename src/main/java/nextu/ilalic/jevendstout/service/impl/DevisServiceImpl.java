package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.*;
import nextu.ilalic.jevendstout.mapper.CategorieMapper;
import nextu.ilalic.jevendstout.mapper.DevisMapper;
import nextu.ilalic.jevendstout.repository.DevisRepository;
import nextu.ilalic.jevendstout.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DevisMapper devisMapper;

    @Autowired
    private CommercialService commercialService;

    @Autowired
    private CategorieMapper categorieMapper;

    @Autowired
    private BanqueService banqueService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addDevis(DevisDTO devisDTO) {
        try {
            devisRepository.save(devisMapper.devisDTOToDevis(devisDTO));
            return "Le devis a bien été enregistré";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement du devis : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String validerDevis(Long id) {
        try {
            DevisDTO devisDTO = devisMapper.devisToDevisDTO(devisRepository.findById(id).get());
            devisDTO.setEstValide(true);
            return "Valider devis - " + updateDevis(devisDTO);
        } catch (Exception e) {
            return "Une erreur est survenue lors de la validation d'un devis : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String validerMutlipleDevis(ValiderMultipleDevisDTO validerMultipleDevisDTO) {
        try {
            CommercialDTO commercialDTO = commercialService.getCommercialById(validerMultipleDevisDTO.getIdCommercial());
            if (commercialDTO.isEstDirecteur()) {
                for (Long idDevis : validerMultipleDevisDTO.getIdDevisList()) {
                    DevisDTO devisDTO = devisMapper.devisToDevisDTO(devisRepository.findById(idDevis).get());
                    devisDTO.setEstValide(true);
                    updateDevis(devisDTO);
                }

                StringBuilder stringBuilder = new StringBuilder();

                for (Long element : validerMultipleDevisDTO.getIdDevisList()) {
                    if (!stringBuilder.isEmpty()) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(element);
                }

                return "Les devis " + stringBuilder + " ont été validés";
            }
            return "Seul un directeur peut valider plusieurs devis en même temps.";
        } catch (Exception e) {
            return "Une erreur est survenue lors de la validation de plusieurs devis : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DevisDTO> getAll() {
        return devisMapper.devissToDevisDTOs(devisRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevisDTO getById(Long id) {
        return devisMapper.devisToDevisDTO(devisRepository.findById(id).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DevisDTO> getByNomClient(String nomClient) {
        return devisMapper.devissToDevisDTOs(devisRepository.findAllByPanierClientNom(nomClient));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String payerDevis(Long id) {
        try {
            DevisDTO devisDTO = devisMapper.devisToDevisDTO(devisRepository.findById(id).get());
            if (devisDTO.isEstValide()) {
                boolean retourBanque = banqueService.payer();
                if (retourBanque) {
                    devisDTO.setEstPaye(true);
                    return "Le devis " + id + " a été payé par le client " + devisDTO.getPanier().getClient().getNom() + " - " + updateDevis(devisDTO);
                } else {
                    return "Le devis " + id + " n'a pas pu être payé à cause d'une erreur de la banque";
                }
            }
            return "Veuillez attendre qu'un commercial valide le devis avant de pouvoir le payer";
        } catch (Exception e) {
            return "Une erreur est survenue lors du paieement du devis : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DevisDTO> getDevisForDirector() {
        return devisMapper.devissToDevisDTOs(devisRepository.findAllByPanierPrixTotalHTGreaterThan(10000f));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DevisDTO> getDevisForCommercial(Long id) {
        CommercialDTO commercialDTO = commercialService.getCommercialById(id);
        List<DevisDTO> devisForCommercial = new ArrayList<>();
        List<DevisDTO> devisWithCommonCategories = devisMapper.devissToDevisDTOs(devisRepository.findByCategoriesInCommercial(categorieMapper.categorieDTOsToCategories(commercialDTO.getCategorieDTOS())));
        for (DevisDTO devisDTO : devisWithCommonCategories) {
            int prixCategoriesOfCommercial = 0;
            int prixCategoriesOthers = 0;

            for (PrixPanierCategorieDTO prixPanierCategorieDTO : devisDTO.getPanier().getPrixPanierCategories()) {
                if (commercialDTO.getCategorieDTOS().stream().anyMatch(o -> o.getNom().equals(prixPanierCategorieDTO.getCategorieDTO().getNom())))
                    prixCategoriesOfCommercial += prixPanierCategorieDTO.getPrix();
                else prixCategoriesOthers += prixPanierCategorieDTO.getPrix();
            }
            if (prixCategoriesOfCommercial > prixCategoriesOthers) devisForCommercial.add(devisDTO);
        }
        return devisForCommercial;
    }

    /**
     * Met à jour le devis
     *
     * @param devisDTO dto contenant les nouvelles valeurs
     * @return message de confirmation
     */
    private String updateDevis(DevisDTO devisDTO) {
        devisRepository.save(devisMapper.devisDTOToDevis(devisDTO));
        return "Devis modifié";
    }
}
