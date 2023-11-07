package com.example.counter.controller;

import com.example.counter.model.CounterRequest;
import com.example.counter.service.CounterServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/counters")
public class CounterController {
    private final CounterServiceImp counterService;

    public CounterController(CounterServiceImp counterService) {
        this.counterService = counterService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createCounter(@RequestBody CounterRequest counterRequest) {
        if (counterService.createCounter(counterRequest.getName())) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Counter created: " + counterRequest.getName());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Counter with name: " + counterRequest.getName() + " already exists.");
        }
    }

    @PutMapping(value = "/increment/{nameCounter}")
    public ResponseEntity<Integer> incrementCounter(@PathVariable String nameCounter) {
        return new ResponseEntity<>(counterService.incrementCounter(nameCounter), HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<Map<String, Integer>> get() {
        return new ResponseEntity<>(counterService.get(), HttpStatus.OK);
    }
}
