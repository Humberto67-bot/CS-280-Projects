package assignments.sorting;

/**
 * Insertion sort implementation.
 */
public class InsertionSort extends SortingAlgorithm {

    /**
     * Default constructor.
     */
    public InsertionSort() {}

    /**
     * Sorts an array of integers using the insertion sort algorithm.
     * 
     * @param array an array of integers to sort
     */
    @Override
    public void sort(Integer[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            Integer key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}