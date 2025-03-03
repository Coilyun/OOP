package ru.nsu.borodkin;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    /**
     *
     impl\count	        |    10     |    1_000   |    100_000   |     1_000_000    |   100_000_000   |
     -------------------------------------------------------------------------------------------------
     consequent	        |  2.0235ms |  0.6387ms  |   70.3677ms	|    2360.5355ms   |   80702.6301ms  |
     parallel stream	|  4.0866ms	|  1.4659ms	 |   24.7077ms	|    1563.9779ms   |   59798.262ms   |
     thread 1	        |  0.2801ms	|  0.3312ms	 |  68.4236ms	|    2373.4553ms   |   80900.6205ms  |
     thread 2	        |  0.3047ms	|  0.3357ms	 |  44.9291ms	|    1549.4902ms   |   54723.7218ms  |
     thread 4	        |  0.5118ms	|  0.4868ms	 |  25.2714ms	|    842.3571ms	   |   36446.658ms   |
     thread 8	        |  0.7773ms	|  0.663ms	 |  32.2149ms	|    602.2999ms	   |   26943.6392ms  |
     thread 16	        |  1.5693ms	|  1.1964ms	 |  46.9553ms	|    1588.7378ms   |   56695.8941ms  |
     thread 32	        |  2.5526ms	|  2.1154ms	 |  46.9501ms	|    1728.941ms	   |   63919.0636ms  |
     thread 64	        |  4.3236ms	|  3.8339ms	 |  46.9313ms	|    1704.7664ms   |   67941.9589ms  |
     thread 128	        |  7.7499ms	|  7.4726ms	 |   35.83ms	|    1625.1344ms   |   67853.4678ms  |
     */

    public static final Pattern NUMBER_SEPARATOR = Pattern.compile("\\s*,\\s*");

    public static void main(String[] args) {

        ConsequentPrimeChecker checker = new ConsequentPrimeChecker();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int[] numbers = NUMBER_SEPARATOR.splitAsStream(input)
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean allPrimes = checker.allPrimes(numbers);
    }
}
