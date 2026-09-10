package assignments.algorithms;

import assignments.sorting.InsertionSort;

public class ERADemo {
    public static void main(String[] args) {
        for (int N = 10; N < 100; N += 10) {
            Integer[] array = randomArray(N);
            InsertionSort<Integer> sorter = new InsertionSort<>();
            long start = System.nanoTime();
            sorter.sort(array);
            long end = System.nanoTime();
            double duration = (end - start) / 1e9;
            System.out.println(N + "\t" + duration);
        }
    }

    private static Integer[] randomArray(int N) {
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * N);
        }
        return array;
    }
}


//*
// class notes 
// CSV : comms seperated values
// -text file of data values seperated by a "delimiter".
// > java - cp lib/* src/assignments/algorithms/ERADemo.java >> dat/instertiondata.csv
// > mkdir dat
// worst case: O(N^2)
// best case O(N)
// average: O(N^k)
// T(N) = C . N^k + o(N^k)
// ^runtime ^(N)-> size of array
//i want -> y = m . x + b
//