package exercise3;

abstract public class Mortgage implements MortgageConstants{
	//Variables
	protected String mortgageNum;
	protected String custName;
	protected double mortgageAmount;
	protected double rate;
	protected int term;
	
	//Constructor
	public Mortgage(String mortgageNum, String custName, double mortgageAmount, double rate, int term){
		this.mortgageNum = mortgageNum;
		this.custName = custName;
		this.mortgageAmount = mortgageAmount;
		this.rate = rate;
		this.term = term;
		
		//If mortgageAmount is greater than MAX_MORTGAGE
		if(mortgageAmount > MAX_MORTGAGE) {
			//Change mortgage amount to MAX_MORTGAGE
			this.mortgageAmount = MAX_MORTGAGE;
		}
		//if else
		else {
			this.mortgageAmount = mortgageAmount;
		}
		
		//If term is undefined
		if(term != SHORT_TERM && term != MEDIUM_TERM && term != LONG_TERM) {
			//Set term to SHORT_TERM
			this.term = SHORT_TERM;
		}
	}
	
	//Display all mortgage info accordingly
	public String getMortgageInfo(){
		return "Mortgage Number: " + mortgageNum + "  Customer Name: " + custName + "  Mortgage Amount: " +  "$" + mortgageAmount + "  Rate: " + rate + "%" + "  Term: " + term;
	}
}
