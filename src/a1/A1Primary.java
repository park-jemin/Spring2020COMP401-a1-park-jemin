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
		
		Customer[] customers = new Customer[scan.nextInt()];
		
		for (int i = 0; i < customers.length; i++) {
		
			customers[i] = new Customer(scan.next(), scan.next(), scan.nextInt());
			
			for (int j = 0; j < customers[i].items.length; j++) {
				int amount = scan.nextInt();
				String name = scan.next();
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
		
		StoreItem[] storeItems = new StoreItem[scan.nextInt()];
		for (int i = 0; i < storeItems.length; i++) {
			storeItems[i] = new StoreItem(scan.next(), scan.nextDouble());
		}
		return storeItems;
	}
	
	/* priceSearch
	 * Finds the price of a named item in the store and adds quantity purchased 
	 * to the Store Item's purchase count
	 *
	 * Input: the number of this item purchased as an int, the item name, and the store data
	 * 
	 * Output: the price found in the store as a double
	 * Returns 0 if price not found
	 */
	public static double priceSearch (int count, String item, StoreItem[] storeItems) {
		for (StoreItem memo : storeItems) {
			if (item.matches(memo.name)) {
				memo.amount += count;
				return memo.price;
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
		for (Customer customer : customers) {
			total += customer.spent;
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
		
		if (key.matches("smallest")) { // finds smallest spender
			for (Customer customer : customers) {
				memo = (customer.spent < memo.spent) ? customer : memo;
			}
			
			
		} else if (key.matches("biggest")) { // finds biggest spender
			for (Customer customer: customers) {
				memo = (customer.spent > memo.spent) ? customer : memo;
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
		return first.charAt(0) + ". " + last + ": " + String.format("%.2f", spent);
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
		return first + " " + last + " (" + String.format("%.2f", spent) + ")";
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
		for (Customer customer : customers) {
			for (Item item : customer.items) {
				if (item.name.matches(this.name)) {
					count++;
					break;
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
		int count = pplPurchased(customers);
		return ((count <= 0) ? "No customers bought " : count + " customers bought " + amount + " ") + name;	
	}
}