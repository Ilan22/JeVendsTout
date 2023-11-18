package nextu.ilalic.jevendstout.service.impl;

import nextu.ilalic.jevendstout.entity.DTO.*;
import nextu.ilalic.jevendstout.mapper.PanierMapper;
import nextu.ilalic.jevendstout.repository.PanierRepository;
import nextu.ilalic.jevendstout.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PanierServiceImpl implements PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private PanierMapper panierMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private DevisService devisService;

    @Autowired
    private TarificationService tarificationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addPanier(PanierDTO panierDTO) {
        try {
            ClientDTO client = clientService.getClientByNom(panierDTO.getClient().getNom());
            panierDTO.setClient(client);
            panierRepository.save(panierMapper.panierDTOToPanier(panierDTO));
            return "Le panier a bien été enregistré";
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'enregistrement du panier : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addArticles(List<String> nomArticles, Long idPanier) {
        try {
            PanierDTO panier = panierMapper.panierToPanierDTO(panierRepository.findById(idPanier).get());

            List<ArticleDTO> articleDTOS = panier.getArticles();
            for (String nom : nomArticles) {
                articleDTOS.add(articleService.getByNom(nom));
            }
            panier.setArticles(articleDTOS);
            return "Ajout d'articles au panier - " + updatePanier(panier);
        } catch (Exception e) {
            return "Une erreur est survenue lors de l'ajout d'articles au panier : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String validerPanier(Long idPanier) {
        try {
            PanierDTO panier = panierMapper.panierToPanierDTO(panierRepository.findById(idPanier).get());
            if (panier.getPrixTotalHT() == 0) {
                return "Valider panier - Veuillez faire tarifer le panier avant de le valider";
            }
            DevisDTO newDevisDTO = new DevisDTO();
            newDevisDTO.setPanier(panier);
            devisService.addDevis(newDevisDTO);
            panier.setEstValide(true);
            return "Valider panier - Création du devis.. - " + updatePanier(panier);
        } catch (Exception e) {
            return "Une erreur est survenue lors de la validation du panier : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setTarif(Long idPanier) {
        try {
            PanierDTO panier = panierMapper.panierToPanierDTO(panierRepository.findById(idPanier).get());

            int prixTotal = 0;
            List<PrixPanierCategorieDTO> prixPanierCategorieDTOS = new ArrayList<>();
            for (ArticleDTO articleDTO : panier.getArticles()) {
                if (prixPanierCategorieDTOS.stream().noneMatch(o -> Objects.equals(o.getCategorieDTO().getNom(), articleDTO.getCategorie().getNom()))) {
                    PrixPanierCategorieDTO prixPanierCategorieDTO = new PrixPanierCategorieDTO();
                    prixPanierCategorieDTO.setCategorieDTO(articleDTO.getCategorie());
                    int newPrix = tarificationService.getTarif();
                    prixTotal += newPrix;
                    prixPanierCategorieDTO.setPrix(newPrix);
                    prixPanierCategorieDTOS.add(prixPanierCategorieDTO);
                } else {
                    for (PrixPanierCategorieDTO prixPanierCategorieDTO : prixPanierCategorieDTOS) {
                        if (Objects.equals(prixPanierCategorieDTO.getCategorieDTO().getNom(), articleDTO.getCategorie().getNom())) {
                            int newPrix = tarificationService.getTarif();
                            prixTotal += newPrix;
                            prixPanierCategorieDTO.setPrix(prixPanierCategorieDTO.getPrix() + newPrix);
                        }
                    }
                }
            }

            panier.setPrixPanierCategories(prixPanierCategorieDTOS);

            panier.setPrixTotalHT(prixTotal);
            panier.setPrixTotalTTC(prixTotal * 1.20f);
            return "Le prix du panier est de " + panier.getPrixTotalHT() + "€ HT soit " + panier.getPrixTotalTTC() + "€ TTC - " + updatePanier(panier);
        } catch (Exception e) {
            return "Une erreur est survenue lors de la tarification du panier : " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PanierDTO> getAll() {
        return panierMapper.paniersToPanierDTOs(panierRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PanierDTO getByIdPanier(Long idPanier) {
        return panierMapper.panierToPanierDTO(panierRepository.findById(idPanier).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PanierDTO> getByNomClient(String nomClient) {
        return panierMapper.paniersToPanierDTOs(panierRepository.findAllByClientNom(nomClient));
    }

    /**
     * Met à jour le panier
     *
     * @param panierDTO dto contenant les nouvelles valeurs
     * @return message de confirmation
     */
    private String updatePanier(PanierDTO panierDTO) {
        panierRepository.save(panierMapper.panierDTOToPanier(panierDTO));
        return "Panier modifié";
    }
}
