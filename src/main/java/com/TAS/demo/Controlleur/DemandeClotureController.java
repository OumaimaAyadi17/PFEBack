package com.TAS.demo.Controlleur;


import com.TAS.demo.dao.IDemandeCloture;
import com.TAS.demo.dao.ITache;
import com.TAS.demo.models.DemandeCloture;
import com.TAS.demo.models.FicheIntervention;
import com.TAS.demo.models.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping (value = "/users/demandesCloture")
public class DemandeClotureController {
    @Autowired
    private IDemandeCloture idemandeCloture;
    @Autowired
    private ITache itache;
    @GetMapping("/all")
    public List<DemandeCloture> listDemandeCloture() {
        return idemandeCloture.findAll();
    }
    @PostMapping("/save/{idtache}")
    public DemandeCloture saveDemandeCloture(@RequestBody DemandeCloture c, @PathVariable Long idtache){
        Tache t = itache.findOne(idtache);
        t.setOk(false);
        t.setVerif(true);
        c.setTache(t);

        return idemandeCloture.save(c);
    }
    @PutMapping("/accepterDemande/{Iddemande}/{idtache}")
    public DemandeCloture AccepterDemande(@PathVariable Long Iddemande,@PathVariable Long idtache) {
        Tache tache = itache.findOne(idtache);
        tache.setOk(true);
        tache.setStatus("done");
        DemandeCloture demandeCloture = idemandeCloture.findOne(Iddemande);
        return idemandeCloture.saveAndFlush(demandeCloture);
    }


}
