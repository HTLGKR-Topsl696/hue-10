package net.htlgrieskirchen.pos.dreib.towimmer18.subarrays;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        new App().run();
    }

    private int subarraySize;
    private List<Integer> numbers = new ArrayList<>();

    public void loadFile() {
        try {
            List<String> lines = Files.lines(Paths.get("numbers2")).collect(Collectors.toList());
            subarraySize = Integer.parseInt(lines.get(0));
            for (int i = 1; i < lines.size(); ++i) {
                numbers.addAll(Arrays.stream(lines.get(i).split(" ")).map(number -> Integer.parseInt(number.trim()))
                        .collect(Collectors.toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        loadFile();

        List<UniqueCounter> counters = new ArrayList<>();
        for (int i = 0; i <= numbers.size() - subarraySize; ++i) {
            counters.add(new UniqueCounter(numbers.subList(i, i + subarraySize).toArray(new Integer[0])));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<Integer>> results = null;
        try {
            results = executorService.invokeAll(counters);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int max = 0;
        for (Future<Integer> f : results) {
            int val = 0;
            try {
                val = f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            if (max < val) max = val;
        }

        System.out.println(max);
        System.exit(0);
    }
}
