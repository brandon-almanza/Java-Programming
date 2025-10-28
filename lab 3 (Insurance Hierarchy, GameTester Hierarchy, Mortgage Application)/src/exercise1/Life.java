package exercise1;

public class Life extends Insurance {
	
	//Default constructor 
	public Life() {
		this.insuranceType = "Life";
	}
	
	//Overriding method to set the monthly insurance cost for Life insurance
	@Override public void setInsuranceCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
		}
	//Overriding method to display Life insurance info
	@Override public void displayInfo() {
		System.out.println("Insurance Type: " + insuranceType + " Monthly Cost: " + "$" + monthlyCost + "\n");
	}
}
