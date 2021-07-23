import java.util.Iterator;

public interface SymbolTable<Key extends Comparable<Key>, Value>  {
    void put(Key key, Value value);
    Value get(Key key);
    Value getOrDefault(Key key, Value defaultValue);

    Iterator<Key> iterator();

     default boolean containsKey(Key key)
    {
        return get(key) != null;
    }
}
