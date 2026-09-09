package assignments.sorting;

/**
 * Insertion Sort implementation working on generic Comparable types.
 *
 * @param <T> the type of elements to be sorted
 */
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Default constructor for documentation purposes.
     */
    public InsertionSort() {}

    @Override
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort<>());
    }
}