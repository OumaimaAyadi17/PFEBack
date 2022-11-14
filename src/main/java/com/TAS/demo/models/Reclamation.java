package com.TAS.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Reclamation {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String description;
    private boolean ok;
    @OneToOne(mappedBy="reclamation")
    private ReponseReclamation reponseReclamation;
    @OneToMany(mappedBy="reclamation")
    private Collection<Notification> notifications;
    @ManyToOne
    @JoinColumn(name="TACHECLIENT_ID")
    private Tache tacheClient;
    private String creationDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tache getTacheClient() {
        return tacheClient;
    }

    public void setTacheClient(Tache tacheClient) {
        this.tacheClient = tacheClient;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    @JsonIgnore
    public ReponseReclamation getReponseReclamation() {
        return reponseReclamation;
    }

    public void setReponseReclamation(ReponseReclamation reponseReclamation) {
        this.reponseReclamation = reponseReclamation;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
