package com.example.counter.service;

import com.example.counter.exception.CounterException;
import com.example.counter.repository.CounterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CounterServiceImp implements CounterService {
    private final CounterRepository counterRepository;

    public CounterServiceImp(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public void createCounter(String name) {
        if (!counterRepository.getCounters().containsKey(name)) {
            counterRepository.getCounters().put(name, 0);
        } else {
            throw new CounterException("Счетчик с таким именем " + name + " уже существует");
        }
    }

    public int incrementCounter(String counterName) {
        if (counterRepository.getCounters().containsKey(counterName)) {
            int counter = counterRepository.getCounters().get(counterName);
            counterRepository.getCounters().put(counterName, counter + 1);
            return counter + 1;
        } else {
            throw new CounterException("Имени счетчика " + counterName + " не найдено");
        }
    }

    public Integer getValueCounter(String nameCounter) {
        if (counterRepository.getCounters().containsKey(nameCounter)) {
            return counterRepository.getCounters().get(nameCounter);
        } else {
            throw new CounterException("Имени счетчика " + nameCounter + " не найдено");
        }
    }

    public void deleteCounter(String nameCounter) {
        if (counterRepository.getCounters().containsKey(nameCounter)) {
            counterRepository.getCounters().remove(nameCounter);
        } else {
            throw new CounterException("Имени счетчика " + nameCounter + " не найдено");
        }
    }

    public int sumCounterValue() {
        return counterRepository.getCounters().values().stream().mapToInt(Integer::intValue).sum();
    }

    public List<String> getNameCounterList() {
        return new ArrayList<>(counterRepository.getCounters().keySet());
    }

}
