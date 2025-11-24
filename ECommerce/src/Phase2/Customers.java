package Phase2;

public class Customers{
private int customerId;
private String name;
private String email;
private AVLTree<Order> orders = new AVLTree<Order>();//each customer has there own orders list

public Customers() {
	customerId = 0;
	name = "";
	email = "";
}


public Customers(int id, String n, String e){
    customerId = id;
	name = n;
	email = e; 
}



public int getCustomersId() {
	return customerId;
}


public void setCustomersId(int customerId) {
	this.customerId = customerId;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public AVLTree<Order> getOrders() {
	return orders;
}

public void setEmail(String email) {
	this.email = email;
}
//==========================================================
//add order
public void addOrder(Order order) {
orders.insert(order.getOrderId() , order );
}

//==========================================================
//remove order
public boolean removeOrder(int ID) {
return orders.removeKey(ID);
}


@Override
public String toString() {
	return "Customers\ncustomerId = " + customerId + "\nname = " + name + "\nEmail = " + email + "\nNumber of orders = " + orders.size() ;
}

public void display() {
    System.out.println("Customer ID: " +customerId);
    System.out.println("Customer Name: " + name);
    System.out.print("Customer Email: "+email );
    
    if(!orders.empty()) {
    	       
    	           System.out.println(orders.retrieve());
    	            //need method for display
=    	    }
    	}//end display


    public String getDataToFile() {
    	return customerId + ", "+name+ ", "+ ", "+email;
    }

} 



