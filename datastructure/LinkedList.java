package datastructure;

import java.util.Iterator;

/**
 * LinkedList
 */
public class LinkedList<T> implements Iterable<T> {

    private Node head; private int size;

    // Default constructor
    public LinkedList() { head = null; size = 0; }

    // Copy constructor
    public LinkedList(Iterable<T> copy) {
        for(T e: copy) this.append(e); this.reverse();
    }

    // Insert new node at first node
    public void append(T data) {
        // Create a new node with given data
        Node new_node = new Node(data);

        // Insert new node at first node
        if (this.head == null)
            this.head = new_node;
        else {
            new_node.next = this.head;
            this.head = new_node;
        }

        // Increase size of list
        this.size = this.size + 1;
    }

    // Insert new specific node by index
    public void insert(T data, int index) {
        // Throw IndexOutOfBoundsException
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, this.size));

        if (index == 0)
            this.append(data);
        else {
            // Create a new node with given data
            Node new_node = new Node(data);
            // Traverse till the node at (index - 1)
            Node p = this.head;
            int loop = index;
            while (--loop > 0)
                p = p.next;
            // Insert the new_node at index
            new_node.next = p.next;
            p.next = new_node;
        }

        // Increase size of list
        this.size = this.size + 1;
    }

    // Remove first node from list
    public void pop() {
        // Stop if the list is empty
        if (this.size == 0)
            return;
        // Remove the head element
        this.head = this.head.next;
        // Decrease size of list
        this.size = this.size - 1;
    }

    // Remove specific node by index
    public void remove(int index) {
        // Throw IndexOutOfBoundsException
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, this.size));

        if (index == 0)
            this.pop();
        else {
            // Traverse till the node at (index - 1)
            Node p = this.head;
            int loop = index;
            while (--loop > 0)
                p = p.next;
            // Remove the node at index
            p.next = p.next.next;
        }

        // Decrease size of list
        this.size = this.size - 1;
    }

    // Linear search for linked list
    public boolean contain(T key) {
        for (Node p = this.head; p != null; p = p.next)
            if (p.data == key)
                return true;
        return false;
    }

    // Reverse linked list
    public void reverse() {
        Node current = this.head, previous = null, next = null;

        while(current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        this.head = previous;
    }

    class Node {
        T data;
        Node next = null;

        Node(T data) {
            this.data = data;
        }
    }

    // Implement iterable(for-each) interface 
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(this);
    }

    class LinkedListIterator implements Iterator<T> { 
        Node current = null;

        public LinkedListIterator(LinkedList<T> obj) { 
            current = obj.head;
        } 
        
        // Checks if the next element exists 
        public boolean hasNext() { 
            return current != null;
        } 
        
        // moves the cursor/iterator to next element 
        public T next() { 
            T data = current.data;
            current = current.next;
            return data;
        } 
        
        // Used to remove an element. Implement only if needed
        public void remove() { 
            throw new UnsupportedOperationException(); 
        } 
    } 
}


class DriverTest {

    public static void main(String[] args) {
        // write test form here
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.append(1); list.append(2); list.append(3);
        for(Integer e: list) System.out.println(e);
    }
}
