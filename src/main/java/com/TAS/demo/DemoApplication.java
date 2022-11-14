package com.TAS.demo;

import com.TAS.demo.dao.IAuthority;
import com.TAS.demo.dao.IUser;
import com.TAS.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
//@EnableSwagger2
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private StorageService storage;

    @Autowired
    private IUser iUser;

    @Autowired
    private IAuthority iAuthority;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

       // storage.init();

//       Authority authority=new Authority();
//        authority.setName("Admin");
//        iAuthority.save(authority);
//
//        User user = new User();
//        user.setFirstName("wissem");
//        user.setLastName("Moncer");
//        user.setUsername("Wissem M");
//        user.setPassword(hash("wissem123"));
//        user.setEnabled(true);
//        user.setAuthorities(authority);
//        iUser.save(user);


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
}
