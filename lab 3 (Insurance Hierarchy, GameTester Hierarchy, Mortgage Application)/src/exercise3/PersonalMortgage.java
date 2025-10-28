package exercise3;

public class PersonalMortgage extends Mortgage {
	public PersonalMortgage(String mortgageNum, String custName, double mortgageAmount, double rate, int term) {
		super(mortgageNum, custName, mortgageAmount, rate, term);
		
		//Set interest rate to 0.02% over the current prime rate
		rate = 0.02;
		this.rate = rate;
	}
}
