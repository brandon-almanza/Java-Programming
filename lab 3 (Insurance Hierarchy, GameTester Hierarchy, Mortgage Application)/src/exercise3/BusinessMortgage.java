package exercise3;

public class BusinessMortgage extends Mortgage {
	
	public BusinessMortgage(String mortgageNum, String custName, double mortgageAmount, double rate, int term) {
		super(mortgageNum, custName, mortgageAmount, rate, term);
		
		//Set interest rate to 0.01% over the current prime rate
		rate = 0.01;
		this.rate = rate;
	}
}
