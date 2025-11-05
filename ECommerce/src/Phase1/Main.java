package Phase1;

import java.util.Scanner;



public class Main {
public static Scanner read = new Scanner(System.in);

public static CustomerChain customersdata = new CustomerChain();	
public static LinkedList<Customers> customers ;	

public static ProductChain productdata = new ProductChain();	
public static LinkedList<Products> products ;

public static OrderChain orderdata = new OrderChain();	
public static LinkedList<Order> orders ;	

public static ReviewChain reviewdata = new ReviewChain();
public static LinkedList<Reviews> reviews;	


//---------------------------------------------------------------------------------------
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
		customersdata.registerCustomer();
		break;
		case 2:
			
		case 3:
			customersdata.viewOrderHistory();
			break;
		case 4:
			System.out.println("Returning to Main Menu...");
		break;
		default:
			System.out.println("Incorrect choice, choose a valid number:");
			break;

	
	}//End of switch


}
//-----------------------------------------------------------------------------------------

public static void ProductsMenu() {
	
}

public static void OrdersMenu() {
	
}

public static void ReviewsMenu() {
	int choice;

	System.out.println("===================================");	
	System.out.println("What would you like to do:");	
	System.out.println("1. Add review");	
	System.out.println("2.");	
	System.out.println("3. ");	
	System.out.println("4. Exit");	
	System.out.println("===================================");	
	choice = read.nextInt();

	switch(choice) {
	case 1: {	
		int cusid;
		int proid;
		System.out.println("Enter customer ID");
		cusid = read.nextInt();
		while(!customersdata.searchCustomerId(cusid)) {
			System.out.println("This customer ID doesnt Exist input new one:");
			cusid = read.nextInt();
		}
		System.out.println("Enter product ID");	
		proid = read.nextInt();
		while(!productdata.searchProductId(proid)) {
			System.out.println("This Product ID doesnt Exist input new one:");
			proid = read.nextInt();
		}
		Reviews newReview = reviewdata.addReview(cusid, proid);
		newReview.display();
	
	}//add review
	
	case 2:
	case 3:
	case 4:
	case 5:
		System.out.println("Returning to Main Menu...");
		break;
	default:
		System.out.println("Incorrect choice, choose a valid number:");


}//End of switch

}




public static void main(String[] args) {	
	int choice;
	
	do {
		System.out.println("==============Main Menu==============");	
		System.out.println("1. Customer Menu");	
		System.out.println("2. Product Menu");	
		System.out.println("3. Orders Menu");	
		System.out.println("4. Check Reviews");	
		System.out.println("5. Exit");	
		System.out.println("=====================================");	
		choice = read.nextInt();
	
		switch(choice) {
		case 1: CustomersMenu();
		break;
		
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:ReviewsMenu();
		break;
		case 5:
			System.out.println("Exiting...");
			break;
		default:
			System.out.println("Incorrect choice, choose a valid number:");
			break;

	
	}//End of switch

		}while(choice!=5);
	}

}//end Main








