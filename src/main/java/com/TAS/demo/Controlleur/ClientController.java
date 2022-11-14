package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IAuthority;
import com.TAS.demo.dao.IClient;
import com.TAS.demo.models.Authority;
import com.TAS.demo.models.Client;
import com.TAS.demo.models.Ingenieur;
import com.TAS.demo.models.UserTokenState;
import com.TAS.demo.utils.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/clients")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IAuthority iAuthority;
    @Autowired
    private StorageService storage;
    @Autowired
    private IClient iclient;
    @GetMapping("/all")
    public List<Client> listClient() {
        return iclient.findAll();
    }
    @PutMapping("/update/{Id}")
    public Client update(@RequestBody Client client, @PathVariable Long Id) {
        client.setId(Id);
        Client c = iclient.findOne(Id);
        client.setPassword(c.getPassword());
        client.setImage(c.getImage());
        client.setUsername(c.getUsername());
        client.setAdresse(c.getAdresse());
        client.setEnabled(true);
        client.setValide(true);
        client.setRole(c.getRole());
        Authority authority=new Authority();
        authority.setName("Client");
        iAuthority.save(authority);
        client.setAuthorities(authority);
        return iclient.saveAndFlush(client);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteClient(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            iclient.delete(Id);
            message.put("etat","client deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","client not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Client getoneclient(@PathVariable Long id) {
        return iclient.findOne(id);
    }
    @PostMapping("/save")
    public Client saveClient(@RequestBody Client c){
        return iclient.save(c);
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
    public ResponseEntity<?> register(Client user, @RequestParam("file") MultipartFile file )
    {
        Authority authority=new Authority();
        authority.setName("Client");
        iAuthority.save(authority);

        user.setEnabled(true);
        user.setValide(false);
        user.setAuthorities(authority);
        user.setPassword(hash(user.getPassword()));
        storage.store(file);
        user.setImage(file.getOriginalFilename());
        user.setRole("Client");

        iclient.save(user);

        return ResponseEntity.ok(new UserTokenState(null, 0, user));

    }
    @PutMapping("/validerClient/{Id}")
    public Client validerClient(@PathVariable Long Id) {
        Client client = iclient.findOne(Id);
        client.setValide(true);
        return iclient.saveAndFlush(client);
    }
    @PutMapping("/desactiverClient/{Id}")
    public Client desactiverClient(@PathVariable Long Id) {
        Client client = iclient.findOne(Id);
        client.setEnabled(false);
        return iclient.saveAndFlush(client);
    }
    @PutMapping("/activerClient/{Id}")
    public Client activerClient(@PathVariable Long Id) {
        Client client = iclient.findOne(Id);
        client.setEnabled(true);
        return iclient.saveAndFlush(client);
    }
    @PutMapping("/modifimage/{id}")
    public Client modif(@RequestParam("file") MultipartFile file, @PathVariable Long id){
        Client c = iclient.findOne(id);
        // c.setId(id);
        // int i = (int) new Date().getTime();
        // System.out.println("Integer : " + i);
        storage.store(file);
        c.setImage(file.getOriginalFilename());
        return iclient.saveAndFlush(c) ;
    }

    @PutMapping("/updatepassword/{Id}")
    public Client modif(String newpassword, @PathVariable Long Id) {
        Client c=iclient.findOne(Id);
        c.setPassword(hash(newpassword));
        return iclient.saveAndFlush(c);
    }
}
