package com.TAS.demo.Controlleur;

import com.TAS.demo.models.Mail;
import com.TAS.demo.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/users/emails")
public class MailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/sendMail/{email}")
    public String sendMail(@PathVariable String email){
        System.out.println("Spring Mail - Sending Simple Email with JavaMailSender Example");
        Mail mail = new Mail();
        mail.setFrom("oumaimaayadi111@gmail.com");
        mail.setTo(email);
        mail.setSubject("Account verification");
        mail.setContent("the administration has validated your account.Now, you can login and access to your account ");
        emailService.sendSimpleMessage(mail);
        return  "ok";
    }
}
