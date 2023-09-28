package main.java;

import java.util.ArrayList;

class MapDemo{
    public static void main(String[] args) {
        MyHashMap<Integer,Product> map = new MyHashMap<>();
        map.put(1,new Product(null,1,1,"dummy",new MyDate("2000-01-01")));
        System.out.println(map.containsKey(1));
        map.display();
    }
}
public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 100;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public int capacity;
    private float loadFactor;
    public int size;

    public Node<K, V>[] getTable() {
        return table;
    }

    private Node<K, V>[] table;

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Node[capacity];
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }
    public void display() {
        display("");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display(String Divider) {
        System.out.println(Divider);
        System.out.println(Divider);
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                System.out.print(node.key + "\n" + node.value + "\n" + Divider + "\n");
                node = node.next;
            }
        }
        System.out.println(Divider);
        System.out.println(Divider);
    }


    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        int h = key.hashCode();
        if (h < 0)
            h *= (-1);
        return h % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        if (size > capacity * loadFactor) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    public void removeValue(V name) {
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            Node<K, V> prev = null;
            while (node != null) {
                if (name.equals(node.value)) {
                    prev.next = node.next;
                    size--;
                    return;
                }
                prev = node;
                node = node.next;
            }
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Node<K, V>[] newTable = new Node[newCapacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                Node<K, V> next = node.next;
                int index = hash(node.key);
                node.next = newTable[index];
                newTable[index] = node;
                node = next;
            }
        }
        table = newTable;
        capacity = newCapacity;
    }
}