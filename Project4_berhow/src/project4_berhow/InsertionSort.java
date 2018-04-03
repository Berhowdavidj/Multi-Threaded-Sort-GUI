package project4_berhow;

import java.util.Arrays;

public class InsertionSort implements Runnable{
    
    private int[] arr;
    public InsertionSort(int[] arr){
        this.arr = arr;
    }
    
    @Override
    public void run() {
        for(int i=0; i<arr.length; i++){
            int currentVal = arr[i];
            int j = i-1;
            
            while(j>=0 && arr[j]>currentVal){
                arr[j+1] = arr[j];
                j--;
            }
            
            arr[j+1]=currentVal;
//            System.out.println(Arrays.toString(arr));
        }
    }
    
}
