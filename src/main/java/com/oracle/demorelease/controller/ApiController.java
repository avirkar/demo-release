package com.oracle.demorelease.controller;
import java.util.concurrent.atomic.AtomicLong;


import com.oracle.demorelease.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private RestTemplate restTemplate;

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/crypto/prices")
    public ResponseEntity<String> weather(@RequestParam(value = "name", defaultValue = "World") String name) {
        String response = restTemplate.getForObject("http://localhost:8081/api/v1/address", String.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}