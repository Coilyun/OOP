package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {
    @Test
    void testHaveNotPrimeSequential() {
        assertTrue(PrimeChecker.HaveNotPrime(new int[]{6, 8, 7, 13, 5, 9, 4}));
        assertFalse(PrimeChecker.HaveNotPrime(new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053}));
        assertFalse(PrimeChecker.HaveNotPrime(new int[]{2, 3, 5, 7, 11, 13}));
        assertTrue(PrimeChecker.HaveNotPrime(new int[]{4, 6, 8, 10, 12}));
    }

    @Test
    void testHaveNotPrimeParallel() throws InterruptedException {
        assertTrue(PrimeChecker.HaveNotPrimeParallel(new int[]{6, 8, 7, 13, 5, 9, 4}, 4));
        assertFalse(PrimeChecker.HaveNotPrimeParallel(new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053}, 4));
        assertFalse(PrimeChecker.HaveNotPrimeParallel(new int[]{2, 3, 5, 7, 11, 13}, 2));
        assertTrue(PrimeChecker.HaveNotPrimeParallel(new int[]{4, 6, 8, 10, 12}, 3));
    }

    @Test
    void testHaveNotPrimeParallelStream() {
        assertTrue(PrimeChecker.HaveNotPrimeParallelStream(new int[]{6, 8, 7, 13, 5, 9, 4}));
        assertFalse(PrimeChecker.HaveNotPrimeParallelStream(new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053}));
        assertFalse(PrimeChecker.HaveNotPrimeParallelStream(new int[]{2, 3, 5, 7, 11, 13}));
        assertTrue(PrimeChecker.HaveNotPrimeParallelStream(new int[]{4, 6, 8, 10, 12}));
    }
}
