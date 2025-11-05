package Phase1;

import java.util.Scanner;



public class Main {
public static Scanner read = new Scanner(System.in);
public static CustomerChain customersdata = new CustomerChain();	
public static LinkedList<Customers> customers ;	
public static LinkedList<Products> products ;	
public static LinkedList<Reviews> reviews;	
public static LinkedList<Order> orders ;	


public static void CustomersMenu() {
	int choice;

		System.out.println("===================================");	
		System.out.println("What would you like to do:");	
		System.out.println("1. Register new customer");	
		System.out.println("2. Place a new order for customer");	
		System.out.println("3. View order history");	
		System.out.println("4. Exit");	
		System.out.println("===================================");	
		choice = read.nextInt();
	
		switch(choice) {
		case 1: 
		case 2:
		case 3:
		case 4:
		case 5:
			System.out.println("Exiting...");
			break;
		default:
			System.out.println("Incorrect choice, choose a valid number:");

	
	}//End of switch


}


public static void ProductsMenu() {
	
}

public static void OrdersMenu() {
	
}

public static void ReviewsMenu() {
	
}










public static void main(String[] args) {	
	int choice;
	
	do {
		System.out.println("===================================");	
		System.out.println("What menu would you like to enter?");	
		System.out.println("1. Customer Menu");	
		System.out.println("2. Product Menu");	
		System.out.println("3. Orders Menu");	
		System.out.println("4. Check Reviews");	
		System.out.println("5. Exit");	
		System.out.println("===================================");	
		choice = read.nextInt();
	
		switch(choice) {
		case 1: 
		case 2:
		case 3:
		case 4:
		case 5:
			System.out.println("Exiting...");
		default:
			System.out.println("Incorrect choice, choose a valid number:");

	
	}//End of switch

		}while(choice!=5);
	}

}//end Main








