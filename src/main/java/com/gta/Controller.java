package com.gta;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(name="/health", method = RequestMethod.GET)
    public String healthCheck() {
        return "OK";
    }
}
