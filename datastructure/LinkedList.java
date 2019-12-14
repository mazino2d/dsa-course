package datastructure;

import java.util.Iterator;

/**
 * LinkedList
 */
public class LinkedList<T> extends  Stack<T> implements Iterable<T> {

    // Default constructor
    public LinkedList() { super(); }

    // Copy constructor
    public LinkedList(Iterable<T> copy) {
        for(T e: copy) this.push(e); this.reverse();
    }

    // Insert new specific node by index
    public void insert(T data, int index) {
        // Throw IndexOutOfBoundsException
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, this.size));

        if (index == 0)
            this.push(data);
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
            previous =  current;
            current = next;
        }

        this.head = previous;
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

class LinkedListDriverTest {

    public static void main(String[] args) {
        // write test form here
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.push(1); list.push(2); list.push(3);
        for(Integer e: list) System.out.println(e);
    }
}
