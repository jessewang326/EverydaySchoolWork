
public class SavingsAccount extends BankAccount {
	private double rate;
	
	
	
	/**
	 * Constructs a new SavingsAccount with an initial balance,
	 * and an interest rate
	 */
	public SavingsAccount(double initialAmount, double rate) {
		// TODO Auto-generated constructor stub
		super(initialAmount);
		rate = 0;		
	}

	
	public double getRate(){
		return rate;
	}
	
	/**
	 * Calculates one month's interest and adds the amount to the balance
	 */
	public void calculateInterest(){
		this.deposit(this.getBalance() * this.getRate());		
	}
	
	
	/**
	 * Returns a String representation of the account's
	 * attributes (balance and interest rate)
	 */
	public String toString(){
		String toString = new String("the current balance is " + this.getBalance() 
									  + " and the interest rate is " + this.getRate());
		return toString;
	}


}
