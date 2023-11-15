import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class InputOutput {

    public static void main(String[] args) throws FileNotFoundException {

//        int[] length = readInputStdin();
//
//        System.out.println(Timber.TimberDP(length));

        time();

    }

    public static int[] readInputStdin() throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] length = Stream.of(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n != length.length) {
            return new int[]{-1};
        }

        return length;

    }

    public static int[] randomIntArray(int n) {

        Random random = new Random();

        int rangeStart = 1;
        int rangeEnd = 1000;

        int[] randomNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            randomNumbers[i] = random.nextInt(rangeEnd) + rangeStart;
        }

        return randomNumbers;
    }

    public static void time() {

        for (int n = 1; n <= 40; n++) {

            double[] recursiveTimes = new double[3];
            double[] dpTimes = new double[3];
            for (int j = 0; j < 1; j++) {
                int[] length = randomIntArray(n*500);

                double start = System.nanoTime();
                Timber.TimberDP(length);
                double end = System.nanoTime();
                dpTimes[j] = (end - start) / 1000000;
            }

            double dpSum = 0;
            for (double time : dpTimes) {
                dpSum += time;
            }

            double dpAvg = dpSum / dpTimes.length;

            System.out.print(dpAvg);
            System.out.println();

        }

    }

}