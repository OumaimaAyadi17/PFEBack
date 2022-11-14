package com.TAS.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class ReponseReclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creationDate;
    private String description;
        @OneToOne
    @JoinColumn(name="RECLMATION_ID")
    private Reclamation reclamation;
    @OneToMany(mappedBy="Reponsereclamation")
    private Collection<Alerte> alertes;
@JsonIgnore
    public Collection<Alerte> getAlertes() {
        return alertes;
    }

    public void setAlertes(Collection<Alerte> alertes) {
        this.alertes = alertes;
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

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
