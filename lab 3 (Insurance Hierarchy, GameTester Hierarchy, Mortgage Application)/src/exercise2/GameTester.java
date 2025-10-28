package exercise2;

public abstract class GameTester {
	
	//Variables
	protected String name;
	protected boolean fullTimeStatus;
	protected double hours;
	
	//Getters
	public String getName() {
		return name;
	}
	
	public boolean getStatus() {
		return fullTimeStatus;
	}
	
	//Setters
	public void setStatus(boolean fullTimeStatus) {
		this.fullTimeStatus = fullTimeStatus;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	//Abstract Methods
	abstract public void determineSalary(double hours);
	abstract public String displayInfo();
}