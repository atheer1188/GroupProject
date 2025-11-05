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
//-----------------------------------------------------------------------------------------


public static void ProductsMenu() {
	
	
	int choice;

	System.out.println("===================================");	
	System.out.println("What would you like to do:");	
	System.out.println("1. Add  a new product");	
	System.out.println("2. Remove a product");	
	System.out.println("3. Update a product's information");
	System.out.println("4. Search for a product");
	System.out.println("5. Track out of stock products");
	System.out.println("6. Exit");	
	System.out.println("===================================");	
	choice = read.nextInt();

	switch(choice) {
	case 1: // adding a new product
		System.out.println("Enter product name: ");
		String pName = read.nextLine();
		read.nextLine();
		System.out.println("Enter product ID: ");
		int pId = read.nextInt();
		System.out.println("Enter product price: ");
		double pPrice = read.nextDouble();
		System.out.println("Enter product stock: ");
		int pStock = read.nextInt();
		
		Products p = new Products(pId, pName, pPrice, pStock);
		Pchain.addProduct(p);
		
		break;		
		
	case 2: //remove product
		System.out.println("Enter product ID: ");
		int ID = read.nextInt();
		Pchain.remove(ID);
		break;

	case 3: //update product
		System.out.println("Enter product ID: ");
		int ID2 = read.nextInt();
		System.out.println("Enter new name: ");
		String newName = read.nextLine();
		read.nextLine();
		System.out.println("Enter new price: ");
		double newPrice = read.nextDouble();
		System.out.println("Enter product stock: ");
		int newStock = read.nextInt();
		
		Pchain.updateProduct(ID2, newName, newPrice, newStock);
		
		break;

	case 4: //search for a product
		System.out.println("Enter 1 if your'e searching by the name, 2 if your'e using ID: ");
		int option = read.nextInt();
		
		switch(option) {
		case 1:
			System.out.println("Enter product name: ");
			String ppName = read.nextLine();
			read.nextLine();
			
			System.out.println( Pchain.search(ppName));
			break;
		case 2:
			System.out.println("Enter product ID: ");
			int ppID = read.nextInt();
			
			System.out.println(Pchain.search(ppID));
			
			break;
			default:
				System.out.println("Incorrect choice, choose a valid number:");
		} // end of search switch

		break;
		
	case 5: //Track out of stock
		System.out.println("Here is a list of all out of stock products: ");
		System.out.println("---------------------------------------------------- ");
		Pchain.TrackOutOfStock().display();
		
		break;
	case 6:
		System.out.println("Exiting...");
		break;
	default:
		System.out.println("Incorrect choice, choose a valid number:");


}//End of switch

public static void OrdersMenu() {
	
}

public static void ReviewsMenu() {
	int choice;

	System.out.println("===================================");	
	System.out.println("What would you like to do:");	
	System.out.println("1. Add review");	
	System.out.println("2. Place a new order for customer");	
	System.out.println("3. View order history");	
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
		while(!customersdata.searchCustomerId(cusid)) {
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
		case 1: 
		case 2:
		case 3:
		case 4:ReviewsMenu();
		case 5:
			System.out.println("Exiting...");
		default:
			System.out.println("Incorrect choice, choose a valid number:");

	
	}//End of switch

		}while(choice!=5);
	}

}//end Main









