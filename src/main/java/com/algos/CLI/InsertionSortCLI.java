package com.algos.CLI;

import com.algos.InsertionSort;
import com.algos.Metrics.InsertionSortMetrics;

import java.util.Random;
import java.util.Scanner;

public class InsertionSortCLI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Insertion Sort Performance Tester ===\n");

        while (true) {
            System.out.print("Enter array size (or 0 to exit): ");
            int size = scanner.nextInt();
            if (size == 0) break;

            System.out.print("Choose data type (1=Sorted, 2=Reverse, 3=Random): ");
            int type = scanner.nextInt();

            System.out.print("Choose mode (1=STANDARD, 2=BINARY): ");
            int modeChoice = scanner.nextInt();

            int[] arr = generateArray(size, type);
            InsertionSort.Mode mode = (modeChoice == 2)
                    ? InsertionSort.Mode.BINARY
                    : InsertionSort.Mode.STANDARD;

            System.out.println("\nSorting...");
            InsertionSortMetrics metrics = InsertionSort.sort(arr, mode);

            System.out.println("\n--- Sort Results ---");
            System.out.println("Mode: " + mode);
            System.out.println("Array size: " + size);
            System.out.println(metrics);
            System.out.println("Is sorted: " + InsertionSort.isSorted(arr));

            System.out.print("\nRun again? (y/n): ");
            String cont = scanner.next();
            if (!cont.equalsIgnoreCase("y")) break;
        }

        System.out.println("\nExiting program. Goodbye!");
    }

    private static int[] generateArray(int size, int type) {
        int[] arr = new int[size];
        Random rand = new Random();

        switch (type) {
            case 1 -> {
                for (int i = 0; i < size; i++) arr[i] = i;
            }
            case 2 -> {
                for (int i = 0; i < size; i++) arr[i] = size - i;
            }
            case 3 -> {
                for (int i = 0; i < size; i++) arr[i] = rand.nextInt(size * 10);
            }
            default -> throw new IllegalArgumentException("Invalid array type: " + type);
        }

        return arr;
    }
}