package com.game.service.domain.services;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E> {
    private final NavigableMap<Float, E> map = new TreeMap<Float, E>();
    private final Random random;
    private float total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(float percent, E result) {
        if (percent <= 0) return this;
        total += percent;
        map.put(total, result);
        return this;
    }

    public E next() {
        float value = random.nextFloat() * total;
        return map.higherEntry(value).getValue();
    }
}