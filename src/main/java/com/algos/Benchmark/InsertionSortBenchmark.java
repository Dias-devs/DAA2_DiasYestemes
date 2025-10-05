package com.algos.Benchmark;

import com.algos.InsertionSort;
import com.algos.Metrics.InsertionSortMetrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Formatter;

/**
 * Automated benchmark for Insertion Sort (Standard vs Binary).
 * Writes formatted CSV that also looks good when opened in a text editor.
 */
public class InsertionSortBenchmark {

    private static final int[] SIZES = {100, 500, 1000, 5000, 10000};
    private static final int TRIALS = 5;

    public static void main(String[] args) {
        String fileName = "benchmark_results.csv";
        System.out.println("=== Insertion Sort Benchmark ===");
        System.out.println("Writing results to: " + fileName);

        try (FileWriter writer = new FileWriter(fileName);
             Formatter formatter = new Formatter(writer)) {

            String header = String.format(
                    "%-10s | %-8s | %-6s | %-15s | %-15s | %-15s | %-15s%n",
                    "Mode", "Type", "Size", "TotalComparisons", "TotalShifts",
                    "AvgComparisons", "AvgShifts");
            writer.write(header);
            writer.write("-".repeat(header.length()) + "\n");

            for (InsertionSort.Mode mode : InsertionSort.Mode.values()) {
                for (int type = 1; type <= 3; type++) {
                    for (int size : SIZES) {
                        long totalComparisons = 0;
                        long totalShifts = 0;

                        for (int t = 0; t < TRIALS; t++) {
                            int[] arr = generateArray(size, type);
                            InsertionSortMetrics metrics = InsertionSort.sort(arr, mode);
                            totalComparisons += metrics.comparisons;
                            totalShifts += metrics.shifts;
                        }

                        long avgComparisons = totalComparisons / TRIALS;
                        long avgShifts = totalShifts / TRIALS;

                        formatter.format("%-10s | %-8s | %-6d | %-15d | %-15d | %-15d | %-15d%n",
                                mode,
                                arrayTypeName(type),
                                size,
                                totalComparisons,
                                totalShifts,
                                avgComparisons,
                                avgShifts);

                        formatter.flush();
                        System.out.printf("%s | %s | n=%d done%n",
                                mode, arrayTypeName(type), size);
                    }
                }
            }

            System.out.println("\nBenchmark complete! Data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    private static String arrayTypeName(int type) {
        return switch (type) {
            case 1 -> "Sorted";
            case 2 -> "Reverse";
            case 3 -> "Random";
            default -> "Unknown";
        };
    }

    private static int[] generateArray(int size, int type) {
        int[] arr = new int[size];
        Random rand = new Random();

        switch (type) {
            case 1 -> { for (int i = 0; i < size; i++) arr[i] = i; }
            case 2 -> { for (int i = 0; i < size; i++) arr[i] = size - i; }
            case 3 -> { for (int i = 0; i < size; i++) arr[i] = rand.nextInt(size * 10); }
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        }
        return arr;
    }
}