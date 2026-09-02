package assignments.sorting;

/**
 * Swap adjacent elements over and over until the whole array is sorted.
 * BubbleSort
 */
public class BubbleSort extends SortingAlgorithm {
    
    /**
     * Sort an array in-place using bubble sort.
     * Post condition: array is sorted in ascending order.
     * 
     * @param array an array of integers
     */
    public void sort(Integer[] array) {
        for (int k = array.length; k >= 2; k--) {
            for (int i = 0; i < k - 1; i++) { 
                // Using square brackets for arrays!
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    /**
     * swap two elements within an array.
     * 
     * @param array the array to swap values
     * @param i the first index to swap
     * @param j the second index to swap
     */
    private void swap(Integer[] array, int i, int j) {
        // Using square brackets for arrays!
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * run validation tests
     * @param args command line args
     */
    public static void main(String[] args) {
        // Capitalized BubbleSort
        SortingAlgorithm.validate(new BubbleSort());
        
        // Capitalized System
        System.out.println("BubbleSort has passed all tests.");

        // Fill an array with random numbers
        int N = 6767;
        Integer[] array = new Integer[N];
        
        // Added the missing 'i'
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(N * Math.random());
        }

        // measuring runtime
        SortingAlgorithm sorter = new BubbleSort();
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;

        System.out.println("Array size: " + N);
        System.out.println("Total duration: " + duration);
    }
}