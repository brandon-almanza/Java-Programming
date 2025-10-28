package exercise2;
import java.util.*;

public class Driver {
	public static void main(String[] args) {
		System.out.println("Brandon Argenal Almanza 301467830");
		Scanner scan = new Scanner(System.in);
		
		//Loop 3 times
		for(int i = 0; i < 3; i++) {
			//Get users status
			System.out.println("\nWhat is your game tester status? (Full Time or Part Time)");
			String status = scan.nextLine();
			
			//If status is equal to part time
			if(status.equalsIgnoreCase("part time")) {
				//Create PartTimeGameTester object
				PartTimeGameTester ptTester = new PartTimeGameTester();
				//Set full time status to false
				ptTester.setStatus(false);
				
				//Get and Set user name
				System.out.println("What is your name?");
				String name = scan.nextLine();
				ptTester.setName(name);
				
				//Get and Set users hours worked
				System.out.println("How many hours have you worked today?");
				double hours = scan.nextDouble();
				scan.nextLine();
				ptTester.determineSalary(hours);
				
				//Display object info
				System.out.println(ptTester.displayInfo());
			}
			//Else if status is equal to full time
			else if(status.equalsIgnoreCase("full time")) {
				//Create FullTimeGameTester 
				FullTimeGameTester ftTester = new FullTimeGameTester();
				//Set full time status to true
				ftTester.setStatus(true);
				
				//Get and Set users name
				System.out.println("What is your name?");
				String name = scan.nextLine();
				ftTester.setName(name);
				
				//Display objects info
				System.out.println(ftTester.displayInfo());
			}
			//If else
			else {
				//Throw error message
				System.out.println("Error Occured, please try again");
			}
		}	
		scan.close();
	}
}
