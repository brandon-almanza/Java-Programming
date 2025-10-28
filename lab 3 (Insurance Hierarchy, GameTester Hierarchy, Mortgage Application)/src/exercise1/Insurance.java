package exercise1;

public abstract class Insurance {
	//Variables
	protected String insuranceType;
	protected double monthlyCost;
	
	//Getters
	public String getInsuranceType(){
		return insuranceType;
	}
	public double getMonthlyCost() {
		return monthlyCost;
	}
	
	//Methods
	abstract public void setInsuranceCost(double monthlyCost);
	abstract public void displayInfo();
	
}
