package Phase1;

public class CustomerManager {

	
private LinkedList<Customers> customers;
 
public void CustomerManager() {
	customers = new LinkedList();
}

public boolean registerCustomer(int id , String n , String e) {
	//check if cutomer already exists
	if(customers.searchId(id) != null) {
		System.out.println("Customer with id: "+id+ " already exists!");
		return false;
	}
	
	Customers registercustomer = new Customers( id, n , e);
	customers.add(registercustomer);
	System.out.println("Welcome "+customers.getName()+"! you have been succesfully registered.");

	return true;
	
	}

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