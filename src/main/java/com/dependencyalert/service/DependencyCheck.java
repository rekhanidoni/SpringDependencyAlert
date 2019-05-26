package com.dependencyalert.service;

import com.dependencyalert.DAO.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.health.HealthEndpoint;

import javax.annotation.PostConstruct;
@PropertySource("classpath:application.properties")
@Component
public class DependencyCheck {

    @Autowired
    UserDAOImpl userDAO;

    @Autowired
    @Dependency
    List<DependencyInterface> dependencies;

    @Autowired
    EmailService emailSvc;

    @Autowired
    HealthEndpoint healthpoint;

    @Value("${dependencyalert.checkFrequency}")
    private int frequency;

    DependencyCheck() {

    }

    @PostConstruct
    public void startCheck() {
        Thread t = new Thread(()-> {
                List<String> down = new ArrayList<String>();

                while(true) {
                    try {
                        //Get health of all dependencies
                        System.out.println("Checking dependencies");
                        Map<String,Object> healthendpoints = healthpoint.health().getDetails();

                        healthendpoints.forEach((key,value)-> {
                            System.out.println("Key:"+key);
                            System.out.println("value:"+value);

                            if(value.toString().startsWith("DOWN "))
                                down.add(key);

                        });

                        //Maintain list of down dependencies to send alerts to users,
                        //All users are alerted every 1 minute of down dependencies.
                        //No complex logic  is added as of nowto check if any user has already received alert.
                        //No complex logic  is added as of nowto check if any user has already received alert.
                        if(!down.isEmpty()) {
                            sendEmails(Arrays.toString(down.toArray()));
                            if(down.contains("mongo") && !down.contains("mongo")) {
                                userDAO.connectionBack();
                            }
                            down.clear();
                            down.addAll(down);
                        } else {
                            System.out.println("No change in dependencies. Hence not sending email");
                        }

                       Thread.sleep(frequency);
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

    private void sendEmails(String text) {
        text = "You are receiving this email since you subscribed for dependency status alert. The following dependencies are down:"+text;
        emailSvc.sendEmail(userDAO.getAllEmailIds(),text);
    }
}
