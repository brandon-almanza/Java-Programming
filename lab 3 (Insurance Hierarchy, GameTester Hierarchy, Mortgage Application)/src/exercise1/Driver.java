package exercise1;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		System.out.println("Brandon Argenal Almanza, 301467830");
		
		//Creating scanner object
		Scanner scan = new Scanner(System.in);
		
		//Creating Insurance array with allocated storage
		Insurance[] references = new Insurance[2];
		
		//For loop
		for(int i = 0; i < references.length; i++) {
			//Gets user input
			System.out.println("What type of insurance are you looking for? (life or Health)");
			String userInput = scan.nextLine();
			
			//If user input is equal to "health"
			if(userInput.equalsIgnoreCase("health")) {
				//Create new Health object
				Health healthUser = new Health();
				//Set insurance cost for health user
				healthUser.setInsuranceCost(127.99);
				//Add the object to the Insurance array (references)
				references[i] = healthUser;
				
		    }
			//Else if user input is equal to "life"
			else if(userInput.equalsIgnoreCase("life")) {
				//Create new Life object
				Life lifeUser = new Life();
				//Set insurance cost for life user
				lifeUser.setInsuranceCost(29.99);
				//Add the object to the insurance array (references)
				references[i] = lifeUser;
			}
        }
			
		//For loop
		for(int i = 0; i < references.length; i++) {
			//Display each objects info
			references[i].displayInfo();
		}
		scan.close();
    }
}
