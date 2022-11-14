package com.TAS.demo.models;

import javax.persistence.*;

@Entity
public class DemandeCloture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
 @JoinColumn(name="TACHECLOT_ID")
  private Tache tache;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }


}
