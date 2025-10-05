package com.algos.Metrics;

public class InsertionSortMetrics {

    public long comparisons = 0;
    public long shifts = 0;
    public long arrayAccesses = 0;
    public long memoryAllocations = 0;
    public long executionTimeNs = 0;

    public void reset() {
        comparisons = 0;
        shifts = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
        executionTimeNs = 0;
    }

    @Override
    public String toString() {
        return String.format(
                "Comparisons: %d | Shifts: %d | Array Accesses: %d | Allocations: %d | Time: %d ns",
                comparisons, shifts, arrayAccesses, memoryAllocations, executionTimeNs);
    }
}