package exercise1;
import javax.swing.JOptionPane;

//Brandon Argenal Almanza, 301467830
public class Main {
	public static void main(String[] args) {
		//Creating new test object
		Test test1 = new Test();
		
		//Loops through the 5 questions
		for(int i = 0; i < 5; i++) {
			//using the simulateQuestions method
			test1.simulateQuestion(i);
		}
		//Calls a method that displays the results
		test1.DisplayResults();
	}
}