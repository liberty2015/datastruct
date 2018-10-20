package collection.bstAVL;

import java.util.Map;

public class AVLEntry<K, V> implements Map.Entry<K, V> {
    public K key;
    public V value;
    public AVLEntry<K, V> left;
    public AVLEntry<K, V> right;
    public AVLEntry<K, V> parent;
    public int height = 1;

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }

    public AVLEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public AVLEntry(K key,V value,AVLEntry<K,V> parent){
        this.key = key;
        this.value = value;
        this.parent=parent;
    }

    public AVLEntry(K key) {
        super();
        this.key = key;
    }

    public AVLEntry(K key, V value, AVLEntry<K, V> left, AVLEntry<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }


    @Override
    public String toString() {
        return "AVLEntry{" +
                "key=" + key +
                ", value=" + value +
                ", height=" + height +
//                ", left=" + left +
//                ", right=" + right +
                '}';
    }

}
