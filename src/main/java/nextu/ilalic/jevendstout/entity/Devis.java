package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVIS_GENERATOR")
    @SequenceGenerator(name = "DEVIS_GENERATOR", sequenceName = "DEVIS_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private boolean estValide;

    @OneToOne
    private Panier panier;

    @Column
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

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public boolean isEstPaye() {
        return estPaye;
    }

    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }
}
