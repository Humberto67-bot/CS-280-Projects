package assignments.sorting;

/**
 * implementation of the SelectionSort algorithm.
 */
public class SelectionSort extends SortingAlgorithm {

    /**
     * Default constructor.
     */
    public SelectionSort() {}

    /**
     * sorts an array of integers in-place using the SelectionSort algorithm
     * 
     * @param array the array to be sorted
     */
    @Override
    public void sort(Integer[] array) {
        int n = array.length;

        // one by one moves the boundary of the unsortd subarray
        for (int i = 0; i< n - 1; i++) {
            //find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }

            //swap the minimum found element with the first element
            Integer temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }

    /**
     * run validation tests
     * @param args command line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new SelectionSort());
    }
}