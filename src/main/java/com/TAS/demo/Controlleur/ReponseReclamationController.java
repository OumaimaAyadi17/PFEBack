package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IReclamation;
import com.TAS.demo.dao.IReponseReclamation;
import com.TAS.demo.models.Reclamation;
import com.TAS.demo.models.ReponseReclamation;
import com.TAS.demo.models.Tache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/reponseReclamations")
public class ReponseReclamationController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IReponseReclamation ireponseReclamation;
    @Autowired
    private IReclamation ireclamation;

    @GetMapping("/all")
    public List<ReponseReclamation> listReponseReclamations() {
        return ireponseReclamation.findAll();
    }
    @GetMapping("/getone/{id}")
    public ReponseReclamation getoneReponseReclamation(@PathVariable Long id) {
        return ireponseReclamation.findOne(id);
    }
    @PostMapping("/save/{idreclamation}")
    public ReponseReclamation saveReponseReclamation(@RequestBody ReponseReclamation reponse,@PathVariable Long idreclamation){
        Reclamation reclamation=ireclamation.findOne(idreclamation);
        reclamation.setOk(true);
        reponse.setReclamation(reclamation);

        return ireponseReclamation.save(reponse);
    }
}
