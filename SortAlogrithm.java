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
        // One by one move boundary of unsorted subarray 
        for(int i = 0; i < array.length; ++i) {
            int idx = i; // Find the minimum element in unsorted array
            for(int j = i + 1; j < array.length; ++j)
                if(array[idx].compareTo(array[j]) > 0) idx = j;
            // Swap the found minimum element with the first element
            swap(array, i, idx);
        }
    }

    // https://www.geeksforgeeks.org/bubble-sort/
    public static <E extends Comparable<E>> void naive_bubble_sort(E[] array) {
        for(int i = 0; i < array.length - 1; ++i)
            for(int j = array.length - 1; j > i; --j)
                 // swap the adjacent elements if they are in wrong order.
                if(array[j].compareTo(array[j - 1]) < 0)  swap(array, j, j - 1);
    }

    public static <E extends Comparable<E>> void optimized_bubble_sort(E[] array) {
        for(int i = 0; i < array.length - 1; ++i) {
            boolean swapped = false;
            for(int j = array.length - 1; j > i; --j)
                 // swap the adjacent elements if they are in wrong order.
                if(array[j].compareTo(array[j - 1]) < 0) 
                    {swap(array, j, j - 1); swapped = true;}
            if(!swapped) return;
        }
    }

    // https://www.geeksforgeeks.org/cocktail-sort/
    public static <E extends Comparable<E>> void cocktail_sort(E[] array) {
        int left = 0, right = array.length - 1, temp = 1;
        while(left < right) {
            for(int i = right; i > left; --i)
                 // swap the adjacent elements if they are in wrong order.
                if(array[i].compareTo(array[i - 1]) < 0)  
                    {swap(array, i, i - 1); temp = i;}
            left = temp;

            for(int i = left; i < right; ++i)
                 // swap the adjacent elements if they are in wrong order.
                if(array[i].compareTo(array[i + 1]) > 0)  
                    {swap(array, i, i + 1); temp = i;}
            right = temp;
        }
    }

    // https://www.geeksforgeeks.org/insertion-sort/
    public static <E extends Comparable<E>> void insertion_sort(E[] array) {
        for(int i = 1; i < array.length; ++i) {
            E key = array[i]; int idx = i - 1;

            /* Move elements of arr[0..i-1], that are  greater than key, 
            to one position ahead  of their current position */
            while(idx >= 0 && array[idx].compareTo(key) > 0)
                {array[idx + 1] = array[idx]; idx = idx - 1;}

            array[idx + 1] = key;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {9, 1, 3, 5, 2, 6,  4, 8, 7, 0};
        // selection_sort(array); print(array);
        // naive_bubble_sort(array); print(array);
        // optimized_bubble_sort(array); print(array);
        // cocktail_sort(array); print(array);
        insertion_sort(array); print(array);
    }
}
