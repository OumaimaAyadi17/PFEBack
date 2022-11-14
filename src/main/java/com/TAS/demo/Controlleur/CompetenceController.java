package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IClient;
import com.TAS.demo.dao.ICompetence;
import com.TAS.demo.dao.IIngenieur;
import com.TAS.demo.models.Client;
import com.TAS.demo.models.Competence;
import com.TAS.demo.models.Ingenieur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/competences")
public class CompetenceController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private ICompetence icompetence;
    @Autowired
    private IIngenieur iingenieur;
    @GetMapping("/all")
    public List<Competence> listCompetence() {
        return icompetence.findAll();
    }
    @PutMapping("/update/{Id}")
    public Competence update(@RequestBody Competence competence, @PathVariable Long Id) {
        competence.setId(Id);
        return icompetence.saveAndFlush(competence);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteCompetence(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            icompetence.delete(Id);
            message.put("etat","competence deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","competence not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Competence getonecompetence(@PathVariable Long id) {
        return icompetence.findOne(id);
    }
    @PostMapping("/save/{iding}")
    public Competence saveCompetence(@RequestBody Competence c,@PathVariable Long iding){
        Ingenieur ingenieur=iingenieur.findOne(iding);
          c.setIngenieur(ingenieur);
        return icompetence.save(c);
    }
}
