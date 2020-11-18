package app.banking.driver;

import java.util.Scanner;

import app.banking.operations.ExistingCustomer;
import app.banking.operations.NewCustomer;

public class BankingApp {

	private static int choice = 0 ;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while(true) {
			
			if ( choice == 3) {
				System.out.println("Thanks for using our services :)");
				break;
			}
			
			System.out.println("Welcome to Bank");
			System.out.println("===================================");
			
			System.out.println("Type  \n   1 for existing customer\n   2 for new customer\n   3 to exit");
			System.out.print("Input: ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					ExistingCustomer.existingCustomer(); 
					break;
				case 2: 
					NewCustomer.newCustomer(); 
					break;
				default:
					break;
			}
			
		}
	}
}
