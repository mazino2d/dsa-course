package datastructure;

/**
 * Heap
 */
public class Heap<T extends Comparable<T>> {
    T[] array = null; int size = 0;

    @SuppressWarnings("unchecked")
    public Heap(int max_size) {
        this.array = (T[]) new Comparable[max_size];
    }

    // The new element is pushed to the last position, and ReheapUp is called for that position
    public void insert(T data) {
        if(size == this.array.length)
            throw new IndexOutOfBoundsException(
                String.format("Size: %d", this.array.length));
        
        
        array[this.size++] = data; // First insert the new key at the end 
        this.reheapUp(this.size - 1); // Fix the heap property if it is violated 
    }
    
    // Remove the max element from max-heap
    public T extract() {
        // Stop if the heap is empty
        if (this.size == 0)
            // Throw IndexOutOfBoundsException
            throw new IndexOutOfBoundsException("Collection is empty!");

        // Store the minimum value, and remove it from heap 
        T data = this.array[0]; this.array[0] = this.array[--this.size]; reheapDown(0);

        return data;
    }

     // Return size of heap
     public int size() { return size; }

     // Check if heap is empty
     public boolean isEmpty() { return size == 0; }

    // Repairs a "broken" heap (i) by floating the element up the tree untill it is in its correct location
    private void reheapUp(int index) {
        if(index == 0) return; 
        
        int p = parent(index);

        if(this.array[p].compareTo(this.array[index]) < 0) 
            { this.swap(index, p); this.reheapUp(p);}
    }

    // Repairs a "broken" heap by pushing the root of subtree down untill is in its correct location
    private void reheapDown(int index) {
        int l = left(index), r = right(index), m = index;

        if(l < size && this.array[l].compareTo(this.array[m]) > 0) m = l;
        if(r < size && this.array[r].compareTo(this.array[m]) > 0) m = r;

        if(m != index) { this.swap(index, m); this.reheapDown(m);}

    }
    
    // Swap 2 elements of array
    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Get index of left/right children and parent
    private static int left(int i) {return 2*i + 1;}
    private static int right(int i) {return 2*i + 2;}
    private static int parent(int i) {return (i - 1)/2;}
}

class HeapDriverTest {

    public static void main(String[] args) {
        // write test form here
        Heap<Integer> stk = new Heap<Integer>(5);
        stk.insert(0); stk.insert(2); stk.insert(3);
        while(!stk.isEmpty()) System.out.println(stk.extract());
    }
}
