package project4_berhow;

import java.util.Arrays;

public class QuickSort implements Runnable{

    private int[] arr;
    private int start, end;
    public QuickSort(int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
     public void quickSort(int[] arr, int start, int end) {
        
        if (start < 0 || end > arr.length || start >= end) {
            return;
        }
        
        // partition
        int splitPoint = partition(arr, start, end);
        
//        System.out.println(Arrays.toString(arr));
        // quicksort the left half
        quickSort(arr, start, splitPoint);
        
        // quicksort the right half
        quickSort(arr, splitPoint+1, end);
        
    }
    // Pivot is the left-most value in the array
    // Create two pointers: lo and hi
    // Move the lo pointer right until you find a value greater than the pivot
    // Move the hi pointer to the left until you find a value less than the pivot
    // If lo < hi, swap the lo and hi values
    // If lo < hi, repeat steps 3 - 5; 
    // otherwise swap the hi value with the pivot if necessary (i.e. if the 
    // hi value is less than the pivot)
    public int partition(int[] arr, int start, int end) {
        int pivotValue = arr[start];
        int low = start + 1;
        int high = end;

        while (low < high) {
            while (low < arr.length && arr[low] <= pivotValue) {
                low++;
            }
            
            while (high >= low && arr[high] >= pivotValue) {
                high--;
            }
            
            if (low < high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        } 

        if (arr[high] < pivotValue) {
            int temp = arr[high];
            arr[high] = pivotValue;
            arr[start] = temp;
            return high;
        } else {
            return start;
        }
    }

    @Override
    public void run() {
        quickSort(arr, start, end);
    }


}
