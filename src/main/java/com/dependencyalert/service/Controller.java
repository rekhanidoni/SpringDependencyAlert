package com.dependencyalert.service;

import com.dependencyalert.DAO.User;
import com.dependencyalert.DAO.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controller {

    @Autowired
    private UserDAOImpl userDAO;
    @Autowired
    HealthEndpoint healthpoint;

    @GetMapping(name = "/health")
    public Map healthCheck() {
        return healthpoint.health().getDetails();
    }


    @PostMapping(name="/signup")
    public ResponseEntity<ResponseMessage> signup(@RequestBody User usr) {
        String result = userDAO.insertUser(usr);
        ResponseMessage msg = new ResponseMessage();
        if(result == null)
        {
            msg.setMessage("OK");
            msg.setStatus("SUCCESS");
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } else {
            msg.setMessage(result);
            msg.setStatus("FAILURE");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);

        }
    }

}
