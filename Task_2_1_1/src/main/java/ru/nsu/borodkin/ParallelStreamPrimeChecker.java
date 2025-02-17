package ru.nsu.borodkin;

import java.util.Arrays;

import static ru.nsu.borodkin.PrimeUtil.isPrime;

public class ParallelStreamPrimeChecker implements PrimeChecker {
    @Override
    public boolean allPrimes(int[] n) {
        return Arrays.stream(n).parallel().allMatch(PrimeUtil::isPrime);
    }
}
