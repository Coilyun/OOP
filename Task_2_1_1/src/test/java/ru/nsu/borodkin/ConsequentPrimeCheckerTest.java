package ru.nsu.borodkin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsequentPrimeCheckerTest {
    @Test
    @Order(1)
    void testHaveNotPrimeSequential_10() {
        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();
        int[] numbers = Numbers.ArrayProvider.getPrimeArray(10);
        assertNotNull(numbers);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers));
        long endTime = System.nanoTime();
        System.out.println("10 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @Test
    @Order(2)
    void testHaveNotPrimeSequential_1_000() {
        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();
        int[] numbers2 = Numbers.ArrayProvider.getPrimeArray(1000);
        assertNotNull(numbers2);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers2));
        long endTime = System.nanoTime();
        System.out.println("1 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @Test
    @Order(3)
    void testHaveNotPrimeSequential_100_000() {
        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();
        int[] numbers3 = Numbers.ArrayProvider.getPrimeArray(100000);
        assertNotNull(numbers3);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers3));
        long endTime = System.nanoTime();
        System.out.println("100 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @Test
    @Order(4)
    void testHaveNotPrimeSequential_1_000_000() {
        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();
        int[] numbers4 = Numbers.ArrayProvider.getPrimeArray(1000000);
        assertNotNull(numbers4);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers4));
        long endTime = System.nanoTime();
        System.out.println("1 000 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @Test
    @Order(5)
    void testHaveNotPrimeSequential_10_000_000() {
        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();
        int[] numbers5 = Numbers.ArrayProvider.getPrimeArray(10000000);
        assertNotNull(numbers5);

        long startTime = System.nanoTime();
        assertTrue(checker.allPrimes(numbers5));
        long endTime = System.nanoTime();
        System.out.println("10 000 000 numbers: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
}
