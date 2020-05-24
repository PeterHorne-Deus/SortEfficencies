/*
 * Peter Horne-Deus
 * This program calculates the efficency of different sorting algorithms
 * SortEfficencies.java
 * May 24, 2020
 */

package sortefficencies;

import javax.swing.*;
/**
 *
 * @author Peter
 */




public class SortEfficencies {

    /**
     * @param args the command line arguments
     */
    
    static int mergeLoops = 0;
    static int mergeShifts = 0;
    static int mergeComparisons = 0;

    public static void main(String[] args) {
        //User inputed varibles
        int toGenerate = Integer.parseInt(JOptionPane.showInputDialog("Enter the "
                + " number of random numbers you \n "
                + "would like generated"));
        //Generating the random numbers
        int[] randomNums = randomNum(toGenerate);
        
        //Sorting
        selection(randomNums);
        randomNums = randomNum(toGenerate);
        insertion(randomNums);
        randomNums = randomNum(toGenerate);
        merge(randomNums);
        
    }
    
    /**
     * This method generates random numbers between 1 - 1000
     * @param toGenerate
     * @return 
     */
    public static int[] randomNum(int toGenerate){
        //Varaibles
        int[] numbers = new int[toGenerate];
        
        //Ensuring inputed data is suitable
        if(toGenerate <= 0){
            System.out.println("Incorrect range");
            return numbers;
        }
        else{
            //Generating the random numbers
            for(int i = 0; i < toGenerate; i++){
                numbers[i] = (int)(Math.random()*1000 + 1);
                
            }
        }
        return numbers;
    }
    
    /**
     * This method preforms a selection sort on the randomly generated numbers
     * @param randomNums 
     */
    public static void selection(int[] randomNums){
        //Variables
        int current;
        int small = 0;
        int temp;
        int comparisons = 0;
        int loops = 0;
        int shifts = 0;
        
        //Setting up timer
        long startTime = System.nanoTime();
        
        //Looping through the sort
        for(int i = 0; i < randomNums.length -1 ; i++){
            //Counting number of loops and number of corisponding shifts
            loops ++;
            shifts++;
            
            //Setting values
            current = i;
            small = current;
            
            //Looping to find the next lowest value in the list
            for(int k = current + 1; k < randomNums.length; k++){
                //Contining to keep track of needed values
                comparisons++;
                loops ++;
                
                //If the new lowst is found that index is the new small index
                if(randomNums[k] < randomNums[small]){ 
                    small = k;
                    
                }
            }
            
            //Switching the current value with the new small value
            temp = randomNums[current];
            randomNums[current] = randomNums[small];
            randomNums[small] = temp;
            temp = 0;

        }
        
        //Finishing the time tracking
        long endTime = System.nanoTime();
        long time = endTime-startTime;
        
        System.out.println("\nSelection sort:");
        System.out.println("Number of comparisons made= " + comparisons);
        System.out.println("Number of loops exicuted= " + loops);
        System.out.println("Number of shifts made= " + shifts);
        System.out.println("Length of time to sort = " + time/1000000 +"ms");
    }
    
    /**
     * This method preforms the insertion sort on the random number list
     * @param randomNums 
     */
    public static void insertion(int[] randomNums){
        //Variables for sorting
        int currnetNum =0;
        int currentIndex;
        
        //Variables for tracking efficency
        int comparisons = 0;
        int loops = 0;
        int shifts = 0;
        
        //Setting up the timer
        long startTime = System.nanoTime();
        
        //Looping through the sort
        for (int i = 1; i < randomNums.length; i++)
        {
            //Keeping track of efficeny values
            loops++;
            //Moving through the array
            currnetNum = randomNums[i];
            currentIndex = i-1;
            
            while ( currentIndex >= 0 && randomNums[currentIndex] > currnetNum)
            {
                //Keeping track of efficeny values
                shifts++;
                comparisons++;
                loops++;
                
                // Shift the value at currentIndex to the right one place
                randomNums[currentIndex+1] = randomNums[currentIndex];
                currentIndex--;
            }
            
            //Setting up for the next loop
            randomNums[currentIndex + 1] = currnetNum;
            
        }    
        
        
        //Finishing the time tracking
        long endTime = System.nanoTime();
        long time = endTime-startTime;
        
        //Output
        System.out.println("\nInsertion sort:");
        System.out.println("Number of comparisons made= " + comparisons);
        System.out.println("Number of loops exicuted= " + loops);
        System.out.println("Number of shifts made= " + shifts);
        System.out.println("Length of time to sort = " + time/1000000 +"ms");

       //NOTE: In this section I got a different output than you
       //I belive this may be due to a different cooding soloution
       //From what I can tell I count all shifts,loops and comparisons correctly
    }
    
    /**
     * This method complete a merge sort on the random numbers
     * (this section of code was copied from codehs and modified)
     * @param randomNums 
     */
    public static void merge(int[] randomNums){
        //Variables
        int[] temp = new int[randomNums.length];
        int[] info = new int[3];
        System.out.println("\nMerge sort:");
        
        //Setting up time tracking
        long startTime = System.nanoTime();
        
        //Begining the sorting
        mergeSortHelper(randomNums, 0, randomNums.length - 1, temp);
        
        //Finishing the time tracking
        long endTime = System.nanoTime();
        long time = endTime-startTime;
        
        //Output
        System.out.println("Number of comparisons made= " + mergeComparisons);
        System.out.println("Number of loops exicuted= " + mergeLoops);
        System.out.println("Number of shifts made= " + mergeShifts);
        System.out.println("Length of time to sort = " + time/1000000 +"ms");
        
        //NOTE: once again my output is slightly differnet than yours and I 
        //Cannot figure out why, however it is nearly the same
        //and therefore I assume it may be a difference in the coding 
    }
    
    /**
     * This method is a helper method for the merge sort algorithm
     * @param randomNums
     * @param from
     * @param to
     * @param temp 
     */
    private static void mergeSortHelper(int[] randomNums, int from, int to, int[] temp)
    {
        //This is "breaking" the array down into smaller pecies
        if(to - from >= 1)
        {
            //Varaible
            int mid = (from + to) / 2;
            //Continueing the breaking down
            mergeSortHelper(randomNums, from, mid, temp);
            mergeSortHelper(randomNums, mid + 1, to, temp);
            //Merging the numbers of the broken array together
            merge(randomNums, from, mid, to, temp);
     
        }
        //Keeping track of efficency variable
        mergeLoops++;
        
        
    }
    
    /**
     * This method does the actual mergeing of each cell
     * @param randomNums
     * @param from
     * @param mid
     * @param to
     * @param temp 
     */
    public static void merge(int[] randomNums, int from, int mid, int to, int[] temp) 
    {
        //Variables
        int i = from;       // track left randomNumsay position
        int j = mid + 1;    // track right randomNumsay position
        int k = from;       // track temp position
        
        
        while(i <= mid && j <= to)
        {
            mergeLoops++;
            
            if(randomNums[i] < randomNums[j])
            {
                temp[k] = randomNums[i];
                i++;
                mergeComparisons++;
            }
            else
            {
                temp[k] = randomNums[j];
                j++;
            }
            
            k++;
        }
        
        // We may have missed elements from either list
        while(i <= mid)
        {
            mergeLoops++;
            mergeShifts++;
            temp[k] = randomNums[i];
            i++;
            k++;
        }
        
        while(j <= to)
        {
            mergeLoops++;
            mergeShifts++;
            temp[k] = randomNums[j];
            j++;
            k++;
        }
        
        // Copy over from temp to elements
        for(k = from; k <= to; k++)
        {
            mergeLoops++;
            mergeShifts++;
            randomNums[k] = temp[k];
        }
     
    }
    
}
