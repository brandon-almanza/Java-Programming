package exercise2;

public class PartTimeGameTester extends GameTester {
	@Override public void determineSalary(double hours) {
		this.hours = hours * 20;
	}
	
	@Override public String displayInfo() {
		return "Name: " + name + " FullTime Status: " + fullTimeStatus + " Pay: " + "$" + hours;
	}
}
