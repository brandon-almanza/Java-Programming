package exercise2;
import java.security.SecureRandom;

//Brandon Argenal Almanza, 301467830
public class Lotto {
	//initializing an array
	//to store 3 values
	private int[] lottoNums = new int[3];
	
	//Creating a random number generator object
	SecureRandom randomNumbers = new SecureRandom();
	
	//Constructor
	public Lotto() {
		//while i is less than the length of the array
		for(int i = 0; i < lottoNums.length; i++) {
			//adds a random number from 1-9
			//and inserts it into each index element
			lottoNums[i] = 1 + randomNumbers.nextInt(9);
		}
	}
	
	//Method that returns the sum of the array
	public int returnArray() {
		
		//initialize sum to 0
		int sum = 0;
		
		//add all the numbers from the array and calculate the sum 
		sum = lottoNums[0] + lottoNums[1] + lottoNums[2];
		
		//return the sum of the array
		return sum;
	}
	
	public int[] getNumbers() {
		//returns the generated lotto numbers
		return lottoNums;
	}
	
}
