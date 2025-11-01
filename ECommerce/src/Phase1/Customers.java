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
//End of RemoveOrder

//not good i have to fix the syntax!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
@Override
public String toString() {
	return "Customers [customerId=" + customerId + ", name=" + name + ", email=" + email + ", orders=" + orders
			+ ", getCustomerId()=" + getCustomersId() + ", getName()=" + getName() + ", getEmail()=" + getEmail() + "]";
}




}