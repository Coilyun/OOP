package ru.nsu.borodkin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ParallelThreadPrimeCheckerTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16, 32, 64, 128})
    @Order(1)
    void testHaveNotPrimeParallel_10(int threadCount) throws InterruptedException {
        ParallelThreadPrimeChecker checker = new ParallelThreadPrimeChecker(threadCount);
        int[] numbers = Numbers.ArrayProvider.getPrimeArray(10);
        assertNotNull(numbers);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers));
        long endTime = System.nanoTime();
        System.out.println(threadCount + " threads | 10 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16, 32, 64, 128})
    @Order(2)
    void testHaveNotPrimeParallel_1_000(int threadCount) throws InterruptedException {
        ParallelThreadPrimeChecker checker = new ParallelThreadPrimeChecker(threadCount);
        int[] numbers = Numbers.ArrayProvider.getPrimeArray(1000);
        assertNotNull(numbers);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers));
        long endTime = System.nanoTime();
        System.out.println(threadCount + " threads | 1 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16, 32, 64, 128})
    @Order(3)
    void testHaveNotPrimeParallel_100_000(int threadCount) throws InterruptedException {
        ParallelThreadPrimeChecker checker = new ParallelThreadPrimeChecker(threadCount);
        int[] numbers = Numbers.ArrayProvider.getPrimeArray(100000);
        assertNotNull(numbers);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers));
        long endTime = System.nanoTime();
        System.out.println(threadCount + " threads | 100 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16, 32, 64, 128})
    @Order(4)
    void testHaveNotPrimeParallel_1_000_000(int threadCount) throws InterruptedException {
        ParallelThreadPrimeChecker checker = new ParallelThreadPrimeChecker(threadCount);
        int[] numbers = Numbers.ArrayProvider.getPrimeArray(1000000);
        assertNotNull(numbers);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers));
        long endTime = System.nanoTime();
        System.out.println(threadCount + " threads | 1 000 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16, 32, 64, 128})
    @Order(5)
    void testHaveNotPrimeParallel_10_000_000(int threadCount) throws InterruptedException {
        ParallelThreadPrimeChecker checker = new ParallelThreadPrimeChecker(threadCount);
        int[] numbers = Numbers.ArrayProvider.getPrimeArray(10000000);
        assertNotNull(numbers);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers));
        long endTime = System.nanoTime();
        System.out.println(threadCount + " threads | 10 000 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
}