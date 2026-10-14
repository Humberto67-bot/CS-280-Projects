package assignments.sorting;

import adt.PriorityQueue;
import java.util.LinkedList;

/**
 * A priority queue that uses a linked list.
 * The smallest item has the highest priority.
 *
 * @param <T> the type of item stored
 */
public class PriorityEnqueuer<T extends Comparable<T>> implements PriorityQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();

    /**
    * Creates an empty priority queue.
    */
    public PriorityEnqueuer() {
    }

    /**
     * Checks if the queue is empty.
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Adds an item to the queue in O(1) time.
     * @param item the item to add
     */
    public void enqueue(T item) {
        queue.addFirst(item);
    }

    /**
     * Removes the largest item from the queue.
     * @return the largest item
     */
    public T dequeue() {
        T highest = peek();
        queue.remove(highest);
        return highest;
    }

    /**
     * Finds the largest item without removing it.
     * @return the largest item
     */
    public T peek() {
        if (queue.isEmpty()) {
            return null;
        }

        T highest = queue.getFirst();

        for (T item : queue) {
            if (item.compareTo(highest) < 0) {
                highest = item;
            }
        }

        return highest;
    }
}
