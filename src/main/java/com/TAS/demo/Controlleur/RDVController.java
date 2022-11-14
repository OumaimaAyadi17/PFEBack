package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IIngenieur;
import com.TAS.demo.dao.IRDV;
import com.TAS.demo.models.Ingenieur;
import com.TAS.demo.models.RDV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/RDV")
public class RDVController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IRDV irdv;
    @Autowired
    private IIngenieur iingenieur;
    @GetMapping("/all")
    public List<RDV> listRDV() {
        return irdv.findAll();
    }
    @PutMapping("/update/{Id}")
    public RDV update(@RequestBody RDV rdv, @PathVariable Long Id) {
        rdv.setId(Id);
        return irdv.saveAndFlush(rdv);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteRDV(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            irdv.delete(Id);
            message.put("etat","RDV deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","RDV not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public RDV getoneRDV(@PathVariable Long id) {
        return irdv.findOne(id);
    }
    @PostMapping("/save/{iding}")
    public RDV saveRDV(@RequestBody RDV c,@PathVariable Long iding){
        Ingenieur ingenieur=iingenieur.findOne(iding);
        c.setIngenieur(ingenieur);
        return irdv.save(c);
    }
}
