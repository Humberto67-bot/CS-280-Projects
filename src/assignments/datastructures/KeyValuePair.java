package assignments.datastructures;

import adt.OrderedPair;

/// An ordered pair representing a key-value pair using Generics.
public class KeyValuePair<K, V> implements OrderedPair<K, V> {
    private K key;
    private V value;

    /**
     * Initialize a key-value pair.
     * @param key the key (immutable)
     * @param value the value (may be changed later)
     */
    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Extract the key from the key-value pair.
     * @return the key
     */
    public K first() {
        return this.key;
    }

    /**
     * Extract the value from the key-value pair.
     * @return the value
     */
    public V second() {
        return this.value;
    }

    /**
     * Construct a new ordered pair with the order of items reversed.
     * Notice the return type is now explicitly {@code OrderedPair<V, K>}.
     * @return a new ordered pair
     */
    public OrderedPair<V, K> reversed() {
        return new OrderedPair<V, K>() {
            V v = value;
            K k = key;
            
            public V first() { return this.v; }
            
            public K second() { return this.k; }
            
            public OrderedPair<K, V> reversed() { 
                // Using the diamond operator <> prevents raw type warnings
                return new KeyValuePair<>(this.k, this.v); 
            }
        };
    }

    /**
     * Replace the value in the key-value pair.
     * @param value the new value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Represent the key-value pair with a string.
     * @return "{key} => {pair}"
     */
    public String toString() {
        return this.key.toString() + " => " + this.value.toString();
    }

    /**
     * Check if the key-value pair is equal to another object.
     * @param o the other object
     * @return true iff o is a key-value pair and its key equals that of this key-value pair
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof KeyValuePair) {
            // We suppress the warning here because we already checked the instance type above
            KeyValuePair<K, V> other = (KeyValuePair<K, V>) o;
            return this.key.equals(other.key);
        }
        return false;
    }

    /**
     * Compute a hash code for the key-value pair.
     * @return the hash code of the key
     */
    public int hashCode() {
        return this.key.hashCode();
    }
    
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        // Added diamond operators <> throughout main to satisfy the "zero warnings" criteria
        OrderedPair.validate(new KeyValuePair<>("", 0));

        KeyValuePair<String, Integer> pair = new KeyValuePair<>("disciples", 12);

        assert pair.second().equals(12);
        pair.setValue(11);
        assert pair.second().equals(11);
        
        assert pair.toString().equals("disciples => 11");
        assert pair.hashCode() == "disciples".hashCode();

        assert pair.equals(new KeyValuePair<>("disciples", 12));
        assert !pair.equals(new KeyValuePair<>("apostles", 11));

        System.out.println("KeyValuePair passes all tests.");
    }
}