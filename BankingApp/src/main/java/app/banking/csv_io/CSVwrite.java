package app.banking.csv_io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.banking.bean.AccountBean;
import app.banking.bean.AccountStatement;
import app.banking.util.BankingConstants;
import app.banking.util.Quoted;

public class CSVwrite {

	static FileWriter writer;

	/**
	 * method to add new account
	 * @param bean
	 * @return
	 */
	public static boolean addAccount(AccountBean bean) {

		boolean flag = false;

		String filepath = BankingConstants.ACCOUNT_CSV;

		String formattedEntry = Integer.toString(bean.getAccount_number()) + "," + Quoted.qoute(bean.getName()) + ","
				+ Double.toString(bean.getBalance());
		ArrayList<String> list = CSVread.readCSV(filepath);

		list.add(formattedEntry);

		try {
			writer = new FileWriter(filepath);
			for (String e : list) {
				writer.write(e);
				writer.write("\n");
			}

			flag = true;
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean depositWithdrawalEntry(AccountBean bean, AccountStatement asbean) {
		boolean flag = false;
		
		/**
		 * writing to AccountStatement.csv
		 */
		ArrayList<String> aslist = CSVread.readCSV(BankingConstants.ACCOUNT_STATEMENT_CSV);
		
		String comfy = Quoted.qoute(asbean.getDate()) + "," + Quoted.qoute(asbean.getTxn_type()) + ","
				+ Integer.toString(asbean.getAccount_number()) + "," + Double.toString(asbean.getTxn_amount()) + ","
				+ Double.toString(asbean.getBalance()) + "," + Quoted.qoute(asbean.getMode());
		aslist.add(comfy);
		try {
			writer = new FileWriter(BankingConstants.ACCOUNT_STATEMENT_CSV);
			for (String e : aslist) {
				writer.write(e);
				writer.write("\n");
			}
			flag = true;
			writer.close();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		
		/**
		 * editing the balance of account.csv
		 */
		String formattedEntry = Integer.toString(bean.getAccount_number()) + "," + bean.getName() + ","
				+ Double.toString(asbean.getBalance());
		
		ArrayList<String> alist = CSVread.readCSV(BankingConstants.ACCOUNT_CSV);
		ArrayList<String> nlist = new ArrayList<String>();
		
		for (int i =0; i< alist.size(); i++) {
			String[] tokens = alist.get(i).split(",");
			
			if (!tokens[1].equalsIgnoreCase(bean.getName()))
				nlist.add(alist.get(i));
			
			else if (tokens[1].equalsIgnoreCase(bean.getName())) 
				nlist.add(formattedEntry);
			
		}
		try {
			writer = new FileWriter(BankingConstants.ACCOUNT_CSV);
			for (String e : nlist) {
				writer.write(e);
				writer.write("\n");
			}
			flag = true;
			writer.close();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

}
