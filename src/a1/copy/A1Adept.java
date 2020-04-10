package a1.copy;

import java.util.Scanner;

public class A1Adept extends A1Primary {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		StoreItem[] storeItems = storeInventory(scan);
		
		Customer[] customers = customerData(scan, storeItems);
		
		scan.close();
		
		output(customers, storeItems);
		
	}	
	
	/* output
	 * Prints 3 lines containing name and total spend of about the biggest spender, the smallest spender, 
	 * and the average spend of all customers given customer set data and store data
	 */
	private static void output (Customer[] customers, StoreItem[] storeItems) {		
		System.out.println("Biggest: " + find("biggest", customers).summary());
		System.out.println("Smallest: " + find("smallest", customers).summary());
		System.out.println("Average: " + String.format("%.2f", spendAverage(customers)));
	}
		
}