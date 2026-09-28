package assignments.sorting;

/**
 * QuickSort implementation extending the generic SortingAlgorithm superclass.
 *
 * @param <T> element type supporting natural ordering via Comparable
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Dummy constructor to satisfy javadoc requirements.
     */
    public QuickSort() {
        super();
    }

    @Override
    public void sort(T[] array) {
        // base case: null or trivially sorted arrays
        if (array == null || array.length <= 1) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            // Partition array around pivot.
            int pivotIndex = partition(array, low, high);

            // Recursively sort left and right partitions
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        // Place elements smaller than pivot to the left.
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        // place pivot into its final position
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        // Validate with the course superclass test runner.
        SortingAlgorithm.validate(new QuickSort<Integer>());
    }
}
