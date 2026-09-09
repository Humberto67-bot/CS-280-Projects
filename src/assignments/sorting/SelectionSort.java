package assignments.sorting;

/**
 * Selection Sort implementation working on generic Comparable types.
 *
 * @param <T> the type of elements to be sorted
 */
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Default constructor for documentation purposes.
     */
    public SelectionSort() {}

    @Override
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        SortingAlgorithm.validate(new SelectionSort<>());
    }
}