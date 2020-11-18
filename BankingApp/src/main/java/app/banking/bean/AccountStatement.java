package app.banking.bean;

public class AccountStatement{

	/**
	 * Columns for account_statement.csv
	 */
	private String date;
	private String txn_type;
	private int account_number;
	private double txn_amount;
	private double balance;
	private String mode;

	public AccountStatement() {

	}

	/**
	 * To initialize along with value
	 * 
	 * @param date
	 * @param txn_type
	 * @param account_number
	 * @param txn_amount
	 * @param balance
	 * @param mode
	 */
	public AccountStatement(String date, String txn_type, int account_number, double txn_amount, double balance,
			String mode) {
		super();
		this.date = date;
		this.txn_type = txn_type;
		this.account_number = account_number;
		this.txn_amount = txn_amount;
		this.balance = balance;
		this.mode = mode;
	}

	/**
	 * getters and setters for all the fields
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public double getTxn_amount() {
		return txn_amount;
	}

	public void setTxn_amount(double txn_amount) {
		this.txn_amount = txn_amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
