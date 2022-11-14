package com.TAS.demo.Controlleur;
import com.TAS.demo.dao.IEvent;


import com.TAS.demo.models.Event;

import com.TAS.demo.models.FicheIntervention;
import com.TAS.demo.models.Tache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping (value = "/users/events")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IEvent ievent;

    @GetMapping("/all")
    public List<Event> listEvents() {
        return ievent.findAll();
    }
    
}
