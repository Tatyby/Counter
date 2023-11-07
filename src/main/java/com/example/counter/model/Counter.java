package com.example.counter.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Counter {
    private String name;
    private int value;
}
