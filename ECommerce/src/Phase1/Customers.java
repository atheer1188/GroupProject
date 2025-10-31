package Phase1;



public class Customers{
private int customerId;
private String name;
private String email;
private LinkedList<Orders> orders;


public void customer() {
	customerId = 0;
	name = "";
	email = "";
	orders = new LinkedList<>();
}


public customers(int id, String n, String e){
    customerId = id;
	name = n;
	email = e; 
	orders = new LinkedList<>();

}

public void addOrder(Orders order) {
	orders.add(order);
}


public int getCustomerId() {
	return customerId;
}


public void setCustomerId(int customerId) {
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


public LinkedList<Orders> getOrders() {
	return orders;
}


public void setOrders(LinkedList<Orders> orders) {
	this.orders = orders;
}


}