package Phase1;
import java.util.Scanner;

public class CustomerData {

public static Scanner read = new Scanner(System.in);
public static LinkedList<Customers> customers = new LinkedList<Customers>();
 
public LinkedList<Customers> getcustomersInfo()
{
	return customers;
}

//do File here ====================================================!!!!!!!!!!!!!!!!!!!!!!

//------------------------------------------------------------------------
public void registerCustomer(int id , String n , String e) {
	Customers customer = new Customers();
	
	System.out.println("Please enter the customers ID:");
	customer.setCustomersId(read.nextInt());
	//do a method for search
	//make sure this is right!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	if(searchCustomerId(customer.getCustomersId())) {
	    System.out.println("Customer ID: " + customer.getCustomersId()+" already exists, Please enter a new Id");
		customer.setCustomersId(read.nextInt());
	}
	System.out.println("Enter customers name: ");
	String name = read.nextLine();
	name = read.nextLine();//for garbage
	customer.setName(name);
	
	System.out.println("Enter customers Email : ");
	String email = read.nextLine();
	email = read.nextLine();//for garbage
	customer.setEmail(email);//+======================================================!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	customers.add(customer);
	}
//------------------------------------------------------------------------

public boolean placeOrder(int customerId , Orders order ) {
	if(customers.searchId(customerId) == null) {
		System.out.println("Customer with id: "+ customerId + " does not exist!");
		return false;
	}
	
	else {
		customers.getOrders().addOrder(order);
		System.out.println("Order for "+ customers.getName() + " has been succesfully placed!");
		return true;
	}


}

public void viewOrderHistory(int customerId) {
	customers customer = searchId(customerId);
}


 }