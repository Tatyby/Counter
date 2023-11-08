package com.example.counter.service;

import com.example.counter.exception.CounterException;
import com.example.counter.repository.CounterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

public class CounterServiceImpTest {
    private final String counterName = "TestCounter";
    private final int initialValue = 5;
    @Mock
    private CounterRepository counterRepository;
    @Mock
    private CounterServiceImp counterServiceImp;

    @BeforeEach
    public void setUp() {
        counterRepository = new CounterRepository();
        counterServiceImp = new CounterServiceImp(counterRepository);
    }

    @Test
    public void createCounterTest() {
        Assertions.assertFalse(counterRepository.getCounters().containsKey(counterName));
        counterServiceImp.createCounter(counterName);
        Assertions.assertTrue(counterRepository.getCounters().containsKey(counterName));
        Assertions.assertEquals(0, counterRepository.getCounters().get(counterName).intValue());
        try {
            counterServiceImp.createCounter(counterName);
            Assertions.fail("Ожидается CounterException");
        } catch (CounterException e) {
            Assertions.assertEquals("Счетчик с таким именем " + counterName + " уже существует", e.getMessage());
        }
    }

    @Test
    public void incrementCounterTest() {
        counterRepository.getCounters().put(counterName, initialValue);
        Assertions.assertEquals(initialValue + 1, counterServiceImp.incrementCounter(counterName));
    }

    @Test
    public void incrementCounterNonExistingCounterThrowsCounterException() {
        String counterName = "NonExistingCounter";
        Assertions.assertThrows(CounterException.class, () -> {
            counterServiceImp.incrementCounter(counterName);
        });
    }

    @Test
    public void getValueCounterTest() {
        counterRepository.getCounters().put(counterName, initialValue);
        Assertions.assertEquals(initialValue, counterServiceImp.getValueCounter(counterName));
    }

    @Test
    public void deleteCounterTest() {
        counterRepository.getCounters().put(counterName, initialValue);
        counterRepository.getCounters().put("Counter", 1);
        counterRepository.getCounters().put("Counter1", 2);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Counter", 1);
        expected.put("Counter1", 2);
        counterServiceImp.deleteCounter(counterName);
        Assertions.assertEquals(expected, counterRepository.getCounters());
    }

    @Test
    public void sumCounterValueTest() {
        counterRepository.getCounters().put(counterName, initialValue);
        counterRepository.getCounters().put("Counter", 1);
        counterRepository.getCounters().put("Counter1", 2);
        int expected = 8;
        int actual = counterServiceImp.sumCounterValue();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getNameCounterListTest() {
        counterRepository.getCounters().put(counterName, initialValue);
        counterRepository.getCounters().put("Counter", 1);
        counterRepository.getCounters().put("Counter1", 2);
        List<String> expected = new ArrayList<>(Arrays.asList(counterName, "Counter", "Counter1"));
        Assertions.assertEquals(expected, counterServiceImp.getNameCounterList());
    }
}
