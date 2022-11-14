package com.TAS.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String description;
    private String status;
    private  String nom;
    private String priority;
    private String start;
    private boolean ok;
    private boolean verif=false;
    private boolean progress=false;
    private String end;
    private String confirmationAdmin;

    private String creationDate;
    @ManyToOne
    @JoinColumn(name="CLIENT_ID")
    private  Client client1;
    @ManyToOne
    @JoinColumn(name="ING_ID")
    private  Ingenieur ing;
    @OneToOne(mappedBy="tache")
    private DemandeCloture demandeCloture;

    @OneToMany(mappedBy="tache")
    private Collection<FicheIntervention> fiches;

    @OneToMany(mappedBy="tacheClient")
    private Collection<Reclamation> reclamations;

    public boolean isVerif() {
        return verif;
    }

    public void setVerif(boolean verif) {
        this.verif = verif;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmationAdmin() {
        return confirmationAdmin;
    }

    public void setConfirmationAdmin(String confirmationAdmin) {
        this.confirmationAdmin = confirmationAdmin;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @JsonIgnore
    public DemandeCloture getDemandeCloture() {
        return demandeCloture;
    }

    public void setDemandeCloture(DemandeCloture demandeCloture) {
        this.demandeCloture = demandeCloture;
    }
//    @JsonIgnore
//    public FicheIntervention getFiche() {
//        return fiche;
//    }
//
//    public void setFiche(FicheIntervention fiche) {
//        this.fiche = fiche;
//    }

    @JsonIgnore
    public Collection<FicheIntervention> getFiches() {
        return fiches;
    }

    public void setFiches(Collection<FicheIntervention> fiches) {
        this.fiches = fiches;
    }

    public Ingenieur getIng() {
        return ing;
    }

    public void setIng(Ingenieur ing) {
        this.ing = ing;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    @JsonIgnore
    public Collection<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(Collection<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }
}
