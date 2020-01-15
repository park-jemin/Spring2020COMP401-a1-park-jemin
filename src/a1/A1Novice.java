package a1;

import java.util.Scanner;

public class A1Novice extends A1Primary {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		StoreItem[] storeItems = new StoreItem[] {}; // builds empty store data for customerData method
		
		Customer[] customers = customerData(scan, storeItems); // builds customer data
		
		scan.close();
		
		output(customers);
		
	}
	
	/* output
	 * Prints the totalSpend information for each customer given customer set data
	 * (see totalSpend method under Customer class in A1Primary for more details)
	 */
	private static void output(Customer[] customers) {
		for (int i = 0; i < customers.length; i++) {
			System.out.println(customers[i].totalSpend());
		}
	}

}