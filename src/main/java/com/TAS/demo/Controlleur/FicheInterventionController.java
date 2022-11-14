package com.TAS.demo.Controlleur;


import com.TAS.demo.dao.IFicheIntervention;
import com.TAS.demo.dao.ITache;
import com.TAS.demo.models.FicheIntervention;
import com.TAS.demo.models.Tache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/fiches")
public class FicheInterventionController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IFicheIntervention ifiche;
    @Autowired
    private ITache iTache;
    @GetMapping("/all")
    public List<FicheIntervention> listFicheIntervention() {
        return ifiche.findAll();
    }
    @PutMapping("/update/{Id}")
    public FicheIntervention update(@RequestBody FicheIntervention fiche, @PathVariable Long Id) {
        fiche.setId(Id);
        return ifiche.saveAndFlush(fiche);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteFiche(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            ifiche.delete(Id);
            message.put("etat","fiche deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","fiche not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public FicheIntervention getonefiche(@PathVariable Long id) {
        return ifiche.findOne(id);
    }
    @PostMapping("/save/{idtache}")
    public FicheIntervention saveFiche(@RequestBody FicheIntervention c,@PathVariable Long idtache){
        Tache t = iTache.findOne(idtache);

        c.setTache(t);

        return ifiche.save(c);
    }

}
