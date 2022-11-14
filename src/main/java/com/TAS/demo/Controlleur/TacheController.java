package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IClient;
import com.TAS.demo.dao.IIngenieur;
import com.TAS.demo.dao.ITache;
import com.TAS.demo.dao.IUser;
import com.TAS.demo.models.*;
import com.TAS.demo.utils.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/taches")
public class TacheController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private ITache itache;
    @Autowired
    private IClient iclient;
    @Autowired
    private IIngenieur iing;
    @Autowired
    private IUser iuser;
    @Autowired
    private EmailService emailService;
    @GetMapping("/all")
    public List<Tache> listTache() {
        return itache.findAll();
    }
    @PutMapping("/update/{Idtache}/{iding}/{idclient}")
    public Tache update(@PathVariable Long Idtache,@PathVariable Long iding,
                        @PathVariable Long idclient,@RequestBody Tache tache) {
        Tache tache1= itache.findOne(Idtache);
        tache.setId(Idtache);
        Ingenieur ing= iing.findOne(iding);
        tache.setIng(ing);
        Client client= iclient.findOne(idclient);
        tache.setClient1(client);
        tache.setStatus(tache1.getStatus());
        tache.setCreationDate(tache1.getCreationDate());
        tache.setConfirmationAdmin(tache1.getConfirmationAdmin());
        return itache.saveAndFlush(tache);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteTache(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            itache.delete(Id);
            message.put("etat","Tache deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","Tache not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Tache getonetache(@PathVariable Long id) {
        return itache.findOne(id);
    }
//    @PostMapping("/save/{iduser}")
//    public Tache saveTache(@RequestBody Tache c,@PathVariable Long iduser){
//User user= iuser.findOne(iduser);
//c.setUser(user);
// return itache.save(c);
//    }

    @PostMapping("/save/{iding}/{idclient}")
    public Tache saveTache(@RequestBody Tache c,@PathVariable Long iding,@PathVariable Long idclient){
        Ingenieur ing= iing.findOne(iding);
        c.setIng(ing);
        c.setConfirmationAdmin("En cours");
        Client client= iclient.findOne(idclient);
        c.setClient1(client);
        return itache.save(c);
    }
    @PostMapping("/saveTacheByAdmin/{iding}/{idclient}")
    public Tache saveTacheByAdmin(@RequestBody Tache c,@PathVariable Long iding,@PathVariable Long idclient){
        Ingenieur ing= iing.findOne(iding);
        c.setIng(ing);
        c.setConfirmationAdmin("confirmé");
        c.setStatus("todo");
        Client client= iclient.findOne(idclient);
        c.setClient1(client);
        return itache.save(c);
    }
    @PostMapping("/saveDemande/{idclient}")
    public Tache saveDemandeClient(@RequestBody Tache c,@PathVariable Long idclient){

        c.setStatus("todo");
        c.setConfirmationAdmin("confirmé");
        Client client= iclient.findOne(idclient);
        c.setClient1(client);

        return itache.save(c);
    }
    @PutMapping("/validateInprogress/{Id}")
    public Tache ValidateEtatInProgress( @PathVariable Long Id) {
        Tache tache = itache.findOne(Id);
        tache.setStatus("inprogress");
        tache.setProgress(true);
        return itache.saveAndFlush(tache);
    }
    @PutMapping("/validateReview/{Id}")
    public Tache ValidateEtatReview( @PathVariable Long Id) {
        Tache tache = itache.findOne(Id);
        tache.setStatus("review");
        return itache.saveAndFlush(tache);
    }
    @PutMapping("/validateDone/{Id}")
    public Tache ValidateEtatDone(@PathVariable Long Id) {
        Tache tache = itache.findOne(Id);
        tache.setStatus("done");
        return itache.saveAndFlush(tache);
    }
    @PutMapping("/affecterIng/{Id}/{Iding}")
    public Tache affecterIng( @PathVariable Long Id,@PathVariable Long Iding) {
        Tache tache = itache.findOne(Id);
        Ingenieur ing = iing.findOne(Iding);
        tache.setIng(ing);

        return itache.saveAndFlush(tache);
    }
    @PutMapping("/validateEncours/{Id}")
    public Tache ValidateEtatEnCours(@PathVariable Long Id) {
        Tache tache = itache.findOne(Id);
        tache.setStatus("todo");
        tache.setConfirmationAdmin("confirmé");
        return itache.saveAndFlush(tache);
    }
    @PutMapping("/RefuserEtatEnCours/{Id}")
    public Tache RefuserEtatEnCours(@PathVariable Long Id) {
        Tache tache = itache.findOne(Id);
        tache.setConfirmationAdmin("Non confirmé");
        return itache.saveAndFlush(tache);
    }

    @PutMapping("/affecterIngDemande/{Id}/{Iding}")
    public Tache affecterIngfordemandeIng( @PathVariable Long Id,@PathVariable Long Iding) {
        Tache tache = itache.findOne(Id);
        Ingenieur ing = iing.findOne(Iding);
        tache.setStatus("todo");
        tache.setConfirmationAdmin("confirmé");
        tache.setIng(ing);
        return itache.saveAndFlush(tache);
    }
    @Scheduled(cron = "0 0/5 * * * *")
    @PostMapping("/sendmailauto")
    public void senmail() throws ParseException {
        List<Tache> liste = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        System.out.println("start");
        List<Tache> listTache= itache.findByStatus("todo");
        System.out.println("listTache"+listTache);
        for(int i=0; i< listTache.size(); i++) {
//            System.out.println("for start");

//            if(listTache.get(i).getStatus()=="todo"){
//                liste.add(listTache.get(i));
//                System.out.println("liste"+liste);
                System.out.println("mmm"+listTache.get(i).getStatus());
                Date currentUtilDate = new Date();
//                System.out.println("currentUtilDate"+currentUtilDate);
                LocalDate DateSysteme = currentUtilDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                System.out.println("DateSysteme"+DateSysteme);
                String dateInString  = listTache.get(i).getStart();
//            System.out.println("dateTache"+dateTache);
//                LocalDate date2 = LocalDate.parse(dateTache);
            Date date1= formatter.parse(dateInString);
            LocalDate date2 = date1.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            System.out.println("date2"+date2);
                int mois2 = date2.getMonthValue();
                int annee2 = date2.getYear();
                int jour2= date2.getDayOfMonth();

                System.out.println(annee2);  //2012
                System.out.println(mois2); //8
                System.out.println(jour2);   //18

                int mois1 = DateSysteme.getMonthValue();
                int annee1 = DateSysteme.getYear();
                int jour1= DateSysteme.getDayOfMonth();

                System.out.println(annee1);  //2012
                System.out.println(mois1); //8
                System.out.println(jour1);   //18
                if(mois1==mois2 && annee1==annee2 && (jour2-jour1)==1 ){
                    Client client = iclient.findOne(listTache.get(i).getClient1().getId());
                    Mail mail = new Mail();
                    mail.setFrom("oumaimaayadi111@gmail.com");

                    mail.setTo(client.getEmail());
                    mail.setSubject("Reminder");
                    mail.setContent("The following email is a reminder to inform you that you have an intervention tomorrow");
                    emailService.sendSimpleMessage(mail);

                }

            }


//        }



    }
}
