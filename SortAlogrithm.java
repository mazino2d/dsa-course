/**
 * SortAlgoritm
 */
class SortAlgorithm {
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i]; array[i] = array[j]; array[j] = temp;
    }

    private static <T> void print(T[] array) {
        for(T e: array) System.out.println(e.toString());
    }

    // https://www.geeksforgeeks.org/selection-sort/
    public static <E extends Comparable<E>> void selection_sort(E[] array) {
        int size = array.length;
        // One by one move boundary of unsorted subarray 
        for(int i = 0; i < size; ++i) {
            int idx = i; // Find the minimum element in unsorted array
            for(int j = i + 1; j < size; ++j)
                if(array[idx].compareTo(array[j]) > 0) idx = j;
            // Swap the found minimum element with the first element
            swap(array, i, idx);
        }
    }

    // https://www.geeksforgeeks.org/bubble-sort/
    public static <E extends Comparable<E>> void naive_bubble_sort(E[] array) {
        int size = array.length;
        
        for(int i = 0; i < size - 1; ++i) {
            for(int j = size - 1; j > i; --j)
                if(array[j].compareTo(array[j - 1]) < 0) swap(array, j, j - 1);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {9, 1, 3, 5, 2, 6,  4, 8, 7, 0};
        naive_bubble_sort(array); print(array);

    }
}
