package assignments;

public class FibonacciBinet {

    // Binet's Formula: F(n) = (phi^n - psi^n) / sqrt(5)
    public static long fibBinet(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        double sqrt5 = Math.sqrt(5.0);
        double phi = (1.0 + sqrt5) / 2.0;
        double psi = (1.0 - sqrt5) / 2.0;

        return Math.round((Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5);
    }

    public static void main(String[] args) {
        // Warm-up loop
        for (int i = 0; i < 50000; i++) {
            fibBinet(100);
        }

        System.out.println("n,T_seconds");

        //using values from 1000 - 20 mil
        //Since the size of N doesnt matter
        int[] testValues = {
            1000, 2000, 5000, 10000, 50000, 100000, 
            500000, 1000000, 5000000, 10000000, 15000000, 20000000
        };

        for (int n : testValues) {
            long startTime = System.nanoTime();
            fibBinet(n);
            long endTime = System.nanoTime();

            double timeInSeconds = (endTime - startTime) / 1e9;
            System.out.printf("%d,%.9f%n", n, timeInSeconds);
        }
    }
}