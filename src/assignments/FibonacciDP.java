package assignments;

public class FibonacciDP {

    public static long fibDP(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long prev = 0;
        long curr = 1;

        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }

        return curr;
    }

    public static void main(String[] args) {
        // Warm-up loop
        for (int i = 0; i < 20000; i++) {
            fibDP(500);
        }

        System.out.println("n,T_seconds");

        for (int n = 1000; n <= 26000; n += 1000) {
            long startTime = System.nanoTime();
            fibDP(n);
            long endTime = System.nanoTime();

            double timeInSeconds = (endTime - startTime) / 1e9;
            System.out.printf("%d,%.9f%n", n, timeInSeconds);
        }
    }
}