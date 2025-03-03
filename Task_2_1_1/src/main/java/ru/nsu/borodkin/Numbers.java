package ru.nsu.borodkin;

public class Numbers {
    public static class ArrayProvider {
        public static int[] getPrimeArray(int size) {
            if (size > 10_000_000) {
                System.out.println("Запрошен слишком большой массив: " + size);
                return null; // Предотвращение нехватки памяти
            }
            int[] primes = new int[size];
            int count = 0, num = 2;
            while (count < size) {
                if (isPrime(num)) {
                    primes[count++] = num;
                }
                num++;
            }
            return primes;
        }

        private static boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }


}
