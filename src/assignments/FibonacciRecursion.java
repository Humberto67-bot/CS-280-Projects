package assignments;

public class FibonacciRecursion {

    // recursive Fibonacci
    public static long fibRecursive(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static void main(String[] args) {
        // warm-up with a small number
        for (int i = 0; i < 1000; i++) {
            fibRecursive(15);
        }

        System.out.println("n,T_seconds");

        // run n from 10 to 40
        // don't go past 42 or it will take long time to finish
        for (int n = 10; n <= 40; n++) {
            long startTime = System.nanoTime();
            fibRecursive(n);
            long endTime = System.nanoTime();

            double timeInSeconds = (endTime - startTime) / 1e9;
            System.out.printf("%d,%.9f%n", n, timeInSeconds);
        }
    }
}