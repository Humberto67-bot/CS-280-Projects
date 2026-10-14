package assignments.sorting;

import adt.PriorityQueue;
import java.util.LinkedList;

/**
 * A priority queue that uses a linked list.
 * The smallest item has the highest priority.
 * Items are kept in order.
 *
 * @param <T> the type of item stored
 */
public class PriorityDequeuer<T extends Comparable<T>> implements PriorityQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();

    /**
     * Creates an empty priority queue.
     */
    public PriorityDequeuer() {
    }

    /**
     * Checks if the queue is empty.
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Adds an item to the queue in order.
     * This takes O(n) time.
     * @param item the item to add
     */
    public void enqueue(T item) {
        int i = 0;

        while (i < queue.size() && queue.get(i).compareTo(item) <= 0) {
            i++;
        }

        queue.add(i, item);
    }

    /**
     * Removes the smallest item from the queue.
     * This takes O(1) time.
     * @return the smallest item
     */
    public T dequeue() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.removeFirst();
    }

    /**
     * Finds the smallest item without removing it.
     * This takes O(1) time.
     * @return the smallest item
     */
    public T peek() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.getFirst();
    }
}

