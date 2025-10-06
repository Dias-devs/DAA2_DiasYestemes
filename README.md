- Insertion Sort Performance Analysis
- Overview

This project implements and evaluates the Insertion Sort algorithm in Java, measuring its performance under different conditions. It records comparisons, shifts, and execution time, then exports the results for analysis and visualization.
The goal is to study how Insertion Sort behaves across various input types and data sizes.

- Components
1. InsertionSort.java

Implements the Insertion Sort algorithm.

Tracks key performance metrics:

🔸 Number of comparisons

🔸 Number of shifts (data movements)

🔸 Execution time (nanoseconds)

Includes a utility method isSorted() to verify sorted results.

2. InsertionSortMetrics.java

Stores performance statistics.

Provides formatted string output for clear reporting.

3. InsertionSortCLI.java

Interactive command-line interface.

Allows users to:

Select array size.

Choose input type (Sorted, Reverse, Random).

Select sorting mode (Standard or Binary search insertion).

Displays results and can export data to a CSV file.

4. InsertionSortBenchmark.java

Automates experiments with multiple array sizes.

Records average comparisons, shifts, and times.

Exports results to benchmark_results.csv.

5. InsertionSortTest.java

JUnit 5 tests verifying correctness:

Empty arrays

Sorted arrays

Reverse-sorted arrays

Random arrays

- Performance Visualization

Results are plotted using a Python script (PlotBenchmarkResults.py).
Generated plots are saved in the performance-plots/ folder.

Generated Graphs

() Comparisons vs Array Size – Growth of element comparisons with increasing input.

() Shifts vs Array Size – Number of data movements during sorting.

() Time vs Array Size – Execution time showing approximate O(n²) behavior.

- Insights

Best case (sorted data): Few comparisons and shifts (≈ O(n)).

Worst case (reverse data): Many shifts and comparisons (≈ O(n²)).

Average case (random data): Still quadratic trend.

Efficient for small datasets, but not scalable for large ones.

- Technologies Used

Java 17+

JUnit 5

Maven

Python (pandas, matplotlib) for visualization
