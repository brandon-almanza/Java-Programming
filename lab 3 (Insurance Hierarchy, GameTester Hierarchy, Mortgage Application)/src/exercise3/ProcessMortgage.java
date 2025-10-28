package exercise3;
import java.util.Scanner;
public class ProcessMortgage {
	public static void main(String[] args) {
		System.out.println("Brandon Argenal Almanza, 301467830");
		Scanner scan = new Scanner(System.in);
		
		//Mortgage array to store 3 objects
		Mortgage[] references = new Mortgage[3];
		
		//Get userRate 
		System.out.println("Please enter current interest rate");
		double userRate = scan.nextDouble();
		scan.nextLine();
		
		//For loop
		for(int i = 0; i < 3; i++) {
			//Asks and Gets userType
			System.out.println("What type of mortgage are you looking for? (Business or Personal)");
			String userType = scan.nextLine();
			
			//If userType is equal to business
			if(userType.equalsIgnoreCase("business")) {
				//Get users mortgage number
				System.out.println("Enter mortgage number");
				String userNumber = scan.nextLine();
				
				//Get users name
				System.out.println("Enter customer name");
				String userName = scan.nextLine();
				
				//Get users mortgage amount
				System.out.println("Enter mortgage amount");
				double userAmount = scan.nextDouble();
				
				//Get users mortgage term
				System.out.println("Enter mortgage term (short = 1	Medium = 3  Long = 5)");
				int userTerm = scan.nextInt();
				scan.nextLine();
				
				//Create BusinessMortgage object and set users inputs to object
				BusinessMortgage bmUser = new BusinessMortgage(userNumber, userName, userAmount, userRate, userTerm );
				//Add object to references array
				references[i] = bmUser;
			}
			//Else if userType is equal to Personal 
			else if(userType.equalsIgnoreCase("personal")) {
				//Get users mortgage number
				System.out.println("Enter mortgage number");
				String userNumber = scan.nextLine();
				
				//Get users name
				System.out.println("Enter customer name");
				String userName = scan.nextLine();
				
				//Get users mortgage amount
				System.out.println("Enter mortgage amount");
				double userAmount = scan.nextDouble();
				
				//Get users mortgage term
				System.out.println("Enter mortgage term (short = 1	Medium = 3  Long = 5)");
				int userTerm = scan.nextInt();
				scan.nextLine();
				
				//Create BusinessMortgage object and set users inputs to object
				PersonalMortgage pmUser = new PersonalMortgage(userNumber, userName, userAmount, userRate, userTerm );
				//Add object to references array
				references[i] = pmUser;
			}
		}
		//For loop
		for(int j = 0; j < references.length; j++) {
			//Displays all objects in mortgage array (references)
			System.out.println(references[j].getMortgageInfo());
		}
	}
}
