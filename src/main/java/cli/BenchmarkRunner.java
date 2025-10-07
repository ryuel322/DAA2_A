package cli;

import algorithms.ShellSort;
import metrics.PerformanceTracker;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {

        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            String[] kv = arg.split("=");
            if (kv.length == 2) params.put(kv[0], kv[1]);
        }

        int n = Integer.parseInt(params.getOrDefault("size", "1000"));
        String algorithm = params.getOrDefault("algorithm", "shell");
        String gap = params.getOrDefault("gaps", "ciura");
        String dist = params.getOrDefault("dist", "random");
        int runs = Integer.parseInt(params.getOrDefault("runs", "5"));
        int warmup = Integer.parseInt(params.getOrDefault("warmup", "2"));
        int seed = Integer.parseInt(params.getOrDefault("seed", "42"));

        Random rnd = new Random(seed);
        PerformanceTracker tracker = new PerformanceTracker();

        
        String csvName = String.format("benchmark-%s-%s-%s-%d.csv", algorithm, gap, dist, n);
        try (PrintWriter writer = new PrintWriter(new File(csvName))) {
            writer.println("run,n,timeNs,comparisons,swaps");

            for (int r = 0; r < runs + warmup; r++) {
                int[] arr = generateArray(n, dist, rnd);
                ShellSort sorter = new ShellSort(tracker);
                tracker.reset();

                tracker.start();
                sorter.sort(arr);
                tracker.stop();

                if (r >= warmup) {
                    writer.printf("%d,%d,%d,%d,%d%n", r - warmup + 1, n,
                            tracker.getTimeNs(), tracker.getComparisons(), tracker.getSwaps());
                }
            }
        }

        System.out.println("Benchmark finished. CSV: " + csvName);
    }

    private static int[] generateArray(int n, String dist, Random rnd) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(1000000);

        switch (dist) {
            case "sorted": Arrays.sort(arr); break;
            case "rev": Arrays.sort(arr); for (int i = 0; i < n / 2; i++) {
                int tmp = arr[i]; arr[i] = arr[n - 1 - i]; arr[n - 1 - i] = tmp;
            } break;
            case "nearly": Arrays.sort(arr);
                for (int i = 0; i < n / 20; i++) { 
                    int a = rnd.nextInt(n), b = rnd.nextInt(n);
                    int tmp = arr[a]; arr[a] = arr[b]; arr[b] = tmp;
                } break;
            case "random": default: break;
        }
        return arr;
    }
}

