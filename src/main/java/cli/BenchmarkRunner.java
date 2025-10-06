package cli;

import algorithms.MinHeap;
import java.util.Random;

public class HeapRunner {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(20);
        Random random = new Random();

        System.out.println("Inserting random numbers...");
        for (int i = 0; i < 10; i++) {
            int value = random.nextInt(100);
            heap.insert(value);
            System.out.print(value + " ");
        }

        System.out.println("\nHeap after insertions:");
        heap.printHeap();

        System.out.println("Extracting minimum: " + heap.extractMin());
        heap.printHeap();

        System.out.println("\nPerformance stats:");
        System.out.println(heap.getTracker());
    }
}
