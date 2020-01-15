package a1;

import java.util.Scanner;

// Note: for this assignment, can assume ALL VALID INPUTS

/* A1Primary
 * Parent class for A1 programs containing methods to build customer and store data, as well as helper methods
 * for finding specific data
 */
public class A1Primary {
	
	/* customerData
	 * Builds customer set data given available store data, customer data, and customer purchase data
	 *
	 * Input: scanner object scan for console input, an array of store items containing store data
	 * 
	 * Output: an array of Customers, with each holding the customer's respective purchase data
	 * 
	 * Preconditions:
	 * Customer data must be entered with the following form:
	 * 	1) integer input indicating the total number of customers
	 * 	2) for each customer, the input must be entered as follows:
	 * 		a) the first name of the customer
	 * 		b) the last name of the customer
	 * 		c) the number of items bought by the customer
	 * 		d) for each item bought, the input must be entered as follows:
	 * 			i) an integer quantity of the item bought
	 * 			ii) the name of the item as a single word
	 * 			iii) the price of the item as a double, if store data is empty
	 */
	public static Customer[] customerData (Scanner scan, StoreItem[] storeItems) {
		
		// Constructs Customer array with an input of the total number of customers
		Customer[] customers = new Customer[scan.nextInt()];
		
		// Loop to fill each Customer with Customer data
		for (int i = 0; i < customers.length; i++) {
			
			// Builds Customer with an input of their name and the total number of items bought
			customers[i] = new Customer(scan.next(), scan.next(), scan.nextInt());
			
			// Loop to fill Item array in Customer object with each item's data
			for (int j = 0; j < customers[i].items.length; j++) {
				
				int amount = scan.nextInt();
				String name = scan.next();
				
				// Checks if store data has been given to determine method for finding price
				double price = (storeItems.length == 0) ? scan.nextDouble() : priceSearch(amount, name, storeItems);
				
				customers[i].items[j] = new Item(amount, name, price);
				customers[i].spent += amount * price;
				
			}
		}
		
		return customers;
	}

	/* storeInventory
	 * Builds store data given unique items and their respective prices
	 *
	 * Input: scanner object scan for console inputs
	 * 
	 * Output: an array of StoreItems, with each holding the item's name and price
	 * 
	 * Preconditions:
	 * Store data must be entered with the following form:
	 * 	1) integer input of number of items in the store
	 * 	2) for each item in the store, the input must be entered as follows:
	 * 		a) the name of the item as a single word
	 * 		b) the price of the item
	 */
	public static StoreItem[] storeInventory (Scanner scan) {
		
		// Constructs StoreItem array given number of items
		StoreItem[] storeItems = new StoreItem[scan.nextInt()];
		
		// Loop to build each store item with their respective names and prices
		for (int i = 0; i < storeItems.length; i++) {
			storeItems[i] = new StoreItem(scan.next(), scan.nextDouble());
		}
		
		return storeItems;
	}
	
	/* priceSearch
	 * Finds the price of a named item in the store and adds quantity purchased 
	 * to the Store Item's purchase count
	 *
	 * Input: an integer of the number of this item purchased, the item name, and the store data
	 * 
	 * Output: the price found in the store as a double
	 * Returns 0 if price not found
	 */
	public static double priceSearch (int count, String item, StoreItem[] storeItems) {
		
		for (int i = 0; i < storeItems.length; i++) {
		
			if (item.matches(storeItems[i].name)) {
				storeItems[i].amount += count;
				return storeItems[i].price;
			}
		}
		
		return 0;
	}
	
	/* spendAverage
	 * Finds the average of a given set of customers' spend
	 * Used for A1Adept
	 *
	 * Input: customer set data as a Customer array
	 * 
	 * Output: the average amount spent by customers as a double
	 */
	public static double spendAverage (Customer[] customers) {
		
		double total = 0;
		
		for (int i=0; i < customers.length; i++) {
			total += customers[i].spent;
		}
		
		return (total/customers.length);
	}

	/* find
	 * Finds and returns a specific customer in a set of customers matching the conditions set by a key
	 * Used to find the biggest and smallest spenders for A1Adept
	 *
	 * Input: a keyword as a string, customer set data as a Customer array
	 * 
	 * Output: the Customer matching the condition
	 */
	public static Customer find (String key, Customer[] customers) {
		
		Customer memo = customers[0];
		
		if (key == "smallest") {
			// Finds smallest spender and records it in memo
			for (int i = 1; i < customers.length; i++) {
				memo = (customers[i].spent < memo.spent) ? customers[i] : memo;
			}
			
			
		} else if (key == "biggest") {
			// Finds biggest spender and records it in memo
			for (int i = 1; i < customers.length; i++) {
				memo = (customers[i].spent > memo.spent) ? customers[i] : memo;
			}
		}
		
		return memo;
	}
	
}

/* Customer
 * Class to hold customer data of their first and last name, their purchased items, and their total spend
 */
class Customer {
	
	String first, last;
	Item[] items;
	double spent = 0;
	
	public Customer(String first, String last, int numOfItems) {
		this.first = first;
		this.last = last;
		items = new Item[numOfItems];
	}
	
	/* totalSpend
	 * Returns a string displaying the customer name and their total spend formatted as
	 * 
	 * 		F. LAST: TOTAL
	 * 
	 * where F. is the first initial, LAST is the last name, and TOTAL is the total spend reduced 
	 * to 2 decimal places
	 * 
	 * Used for A1Novice
	 */
	public String totalSpend () {
		return this.first.charAt(0) + ". " + this.last + ": " + String.format("%.2f", this.spent);
	}	
	
	/* summary
	 * Returns a string displaying the customer name and their total spend formatted as
	 * 
	 * 		FIRST LAST (AMOUNT)
	 * 
	 * where FIRST is the first name, LAST is the last name, and AMOUNT is the total spend reduced 
	 * to 2 decimal places
	 * 
	 * Used for A1Adept 
	 */
	public String summary () {
		return this.first + " " + this.last + " (" + String.format("%.2f", this.spent) + ")";
	}

}

/* Item
 * Class to hold item data of the amount purchased, the item name, and the price of the item
 */
class Item {
	
	int amount;
	String name;
	double price;
	
	public Item ( int amt, String name, double price ) {
		this.amount = amt;
		this.name = name;
		this.price = price;
	}
	
}

/* StoreItem
 * Child class of Item to hold item data specific to the store, along with additional helper methods
 */
class StoreItem extends Item {

	public StoreItem(String name, double price) {
		super(0, name, price);
	}

	/* pplPurchased
	 * Returns the number of customers that purchased this item given customer set data 
	 */
	public int pplPurchased (Customer[] customers) {
		
		int count = 0;
		
		for (int i = 0; i < customers.length; i++) {
			for (int j = 0; j < customers[i].items.length; j++) {
				if (customers[i].items[j].name.matches(this.name)) {
					count++; 
					j += customers[i].items.length; 
					// edge case accounting for repeat buys by same customer
				}
			}
		}
		return count;
	}
	
	/* salesReport
	 * Returns a string detailing the number of customers that bought this item formatted as
	 * 
	 * 		NUMBER customers bought TOTAL ITEM
	 * 
	 * where NUMBER is the number of customers that bought this item, TOTAL is the quantity purchased,
	 * and ITEM is the item name
	 * 
	 * If no customers have purchased this item, the output will instead be the following:
	 * 
	 * 		No customers bought ITEM
	 */
	public String salesReport (Customer[] customers) {
		
		int count = this.pplPurchased(customers);
		String output = " customers bought ";
		
		if (count <= 0) {
			return "No" + output + this.name;
		}
		
		return count + output + this.amount + " " + this.name;
	
	}
}