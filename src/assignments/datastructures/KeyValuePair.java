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
     * Notice the return type is now explicitly OrderedPair<Integer, String>.
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

    public String toString() {
        return this.key.toString() + " => " + this.value.toString();
    }

    public boolean equals(Object o) {
        return (o instanceof KeyValuePair) && this.key.equals(((KeyValuePair)o).key);
    }

    public int hashCode() {
        return this.key.hashCode();
    }
    
    public static void main(String[] args) {
        // Depending on how validate() is defined in adt.OrderedPair, you may need to adjust this call.
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