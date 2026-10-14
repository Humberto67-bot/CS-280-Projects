package assignments.sorting;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

import adt.Queue;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its less child,
///  and less than or equal to that of its right child.
/// 
/// @param <T> the type of each element, which must have a natural ordering
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Manually construct a tree so we can test iterator methods.
     * 
     * NOTE: You will remove this method once we've learned how to add.
     * 
     * @param demo a specific sequence of numbers defined in the main method
     */
    public void fillForDay1Tests(T[] demo) {
        this.root = new Node(demo[0]);
        this.root.left = new Node(demo[1]);
        this.root.left.left = new Node(demo[2]);
        this.root.left.right = new Node(demo[3]);
        this.root.left.left.right = new Node(demo[4]);
        this.root.left.right.left = new Node(demo[5]);
        this.root.left.left.right.right = new Node(demo[6]);
        this.root.right = new Node(demo[7]);
        this.root.left.left.right.right.left = new Node(demo[8]);
        this.root.left.right.right = new Node(demo[9]);
        this.root.left.right.left.right = new Node(demo[10]);
        this.root.left.right.left.left = new Node(demo[11]);
        this.root.left.right.right.right = new Node(demo[12]);
        this.root.left.right.left.right.right = new Node(demo[13]);
        this.root.left.left.right.right.right = new Node(demo[14]);
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        return count(root);
    }

    private int count(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + count(node.left) + count(node.right);
    }

    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        return getDegree(root);
    }

    private int getDegree(Node node) {
        if (node == null) {
            return 0;
        }

        int children = 0;

        if (node.left != null) {
            children++;
        }

        if (node.right != null) {
            children++;
        }

        return Math.max(children,
            Math.max(getDegree(node.left), getDegree(node.right)));
    }

    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        ArrayList<T> list = new ArrayList<>();
        pre(root, list);
        return list.iterator();
    }

    private void pre(Node node, ArrayList<T> list) {
        if (node == null) {
            return;
        }

        list.add(node.data);
        pre(node.left, list);
        pre(node.right, list);
    }

    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        ArrayList<T> list = new ArrayList<>();
        in(root, list);
        return list.iterator();
    }

    private void in(Node node, ArrayList<T> list) {
        if (node == null) {
            return;
        }

        in(node.left, list);
        list.add(node.data);
        in(node.right, list);
    }

    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        ArrayList<T> list = new ArrayList<>();
        post(root, list);
        return list.iterator();
    }

    private void post(Node node, ArrayList<T> list) {
        if (node == null) {
            return;
        }

        post(node.left, list);
        post(node.right, list);
        list.add(node.data);
    }

    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        ArrayList<T> list = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            list.add(node.data);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return list.iterator();
    }

    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }

    // NOTE: You're going to add more public methods here on the second day of trees.

    /**
     * Checks if an item is in the tree.
     * @param item the item to look for
     * @return true if found, false if not
     */
    public boolean contains(T item) {
        Node current = root;

        while (current != null) {
            int result = item.compareTo(current.data);

            if (result == 0) {
                return true;
            } else if (result < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    /**
     * Adds an item to the tree.
     * @param item the item to add
     */
    public void add(T item) {
        root = addNode(root, item);
    }

    private Node addNode(Node node, T item) {
        if (node == null) {
            return new Node(item);
        }

        if (item.compareTo(node.data) < 0) {
            node.left = addNode(node.left, item);
        } else {
            node.right = addNode(node.right, item);
        }

        return node;
    }

    /**
     * Removes an item from the tree.
     * @param item the item to remove
     */
    public void remove(T item) {
        root = removeNode(root, item);
    }

    private Node removeNode(Node node, T item) {
        if (node == null) {
            return null;
        }

        if (item.compareTo(node.data) < 0) {
            node.left = removeNode(node.left, item);
        } else if (item.compareTo(node.data) > 0) {
            node.right = removeNode(node.right, item);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node smallest = node.right;

            while (smallest.left != null) {
                smallest = smallest.left;
            }

            node.data = smallest.data;
            node.right = removeNode(node.right, smallest.data);
        }

        return node;
    }

    /**
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new BinarySearchTree<Integer>());

        // Build a sample tree.
        Integer[] numbers = {4, -4, -6, 0, -6, -3, -5, 5, -6, 0, -3, -4, 3, -3, -5};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.fillForDay1Tests(numbers);     // NOTE: To be replaced once we learn how to add.

        // Check structure.
        assert tree.length() == 15;
        assert tree.degree() == 2;
        assert tree.height() == 6;

        // Check traversals.
        assert testTraversal(tree.preorder(),   new int[]{4, -4, -6, -6, -5, -6, -5, 0, -3, -4, -3, -3, 0, 3, 5});
        assert testTraversal(tree.inorder(),    new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        assert testTraversal(tree.postorder(),  new int[]{-6, -5, -5, -6, -6, -4, -3, -3, -3, 3, 0, 0, -4, 5, 4});
        assert testTraversal(tree.levelorder(), new int[]{4, -4, 5, -6, 0, -6, -3, 0, -5, -4, -3, 3, -6, -5, -3});
        assert testTraversal(tree.iterator(),   new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});

        // Check contains.
        assert tree.contains(4);
        assert tree.contains(-6);
        assert !tree.contains(100);

        // Check add.
        tree.add(10);
        assert tree.contains(10);
        assert tree.length() == 16;

        // Check remove.
        tree.remove(10);
        assert !tree.contains(10);
        assert tree.length() == 15;

        // Check removing a value with children.
        tree.remove(4);
        assert !tree.contains(4);
        assert tree.length() == 14;

        System.out.println("BinarySearchTree passes all tests.");
    }

    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        boolean expected = true;
        for (int i = 0; i < array.length; i ++) {
            Integer next = iterator.next();
            expected = expected && next.equals(array[i]);
        }
        return expected && !iterator.hasNext();
    }
}
