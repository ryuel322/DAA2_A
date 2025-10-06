package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {

    @Test
    public void testEdgeCases() {
        PerformanceTracker t = new PerformanceTracker();
        int[] empty = new int[0];
        ShellSort.sort(empty, ShellSort.GapType.CIURA, t);
        assertEquals(0, empty.length);

        int[] single = new int[]{5};
        ShellSort.sort(single, ShellSort.GapType.CIURA, t);
        assertArrayEquals(new int[]{5}, single);
    }

    @Test
    public void testRandomMatchesJavaSort() {
        Random rnd = new Random(42);
        for (int it = 0; it < 20; it++) {
            int n = 200;
            int[] a = rnd.ints(n, -1000, 1000).toArray();
            int[] b = Arrays.copyOf(a, a.length);
            ShellSort.sort(a, ShellSort.GapType.CIURA, new PerformanceTracker());
            Arrays.sort(b);
            assertArrayEquals(b, a);
        }
    }
}
