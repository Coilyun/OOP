package ru.nsu.borodkin;

import static ru.nsu.borodkin.PrimeUtil.isPrime;

public class ParallelThreadPrimeChecker implements PrimeChecker {
    final int threadCount;
    volatile boolean hasComposite;

    public ParallelThreadPrimeChecker(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean allPrimes(int[] numbers) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        int chunkSize = (int) Math.ceil((double) numbers.length / threadCount);
        hasComposite = false;
        for (int i = 0; i < threadCount; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, numbers.length);

            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (!isPrime(numbers[j])) {
                        hasComposite = true;
                    }
                    if (hasComposite) {
                        break;
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return !hasComposite;
    }
}
