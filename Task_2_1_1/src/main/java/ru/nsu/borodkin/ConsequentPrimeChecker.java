package ru.nsu.borodkin;

import static ru.nsu.borodkin.PrimeUtil.isPrime;

public class ConsequentPrimeChecker implements PrimeChecker {
    @Override
    public boolean allPrimes(int[] n) {
        for (int j : n) {
            if (!isPrime(j)) {
                return false;
            }
        }
        return true;
    }
}
