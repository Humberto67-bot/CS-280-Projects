package assignments.sorting;

/**
 * Swap adjacent elements over and over until the whole array is sorted.
 * BubbleSort
 */
public class BubbleSort extends SortingAlgorithm {
    
    /**
     * Default constructor. Dummy constructor line
     */
    public BubbleSort() {}

    /**
     * Sort an array in-place using bubble sort.
     * Post condition: array is sorted in ascending order.
     * 
     * @param array an array of integers
     */
    @Override
    public void sort(Integer[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int k = array.length; k >= 2; k--) {
            for (int i = 0; i < k - 1; i++) { 
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    /**
     * Swap two elements within an array.
     * 
     * @param array the array to swap values
     * @param i the first index to swap
     * @param j the second index to swap
     */
    private void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * run validation tests
     * @param args command line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new BubbleSort());
        
        System.out.println("BubbleSort has passed all tests.");

        int N = 6767;
        Integer[] array = new Integer[N];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(N * Math.random());
        }

        SortingAlgorithm sorter = new BubbleSort();
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;

        System.out.println("Array size: " + N);
        System.out.println("Total duration: " + duration);
    }
}