package Phase1;
import java.time.LocalDate;
import java.util.Date;

public class Order {
private int orderId;
private int customerId;
private int produtId;
private double price;
private LocalDate orderDate;
private String status;
private LinkedList<Integer>productIds;

public Order(int orderId, int customerId, int produtId, double price, LocalDate orderDate, String status) {
	this.orderId = orderId;
	this.customerId = customerId;
	this.produtId = produtId;
	this.orderDate = orderDate;
	this.status = status;
	this.productIds = new LinkedList<>();
	addIds(productIds);
}

public void addIds(String Ids) {
	String ids[] = Ids.split(";");
	for(int i = 0 ; i < ids.length; i++)
		productIds.add(Integer.parseInt(ids[i].trim()));
			
}

public void addId(int id) {
	productIds.add(id);
}

public void UpdateOrder(Order order) {
	this.orderId = order.orderId;
	this.customerId = order.customerId;
	this.produtId = order.produtId;
	this.price = order.price;
	this.orderDate = order.orderDate;
	this.status = order.status;
	this.productIds = order.productIds;
	
			
}

public int getOrderId() {
	return orderId;
	
}

public int getCustomerId() {
	return customerId;
	
}

public int grtprodutId() {
	return produtId;
	
}

public LinkedList<Integer> getproductIds() {
	return productIds;
	
}
public double getPrice() {
	return price;
	
}
public  LocalDate getOrderDate() {
	return orderDate;
	
}
public String getStatus() {
	return status;
	
}

public void setStatus(String status) { 
	this.status = status;
}


public void display() {
    System.out.println("Order ID: " + orderId);
    System.out.println("Customer ID: " + customerId);
    System.out.print("Product IDs: " );
    productIds.display();
    System.out.println("Total Price: $" + price);
    System.out.println("Date: " + orderDate);
    System.out.println("Status: " + status);
 
}  




}

