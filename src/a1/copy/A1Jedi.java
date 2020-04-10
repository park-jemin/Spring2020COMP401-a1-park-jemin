package a1.copy;

import java.util.Scanner;

public class A1Jedi extends A1Primary {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		StoreItem[] storeItems = storeInventory(scan);
		
		Customer[] customers = customerData(scan, storeItems);
		
		scan.close();
		
		output(customers, storeItems);
		
	}
	
	/* output
	 * Prints the salesReport information for each customer given customer set data and store data
	 * (see salesReport method under StoreItem class in A1Primary for more details)
	 */
	private static void output (Customer[] customers, StoreItem[] storeItems) {
		for (StoreItem item : storeItems) {
			System.out.println(item.salesReport(customers));
		}
	}
	
}