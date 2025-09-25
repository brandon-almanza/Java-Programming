package exercise1;
import java.util.Random;
import javax.swing.JOptionPane;

//Brandon Argenal Almanza, 301467830
public class Test {
	
	//Initialize question array with questions and its options for each question
	private String[] questions = {"1. Which of the following statements is false about Java method statements? "
			+ "\na) Statements in a method body is written only once "
			+ "\nb) Statements in a method body are not hidden from other methods. "
			+ "\nc) Statements in a method body determine the behavior of an object. "
			+ "\nd) Statements in a method body use existing classes and methods as building blocks to create new programs.",
			"2. Method arguments may be? "
			+ "\na) only strings "
			+ "\nb) only variables "
			+ "\nc) only constants "
			+ "\nd) constants, variables, or expressions",
			"3. Finally block, if it exists does one of the following: "
			+ "\na) executes only if catch block(s) catch(es) an exception. "
			+ "\nb) executes only if there are more than one catch block. "
			+ "\nc) always executes no matter whether the catch block(s) catch(es) exceptions or not. "
			+ "\nd) executes only if catch block(s) fail(s) to catch an exception.",
			"4. String-concatenation actually does one of the following: "
			+ "\na) modifies the original string and saves the string with the modified data. "
			+ "\nb) replaces the original string and the original string gets deleted. "
			+ "\nc) does not exist in Java programming language. "
			+ "\nd) creates a new String object containing the concatenated values - the original String objects are not modified.",
			"5. Get methods are also commonly called one of the following: "
			+ "\na) mutator methods "
			+ "\nb) accessor methods or query methods "
			+ "\nc) static methods "
			+ "\nd) non-static methods"};
	
	//Initializing answers to the correct answers
	private char[] answers = {'b', 'b', 'd', 'd', 'b' };
	
	//Initialize correct Count to 0
	private int correctCount = 0;
	//Initialize incorrect Count to 0
	private int incorrectCount = 0;
	
	
	public void simulateQuestion(int index) {
		//Getting the users choice from the ShowInputDialog and 
		String userChoice = JOptionPane.showInputDialog(questions[index] + "\nEnter an answer from A to D");
		
		//Calling the next method
		inputAnswer(index, userChoice);
	}
	
	public void inputAnswer(int index, String userInput) {
		//with the values we got from simulate question 
		//Call the next method to check the users answer
		checkAnswer(index, userInput);
	}
	
	public void checkAnswer(int index, String userInput) {
		//initialize the correct answer to answers index
		char correctAnswer = answers[index];
		
		//If userInput is equal to correctAnswer
		if(userInput.toLowerCase().charAt(0) == correctAnswer ) {
			//Increment correctCount
			correctCount++;
			
			//initialize method argument to true
			generateMessage(true);
		}
		//if else
		else {
			//increment incorrectCount
			incorrectCount++;
			 
			//Display message showing the user the correct answer
			JOptionPane.showMessageDialog(null, "Incorrect Answer. the correct answer is: " + correctAnswer);
			//Initialize method to false
			generateMessage(false);
			
		}
	}
	
	public void generateMessage(boolean isCorrect) {
		//Creating random number generator
		Random randomMessage = new Random();
		
		//Initialize choice to randomMessage
		//this will generate a random number from 0-4 (4 exclusive)
		int choice = randomMessage.nextInt(4);
		
		//If isCorrect is true
		if(isCorrect == true) {
			//Display any congratulatory message
			switch(choice) {
			case 0: JOptionPane.showMessageDialog(null, "Good!");
			break;
			case 1: JOptionPane.showMessageDialog(null, "Great!");
			break;
			case 2: JOptionPane.showMessageDialog(null, "Awesome!");
			break;
			case 3: JOptionPane.showMessageDialog(null, "Outstanding!");
			break;
			}
		}
			//If else
			else {
				//Display any critical message
				switch(choice) {	
				case 0: JOptionPane.showMessageDialog(null, "Nope, Keep Trying!");
						break;
				case 1: JOptionPane.showMessageDialog(null, "Don't give up!");
						break;
				case 2: JOptionPane.showMessageDialog(null, "No, please try again");
						break;
				case 3: JOptionPane.showMessageDialog(null, "unlucky..");
						break;
				}
			}
	}
	
	public void DisplayResults() {
		//initialize total to the number of questions
		int total = questions.length;
		
		//Calculate the users score
		//by multiplying the correctCount (correct answers) by 100
		//then dividing it by the total (number of questions) to get the score
		double scorePercent = (correctCount * 100) / total;
		
		//Displays the results using ShowMessageDialog
		JOptionPane.showMessageDialog(null, "Incorrect: " + incorrectCount + "\nCorrect: " + correctCount + "\nScore: " + scorePercent + "%");
	}
}
