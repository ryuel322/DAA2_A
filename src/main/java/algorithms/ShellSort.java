package algorithms;

import java.util.Arrays;
import metrics.PerformanceTracker;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
        this.tracker = new PerformanceTracker();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        tracker.incrementSwaps();
    }

    public void insert(int value) {
        tracker.start();
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }

        heap[size] = value;
        int current = size;
        size++;

        while (current != 0 && heap[parent(current)] > heap[current]) {
            tracker.incrementComparisons();
            swap(current, parent(current));
            current = parent(current);
        }
        tracker.stop();
    }

    public int extractMin() {
        tracker.start();
        if (size <= 0) throw new IllegalStateException("Heap is empty");
        if (size == 1) {
            tracker.stop();
            return heap[--size];
        }

        int root = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        tracker.stop();
        return root;
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < size) {
            tracker.incrementComparisons();
            if (heap[l] < heap[smallest]) smallest = l;
        }

        if (r < size) {
            tracker.incrementComparisons();
            if (heap[r] < heap[smallest]) smallest = r;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    public void decreaseKey(int i, int newValue) {
        if (i >= size || newValue > heap[i])
            throw new IllegalArgumentException("Invalid decrease-key operation");

        heap[i] = newValue;
        while (i != 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public static MinHeap merge(MinHeap h1, MinHeap h2) {
        MinHeap merged = new MinHeap(h1.size + h2.size);
        for (int i = 0; i < h1.size; i++) merged.insert(h1.heap[i]);
        for (int i = 0; i < h2.size; i++) merged.insert(h2.heap[i]);
        return merged;
    }

    public void printHeap() {
        System.out.println(Arrays.toString(Arrays.copyOf(heap, size)));
    }

    public PerformanceTracker getTracker() {
        return tracker;
    }
}
