package com.gta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmailService {

    public EmailService() {
    }

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(Set<String> emailIdList) {

        SimpleMailMessage msg = new SimpleMailMessage();
        String emailIdStr = Arrays.toString(emailIdList.toArray()).replace("[","").replace("]","");
        System.out.println("Sending emails to:"+emailIdStr);
        msg.setBcc(emailIdStr);

        msg.setSubject("SpringDependencyAlert: Dependency down");
        msg.setText("This message is being sent since you subscribed to receive message for dependency health alert.\n One of the dependencies is down");
        javaMailSender.send(msg);
    }


}
