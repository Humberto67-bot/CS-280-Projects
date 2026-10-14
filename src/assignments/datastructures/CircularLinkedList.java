package assignments.datastructures;

import adt.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

/// A circular linked list backed by a chain of nodes.
///
/// Each node contains data and a link to the next node.
/// The last node links back to the first node.
/// This allows insertion and deletion without moving other elements.
///
/// @param <T> the type of each element
public class CircularLinkedList<T> implements List<T>, Iterable<T> {
    private Node tail;
    private int size;

    /**
     * Initialize an empty circular linked list.
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
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
        assert 0 <= index && index < this.size;

        Node current = this.tail.link;

        for (int i = 0; i < index; i++) {
            current = current.link;
        }

        return current.data;
    }

    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;

        Node current = this.tail.link;

        for (int i = 0; i < index; i++) {
            current = current.link;
        }

        current.data = value;
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

        Node current = this.tail.link;

        for (int i = 0; i < this.size; i++) {
            if (value == null && current.data == null) {
                return true;
            }

            if (value != null && value.equals(current.data)) {
                return true;
            }

            current = current.link;
        }

        return false;
    }

    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;

        if (this.size == 0) {
            Node newNode = new Node(value, null);
            this.tail = newNode;
            this.tail.link = newNode;
        } else if (index == 0) {
            Node newNode = new Node(value, this.tail.link);
            this.tail.link = newNode;
        } else {
            Node current = this.tail.link;

            for (int i = 0; i < index - 1; i++) {
                current = current.link;
            }

            Node newNode = new Node(value, current.link);
            current.link = newNode;

            if (index == this.size) {
                this.tail = newNode;
            }
        }

        this.size++;
    }

    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;

        Node removed;

        if (this.size == 1) {
            removed = this.tail;
            this.tail = null;
        } else if (index == 0) {
            removed = this.tail.link;
            this.tail.link = removed.link;
        } else {
            Node current = this.tail.link;

            for (int i = 0; i < index - 1; i++) {
                current = current.link;
            }

            removed = current.link;
            current.link = removed.link;

            if (removed == this.tail) {
                this.tail = current;
            }
        }

        this.size--;
        return removed.data;
    }

    /**
     * An encapsulation of a value with a pointer, allowing us to chain to another value.
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node with no children.
         * @param data the data value
         * @param link the next node in the chain
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    /**
     * Returns an iterator to go through the list.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = tail;
            private int count = 0;

            public boolean hasNext() {
                return count < size;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                current = current.link;
                count++;

                return current.data;
            }
        };
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());

        CircularLinkedList<String> list = new CircularLinkedList<>();

        list.insert(0, "A");
        list.insert(1, "B");
        list.insert(2, "C");

        // Test iterator
        String result = "";

        for (String value : list) {
            result += value;
        }

        assert result.equals("ABC");

        // Test deleting elements
        list.delete(0);
        list.delete(0);
        list.delete(0);

        assert list.length() == 0;

        // Test empty iterator
        assert !list.iterator().hasNext();

        System.out.println("CircularLinkedList passes all tests.");
    }
}