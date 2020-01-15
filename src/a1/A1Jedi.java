package a1;

import java.util.Scanner;

public class A1Jedi extends A1Primary {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		StoreItem[] storeItems = storeInventory(scan); // builds store data
		
		Customer[] customers = customerData(scan, storeItems); // builds customer data
		
		scan.close();
		
		output(customers, storeItems);
		
	}
	
	/* output
	 * Prints the salesReport information for each customer given customer set data and store data
	 * (see salesReport method under StoreItem class in A1Primary for more details)
	 */
	private static void output (Customer[] customers, StoreItem[] storeItems) {
		for (int i = 0; i < storeItems.length; i++) {
			System.out.println(storeItems[i].salesReport(customers));
		}
	}
	
}