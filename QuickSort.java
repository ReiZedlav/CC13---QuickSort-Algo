import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/* 
QUICKSORT
Bagabaldo
Gutierrez
Rojo
Pacatan
Valdez
Waga
*/

class QuickSort{
    public static void main(String[] args) {

        //WARNING (Bug) - Adding a newline character \n at the end of input.txt file will add an extra zero. 

        //Specify file/IO path (pls change) - P.S gi tuyo ni nga directory e butang, if dili ni dir, goodluck pangita sa C:// or D:// drive :D
        String inputPath = "C:/Users/Albert/Desktop/input.txt";
        String outputPath = "C:/Users/Albert/Desktop/output.txt";;

        int Data[] = Logic.fillArray(inputPath); //Creates an array with the appropriate size, satisfying the 100 limit, and filling the array with values

        quicksort(Data);

        System.out.println();
        
        printArray(Data);

        Logic.export(Data,outputPath);
    }

    public static void quicksort(int[] arr) { 
        quicksort(arr, 0, arr.length - 1);
    }
    
    public static void quicksort(int[] arr, int low, int high) {
        if (low >= high) { // Base case - Stops the recursion when the subarray has one or no elements.
            return;
        }

        int pivot = arr[high]; // Choose the last element as the pivot.

        int leftPointer = low; // Start leftPointer at the beginning of the subarray.
        int rightPointer = high; // Start rightPointer at the end of the subarray.

        while (leftPointer < rightPointer) { // Continue until the pointers meet.
            while (arr[leftPointer] <= pivot && leftPointer < rightPointer) { // Move leftPointer to the right until an element greater than the pivot is found.
                leftPointer++;
            }

            while (arr[rightPointer] >= pivot && leftPointer < rightPointer) { // Move rightPointer to the left until an element less than the pivot is found.
                rightPointer--;
            }

            if (leftPointer < rightPointer) { // Swap elements if pointers have not crossed.
                swap(arr, leftPointer, rightPointer);
            }
        }

        swap(arr, leftPointer, high); // Place the pivot in its correct position after left and right pointers have intertwined.

        // Recursively sort the subarrays on either side of the pivot.
        quicksort(arr, low, leftPointer - 1);
        quicksort(arr, leftPointer + 1, high);
    }

    //Recursive method for swapping array values.
    public static void swap(int[] arr, int indexOne, int indexTwo) {
        int tmp = arr[indexOne];

        arr[indexOne] = arr[indexTwo];

        arr[indexTwo] = tmp;
    }

    public static void printArray(int[] arr) {
        System.out.println("Array Sorted using Quicksort: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); 
    }

}

class Logic{
    public static int[] fillArray(String textfile){
        int size = 0;
        int limit = 100;

        int index = 0;

        try{ //Checks if newlines are less than 100, and calculates the final size of array.
            File inputFile = new File(textfile);

            Scanner IO = new Scanner(inputFile);

            while (IO.hasNextLine() && size <= limit){ 
                String counter = IO.nextLine();
                size++;
            }
            IO.close(); 
        } catch (FileNotFoundException DebugMeBabyOneMoreTime) {
            System.out.println("File doesn't prolly exist homie, try again.");
        }

        int Data[] = new int[size]; 

        try{ //Logic that returns array values.
            File inputFile = new File(textfile);

            Scanner IO = new Scanner(inputFile);

            while (IO.hasNextLine() && index <= limit){ 
                if (IO.hasNextInt()){
                    Data[index] = IO.nextInt();
                }
                index++;
            }
            IO.close();
        } catch (FileNotFoundException DebugMeBabyOneMoreTime) {
            System.out.println("File doesn't prolly exist homie, try again.");
        }

        System.out.println("Unsorted Array: ");
        for (int i = 0; i < Data.length; i++){
            System.out.print(Data[i] + " ");
        }

        return Data;
    }

    public static void export(int[] arr, String outputPath) {
    File outputFile = new File(outputPath);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        for (int i = 0; i < arr.length; i++) {
            writer.write(String.valueOf(arr[i]));
            if (i < arr.length - 1) {
                writer.write(" "); // Add a space between numbers
            }
        }
    } catch (IOException DebugMeBabyOneMoreTime) {
        DebugMeBabyOneMoreTime.printStackTrace(); // Handle exceptions here
    }
}

}
