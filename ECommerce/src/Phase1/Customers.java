package Phase1;



public class Customers{
private int customerId;
private String name;
private String email;
private LinkedList<Integer> orders = new LinkedList<Integer>();


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


public LinkedList<Integer> getOrders() {
	return orders;
}


public void setOrders(LinkedList<Integer> orders) {
	this.orders = orders;
}


public void setEmail(String email) {
	this.email = email;
}

public void addOrder(Integer order) {
orders.add(order);
}

//==========================================================
public boolean removeOrder(Integer order) {
	if(! orders.empty())
	{
		orders.findfirst();
		while(orders.last()) {//check all nodes if equals the given order
			if(orders.retrieve().equals(order)) {
				orders.remove();
				return true;
			}
			
			else
				orders.findnext();
		}
		if(orders.retrieve().equals(order)) {//for the last Node
			orders.remove();
			return true;
	 }
	}
	return false;
}
//==========================================================End of RemoveOrder



//not good i have to fix the syntax!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public void display() {
    System.out.println("Customer ID: " +customerId);
    System.out.println("Customer Name: " + name);
    System.out.print("Customer Email: "+email );
    
    if(!orders.empty()) {
    	        orders.findfirst();
    	        for (int i = 0; i < orders.size(); i++) {
    	           System.out.println(orders.retrieve());
    	            orders.findnext();
    	        }
    	    }
    	}//end display
    

} 



