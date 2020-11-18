package app.banking.operations;

import java.util.Scanner;

import app.banking.bean.AccountBean;
import app.banking.bean.AccountStatement;
import app.banking.csv_io.CSVread;
import app.banking.csv_io.CSVwrite;
import app.banking.driver.BankingApp;
import app.banking.util.DateAndTime;

public class ExistingCustomer {

	private static Scanner sc = new Scanner(System.in);

	/**
	 * Landing method for existing customers
	 */
	public static void existingCustomer() {
		System.out.println("===================================");
		System.out.println(
				"Type \n1 for Deposit amount \n2 for Withdraw amount \n3 for Check Balance \n4 to goto main menu");

		System.out.print("Input: ");

		int choice = sc.nextInt();

		switch (choice) {
			case 1:
				deposit();
				break;
			case 2:
				withdraw();
				break;
			case 3:
				checkBalance();
				break;
			case 4:
				String[] args = { "", "" };
				BankingApp.main(args);
			default:
				existingCustomer();
		}
	}

	/**
	 * method to deposit money
	 */
	public static void deposit() {
		System.out.println("==== Deposit ====");
		System.out.print("Enter account unique name: ");
		String name = sc.next();
		
		AccountBean bean = new AccountBean();
		bean.setName(name);
		//AccountBean bean = new AccountBean();
		bean = CSVread.checkAccount(bean);
		
		if(bean == null) {
			System.out.println("No such account found\n");
			deposit();
		}
		else {
			System.out.println("Account number: " + bean.getAccount_number());
			System.out.print("Mode(1 for Cash, 2 for Online): ");
			int choice = sc.nextInt();
			String mode = null;
			if(choice == 1)
				mode = "cash";
			else if(choice == 2)
				mode = "online";
			System.out.print("Amount: ");
			int txn_amount = sc.nextInt();
			
			double balance = bean.getBalance() + txn_amount;
			String date = DateAndTime.dateTime() ;
			String txn_type = "Cr";
			int account_number = bean.getAccount_number();
			
			bean = new AccountBean(bean.getAccount_number(), bean.getName(), balance);
			AccountStatement asbean = new AccountStatement(date, txn_type, account_number, txn_amount, balance, mode);
			
			boolean flag = CSVwrite.depositWithdrawalEntry(bean, asbean);
			if (flag) {
				System.out.println("Transaction successful");
				existingCustomer();
			}
			else {
				System.out.println("Transaction failed");
				existingCustomer();
			}
			
			//System.out.println(bean +"\n"+ asbean);
			
		}
	}

	/**
	 * method to withdraw money
	 */
	public static void withdraw() {
		System.out.println("==== Withdrawal ====");
		System.out.print("Enter account unique name: ");
		String name = sc.next();
		
		AccountBean bean = new AccountBean();
		bean.setName(name);
		bean = CSVread.checkAccount(bean);
		
		if(bean == null) {
			System.out.println("No such account found\n");
			withdraw();
		}
		else {
			System.out.println("Account number: " + bean.getAccount_number());
			System.out.print("Mode(1 for Cash, 2 for Online, 3 for ATM): ");
			int choice = sc.nextInt();
			String mode = null;
			if(choice == 1)
				mode = "cash";
			else if(choice == 2)
				mode = "online";
			else if(choice == 3)
				mode = "atm";
			System.out.print("Amount: ");
			int txn_amount = sc.nextInt();
			
			//lower balance than withdrawal request
			if(txn_amount > bean.getBalance()) {
				System.out.println("Insufficient balance");
				existingCustomer();
			}
			
			double balance = bean.getBalance() - txn_amount;
			String date = DateAndTime.dateTime() ;
			String txn_type = "Dr";
			int account_number = bean.getAccount_number();
			
			bean = new AccountBean(bean.getAccount_number(), bean.getName(), balance);
			AccountStatement asbean = new AccountStatement(date, txn_type, account_number, txn_amount, balance, mode);
			
			boolean flag = CSVwrite.depositWithdrawalEntry(bean, asbean);
			if (flag) {
				System.out.println("Transaction successful");
				existingCustomer();
			}
			else {
				System.out.println("Transaction failed");
				existingCustomer();
			}
			
			//System.out.println(bean +"\n"+ asbean);
			
		}
	}

	/**
	 * method to check balance
	 */
	public static void checkBalance() {

		System.out.println("==== Balance Enquiry ====");
		System.out.print("Enter account unique name: ");
		String name = sc.next();
		
		AccountBean bean = new AccountBean();
		bean.setName(name);
		bean = CSVread.checkAccount(bean);
		
		if(bean == null) {
			System.out.println("No such account found\n");
			existingCustomer();
		}
		System.out.println("Account Number: "+bean.getAccount_number());
		System.out.println("Balance: "+bean.getBalance());
		
		System.out.println("\nType \n1 for previous menu \n2 for main menu");
		System.out.print("Input: ");
		int choice = sc.nextInt();
		switch(choice) {
			case 1: 
				existingCustomer();
				break;
			case 2: 
				String[] args = {"",""};
				BankingApp.main(args);
			default:
				String[] argss = {"",""};
				BankingApp.main(argss);
		}
		
	}
}
