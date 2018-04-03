package project4_berhow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Project4_berhow extends Application {

    //this is our list object
    private DataGenerator ourList;
    private int[] arr;
    private int sortTypeMagicNumber;// =1 -> selection, =2 -> bubble, =3 -> insertion, =4 -> quick
    private int listTypeMagicNumber;// =1 -> alreadySorted, =2 -> reverseOrder, =3 -> random

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//layout
        VBox root = new VBox(); //this will collect the sortChoice, listType, input, and go
        VBox sortChoice = new VBox();//radioButtons selection, bubble, insertion, quick
        VBox listType = new VBox();//radioButtons already sorted, reverse order, random
        VBox input = new VBox();//textfield input size, block size
        Button go = new Button("Go"); //starts the sorting excercise
        go.setPrefWidth(Double.MAX_VALUE);

//sortChoice
        Label sortingAlgorithm = new Label("Sorting Algorithm");
        //selectionSort    
        RadioButton selection = new RadioButton("Selection");//going to make a sort class that takes array and has different methods for different sorts
        selection.setOnAction(e -> {
            sortTypeMagicNumber = 1;
        });
    //bubbleSort
        RadioButton bubble = new RadioButton("Bubble");
        bubble.setOnAction(e -> {
            sortTypeMagicNumber = 2;
        });
    //insertionSort
        RadioButton insertion = new RadioButton("Insertion");
        insertion.setOnAction(e -> {
            sortTypeMagicNumber = 3;
        });
    //QuickSort
        RadioButton quick = new RadioButton("Quick");
        quick.setOnAction(e -> {
            sortTypeMagicNumber = 4;
        });
    //SortingGroup
        ToggleGroup sortingGroup = new ToggleGroup();
        selection.setToggleGroup(sortingGroup);
        bubble.setToggleGroup(sortingGroup);
        insertion.setToggleGroup(sortingGroup);
        quick.setToggleGroup(sortingGroup);
    //add nodes to pane
        sortChoice.getChildren().addAll(sortingAlgorithm, selection, bubble, insertion, quick);
        sortChoice.setSpacing(3);

//listType                                                                                          
        Label inputType = new Label("Input Type");

        RadioButton alreadySorted = new RadioButton("Already Sorted");
        alreadySorted.setOnAction(e -> {
            listTypeMagicNumber = 1;
        });
        RadioButton reverseOrder = new RadioButton("Reverse Order");
        reverseOrder.setOnAction(e -> {
            listTypeMagicNumber = 2;
        });
        RadioButton random = new RadioButton("Random");
        random.setOnAction(e -> {
            listTypeMagicNumber = 3;
        });
    //toggleGroup for listType
        ToggleGroup listGroup = new ToggleGroup();
        alreadySorted.setToggleGroup(listGroup);
        reverseOrder.setToggleGroup(listGroup);
        random.setToggleGroup(listGroup);
        //put nodes in pane
        listType.getChildren().addAll(inputType, alreadySorted, reverseOrder, random);
        listType.setSpacing(3);

//input
        HBox inputSizeBox = new HBox();
    //input Size
        Label inputSize = new Label("Input Size ");
        TextField inputSizeTF = new TextField();

        inputSizeBox.getChildren().addAll(inputSize, inputSizeTF);
    //block Size    
        HBox blockSizeBox = new HBox();
        Label blockSize = new Label("Block Size ");
        TextField blockSizeTF = new TextField();

        blockSizeBox.getChildren().addAll(blockSize, blockSizeTF);

        input.getChildren().addAll(inputSizeBox, blockSizeBox);
        input.setSpacing(3);

////////////////////////////
////Go:Important Part!!/////
////////////////////////////
        go.setOnAction(e -> {                                                                                 
            try{                                                                
                int arraySize = -1;
                int sortBlockSize = -1;

                String inputSizeResponse = inputSizeTF.getText();
                if(inputSizeResponse == null){
                    throw new Exception("You must enter a number greater than 0");
                }
                arraySize = Integer.parseInt(inputSizeResponse);
                if(arraySize<=0){
                    throw new Exception("Array must be larger than 0 elements.");
                }
            //define the array by size
                arr = new int[arraySize];
                ourList = new DataGenerator(arraySize);

            //load the array
                if (listTypeMagicNumber == 1) {
                    arr = ourList.inOrder();
                    System.out.println(Arrays.toString(arr));
                } else if (listTypeMagicNumber == 2) {
                    arr = ourList.inReverseOrder();
                    System.out.println(Arrays.toString(arr));
                } else if (listTypeMagicNumber == 3) {
                    arr = ourList.inRandomOrder();
                    System.out.println(Arrays.toString(arr));
                } else {
                    throw new Exception("No dice...:( Select list type");
                }
                
                String blockSizeResponse = blockSizeTF.getText();
                if(blockSizeResponse == null){
                    throw new Exception("You must enter a block size");
                }
                sortBlockSize = Integer.parseInt(blockSizeResponse);
                if(sortBlockSize < 1){
                    if(sortBlockSize == 0){
                        throw new Exception("... Division by 0... Really... No go, try again...");
                    }else{
                        throw new Exception("Block size must be greater than or equal to 1.");
                    }
                }

            //how many blocks -> how many threads 
                int numberOfThreads = arraySize / sortBlockSize;//this is for threading...
                ArrayList<int[]> setOfSubArrays = partitionArray(arr, sortBlockSize);
                ArrayList<Thread> threads = new ArrayList<>();
                                                                                
                long beginTime = System.currentTimeMillis();
                
                //make the threads
                if(sortTypeMagicNumber == 1){// =1 -> selection, =2 -> bubble, =3 -> insertion, =4 -> quick
                    for(int i=0; i<setOfSubArrays.size(); i++){
                        threads.add(new Thread(new SelectionSort(setOfSubArrays.get(i))));
                        threads.get(i).start();
                        
                    }
                }else if(sortTypeMagicNumber == 2){
                    for(int i=0; i<setOfSubArrays.size(); i++){
                        threads.add(new Thread(new BubbleSort(setOfSubArrays.get(i))));
                        threads.get(i).start();
                    }
                }else if(sortTypeMagicNumber == 3){
                    for(int i=0; i<setOfSubArrays.size(); i++){
                        threads.add(new Thread(new InsertionSort(setOfSubArrays.get(i))));
                        threads.get(i).start();
                    }
                }else if(sortTypeMagicNumber == 4){
                    for(int i=0; i<setOfSubArrays.size(); i++){
                        threads.add(new Thread(new QuickSort(setOfSubArrays.get(i), 0, setOfSubArrays.get(i).length-1)));
                        threads.get(i).start();
                    }
                }else {
                    throw new Exception("No dice...:( Select sort type");
                }
                
                for(int i=0; i<threads.size(); i++){
                    threads.get(i).join();
                }
                
                ArrayDeque<int[]> bunchOfArrays = new ArrayDeque();//going to merge subarrays by taking the main collection and combining it with the next array
                
                for(int i=0; i<setOfSubArrays.size(); i++){
                    bunchOfArrays.offer(setOfSubArrays.get(i));
                }
                
                while(bunchOfArrays.size() > 1){
                    bunchOfArrays.offer(merge(bunchOfArrays.pop(), bunchOfArrays.pop()));
                    
                }
                
                arr = bunchOfArrays.poll(); //merged array
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - beginTime;
                
                Alert sortingTime = new Alert(AlertType.INFORMATION);
                sortingTime.setContentText("The total time to sort is : " + totalTime);
                sortingTime.show();
                
                System.out.println(Arrays.toString(arr));
            

            }catch(Exception a){
                Alert alert = new Alert(AlertType.ERROR, a.getMessage());
                alert.show();
                System.out.println(a.getMessage());
            }
        });

//put sortChoice, listType, input, and go into root
        root.getChildren().addAll(sortChoice, listType, input, go);
        root.setSpacing(15);

//build everything
        root.setPadding(new Insets(0, 10, 0, 10));
        VBox.setVgrow(root, Priority.ALWAYS);
        Scene scene = new Scene(root, 225, 310);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * divides list up into n subarrays
     * @param passedArr
     * @param n
     * @return setOfArrays = ArrayList<int[]> of size n where each subArray is of length passedArray.length/n
     */
    public static ArrayList<int[]> partitionArray(int[] passedArr, int n){
        int numberOfPartitions = passedArr.length/n;
        
        ArrayList<int[]> setOfArrays = new ArrayList<>(numberOfPartitions);
        for(int i=0; i*n <passedArr.length; i++){
            setOfArrays.add(Arrays.copyOfRange(passedArr, i*n, (i+1)*n));
        }
        
        return setOfArrays;
    }
    
    public static int[] merge(int[] arr1, int[] arr2){
        int[] mergedArray = new int[arr1.length + arr2.length];
        int n=0;//pointer for arr1
        int m=0;//pointer for arr2
        for(int i=0; i<mergedArray.length;i++){
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
        
        return mergedArray;
        
    }
    
    
}
