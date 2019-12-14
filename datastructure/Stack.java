package datastructure;

/**
 * Stack
 */
public class Stack<T>{
    protected Node head; protected int size;

    // Default constructor
    public Stack() { head = null; size = 0; }

    // Insert new node at first node
    public void push(T data) {
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

    // Remove first node from list
    public T pop() {
        // Stop if the list is empty
        if (this.size == 0)
            // Throw IndexOutOfBoundsException
            throw new IndexOutOfBoundsException("Collection is empty!");

        // Sava head node data
        T data = this.head.data;
        // Remove the head element
        this.head = this.head.next;
        // Decrease size of list
        this.size = this.size - 1;
        
        return data;
    }

    // Return size of list
    public int size() { return size; }

    // Check if list is empty
    public boolean isEmpty() { return size == 0; }

    protected class Node {
        T data;
        Node next = null;

        Node(T data) {
            this.data = data;
        }
    }
}

class StackDriverTest {

    public static void main(String[] args) {
        // write test form here
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(1); stk.push(2); stk.push(3);
        while(!stk.isEmpty()) System.out.println(stk.pop());
    }
}
