package algorithm;

import java.util.Random;
import java.util.function.Consumer;

/**
 * SortAlgorithm
 * 
 * @author Khoi Dang Do
 */
class SortAlgorithm {
    // Selection Sort
    // https://www.geeksforgeeks.org/selection-sort/
    public final static <E extends Comparable<E>> void selection_sort(E[] array) {
        // One by one move boundary of unsorted subarray 
        for(int i = 0; i < array.length; ++i) {
            int idx = i; // Find the minimum element in unsorted array
            for(int j = i + 1; j < array.length; ++j)
                if(array[idx].compareTo(array[j]) > 0) idx = j;
            // Swap the found minimum element with the first element
            swap(array, i, idx);
        }
    }

    // Bubble Sort
    // https://www.geeksforgeeks.org/bubble-sort/
    public final static <E extends Comparable<E>> void naive_bubble_sort(E[] array) {
        for(int i = 0; i < array.length - 1; ++i)
            for(int j = array.length - 1; j > i; --j)
                 // swap the adjacent elements if they are in wrong order.
                if(array[j].compareTo(array[j - 1]) < 0)  swap(array, j, j - 1);
    }

    public final static <E extends Comparable<E>> void optimized_bubble_sort(E[] array) {
        for(int i = 0; i < array.length - 1; ++i) {
            boolean swapped = false;
            for(int j = array.length - 1; j > i; --j)
                 // swap the adjacent elements if they are in wrong order.
                if(array[j].compareTo(array[j - 1]) < 0) 
                    {swap(array, j, j - 1); swapped = true;}
            if(!swapped) return;
        }
    }

    // Cocktail Sort
    // https://www.geeksforgeeks.org/cocktail-sort/
    public final static <E extends Comparable<E>> void cocktail_sort(E[] array) {
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

    // Insertion Sort
    // https://www.geeksforgeeks.org/insertion-sort/
    public final static <E extends Comparable<E>> void insertion_sort(E[] array) {
        for(int i = 1; i < array.length; ++i) {
            E key = array[i]; int idx = i - 1;

            /* Move elements of arr[0..i-1], that are  greater than key, 
            to one position ahead  of their current position */
            while(idx >= 0 && array[idx].compareTo(key) > 0)
                {array[idx + 1] = array[idx]; idx = idx - 1;}

            array[idx + 1] = key;
        }
    }

    // Shell Sort
    // https://www.geeksforgeeks.org/shellsort/
    public final static <E extends Comparable<E>> void shell_sort(E[] array) {
        for(int gap = array.length/2; gap > 0; gap /= 2) {
            for(int i = gap; i < array.length; i += gap) {
                E key = array[i]; int idx = i - gap;
    
                /* Move elements of arr[0..i-1], that are  greater than key, 
                to one position ahead  of their current position */
                while(idx >= 0 && array[idx].compareTo(key) > 0)
                    {array[idx + gap] = array[idx]; idx = idx - gap;}
    
                array[idx + gap] = key;
            }
        }
    }

    // Merge Sort
    // https://www.geeksforgeeks.org/merge-sort/
    public static <E extends Comparable<E>> void merge_sort(E[] array) {
        recursive_merge_sort(array, 0, array.length - 1); 
    }

    private static <E extends Comparable<E>> void recursive_merge_sort(E[] array, int l, int r) {
        if(l < r) {
            int m = (l + r)/2; // Find the middle point 

            // Sort first and second halves 
            recursive_merge_sort(array, l, m);
            recursive_merge_sort(array, m + 1, r);

            // Merge the sorted halves
            recursive_merge(array, l, m, r);
        }
    }

    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> void recursive_merge(E[] array, int l, int m, int r) {
        // Create two temporary array
        E[] left = (E[])new Comparable[m - l + 1];
        E[] right = (E[])new Comparable[r - m];
        
        // Copy data from original array to temporary array
        for(int i = 0; i < left.length; ++i) left[i] = array[l + i];
        for(int i = 0; i < right.length; ++i) right[i] = array[m + 1 + i];

        // Merge the temp arrays
        int l_idx = 0, r_idx = 0, idx = l;

        while(l_idx < left.length && r_idx < right.length) {
            if(left[l_idx].compareTo(right[r_idx]) <= 0)
                array[idx] = left[l_idx++];
            else 
                array[idx] = right[r_idx++];

            idx = idx + 1;
        }

        // Copy remaining elements of L and R to original array
        while(l_idx < left.length) array[idx++] = left[l_idx++];
        while(r_idx < right.length) array[idx++] = right[r_idx++];
    }

    // Quick Sort
    // https://www.geeksforgeeks.org/quick-sort/
    public static <E extends Comparable<E>> void quick_sort(E[] array) {
        recursive_quick_sort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void recursive_quick_sort(E[] array, int low, int high) {
        if(low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(array, low, high);

            // Recursively sort elements before partition and after partition 
            recursive_quick_sort(array, low, pi - 1);
            recursive_quick_sort(array, pi + 1, high);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        E pivot = array[high]; int left = low, right = high - 1;

        while(true) {
            while (left <= right && array[left].compareTo(pivot) < 0) ++left; 
            while (right >= left && array[right].compareTo(pivot) > 0) --right;

            if(left >= right) break; else swap(array, left++, right--);
        }
        swap(array, left, high);

        return left;
    }
    
    public static void main(String[] args) {
        test(100,5000, "Selection Sort",SortAlgorithm::selection_sort);
        test(100,5000, "Bubble Sort 1",SortAlgorithm::naive_bubble_sort);
        test(100,5000, "Bubble Sort 2",SortAlgorithm::optimized_bubble_sort);
        test(100,5000, "Cooktail Sort",SortAlgorithm::cocktail_sort);
        test(100,5000, "Insertion Sort",SortAlgorithm::insertion_sort);
        test(100,5000, "Shell Sort",SortAlgorithm::shell_sort);
        test(100,5000, "Merge Sort",SortAlgorithm::merge_sort);
        test(100,5000, "Quick Sort",SortAlgorithm::quick_sort);
    }

    // Swap 2 element of array
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i]; array[i] = array[j]; array[j] = temp;
    }

    // Check if array is in ascending order java
    private static <T extends Comparable<T>> boolean check(T[] array) {
        for (int i = 0; i < array.length - 1; ++i)
            if (array[i].compareTo(array[i + 1]) > 0) return false;
        
        return true; // If 
    }

    // Generate test case for sort function
    private static void test( int test_size, int array_size, 
        String test_name, Consumer<Integer[]> function)         
    {
        Random rd = new Random(1);  int count = 0; 
        Integer[] array = new Integer[array_size];

        long start = System.nanoTime();
        for(int i = 0; i < test_size; ++i) {
            for (int j = 0; j < array.length; ++j) array[j] = rd.nextInt();
            function.accept(array);  if(check(array)) ++count;
        }
        long end = System.nanoTime();
        
        System.out.print(test_name + "\t" + count + "/" + test_size + "\t");
        System.out.print((end - start) / Math.pow(10, 9) + "(s)");

        if(count == test_size) System.out.println(" SUCCESS ");
        else System.out.println(" FAIL ");
    }
}
