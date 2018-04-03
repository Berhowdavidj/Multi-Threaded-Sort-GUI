package project4_berhow;

import java.util.Arrays;

public class Merge implements Runnable{
    private int[] arr1; 
    private int[] arr2;
    private int[] mergedArray;
    
    public Merge(int[] arr1, int[] arr2){
        this.arr1 = arr1; 
        this.arr2 = arr2;
        this.mergedArray = new int[arr1.length + arr2.length];
    }
    
    @Override
    public void run() {
        int n=0;//pointer for arr1
        int m=0;//pointer for arr2
        for(int i=0; i<mergedArray.length;i++){
            System.out.println(Arrays.toString(mergedArray));
            //if n is larger or equal to arr1, then the rest of the array is filled with the remaining elements of arr2
            if (n >= arr1.length) {
                for (int j = i; j < mergedArray.length; j++) {
                    mergedArray[j] = arr2[m];
                    m++;
                }
                break;
            //if n is larger or equal to arr1, then the rest of the array is filled with the remaining elements of arr2
            }else if (m >= arr2.length) {
                for (int j = i; j < mergedArray.length; j++) {
                    mergedArray[j] = arr1[n];
                    n++;
                }
                break;
            //puts element from arr1 into mergedArray    
            }else if (arr1[n] <= arr2[m]) {
                mergedArray[i] = arr1[n];
                n++;
            //does opposite    
            }else {
                mergedArray[i] = arr2[m];
                m++;
            }
            
        }
    }
    
}
