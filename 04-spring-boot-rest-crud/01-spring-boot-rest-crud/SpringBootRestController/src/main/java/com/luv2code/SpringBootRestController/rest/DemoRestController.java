package com.luv2code.SpringBootRestController.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //create RESTful web services using Spring MVC
@RequestMapping("/test")
public class DemoRestController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }

}
