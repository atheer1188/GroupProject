package Phase1;

import java.util.Scanner;



public class Main {
public static Scanner read = new Scanner(System.in);

public static CustomerChain customersdata = new CustomerChain("");	
public static LinkedList<Customers> customers ;	

public static ProductChain productdata = new ProductChain("");	
public static LinkedList<Products> products ;

public static OrderChain orderdata = new OrderChain("");	
public static LinkedList<Order> orders ;	

public static ReviewChain reviewdata = new ReviewChain("");
public static LinkedList<Reviews> reviews;	

//---------------------------------------------------------------------------------------
//read files
public static void loadData() {
	System.out.println("loading data from CVSs files");
	customers = customersdata.customersInfo();
	products = productdata.getProductChain();
	orders = orderdata.getOrders();
	reviews = reviewdata.getReviews();
	
	customers.findfirst();
	for(int i = 0 ; i<customers.size() ; i++) {
		orders.findfirst();
		for(int k = 0; k<orders.size(); k++) {
			if(customers.retrieve().getCustomersId() == orders.retrieve().linkOrderToCustomer()) {
				int orderid =orders.retrieve().getOrderId();
				orders.retrieve().getOrderId();
				customers.retrieve().addOrder(orderid);
			}
			orders.findnext();
		}
		customers.findnext();		
	}
	////////
	products.findfirst();
	for(int j = 0; j<products.size(); j++)
	{
		reviews.findfirst();
		for(int d = 0; d<products.size(); d++) {
			if(products.retrieve().getProductid() == reviews.retrieve().getProduct) {
			
			}
		}
	}

}
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
			//cancelOrder
			break;
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
		productdata.addProduct(p);
		
		break;		
		
	case 2: //remove product
		System.out.println("Enter product ID: ");
		int ID = read.nextInt();
		productdata.remove(ID);
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
		
		productdata.updateProduct(ID2, newName, newPrice, newStock);
		
		break;

	case 4: //search for a product
		System.out.println("Enter 1 if your'e searching by the name, 2 if your'e using ID: ");
		int option = read.nextInt();
		
		switch(option) {
		case 1:
			System.out.println("Enter product name: ");
			String ppName = read.nextLine();
			read.nextLine();
			
			System.out.println( productdata.search(ppName));
			break;
		case 2:
			System.out.println("Enter product ID: ");
			int ppID = read.nextInt();
			
			System.out.println(productdata.search(ppID));
			
			break;
			default:
				System.out.println("Incorrect choice, choose a valid number:");
		} // end of search switch

		break;
		
	case 5: //Track out of stock
		System.out.println("Here is a list of all out of stock products: ");
		System.out.println("---------------------------------------------------- ");
		productdata.TrackOutOfStock().display();
		
		break;
	case 6:
		System.out.println("Exiting...");
		break;
	default:
		System.out.println("Incorrect choice, choose a valid number:");


}//End of switch
}//-------------------------------------------------------------------------------------------------

public static void OrdersMenu() {
	
}
//--------------------------------------------------------------------------------------------------
public static void ReviewsMenu() {
	int choice;

	System.out.println("===================================");	
	System.out.println("What would you like to do:");	
	System.out.println("1. Add review");	
	System.out.println("2. Check top 3 highest reviewed products");	
	System.out.println("3. Average rating");	
	System.out.println("");	
	System.out.println("");	
	System.out.println("6. Return");	
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
		productdata.addReviewToProduct(proid,newReview);//havent tested it yet
		newReview.display();
	break;
	}//add review
	
	case 2:
		productdata.top3Products();
		LinkedList<Products> topProducts = productdata.top3Products();
		topProducts.findfirst();
		System.out.println("Top 3 highest rated products respectivley");
		System.out.println("=========================================");
		for(int k = 1 ; k<=topProducts.size() ; k++) {
		System.out.println("Product number: "+ k);
		System.out.println("Products ID: "+ topProducts.retrieve().getProductId());
		System.out.println("Products name: "+ topProducts.retrieve().getName());
		System.out.println("Products Price: "+ topProducts.retrieve().getPrice());
		System.out.println("Products Average ratings: "+ productdata.getAverageRating(topProducts.retrieve().getProductId()));
		topProducts.findnext();
		}
		System.out.println("=========================================");

		break;
		
	case 3:
		int id;
		System.out.println("Enter products ID:");
		 id = read.nextInt();
		while(!productdata.searchProductId(id)){
		System.out.println("Wrong ID,Enter a new products ID:");
		 id = read.nextInt();
		}
		System.out.println("The products Average rating is: "+ productdata.getAverageRating(id));
		break;
	case 4:
		
	case 6:
		System.out.println("Returning to Main Menu...");
		break;
	default:
		System.out.println("Incorrect choice, choose a valid number:");


}//End of switch

}
//-----------------------------------------------------------------------------------------------



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
			CustomersMenu();
		    break;
		
		case 2:
			ProductsMenu();
			break;
			
		case 3:
			 OrdersMenu();
			 break;
			
		case 4:
		    ReviewsMenu();
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











