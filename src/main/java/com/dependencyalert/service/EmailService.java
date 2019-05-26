package com.dependencyalert.service;

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

    void sendEmail(Set<String> emailIdList,String text) {

        if(!emailIdList.isEmpty()) {
            SimpleMailMessage msg = new SimpleMailMessage();
            String emailIdStr = Arrays.toString(emailIdList.toArray()).replace("[", "").replace("]", "");
            System.out.println("Sending emails to:" + emailIdStr);
            msg.setBcc(emailIdStr);

            msg.setSubject("SpringDependencyAlert: Dependency down");
            msg.setText(text);
            javaMailSender.send(msg);
        }
    }


}
