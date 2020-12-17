package net.htlgrieskirchen.pos.dreib.towimmer18.subarrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class UniqueCounter implements Callable<Integer> {
    private final Integer[] numbers;

    public UniqueCounter(Integer[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() {
        Set<Integer> set = new HashSet<>();
        Arrays.stream(numbers).forEach(number -> set.add(number));
        return set.size();
    }
}
