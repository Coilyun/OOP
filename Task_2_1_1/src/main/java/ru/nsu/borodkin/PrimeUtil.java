package ru.nsu.borodkin;

public class PrimeUtil {

    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        else if (num % 2 == 0){
            return false;
        }
        for (int i = 3; i <= Math.sqrt(num) + 1; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private PrimeUtil() {
        throw new UnsupportedOperationException();
    }
}
