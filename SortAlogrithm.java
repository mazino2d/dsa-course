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

    private static <E extends Comparable<E>> void selection_sort(E[] array) {
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
    public static void main(String[] args) {
        Integer[] array = new Integer[] {9, 1, 3, 5, 2, 6,  4, 8, 7, 0};
        selection_sort(array); print(array);

        
    }
}
