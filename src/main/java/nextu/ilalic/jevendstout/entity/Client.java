package nextu.ilalic.jevendstout.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_GENERATOR")
    @SequenceGenerator(name = "CLIENT_GENERATOR", sequenceName = "CLIENT_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique=true)
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
