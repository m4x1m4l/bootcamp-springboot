package com.max.bootcampspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }

    @PostMapping("/vocal")
    public int countVocals(@RequestBody String text){
        return text.toLowerCase().replaceAll("[^aeiou]", "").length();
    }
}
