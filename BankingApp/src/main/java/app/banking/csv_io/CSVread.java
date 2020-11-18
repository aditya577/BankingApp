package app.banking.csv_io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import app.banking.bean.AccountBean;
import app.banking.util.BankingConstants;
import app.banking.util.Quoted;

public class CSVread {

	private static Scanner myf1 = null;
	private static File accountCSV;

	/**
	 * Method to check if account_number and name exist in account.csv before new
	 * customer registration to keep these two fields UNIQUE
	 */
	public static String checkForExistingEntry(AccountBean bean) {

		// set flag to available i.e., no such entry exist in CSV
		String flag = BankingConstants.AVAILABLE;

		String line = "";
		@SuppressWarnings("unused")
		int lineNumber = 0;

		// fetching the path of account.csv from BankingConstants.java
		// File csvFile = new File(BankingConstants.ACCOUNT_CSV);
		BufferedReader br;
		accountCSV = new File( BankingConstants.ACCOUNT_CSV);
		try {
			br = new BufferedReader(new FileReader(accountCSV));
			while ((line = br.readLine()) != null) {

				// Using the comma(,) as defined delimiter
				String[] arr = line.split(",");

				// Assigning existing account_number and name from CSV to compare
				String csv_account_number = arr[0];
				String csv_name = arr[1];

				// Comparing the new passed name and existing name
				if (csv_name.equalsIgnoreCase(Quoted.qoute(bean.getName()))) {

					flag = BankingConstants.NAME_TAKEN;
					break;
				}

				// Comparing the new account number and existing account numbers
				if (csv_account_number.equalsIgnoreCase(Integer.toString(bean.getAccount_number()))) {

					flag = BankingConstants.NUMBER_TAKEN;
					break;
				}

				lineNumber++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * method to read the whole content of file for which path is given
	 */
	public static ArrayList<String> readCSV(String filepath) {
		ArrayList<String> list = new ArrayList<String>();

		accountCSV = new File(filepath);
		
		String temp = null;

		try {
			// reading the account.csv file
			myf1 = new Scanner(accountCSV);

			while (myf1.hasNextLine()) {
				temp = myf1.nextLine();
				list.add(temp);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			myf1.close();
		}

		return list;
	}
	
	/**
	 * method to check for name
	 */
	public static AccountBean checkAccount(AccountBean bean) {
		AccountBean abean = null;
		
		ArrayList<String> list =  readCSV(BankingConstants.ACCOUNT_CSV);
		
		/*
		 * for(String e: list) System.out.println(e);
		 */
		
		for (String e: list) {
			//System.out.println(e);
			String[] tokens = e.split(",");
			if (tokens[1].equalsIgnoreCase(Quoted.qoute(bean.getName())) ) {
				//System.out.println(tokens[1]);
				abean = new AccountBean(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]));
				break;
			}
		}
		//System.out.println(abean);
		return abean;
	}
	
}
