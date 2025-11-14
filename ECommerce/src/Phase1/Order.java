package Phase1;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private int customerId;
    private double price;
    private LocalDate orderDate;
    private String status;
    private LinkedList<Integer> productIds;

    public Order(int orderId, int customerId, double price, LocalDate orderDate, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.orderDate = orderDate;
        this.status = status;
        this.productIds = new LinkedList<Integer>();
    }

    public void addIds(String ids) {
        String[] parts = ids.split(";");
        for (int i = 0; i < parts.length; i++) {
            int pid = Integer.parseInt(parts[i].trim());
            productIds.add(pid);
        }
    }

    public void addId(int id) {
        if (productIds.empty()) {
            productIds.add(id);
        } else {
            productIds.findfirst();
            while (!productIds.last()) {
                productIds.findnext();
            }
            productIds.add(id);
        }
    }

    public void updateOrder(Order order) {
        if (order == null) return;
        this.orderId = order.orderId;
        this.customerId = order.customerId;
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

    public LinkedList<Integer> getproductIds() {
        return productIds;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getOrderDate() {
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
        System.out.print("Product IDs: ");
        productIds.display();
        System.out.println("Total Price: " + price);
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + status);
    }
}
