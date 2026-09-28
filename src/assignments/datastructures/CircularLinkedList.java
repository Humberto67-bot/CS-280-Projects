package assignments.datastructures;

import adt.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Circular singly linked list implementation for the adt.List interface.
 * Maintains a reference to the tail node, where tail.next represents head.
 *
 * @param <E> element type stored in the list
 */
public class CircularLinkedList<E> implements List<E>, Iterable<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> tail;
    private int size;

    /**
     * Constructs an empty circular linked list.
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the list length
     */
    @Override
    public int length() {
        return size;
    }

    /**
     * Helper returning current size.
     *
     * @return number of items
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if size is 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an element to the front of the circle.
     *
     * @param data value to insert
     */
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data, null);
        if (isEmpty()) {
            tail = newNode;
            newNode.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    /**
     * Adds an element to the back of the circle.
     *
     * @param data value to append
     */
    public void addLast(E data) {
        addFirst(data);
        tail = tail.next;
    }

    /**
     * Inserts an element at the given index.
     *
     * @param index insertion position
     * @param data element to insert
     */
    @Override
    public void insert(int index, E data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node<E> prev = getNode(index - 1);
            prev.next = new Node<>(data, prev.next);
            size++;
        }
    }

    /**
     * Gets the element at the specified index.
     *
     * @param index element position
     * @return data at index
     */
    @Override
    public E at(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    /**
     * Updates the element at the given index.
     *
     * @param index position to replace
     * @param data new element value
     */
    @Override
    public void set(int index, E data) {
        checkIndex(index);
        getNode(index).data = data;
    }

    /**
     * Deletes and returns the element at the given index.
     *
     * @param index position to remove
     * @return removed element
     */
    @Override
    public E delete(int index) {
        checkIndex(index);
        if (index == 0) return removeFirst();

        Node<E> prev = getNode(index - 1);
        Node<E> target = prev.next;
        prev.next = target.next;
        if (target == tail) tail = prev;
        size--;
        return target.data;
    }

    /**
     * Removes and returns the first element.
     *
     * @return data from the removed head
     */
    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Node<E> head = tail.next;
        if (size == 1) {
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        return head.data;
    }

    /**
     * Checks if the list contains a given value.
     *
     * @param data target value
     * @return true if present, false otherwise
     */
    @Override
    public boolean contains(E data) {
        if (isEmpty()) return false;
        Node<E> curr = tail.next;
        for (int i = 0; i < size; i++) {
            if (data == null ? curr.data == null : data.equals(curr.data)) return true;
            curr = curr.next;
        }
        return false;
    }

    private Node<E> getNode(int index) {
        Node<E> curr = tail.next;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    /**
     * Returns an iterator that visits each item in the circle once.
     *
     * @return Iterator instance
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curr = (tail == null) ? null : tail.next;
            private int count = 0;

            @Override
            public boolean hasNext() { return count < size; }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E val = curr.data;
                curr = curr.next;
                count++;
                return val;
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail.next;
        for (int i = 0; i < size; i++) {
            sb.append(curr.data).append(i < size - 1 ? " -> " : "");
            curr = curr.next;
        }
        return sb.append("]").toString();
    }

    /**
     * Test runner verifying iterator and list operations.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();

        // inserts
        list.addFirst("B");
        list.addFirst("A");
        list.addLast("D");
        list.insert(2, "C");

        if (list.length() != 4 || !"A".equals(list.at(0)) || !"D".equals(list.at(3))) {
            throw new AssertionError("Insertion check failed");
        }

        // set and contains
        list.set(1, "Beta");
        if (!list.contains("Beta") || list.contains("Ghost")) {
            throw new AssertionError("Set or contains failed");
        }

        // iterator test
        String joined = "";
        for (String s : list) joined += s + " ";
        if (!"A Beta C D ".equals(joined)) {
            throw new AssertionError("Iterator traversal failed");
        }

        // deletes down to empty
        if (!"A".equals(list.delete(0)) ||
            !"D".equals(list.delete(list.length() - 1)) ||
            !"Beta".equals(list.removeFirst()) ||
            !"C".equals(list.delete(0)) ||
            !list.isEmpty()) {
            throw new AssertionError("Deletion checks failed");
        }

        System.out.println("All tests passed!");
    }
}