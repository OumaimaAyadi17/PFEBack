package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IAlerte;
import com.TAS.demo.dao.INotification;
import com.TAS.demo.dao.IReclamation;
import com.TAS.demo.dao.IReponseReclamation;
import com.TAS.demo.models.Alerte;
import com.TAS.demo.models.Notification;
import com.TAS.demo.models.Reclamation;
import com.TAS.demo.models.ReponseReclamation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/alertes")
public class AlerteController {
    @Autowired
    private IAlerte ialerte;
    @Autowired
    private IReponseReclamation ireponseReclamation;
    @GetMapping("/all")
    public List<Alerte> listAlertes() {
        return ialerte.findAll();
    }
    @PostMapping("/save/{idReponseReclamation}")
    public Alerte saveAlerte(@RequestBody Alerte t,@PathVariable Long idReponseReclamation){
        ReponseReclamation reponseReclamation=ireponseReclamation.findOne(idReponseReclamation);
        t.setReponsereclamation(reponseReclamation);
        return ialerte.save(t);
    }
}
