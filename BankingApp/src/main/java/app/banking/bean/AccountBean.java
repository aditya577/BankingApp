package app.banking.bean;

public class AccountBean{

	/**
	 * Columns for account.csv
	 */
	private int account_number;
	private String name;
	private double balance;

	public AccountBean() {
	}

	/**
	 * To initialize along with value
	 * 
	 * @param account_number
	 * @param name
	 * @param balance
	 */
	public AccountBean(int account_number, String name, double balance) {
		super();
		this.account_number = account_number;
		this.name = name;
		this.balance = balance;
	}

	/**
	 * Getters and setters for all the variables above
	 */
	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
