package com.gta.service;

import com.gta.DAO.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Email;

@Component
public class DependencyCheck {

    @Autowired
    UserDAOImpl userDAO;

    @Autowired
    @Dependency
    List<DependencyInterface> dependencies;

    @Autowired
    EmailService emailSvc;

    DependencyCheck() {

    }

    @PostConstruct
    public void startCheck() {
        Thread t = new Thread(()-> {
                List<DependencyInterface> down = new ArrayList<DependencyInterface>();
                while(true) {
                   try {
                       System.out.println("Checking dependencies");
                       for(DependencyInterface dependency : dependencies) {
                           //If dependency is down add it to down list and send email
                           if(!dependency.isAlive() && !down.contains(dependency)) {
                               System.out.println("Sending alert");
                               sendEmails();
                               down.add(dependency);
                               break;
                           } else if(dependency.isAlive() && down.contains(dependency)) {
                               //dependency is alive now, Remove it from down list
                               down.remove(dependency);
                               if(dependency instanceof UserDAOImpl) {
                                   ((UserDAOImpl)dependency).connectionBack();
                               }
                           }
                       }
                       Thread.sleep(30000);
                   } catch(InterruptedException ie) {
                       System.out.println("interrupted exception");
                   }
                   catch (Exception e) {
                       System.out.println("checking dependency:"+e.getMessage());
                   }

               }
            });
        t.start();

    }

    private void sendEmails() {
        emailSvc.sendEmail(userDAO.getAllEmailIds());
    }
}
