package assignments.sorting;

/**
 * Bubble Sort implementation working on generic Comparable types.
 *
 * @param <T> the type of elements to be sorted
 */
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Default constructor for documentation purposes.
     */
    public BubbleSort() {}

    @Override
    public void sort(T[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        SortingAlgorithm.validate(new BubbleSort<>());
    }
}