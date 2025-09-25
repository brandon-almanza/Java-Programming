package exercise3;

//Brandon Argenal Almanza, 301467830
public class Main {
	public static void main(String[] args) {
		
		//Calling method one 
		//Calculates the two arguments and returns the sum
		System.out.println("Method One: " + Overloading.calculate(10, 25));
		
		//Calling method two
		//Returns the value of the string n amount of times
		System.out.println("Method Two: " + Overloading.calculate("Hello", 5));
		
		//Calling method three
		//Divides the two doubles and returns the sum
		System.out.println("Method Three: " + Overloading.calculate(10.00, 20.00));
	}
	
	
}
