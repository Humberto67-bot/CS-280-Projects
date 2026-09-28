package assignments.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import adt.List;
import adt.Queue;

/// A circular singly-linked list backed by node links.
/// 
/// The last node links back to the first node.
/// Keeping a tail pointer gives us quick access to both the start and end.
/// 
/// @param <T> the type of each element
public class CircularLinkedList<T> implements List<T>, Queue<T>, Iterable<T> {

    // internal node class
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    // Points to the very last node in the cycle
    private Node<T> tail;
    // size of the list
    private int size;

    /**
     * Initialize an empty circular linked list.
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this collection.
     * @return the number of items
     */
    public int length() {
        return this.size;
    }

    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        return getNode(index).data;
    }

    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        getNode(index).data = value;
    }

    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        if (this.size == 0) {
            return false;
        }

        // start searching from head
        Node<T> current = this.tail.next;
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(current.data, value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index <= this.size;

        Node<T> newNode = new Node<>(value);

        if (this.size == 0) {
            // first element points to itself
            newNode.next = newNode;
            this.tail = newNode;
        } else if (index == 0) {
            // insert at front
            newNode.next = this.tail.next;
            this.tail.next = newNode;
        } else if (index == this.size) {
            // Insert at the end and update tail reference
            newNode.next = this.tail.next;
            this.tail.next = newNode;
            this.tail = newNode;
        } else {
            // walk up to the spot
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }

        this.size++;
    }

    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        T removedData;

        if (this.size == 1) {
            // only one item left
            removedData = this.tail.data;
            this.tail = null;
        } else if (index == 0) {
            // removing front node
            Node<T> head = this.tail.next;
            removedData = head.data;
            this.tail.next = head.next;
        } else {
            Node<T> prev = getNode(index - 1);
            Node<T> target = prev.next;
            removedData = target.data;
            prev.next = target.next;

            // update tail if we deleted the last node
            if (index == this.size - 1) {
                this.tail = prev;
            }
        }

        this.size--;
        return removedData;
    }

    // helper to step through nodes to an index
    private Node<T> getNode(int index) {
        Node<T> current = this.tail.next; // start at head
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // --- Queue methods ---

    /**
     * Add an item to the back of the queue.
     * @param value the item to add
     */
    @Override
    public void enqueue(T value) {
        // just insert at the end of the list
        insert(this.size, value);
    }

    /**
     * Remove and return the front item.
     * @return front item
     */
    @Override
    public T dequeue() {
        assert this.size > 0;
        // takes from the front
        return delete(0);
    }

    /**
     * Look at the front item without removing it.
     * @return front item
     */
    @Override
    public T peek() {
        assert this.size > 0;
        return at(0);
    }

    /**
     * Check if queue is empty
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // --- Iterator and main ---

    /**
     * Returns an iterator over the elements in this list.
     * @return an Iterator over the elements
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            // start at head if it exists
            private Node<T> current = (tail == null) ? null : tail.next;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                cursor++;
                return data;
            }
        };
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());
        Queue.validate(new CircularLinkedList<>());

        System.out.println("CircularLinkedList passes all tests.");
    }
}