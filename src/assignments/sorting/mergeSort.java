package assignments.sorting;

import java.util.Arrays;

/**
 * MergeSort implementation extending the generic SortingAlgorithm superclass.
 *
 * @param <T> element type supporting natural ordering via Comparable
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Dummy constructor to satisfy javadoc requirements.
     */
    public MergeSort() {
        super();
    }

    @Override
    public void sort(T[] array) {
        // base case: arrays of length 0 or 1 are already sorted
        if (array == null || array.length <= 1) {
            return;
        }

        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        // Sort both halves recursively.
        sort(left);
        sort(right);

        // combine both slices back into the main array
        merge(array, left, right);
    }

    private void merge(T[] result, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;

        // Compare items using natural order.
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // drain remaining elements
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }

    public static void main(String[] args) {
        // Validate with the course superclass test runner.
        SortingAlgorithm.validate(new MergeSort<Integer>());
    }
}
