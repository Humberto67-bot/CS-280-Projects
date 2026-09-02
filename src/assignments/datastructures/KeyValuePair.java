package assignments.datastructures;

import adt.OrderedPair;

/// An ordered pair representing a key-value pair using Generics.
public class KeyValuePair implements OrderedPair<String, Integer> {
    private String key;
    private Integer value;

    /**
     * Initialize a key-value pair.
     * @param key the key (immutable)
     * @param value the value (may be changed later)
     */
    public KeyValuePair(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Extract the key from the key-value pair.
     * @return the key
     */
    public String first() {
        return this.key;
    }

    /**
     * Extract the value from the key-value pair.
     * @return the value
     */
    public Integer second() {
        return this.value;
    }

    /**
     * Construct a new ordered pair with the order of items reversed.
     * Notice the return type is now explicitly {@code OrderedPair<Integer, String>}.
     * @return a new ordered pair
     */
    public OrderedPair<Integer, String> reversed() {
        return new OrderedPair<Integer, String>() {
            Integer v = value;
            String k = key;
            
            public Integer first() { return this.v; }
            
            public String second() { return this.k; }
            
            public OrderedPair<String, Integer> reversed() { 
                return new KeyValuePair(this.k, this.v); 
            }
        };
    }

    /**
     * Replace the value in the key-value pair.
     * @param value the new value
     */
    public void setValue(Integer value) {
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
    public boolean equals(Object o) {
        return (o instanceof KeyValuePair) && this.key.equals(((KeyValuePair)o).key);
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
        OrderedPair.validate(new KeyValuePair("", 0));

        KeyValuePair pair = new KeyValuePair("disciples", 12);

        assert pair.second().equals(12);
        pair.setValue(11);
        assert pair.second().equals(11);
        
        assert pair.toString().equals("disciples => 11");
        assert pair.hashCode() == "disciples".hashCode();

        assert pair.equals(new KeyValuePair("disciples", 12));
        assert !pair.equals(new KeyValuePair("apostles", 11));

        System.out.println("KeyValuePair passes all tests.");
    }
}