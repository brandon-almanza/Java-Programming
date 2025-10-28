package exercise2;

public class FullTimeGameTester extends GameTester {
	
	@Override public void determineSalary(double hours) {
		hours = 3000;
	}
	
	@Override public String displayInfo() {
		return "Name: " + name + " FullTime Status: " + fullTimeStatus + " Salary: $3000 ";
	}
}
