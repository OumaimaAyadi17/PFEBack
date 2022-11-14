package com.TAS.demo.models;

import javax.persistence.*;

@Entity
public class RDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String date;
    @ManyToOne
    @JoinColumn(name="ING_ID")
    private Ingenieur ingenieur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Ingenieur getIngenieur() {
        return ingenieur;
    }

    public void setIngenieur(Ingenieur ingenieur) {
        this.ingenieur = ingenieur;
    }
}
