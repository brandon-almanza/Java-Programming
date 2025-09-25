package exercise3;

//Brandon Argenal Almanza, 301467830
public class Overloading {
	//Method that adds the two numbers together
	public static int calculate(int num1, int num2) {
		int sum = num1 + num2;
		
		//returns the sum
		return sum;
	}
	
	public static String calculate(String word, int num ) {
		
		//Initialize variable to an empty string
		String result = "";
		
		//If num is greater than i
		for(int i = 0; i < num; i++) {
			//repeat the string
			result += word;
		}
		//return the result
		return result;
	}
	
	//Calculates two numbers by using division
	public static double calculate(double num1, double num2) {
		
		//Initialize sum to 0
		double sum = 0;
		
		//If the second number is equal or less than 0
		if(num2 <= 0.0) {
			//Display an error
			System.out.println("\ncannot divide by this number");
		}
		//if else
		else {
			//calculate the two numbers
			sum = num1 / num2;
		}
		//return the sum
		return sum;
	}
}
