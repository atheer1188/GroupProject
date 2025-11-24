package Phase1;



public class Customers{
private int customerId;
private String name;
private String email;
private AVLTree<Order> orders = new AVLTree<>();

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

public void addOrder(Order order) {
orders.insert(order.getOrderId() , order );
}

//==========================================================
public boolean removeOrder(int ID) {
return orders.removeKey(ID);
}
//==========================================================End of RemoveOrder



//not good i have to fix the syntax!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public void display() {
    System.out.println("Customer ID: " +customerId);
    System.out.println("Customer Name: " + name);
    System.out.print("Customer Email: "+email );
    
    if(!orders.empty()) {
    	       
    	           System.out.println(orders.retrieve());
    	            //need method for display
    	       
    	    }
    	}//end display
    public String getDataToFile() {
    	return customerId + ", "+name+ ", "+ ", "+email;
    }

} 



