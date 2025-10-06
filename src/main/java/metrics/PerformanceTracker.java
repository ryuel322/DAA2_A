package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public double getElapsedTimeMs() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        startTime = 0;
        endTime = 0;
    }

    @Override
    public String toString() {
        return String.format("Comparisons: %d | Swaps: %d | Time: %.3f ms",
                comparisons, swaps, getElapsedTimeMs());
    }
}
