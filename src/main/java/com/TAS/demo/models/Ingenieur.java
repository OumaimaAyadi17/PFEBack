package com.TAS.demo.models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Ingenieur extends User{


    private String disponibilite;
    private String telephone;
    @OneToMany(mappedBy="ingenieur")
    private Collection<Competence> competences;
    @OneToMany(mappedBy="ingenieur")
    private Collection<RDV> RDVs;
    @OneToMany(mappedBy="ing")
    private Collection<Tache> taches;



    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    @JsonIgnore
    public Collection<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Collection<Competence> competences) {
        this.competences = competences;
    }
    @JsonIgnore
    public Collection<RDV> getRDVs() {
        return RDVs;
    }

    public void setRDVs(Collection<RDV> RDVs) {
        this.RDVs = RDVs;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @JsonIgnore
    public Collection<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Collection<Tache> taches) {
        this.taches = taches;
    }
}
