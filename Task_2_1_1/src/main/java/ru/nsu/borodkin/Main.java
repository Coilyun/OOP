package ru.nsu.borodkin;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    /**
     * impl\count                   10             1_000              1_000_000         100_000_000
     * consequent
     * parallel stream
     * thread 1
     * thread 2
     * thread 4
     * thread 8                                                      
     * thread 16
     * thread 32
     * thread 64
     * thread 128
     */
    public static final Pattern NUMBER_SEPARATOR = Pattern.compile("\\s*,\\s*");

    public static void main(String[] args) {

        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int[] numbers = NUMBER_SEPARATOR.splitAsStream(input)
                .mapToInt(Integer::parseInt)
                .toArray();

        long startTime = System.nanoTime();
        boolean allPrimes = checker.allPrimes(numbers);
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);
    }
}
