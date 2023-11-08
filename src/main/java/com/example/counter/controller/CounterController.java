package com.example.counter.controller;

import com.example.counter.model.CounterRequest;
import com.example.counter.service.CounterServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/counters")
public class CounterController {
    private final CounterServiceImp counterService;

    public CounterController(CounterServiceImp counterService) {
        this.counterService = counterService;
    }

    @Operation(summary = "Создание счетчика с уникальным именем")
    @PostMapping(value = "/create")
    public ResponseEntity<?> createCounter(@RequestBody CounterRequest counterRequest) {
        counterService.createCounter(counterRequest.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Инкремент значения счетчика с указанным именем")
    @PutMapping(value = "/increment/{nameCounter}")
    public ResponseEntity<Integer> incrementCounter(@PathVariable String nameCounter) {
        return new ResponseEntity<>(counterService.incrementCounter(nameCounter), HttpStatus.OK);
    }

    @Operation(summary = "Получение значение счетчика с указанным именем")
    @GetMapping("/value/{nameCounter}")
    public ResponseEntity<Integer> getValueCounter(@PathVariable String nameCounter) {
        return new ResponseEntity<>(counterService.getValueCounter(nameCounter), HttpStatus.OK);

    }

    @Operation(summary = "Удаление счетчика с указанным именем")
    @DeleteMapping("/delete/{nameCounter}")
    public ResponseEntity<?> deleteCounter(@PathVariable String nameCounter) {
        counterService.deleteCounter(nameCounter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получение суммарного значения всех счетчиков")
    @GetMapping("/sum")
    public ResponseEntity<Integer> sumAllCounter() {
        return new ResponseEntity<>(counterService.sumCounterValue(), HttpStatus.OK);
    }

    @Operation(summary = "Получение уникальных имён счетчиков в виде списка")
    @GetMapping("/nameList")
    public ResponseEntity<List<String>> getNameCounterList() {
        return new ResponseEntity<>(counterService.getNameCounterList(), HttpStatus.OK);
    }
}
