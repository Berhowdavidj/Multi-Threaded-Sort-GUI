package project4_berhow;

import java.util.Arrays;

public class SelectionSort implements Runnable{
    
    private int[] arr;
    public SelectionSort(int[] arr){
        this.arr = arr;
    }
    
    @Override
    public void run() {
        for(int i=0; i<arr.length; i++){
            
            //find the min 
            int unsortMin_index = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[unsortMin_index] > arr[j]){
                    unsortMin_index = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[unsortMin_index];
            arr[unsortMin_index] = tmp;
//            System.out.println(Arrays.toString(arr));
            
            for(int k=0; k<arr.length - 1; k++){
                if(arr[k] <= arr[k+1]){
                    continue;
                }else if(k == arr.length - 2){
                    if(arr[k] <= arr[k+1]){
                        return;
                    }
                }else{
                    break;
                }
            }
        }
    }
    
}
