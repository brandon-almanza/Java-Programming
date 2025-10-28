package exercise1;

public class Health extends Insurance {
	
	//Default constructor
	public Health() {
		this.insuranceType = "Health";
	}
	
	//Overriding method to set monthlyCost of Health insurance
	@Override public void setInsuranceCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}
	
	//Overriding method to display Health insurance info
	@Override public void displayInfo() {
		System.out.println("Insurance Type: " + insuranceType + "  Monthly Cost: " +  "$" + monthlyCost + "\n");
	}
}
