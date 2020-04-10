package a1.copy;

import java.util.Scanner;

public class A1Novice extends A1Primary {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		StoreItem[] storeItems = new StoreItem[] {}; 
		
		Customer[] customers = customerData(scan, storeItems);
		
		scan.close();
		
		output(customers);
		
	}
	
	/* output
	 * Prints the totalSpend information for each customer given customer set data
	 * (see totalSpend method under Customer class in A1Primary for more details)
	 */
	private static void output(Customer[] customers) {
		for (Customer customer : customers) {
			System.out.println(customer.totalSpend());
		}
	}

}