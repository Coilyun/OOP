package ru.nsu.borodkin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PrimeChecker {

    public static boolean isPrime(int num){
        if (num == 2) return true;
        else if(num % 2 == 0) return false;
        for(int i = 3; i <= Math.sqrt(num)+1; i += 2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean HaveNotPrime(int[] n){
        for(int i = 0; i< n.length; i++){
            if(!isPrime(n[i])){
                return true;
            }
        }
        return false;
    }

    public static boolean HaveNotPrimeParallel(int[] numbers, int threadCount) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        int chunkSize = (int) Math.ceil((double) numbers.length / threadCount);
        boolean[] results = new boolean[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, numbers.length);
            final int index = i;

            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (!isPrime(numbers[j])) {
                        results[index] = true;
                        break;
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (boolean result : results) {
            if (result) return true;
        }
        return false;
    }

    public static boolean HaveNotPrimeParallelStream(int[] n){
        return Arrays.stream(n).parallel().anyMatch(i -> !isPrime(i));
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] numbers = Arrays.stream(input.replaceAll("\\s", "").split(","))
                .mapToInt(Integer::parseInt)
                .toArray();


        System.out.println(HaveNotPrime(numbers));
    }
}