package nextu.ilalic.jevendstout.entity.DTO;

public class DevisDTO {

    private Long id;

    private boolean estValide;

    private PanierDTO panier;

    private boolean estPaye;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public PanierDTO getPanier() {
        return panier;
    }

    public void setPanier(PanierDTO panier) {
        this.panier = panier;
    }

    public boolean isEstPaye() {
        return estPaye;
    }

    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }
}
