package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IAuthority;
import com.TAS.demo.dao.IIngenieur;
import com.TAS.demo.models.*;
import com.TAS.demo.utils.EmailService;
import com.TAS.demo.utils.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/ingenieurs")
public class IngenieurController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IAuthority iAuthority;
    @Autowired
    private StorageService storage;
    @Autowired
    private IIngenieur iingenieur;
    @Autowired
    private EmailService emailService;
    @GetMapping("/all")
    public List<Ingenieur> listIngenieur() {
        return iingenieur.findAll();
    }
    @PutMapping("/update/{Id}")
    public Ingenieur update(@RequestBody Ingenieur ingenieur, @PathVariable Long Id) {

        ingenieur.setId(Id);
      Ingenieur i = iingenieur.findOne(Id);
      ingenieur.setPassword(i.getPassword());
      ingenieur.setImage(i.getImage());
      ingenieur.setUsername(i.getUsername());
      ingenieur.setDisponibilite(i.getDisponibilite());
      ingenieur.setEnabled(true);
      ingenieur.setValide(true);
      ingenieur.setRole(i.getRole());
        Authority authority=new Authority();
        authority.setName("Ingenieur");
        iAuthority.save(authority);
ingenieur.setAuthorities(authority);
        return iingenieur.saveAndFlush(ingenieur);
    }
    @PutMapping("/validerIngenieur/{Id}")
    public Ingenieur validerIngenieur(@PathVariable Long Id) {
        Ingenieur ingenieur = iingenieur.findOne(Id);
        ingenieur.setValide(true);

        return iingenieur.saveAndFlush(ingenieur);
    }
    @PutMapping("/desactiverIngenieur/{Id}")
    public Ingenieur desactiverIngenieur(@PathVariable Long Id) {
        Ingenieur ingenieur = iingenieur.findOne(Id);
        ingenieur.setEnabled(false);
        return iingenieur.saveAndFlush(ingenieur);
    }
    @PutMapping("/activerIngenieur/{Id}")
    public Ingenieur activerIngenieur(@PathVariable Long Id) {
        Ingenieur ingenieur = iingenieur.findOne(Id);
        ingenieur.setEnabled(true);
        return iingenieur.saveAndFlush(ingenieur);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteIngenieur(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            iingenieur.delete(Id);
            message.put("etat","ingenieur deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","ingenieur not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Ingenieur getoneingenieur(@PathVariable Long id) {
        return iingenieur.findOne(id);
    }
    @PostMapping("/save")
    public Ingenieur saveIngenieur(@RequestBody Ingenieur c){
        return iingenieur.save(c);
    }
    String hash(String password) {


        String hashedPassword = null;
        int i = 0;
        while (i < 5) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(password);
            i++;
        }

        return hashedPassword;
    }

    @RequestMapping("/register")
    public ResponseEntity<?> register(Ingenieur user, @RequestParam("file") MultipartFile file )
    {
        Authority authority=new Authority();
        authority.setName("Ingenieur");
        iAuthority.save(authority);

        user.setEnabled(true);
        user.setValide(false);
        user.setAuthorities(authority);
        user.setPassword(hash(user.getPassword()));
        user.setDisponibilite("disponible");
        storage.store(file);
        user.setImage(file.getOriginalFilename());
        user.setRole("Ingenieur");

        iingenieur.save(user);

        return ResponseEntity.ok(new UserTokenState(null, 0, user));

    }

    @PutMapping("/modifimage/{id}")
    public Ingenieur modif(@RequestParam("file") MultipartFile file, @PathVariable Long id){
        Ingenieur i = iingenieur.findOne(id);
        // c.setId(id);
        // int i = (int) new Date().getTime();
        // System.out.println("Integer : " + i);
        storage.store(file);
        i.setImage(file.getOriginalFilename());
        return iingenieur.saveAndFlush(i) ;
    }

    @PutMapping("/updatepassword/{Id}")
    public Ingenieur modif(String newpassword, @PathVariable Long Id) {
        Ingenieur i=iingenieur.findOne(Id);
        i.setPassword(hash(newpassword));
        return iingenieur.saveAndFlush(i);
    }
    @PutMapping("/changeToDispo/{Id}")
    public Ingenieur changeToDispo(@PathVariable Long Id) {
        Ingenieur i=iingenieur.findOne(Id);
        i.setDisponibilite("disponible");
        return iingenieur.saveAndFlush(i);
    }
    @PutMapping("/changeToIndispo/{Id}")
    public Ingenieur changeToIndispo(@PathVariable Long Id) {
        Ingenieur i=iingenieur.findOne(Id);
        i.setDisponibilite("indisponible");
        return iingenieur.saveAndFlush(i);
    }

}
