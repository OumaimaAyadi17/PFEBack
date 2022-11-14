package com.TAS.demo.models;

import javax.persistence.*;
@Entity
public class Alerte
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="ReponseRECLAMATION_ID")
    private ReponseReclamation Reponsereclamation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReponseReclamation getReponsereclamation() {
        return Reponsereclamation;
    }

    public void setReponsereclamation(ReponseReclamation reponsereclamation) {
        Reponsereclamation = reponsereclamation;
    }
}
