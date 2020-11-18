package app.banking.operations;

import java.util.Random;
import java.util.Scanner;

import app.banking.bean.AccountBean;
import app.banking.csv_io.CSVread;
import app.banking.csv_io.CSVwrite;
import app.banking.driver.BankingApp;
import app.banking.util.BankingConstants;

public class NewCustomer {

	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * landing method for new customer
	 */
	public static void newCustomer() {
		System.out.print("Please enter unique name to open account \nOR type \"X/x\" for main menu \nType: ");
		String name = sc.next();
	
		//taking customer to main menu
		if(name.equalsIgnoreCase("X")) {
			String [] args = {"",""};
			BankingApp.main(args);
		}
		
		AccountBean bean = new AccountBean();
		bean.setName(name);
		AccountBean nbean = customerRegistration(bean);
		if (nbean != null) {
			System.out.println("Account created successfully");
			System.out.println("Name: "+nbean.getName());
			System.out.println("Account Number: "+nbean.getAccount_number());
			System.out.println("Balance: "+nbean.getBalance());
		}
		else {
			System.out.println("The name you enetered is already taken");
			newCustomer();
		}
		
		System.out.println("\n\n");
	}
	
	/**
	 * method to register a new customer
	 * @param bean
	 * @return
	 */
	public static AccountBean customerRegistration(AccountBean bean) {
		Random random = new Random();
		
		// generating unique account_number assuming only 0 to 999 number of customers
		int account_number = random.nextInt(1000);
		bean.setAccount_number(account_number);
		
		int initial_balance = 0;
		bean.setBalance(initial_balance);
		
		// checking for account_number and name in CSV to ensure UNIQUENESS
		String returnFlag = CSVread.checkForExistingEntry(bean);

		//checking if account number generated is existing or not already
		if (BankingConstants.NUMBER_TAKEN.equals(returnFlag))
			customerRegistration(bean);
		
		//checking if the name provided by customer is existing or not already
		if (BankingConstants.AVAILABLE.equals(returnFlag)) {
			boolean newFlag = CSVwrite.addAccount(bean);
			
			if (newFlag)
				return bean;
		}

		System.out.println(returnFlag);

		return null;
	}
}
