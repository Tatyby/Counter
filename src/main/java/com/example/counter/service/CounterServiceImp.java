package com.example.counter.service;

import com.example.counter.exception.CounterException;
import com.example.counter.repository.CounterRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CounterServiceImp implements CounterService {
    private final CounterRepository counterRepository;

    public CounterServiceImp(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public boolean createCounter(String name) {
        if (!counterRepository.getCounters().containsKey(name)) {
            counterRepository.getCounters().put(name, 0);
            return true;
        } else {
            return false;
        }
    }

    public int incrementCounter(String counterName) {
        if (counterRepository.getCounters().containsKey(counterName)) {
            int counter = counterRepository.getCounters().get(counterName);
            counterRepository.getCounters().put(counterName, counter + 1);
            return counterRepository.getCounters().get(counterName);
        } else {
            throw new CounterException("Имени счетчика " + counterName + " не найдено");
        }
    }

    public Map<String, Integer> get() {
        return counterRepository.getCounters()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
