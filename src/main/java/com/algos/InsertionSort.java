package com.algos;

import com.algos.Metrics.InsertionSortMetrics;

public final class InsertionSort {
    private InsertionSort() {}

    public enum Mode { STANDARD, BINARY }

    public static InsertionSortMetrics sort(int[] arr, Mode mode) {
        InsertionSortMetrics m = new InsertionSortMetrics();
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");

        int n = arr.length;
        if (n < 2) return m;

        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            m.arrayAccesses++;
            int key = arr[i];

            m.arrayAccesses++;
            m.comparisons++;
            if (key >= arr[i - 1]) continue;

            int insertPos = (mode == Mode.BINARY)
                    ? binarySearchInsert(arr, key, i - 1, m)
                    : linearSearchInsert(arr, key, i - 1, m);

            shiftRight(arr, insertPos, i, key, m);
        }

        m.executionTimeNs = System.nanoTime() - startTime;
        return m;
    }

    private static int linearSearchInsert(int[] arr, int key, int end, InsertionSortMetrics m) {
        int pos = end;
        while (pos >= 0 && arr[pos] > key) {
            m.comparisons++;
            m.arrayAccesses += 2;
            pos--;
        }
        return pos + 1;
    }

    private static int binarySearchInsert(int[] arr, int key, int high, InsertionSortMetrics m) {
        int low = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            m.arrayAccesses++;
            m.comparisons++;
            if (arr[mid] <= key) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    private static void shiftRight(int[] arr, int from, int to, int key, InsertionSortMetrics m) {
        for (int j = to; j > from; j--) {
            m.arrayAccesses += 2;
            arr[j] = arr[j - 1];
            m.shifts++;
        }
        arr[from] = key;
        m.arrayAccesses++;
        m.shifts++;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}