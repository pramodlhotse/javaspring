package com.spring.java.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/bye/{value}")
    public String bye(@PathVariable("value") String value) {
        return value;
    }

    @GetMapping("/foos")
    @ResponseBody
    public String getFoos(@RequestParam String id) {
        return "ID: " + id;
    }
    @PostMapping("/foos")
    @ResponseBody
    public String addFoo(@RequestParam String fooId, @RequestParam Optional <String> name) {
        return "ID: " + fooId + " Name: " + name.orElseGet(() -> "not provided");
    }
}
