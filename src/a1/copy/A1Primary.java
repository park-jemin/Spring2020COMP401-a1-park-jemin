package a1.copy;

import java.util.Scanner;

public class A1Primary {
	
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

	public static StoreItem[] storeInventory (Scanner scan) {
		
		StoreItem[] storeItems = new StoreItem[scan.nextInt()];
		for (int i = 0; i < storeItems.length; i++) {
			storeItems[i] = new StoreItem(scan.next(), scan.nextDouble());
		}
		return storeItems;
	}

	public static double priceSearch (int count, String item, StoreItem[] storeItems) {
		for (StoreItem memo : storeItems) {
			if (item.matches(memo.name)) {
				memo.amount += count;
				return memo.price;
			}
		}
		return 0;
	}
	
	public static double spendAverage (Customer[] customers) {
		
		double total = 0;
		for (Customer customer : customers) {
			total += customer.spent;
		}
		return (total/customers.length);
	}

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

class Customer {
	
	String first, last;
	Item[] items;
	double spent = 0;
	
	public Customer(String first, String last, int numOfItems) {
		this.first = first;
		this.last = last;
		items = new Item[numOfItems];
	}

	public String totalSpend () {
		return first.charAt(0) + ". " + last + ": " + String.format("%.2f", spent);
	}	

	public String summary () {
		return first + " " + last + " (" + String.format("%.2f", spent) + ")";
	}

}

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

class StoreItem extends Item {

	public StoreItem(String name, double price) {
		super(0, name, price);
	}

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

	public String salesReport (Customer[] customers) {
		int count = pplPurchased(customers);
		return ((count <= 0) ? "No customers bought " : count + " customers bought " + amount + " ") + name;	
	}
}