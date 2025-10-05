import com.algos.InsertionSort;
import com.algos.Metrics.InsertionSortMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class InsertionSortTest {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        InsertionSortMetrics metrics = InsertionSort.sort(arr, InsertionSort.Mode.STANDARD);

        assertTrue(InsertionSort.isSorted(arr), "Empty array should remain sorted");
        assertEquals(0, metrics.comparisons, "No comparisons expected on empty array");
        assertEquals(0, metrics.shifts, "No shifts expected on empty array");
    }

    @Test
    public void testSingleElementArray() {
        int[] arr = {42};
        InsertionSortMetrics metrics = InsertionSort.sort(arr, InsertionSort.Mode.STANDARD);

        assertTrue(InsertionSort.isSorted(arr), "Single-element array should remain sorted");
        assertEquals(0, metrics.shifts, "No shifts expected for single-element array");
        assertEquals(0, metrics.comparisons,
                "No comparisons expected for single-element array");
        assertEquals(42, arr[0], "Single element should remain unchanged");
    }

    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        InsertionSortMetrics metrics = InsertionSort.sort(arr, InsertionSort.Mode.STANDARD);

        assertTrue(InsertionSort.isSorted(arr), "Array should remain sorted");
        assertTrue(metrics.shifts <= arr.length * 2,
                "Expected few shifts for sorted input");
        assertTrue(metrics.comparisons > 0,
                "Some comparisons should still occur for sorted input");
    }

    @Test
    public void testUnsortedArray() {
        int[] arr = {9, 7, 5, 3, 1};
        InsertionSortMetrics metrics = InsertionSort.sort(arr, InsertionSort.Mode.STANDARD);

        assertTrue(InsertionSort.isSorted(arr), "Array should be sorted after insertion sort");
        assertTrue(metrics.shifts > 0, "Should perform shifts for unsorted array");
        assertTrue(metrics.comparisons > 0, "Should perform comparisons for unsorted array");
    }

    @Test
    public void testRandomArray() {
        Random rand = new Random(42);
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt(1000);

        int[] expected = arr.clone();
        Arrays.sort(expected);

        InsertionSort.sort(arr, InsertionSort.Mode.BINARY);
        assertArrayEquals(expected, arr,
                "Binary insertion sort must produce the same result as Arrays.sort()");
    }
}