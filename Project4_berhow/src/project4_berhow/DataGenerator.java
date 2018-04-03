package project4_berhow;

public class DataGenerator {
    private int [] list;
    private int sizeOfList;
    
    /**
     * This constructor takes the size of the list (given from the inputSizeTF)
     * and creates an array of that size
     * @param sizeOfList 
     */
    public DataGenerator(int sizeOfList){
        this.list = new int[sizeOfList];
        this.sizeOfList = sizeOfList;
    }
    
    /**
     * inOrder fills the array with values in order from 0 to sizeOfArray-1
     */
    public int[] inOrder(){
        for(int i=0; i<sizeOfList; i++){
            list[i] = i;
        }
        return list;
    }
    
    /**
     * inReverseOrder fills the array with values in order from sizeOfArray-1 to 0
     */
    public int[] inReverseOrder(){
        int reverseValue = sizeOfList-1;
        for(int i=0; i<sizeOfList; i++){
            list[i] = reverseValue;
            reverseValue--;
        }
        return list;
    }
    
    /**
     * inRandomOrder fills the array with random values in the range of 0 to 99
     */
    public int[] inRandomOrder(){
        for(int i=0; i<sizeOfList; i++){
            list[i] = (int)(1 + Math.random() * 99);
        }
        return list;
    }
       
    
    
}
