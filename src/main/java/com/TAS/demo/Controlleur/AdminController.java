package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IAdmin;
import com.TAS.demo.dao.IAuthority;
import com.TAS.demo.models.Admin;
import com.TAS.demo.models.Authority;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/admins")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IAuthority iAuthority;
    @Autowired
    private StorageService storage;
    @Autowired
    private IAdmin iadmin;


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
    public ResponseEntity<?> register(Admin user, @RequestParam("file") MultipartFile file )
    {
        Authority authority=new Authority();
        authority.setName("Admin");
        iAuthority.save(authority);

        user.setEnabled(true);
        user.setValide(true);
        user.setAuthorities(authority);
        user.setPassword(hash(user.getPassword()));
        storage.store(file);
        user.setImage(file.getOriginalFilename());
        user.setRole("Admin");

        iadmin.save(user);

        return ResponseEntity.ok(new UserTokenState(null, 0, user));

    }
}

