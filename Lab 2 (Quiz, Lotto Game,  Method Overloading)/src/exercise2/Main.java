package exercise2;
import javax.swing.JOptionPane;

//Brandon Argenal Almanza, 301467830
public class Main {
	public static void main(String[] args) {
		//Get user input for the lotto
		String userInput = JOptionPane.showInputDialog("Choose a number between 3 and 27");
		
		//Converting the input from string to integer
		 int input = Integer.parseInt(userInput);
		 
		 //initialize winLose to false
		 boolean winLose = false;
		 
		 //loops through the lotto 5 times 
		 for(int i = 0; i < 5; i++) {
			 //Create new lotto object
			 Lotto lotto = new Lotto();
			 
			 //Each sum of the arrays will get placed into this sum variable
			 int sum = lotto.returnArray();
			 
			 //The generated numbers will get added to an array called numbers
			 int[] numbers = lotto.getNumbers();
			 
			 //Display numbers, and the sum using JOptionPane
			 JOptionPane.showMessageDialog(null, "numbers: " + numbers[0] + ", " + numbers[1] + ", " + numbers[2] + "\nSum: " + sum);

			 //if the input is equal to the sum
			 if(input == sum) {
				 //Display winning message with JOptionPane
				 JOptionPane.showMessageDialog(null, "CONGRATULATIONS! YOU WIN!");
				 
				 //change winLose value to true
				 winLose = true;
				 //break out of loop
				 break;
			 }
			 //if input is less than 3 and greater than 27
			 else if(input < 3 || input > 27) {
				 //Display error message
				 JOptionPane.showMessageDialog(null, "Invalid Number. Please choose a number from 3 to 27");
				 //break out of loop
				 break;
			 }
		}
		    //if winLose is false
		 	if(!winLose){
		 		//Display losing message using JOptionPane
		 		JOptionPane.showMessageDialog(null, "Ouch.. You lost.. better luck next time!");
		 		//change winLose value to false
		 		winLose = false;
		 		}
		 }
		 
	}

