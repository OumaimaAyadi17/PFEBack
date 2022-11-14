package com.TAS.demo.models;

import javax.persistence.*;

@Entity
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String titre;
    @ManyToOne
    @JoinColumn(name="ING_ID")
    private Ingenieur ingenieur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ingenieur getIngenieur() {
        return ingenieur;
    }

    public void setIngenieur(Ingenieur ingenieur) {
        this.ingenieur = ingenieur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
