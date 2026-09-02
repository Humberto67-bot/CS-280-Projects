package assignments.sorting;

/**
 * 
 * InsertionSort
 */
public class InsertionSort extends SortingAlgorithm{

    /**
     * default constructor
     */

    public InsertionSort() {
        //Empty constructor
    }

    /**
     * sorts an array of integers using the insertion sort algoritm
     * 
     * @param array helps to sort the array 
     */
    @Override
    public void sort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            // Moves elements of arr that are greater than 
            // the number to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /**
    * run validation tests
    * @param args command line args
    */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort());
    }
    
}
