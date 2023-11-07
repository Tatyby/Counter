package com.example.counter.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Data
public class CounterRepository {
    private final Map<String, Integer> counters = new ConcurrentHashMap<>();
}
