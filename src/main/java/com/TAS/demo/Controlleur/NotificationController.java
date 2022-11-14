package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.INotification;
import com.TAS.demo.dao.IReclamation;
import com.TAS.demo.models.Notification;
import com.TAS.demo.models.Reclamation;
import com.TAS.demo.models.Tache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/notifications")
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private INotification inotification;
    @Autowired
    private IReclamation ireclamation;
    @GetMapping("/all")
    public List<Notification> listNotifications() {
        return inotification.findAll();
    }
    @PostMapping("/save/{idReclamation}")
    public Notification saveNotification(@RequestBody Notification t,@PathVariable Long idReclamation){
        Reclamation reclamation=ireclamation.findOne(idReclamation);
       t.setReclamation(reclamation);
        return inotification.save(t);
    }
}
