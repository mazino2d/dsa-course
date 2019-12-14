package datastructure;

/**
 * Queue
 */
public class Queue<T> extends Stack<T>{
    
    private Node tail;

    // Default constructor
    public Queue() { super(); tail = null; }

    // Insert new node at last node
    @Override
    public void push(T data) {
        // Create a new node with given data
        Node new_node = new Node(data);

        // Insert new node at last node
        if (this.head == null) {
            this.head = new_node;
            this.tail = new_node;
        }
        else {
            this.tail.next = new_node;
            this.tail = new_node;
        }

        // Increase size of list
        this.size = this.size + 1;
    }
    
    // Remove first node from list
    @Override
    public T pop() {
        T data = super.pop();
        if(this.head == null) 
            this.tail = null;
        return data;
    }
}

class QueueDriverTest {

    public static void main(String[] args) {
        // write test form here
        Queue<Integer> stk = new Queue<Integer>();
        stk.push(1); stk.push(2); stk.push(3);
        while(!stk.isEmpty()) System.out.println(stk.pop());
    }
}
