package com.TAS.demo.models;

import javax.persistence.*;

@Entity
public class FicheIntervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String debut;
    private  String fin;
    private String description;
//    @OneToOne
//    @JoinColumn(name="TACHE_ID")
//    private Tache tache;
@ManyToOne
@JoinColumn(name="TACHE_ID")
private Tache tache;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

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

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
//    public Tache getTache() {
//        return tache;
//    }
//
//    public void setTache(Tache tache) {
//        this.tache = tache;
//    }
}
