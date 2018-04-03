package project4_berhow;

import java.util.Arrays;


public class BubbleSort implements Runnable{
    private int[] arr;
    public BubbleSort(int[] arr){
        this.arr = arr;
    }
    @Override
    public void run() {
        for(int i=0; i<arr.length-1; i++){
            
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j]= arr[j+1]; 
                    arr[j+1] = tmp;
                }
//                System.out.println(Arrays.toString(arr));
            }
        }
        
    }
    
}
