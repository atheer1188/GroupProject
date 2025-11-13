package Phase1;

import java.io.File;
import java.util.Scanner;



public class Main {
public static Scanner read = new Scanner(System.in);

public static CustomerChain customersdata = null;	
public static LinkedList<Customers> customers = null ;	

public static ProductChain productdata = null;	
public static LinkedList<Products> products =null;

public static OrderChain orderdata = null;	
public static LinkedList<Order> orders =null;	

public static ReviewChain reviewdata ;
public static LinkedList<Reviews> reviews ;	

//---------------------------------------------------------------------------------------
//read files
public static void loadData() {
	
	 customersdata = new CustomerChain("customers.csv");	
	 productdata = new ProductChain("products.csv");	
	 reviewdata = new ReviewChain("reviews.csv");
	 orderdata = new OrderChain();	
	
	orderdata.readOrdersFromFile("orders.csv");

	System.out.println("loading data from CSVs files");
	customers = customersdata.customersInfo();
	products = productdata.getProductChain();
	orders = orderdata.getOrders();
	reviews = reviewdata.getReviews();
	
	linkReviewsToProducts();
	
	if(!customers.empty() && !orders.empty()) {
	customers.findfirst();
	for(int i = 0 ; i<customers.size() ; i++) {
		orders.findfirst();
		for(int k = 0; k<orders.size(); k++) {
			if(customers.retrieve().getCustomersId() == orders.retrieve().getCustomerId()) {
				int orderid =orders.retrieve().getOrderId();
				customers.retrieve().addOrder(orderid);
			}
			if(!orders.last())orders.findnext();
		}
		if(!customers.last())customers.findnext();
	}
}
System.out.println("All data loaded succesfully!");
}
		
private static void linkReviewsToProducts() {
    if (reviews == null || reviews.empty()) {
        System.out.println("No reviews to link");
        return;
    }
    
    reviews.findfirst();
    for (int i = 0; i < reviews.size(); i++) {
        Reviews review = reviews.retrieve();
        int productId = review.getProductID();
        
        // Find the product and add this review to it
        Products product = productdata.search(productId);
        if (product != null) {
            product.addReview(review);
        }
        
        if (reviews.last()) break;
        reviews.findnext();
    }
    System.out.println("✓ Reviews linked to products");
}


//---------------------------------------------------------------------------------------
public static void CustomersMenu() {
	int choice;

		System.out.println("===================================");	
		System.out.println("What would you like to do:");	
		System.out.println("1. Register new customer");	
		System.out.println("2. View order history");	
		System.out.println("3. Exit");	
		System.out.println("===================================");	
		choice = read.nextInt();
	
		switch(choice) {
		case 1: 
		    customersdata.registerCustomer();
		    break;
		
		case 2:
			customersdata.viewOrderHistory();
			break;
		case 3:
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
		read.nextLine();
		String pName = read.nextLine();
		System.out.println("Enter product ID: ");
		int pId = read.nextInt();
		System.out.println("Enter product price: ");
		double pPrice = read.nextDouble();
		System.out.println("Enter product stock: ");
		int pStock = read.nextInt();
		
		Products p = new Products(pId, pName, pPrice, pStock);
		if(productdata.addProduct(p));
		System.out.println("Product added successfully");
		
		break;		
		
	case 2: //remove product
		System.out.println("Enter product ID: ");
		int ID = read.nextInt();
		if(productdata.remove(ID));
		System.out.println("Product Removed successfully");

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
			read.nextLine();//clear
			String ppName = read.nextLine();
			
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
    int choice = -1;
    java.time.format.DateTimeFormatter DF =
        java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

    while (choice != 9) {
        System.out.println("===================================");
        System.out.println("Orders Menu - choose an option:");
        System.out.println("1. Load orders from CSV");
        System.out.println("2. Create a new order");
        System.out.println("3. Add Product ID to an order");
        System.out.println("4. Update order status");
        System.out.println("5. Remove an order");
        System.out.println("6. Search & display order");
        System.out.println("7. Display all orders");
        System.out.println("8. All orders between two dates");
        System.out.println("9. Return");
        System.out.println("===================================");
        choice = read.nextInt();

        switch (choice) {
            case 1: { //Load from CSV
                System.out.print("Enter CSV file path: ");
                String path = read.next();
                orderdata.readOrdersFromFile(path);
                break;
            }

            case 2: { 
                System.out.print("Order ID: ");
                int oid = read.nextInt();
                while(orderdata.findOrderById(oid) != null) {
                	System.out.println("Order Already exists, enter new one");
                    oid = read.nextInt();
                }
                

                System.out.print("Customer ID: ");
                int cid = read.nextInt();
                while(!customersdata.searchCustomerId(cid)) {
                	System.out.println("Customer Id doesnt exist, enter new one:");
                    cid = read.nextInt();
                }

                System.out.print("Total Price: ");
                double price = read.nextDouble();

                System.out.print("Date (yyyy-MM-dd): ");
                String d = read.next();
                java.time.LocalDate date = java.time.LocalDate.parse(d, DF);

                System.out.print("Status: ");
                String st = read.next();

             
                Order o = new Order(oid, cid, price, date, st);
                orderdata.insertOrder(o);

            
                System.out.print("Add Product IDs now? (y/n): ");
                String ans = read.next();
                if (ans.equalsIgnoreCase("y")) {
                	System.out.print("ID: ");
                    String ids = read.next();
                    o.addIds(ids);
                    System.out.println("Product IDs added.");
                }
                break;
            }

            case 3: { 
                System.out.print("Order ID: ");
                int oid = read.nextInt();
                Order found = orderdata.findOrderById(oid);
                if (found == null) {
                    System.out.println("Order not found.");
                } else {
                    System.out.print("Product ID to add: ");
                    int pid = read.nextInt();
                    //validate
                    while(!productdata.searchProductId(pid)) { 
                    	System.out.println("The ID: "+pid +" doesnt exist, enter a new one:");
                    	pid = read.nextInt();
                    }
                     
                    found.addId(pid);
                    System.out.println("Product Added Successfully!");
                    
                }
                break;
            }

            case 4: { // Update status
                System.out.print("Order ID: ");
                int oid = read.nextInt();
                System.out.print("New status: ");
                String st = read.next();
                orderdata.changeStatus(oid, st);
                break;
            }

            case 5: { // Delete order
                System.out.print("Order ID: ");
                int oid = read.nextInt();
                orderdata.deleteOrder(oid);
                break;
            }

            case 6: { // Search and display
                System.out.print("Order ID: ");
                int oid = read.nextInt();
                Order found = orderdata.findOrderById(oid);
                if (found == null) System.out.println("Order not found.");
                else found.display();
                break;
            }

            case 7: { // Display all 
                orderdata.displayAllOrders();
                break;
            }
            case 8:
                System.out.println("Enter two dates in order:");
                System.out.print("First Date (yyyy-MM-dd): ");
                String d1 = read.next();
                java.time.LocalDate date1 = java.time.LocalDate.parse(d1, DF);
                
                System.out.print("First Date (yyyy-MM-dd): ");
                String d2 = read.next();
                java.time.LocalDate date2 = java.time.LocalDate.parse(d2, DF);
                orderdata.AllOrdersBetweenDates(date1 , date2);
                break;
            case 9:
                System.out.println("Returning to Main Menu...");
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }

        System.out.println();
    }
}


//--------------------------------------------------------------------------------------------------
public static void ReviewsMenu() {
    int choice;

    System.out.println("===================================");
    System.out.println("What would you like to do:");
    System.out.println("1. Add review");
    System.out.println("2. Check top 3 highest reviewed products");
    System.out.println("3. Average rating");
    System.out.println("4. Show all reviews for a product");
    System.out.println("5. Show all reviews by a customer");
    System.out.println("6. common review by two customers");
    System.out.println("7. Return");
    System.out.println("===================================");
    choice = read.nextInt();

    switch(choice) {
        case 1: { // Add review
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
            productdata.addReviewToProduct(proid, newReview);
            newReview.display();
            break;
        }

        case 2: { // Top 3 products
            LinkedList<Products> topProducts = productdata.top3Products();
            if (topProducts == null || topProducts.empty()) {
                System.out.println("No products found.");
                break;
            }
            topProducts.findfirst();
            System.out.println("Top 3 highest rated products respectivley");
            System.out.println("=========================================");
            for (int k = 1; k <= topProducts.size(); k++) {
                Products p = topProducts.retrieve();
                System.out.println("Product number: " + k);
                System.out.println("Products ID: " + p.getProductId());
                System.out.println("Products name: " + p.getName());
                System.out.println("Products Price: " + p.getPrice());
                System.out.println("Products Average ratings: " + productdata.getAverageRating(p.getProductId()));
                if (topProducts.last()) break;
                topProducts.findnext();
            }
            System.out.println("=========================================");
            break;
        }

        case 3: { // Average rating
            int id;
            System.out.println("Enter products ID:");
            id = read.nextInt();
            while(!productdata.searchProductId(id)){
                System.out.println("Wrong ID,Enter a new products ID:");
                id = read.nextInt();
            }
            System.out.println("The products Average rating is: " + productdata.getAverageRating(id));
            break;
        }
        case 4: { // Search/Show all reviews for a product 
            System.out.println("Enter product ID:"); 
            int pid = read.nextInt(); 
            while(!productdata.searchProductId(pid)){ 
                System.out.println("Wrong ID. Enter a valid product ID:"); 
                pid = read.nextInt(); 
            } 
         
            LinkedList<Reviews> rs = reviewdata.searchReviewsByProduct(pid); 
            if (rs == null || rs.empty()) { 
                System.out.println("No reviews for this product."); 
                break; 
            } 
         
            System.out.println("Reviews for product ID " + pid + ":"); 
            rs.findfirst(); 
            for (int i = 0; i < rs.size(); i++) { 
                Reviews r = rs.retrieve(); 
                System.out.println("- ReviewID: " + r.getReviewID() + 
                                   " | Rating: " + r.getRating() + 
                                   " | Comment: " + r.getComment()); 
                if (rs.last()) break; 
                rs.findnext(); 
            } 
            break; 
        } 
      
         
       case 5: { // Search/Show all reviews by a customer 
            System.out.println("Enter customer ID:"); //1
            int cid = read.nextInt(); //1
            while(!customersdata.searchCustomerId(cid)) { //c
                System.out.println("This customer ID doesnt Exist input new one:"); //1
                cid = read.nextInt(); //1
            } 
         
            LinkedList<Reviews> rs = reviewdata.searchReviewsByCustomer(cid); 
            if (rs == null || rs.empty()) { 
                System.out.println("No reviews for this customer."); 
                break; 
            } 
         
            System.out.println("Reviews by customer ID " + cid + ":"); 
            rs.findfirst(); 
            for (int i = 0; i < rs.size(); i++) { 
                Reviews r = rs.retrieve(); 
                System.out.println("- ReviewID: " + r.getReviewID() + 
                                   " | ProductID: " + r.getProductID() + 
                                   " | Rating: " + r.getRating() + 
                                   " | Comment: " + r.getComment()); 
                if (rs.last()) break; 
                rs.findnext(); 
            } 
            break; 
        }

        /*case 4: { // Show all reviews for a product
            System.out.println("Enter product ID:");
            int pid = read.nextInt();
            while(!productdata.searchProductId(pid)){
                System.out.println("Wrong ID. Enter a valid product ID:");
                pid = read.nextInt();
            }

            Products prod = productdata.search(pid); 
            if (prod == null) {
                System.out.println("Product not found.");
                break;
            }

            LinkedList<Reviews> rs = prod.getReviews();
            if (rs == null || rs.empty()) {
                System.out.println("No reviews for this product.");
                break;
            }

            System.out.println("Reviews for product ID " + pid + ":");
            rs.findfirst();
            for (int i = 0; i < rs.size(); i++) {
                Reviews r = rs.retrieve();
                System.out.println("- ReviewID: " + r.getReviewID() +
                                   " | Rating: " + r.getRating() +
                                   " | Comment: " + r.getComment());
                if (rs.last()) break;
                rs.findnext();
            }
            break;
        }
        case 5: { // Show all reviews by a customer
            System.out.println("Enter customer ID:");
            int cid = read.nextInt();
            while(!customersdata.searchCustomerId(cid)) {
                System.out.println("This customer ID doesnt Exist input new one:");
                cid = read.nextInt();
            }

            LinkedList<Reviews> rs = ReviewChain.reviews;
            if (rs == null || rs.empty()) {
                System.out.println("No reviews found.");
                break;
            }

            boolean any = false;
            rs.findfirst();
            for (int i = 0; i < rs.size(); i++) {
                Reviews r = rs.retrieve();
                if (r.getCustomerID() == cid) {
                    if (!any) {
                        System.out.println("Reviews by customer ID " + cid + ":");
                        any = true;
                    }
                    System.out.println("- ReviewID: " + r.getReviewID() +
                                       " | ProductID: " + r.getProductID() +
                                       " | Rating: " + r.getRating() +
                                       " | Comment: " + r.getComment());
                }
                if (rs.last()) break;
                rs.findnext();
            }

            if (!any) System.out.println("No reviews for this customer.");
            break;

        }*/
        case 6:
            System.out.println("Enter first customer ID:");
            int cid1 = read.nextInt();
            while(!customersdata.searchCustomerId(cid1)) {
                System.out.println("This customer ID doesnt Exist input new one:");
                cid1 = read.nextInt();}
            
            System.out.println("Enter second customer ID:");
            int cid2 = read.nextInt();
            while(!customersdata.searchCustomerId(cid2)) {
                System.out.println("This customer ID doesnt Exist input new one:");
                cid2 = read.nextInt();}
            
            LinkedList<Products> common = commonProducts(cid1,cid2);
            common.display();
   break;

        case 7:
            System.out.println("Returning to Main Menu...");
            break;

        default:
            System.out.println("Incorrect choice, choose a valid number:");
            break;
    } // End of switch
}

//-----------------------------------------------------------------------------------------------
/* public static LinkedList<Products> commonProducts(int cusID1, int cusID2){
	
	if(reviewdata.reviews.empty()) {
		System.out.println("No common products");
	    return new LinkedList<Products>();
	}
	
	LinkedList<Products> comProducts = new LinkedList();
	LinkedList<Reviews> R1 = new LinkedList();
	LinkedList<Reviews> R2 = new LinkedList();

	reviewdata.reviews.findfirst();
	while(reviewdata.reviews.retrieve()!= null) {
		if(reviewdata.reviews.retrieve().customerID == cusID1)
			R1.add(reviewdata.reviews.retrieve());
		if(reviewdata.reviews.retrieve().customerID == cusID2)
			R2.add(reviewdata.reviews.retrieve());
		
		reviewdata.reviews.findnext();
	}
	
	R1.findfirst();
	while(R1.retrieve()!= null) {
		
		R2.findfirst();
		while(R2.retrieve()!= null) {
		
			if(R1.retrieve().ProductID==R2.retrieve().ProductID) {
				Products p = productdata.search(R2.retrieve().ProductID);
				
					if(p != null && productdata.getAverageRating(p.getProductId()) > 4) {
						comProducts.add(p);  }
				break;
			}
			R2.findnext();
		}
		R1.findnext();
	}
	
	return comProducts;
}*/

	//-----------------------------------------------------------------------------------------------

	public static LinkedList<Products> commonProducts(int cusID1, int cusID2){
	
	if(reviewdata.reviews.empty()) {//1
		System.out.println("No common products");//1
	    return new LinkedList<Products>();//1
	}
	
	LinkedList<Products> comProducts = new LinkedList();//1
	LinkedList<Reviews> R1 = new LinkedList();//1
	LinkedList<Reviews> R2 = new LinkedList();//1

	reviewdata.reviews.findfirst();//1
	for(int i=0; i<reviewdata.reviews.size(); i++) {//R+1
		if(reviewdata.reviews.retrieve().customerID == cusID1)//R
			R1.add(reviewdata.reviews.retrieve());//R
		if(reviewdata.reviews.retrieve().customerID == cusID2)//R
			R2.add(reviewdata.reviews.retrieve());//R
		
		reviewdata.reviews.findnext();//R
	}
	
	R1.findfirst();//1
	for(int i=0; i<R1.size(); i++) {//R1+1
		
		R2.findfirst();//R1
		for(int j=0; j<R2.size(); j++ ) {//R1(R2+1)
		
			if(R1.retrieve().ProductID==R2.retrieve().ProductID) {//R1*R2
				Products p = productdata.search(R2.retrieve().ProductID);//O(P)*R1*R2
				
					if(p != null && productdata.getAverageRating(p.getProductId()) > 4) {//R1*R2*O(R)
						comProducts.add(p);  }//R1*R2*O(R)
				break;//R1*R2
			}
			R2.findnext();//R1*R2
		}
		R1.findnext();//R1
	}
	
	return comProducts;//1
}//(R^3+R^2P) badddd
//-----------------------------------------------------------------------------------------------


public static void main(String[] args) {	
	loadData();
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


















