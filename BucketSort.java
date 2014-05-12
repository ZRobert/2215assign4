/*********************************************************
* BucketSort.java
* Author: Robert Payne
* Date: 12/2/2012
* Class: ITCS 2215
*
* Purpose:  To implement the distribution counting algorithm.
  for an input set restricted to values between 20-40. This
  algorithm counts the frequencies and records the 
  distribution values for each of the possible inputs. Using
  this information, the array is sorted in less than O(n log n)
  time. The trade off is the need to use more memory to store
  the frequency/distribution values for each possible input. 

/*********************************************************/
import java.util.Random;

public class BucketSort {

/*	main()	 
===========================================================

---------------------------------------------------------*/ 
	public static void main(String[] args) {	
	 
	 	//DECLARATIONS-------------------------------------------
		//=======================================================

		int[] sortBucket = new int[256]; //the array to be filled
														//with random numbers
														//initialized to the 
														//maximum size needed

		int[] frequencyList = new int[20]; //the array to be filled with
														//the frequency of occurance of
														//the 20 possible numbers (20,...,39)
		
		int[] distributionList = new int[20];	//the array to be filled with
																//the distribution values
		
		Random randomNumber = new Random();	//random number object

		int powOfTwo = 3;							//placeholder for which
														//power of two currently on

		int currentSize;							//current size of array
														//being analyzed

		int currentPosition;						//current position in the
														//bucket
		
	//MAIN LOOP----------------------------------------------------
	//=============================================================
			
		//This loop iterates for each power of 2 from 3 to 8
		while(powOfTwo <= 8) {
			
			currentSize =(int) Math.pow(2, powOfTwo); //set the currentSize
			currentPosition = 0; //for sorting the array
			//initialize the frequency and distribution lists
			for(int i = 0; i < 20; i++) {
			
				frequencyList[i] = 0;
				distributionList[i] = 0;
			}
			
			//initialize the array with random numbers
			for(int i = 0; i < currentSize; i++) 			
				sortBucket[i] = randomNumber.nextInt(20)+20;
				
			//Set the values of the frequency list
			for(int i = 0; i < currentSize; i++)
				frequencyList[sortBucket[i]-20]++;
			
			//Sort the array and set the distribution values
			for(int i = 0; i < 20; i++) {	//loop for each possible value
			
				for(int k = 0; k < frequencyList[i]; k++) {	//set each value into sorted position
				
					sortBucket[currentPosition] = i + 20;
					currentPosition++;		//increment the current position in the array
				}
				
				distributionList[i] = currentPosition; //set the distribution value for i
			}
			
	//OUTPUT--------------------------------------------------------
	//==============================================================		
			
			//Output the size of the current set
			System.out.println("N = " + currentSize);

			//Output the possible array values
			System.out.print("Array Values: \t");
			for(int i = 0; i < 20; i++) {
			
				System.out.print((i + 20) + ", ");			
			}
			
			//Output the frequency list
			System.out.print("Frequenices: \t");
			
			for(int i = 0; i < 19; i++)
				System.out.print(frequencyList[i] + ", ");
			
			System.out.println(frequencyList[19]);
			
			//Output the distribution list
			System.out.print("Distribution Vals:  ");
			
			for(int i = 0; i < 19; i++)
				System.out.print(distributionList[i] + ", ");
				
			System.out.println(frequencyList[19]);
			
			//Output the sorted array
			System.out.print("Sorted Array: \t");
			for(int i = 0; i < currentSize -1; i++) {
				
				System.out.print(sortBucket[i] + ", ");
			}
			
			System.out.println(sortBucket[currentSize -1] + "\n");
								
			powOfTwo++;	//increment the search array size for the next iteration
			
		}
	}
}