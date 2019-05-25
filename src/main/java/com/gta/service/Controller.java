package com.gta.service;

import com.gta.DAO.User;
import com.gta.DAO.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private UserDAOImpl userDAO;

    @GetMapping(name = "/health")
    public String healthCheck() {
        return "OK";
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
