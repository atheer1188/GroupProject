package Phase1;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderChain {

    private LinkedList<Order> orderList;      
    private LinkedList<Customers> customerList; 
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Scanner read = new Scanner(System.in);

    public OrderChain() {
        orderList = new LinkedList<Order>();       
        customerList = CustomerChain.customers;     
    }

    public LinkedList<Order> getOrders() {
        return orderList;
    }

    // ---------------------------------------------------
    // Search order by ID
    public Order findOrderById(int orderId) {

        if (orderList.empty()) return null;

        orderList.findfirst();
        for (int i = 0; i < orderList.size(); i++) {
            Order currentOrder = orderList.retrieve();
            if (currentOrder.getOrderId() == orderId)
                return currentOrder;

            if (orderList.last()) break;
            orderList.findnext();
        }
        return null;
    }

    // ---------------------------------------------------
    // Update order status
    public void changeStatus(int orderId, String statusName) {
        Order target = findOrderById(orderId);

        if (target == null) {
            System.out.println("Order ID not found!");
            return;
        }

        target.setStatus(statusName);
        System.out.println("Status updated for order " + orderId);
    }

    // ---------------------------------------------------
    // Cancel order (used instead of delete)
    public void cancelOrder(int orderId) {
        Order target = findOrderById(orderId);

        if (target == null) {
            System.out.println("Order ID not found!");
            return;
        }

        target.setStatus("Cancelled");
        System.out.println("Order " + orderId + " has been cancelled.");
    }


    // ---------------------------------------------------
    // Link order to customer (add orderId to customer.orders list)
    private void linkOrderToCustomer(Order orderObj) {

        if (customerList.empty()) return;

        customerList.findfirst();
        for (int i = 0; i < customerList.size(); i++) {
            Customers currentCustomer = customerList.retrieve();

            if (currentCustomer.getCustomersId() == orderObj.getCustomerId()) {
                currentCustomer.addOrder(orderObj.getOrderId());
                return;
            }

            if (customerList.last()) break;
            customerList.findnext();
        }
    }

    // ---------------------------------------------------
    // Insert new order (create order)
    public void insertOrder(Order orderObj) {

        if (findOrderById(orderObj.getOrderId()) != null) {
            System.out.println("Order already exists!");
            return;
        }

        addToEnd(orderList, orderObj);
        linkOrderToCustomer(orderObj);
    }

    private void addToEnd(LinkedList<Order> list, Order item) {

        if (list.empty()) {
            list.add(item);
            return;
        }

        list.findfirst();
        while (!list.last()) {
            list.findnext();
        }

        list.add(item);
    }

    // ---------------------------------------------------
    // Convert CSV line to Order
    public static Order convertLineToOrder(String line) {

        try {
            String[] fields = line.split(",");

            int orderId = Integer.parseInt(fields[0].trim().replace("\"", ""));
            int customerId = Integer.parseInt(fields[1].trim().replace("\"", ""));
            String productIdList = fields[2].trim().replace("\"", "");
            double price = Double.parseDouble(fields[3].trim());
            LocalDate date = LocalDate.parse(fields[4].trim(), dateFormat);
            String status = fields[5].trim();

            
            Order obj = new Order(orderId, customerId, price, date, status);
            obj.addIds(productIdList);


            return obj;

        } catch (Exception e) {
            System.out.println("Error in line: " + line);
            return null;
        }
    }

    // ---------------------------------------------------
    // Read orders from file
    public void readOrdersFromFile(String fileName) {

        try {
            File f = new File(fileName);
            Scanner scan = new Scanner(f);

            if (scan.hasNextLine())
                scan.nextLine(); // skip header

            while (scan.hasNextLine()) {
                String row = scan.nextLine().trim();
                if (row.length() == 0) continue;

                Order newOrder = convertLineToOrder(row);
                if (newOrder != null) insertOrder(newOrder);
            }

            scan.close();
            System.out.println("Orders loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error while loading orders: " + e.getMessage());
        }
    }

    // ---------------------------------------------------
    // All orders between two dates
    public LinkedList<Order> AllOrdersBetweenDates(LocalDate first_date, LocalDate second_date) {

        LinkedList<Order> orders = new LinkedList<Order>();

        if (orderList.empty()) {
            System.out.println("No orders in the system.");
            return orders;
        }

        if (second_date.isBefore(first_date)) {
            LocalDate tmp = first_date;
            first_date = second_date;
            second_date = tmp;
        }

        boolean found = false;

        orderList.findfirst();
        for (int j = 0; j < orderList.size(); j++) {

            Order currentOrder = orderList.retrieve();
            LocalDate d = currentOrder.getOrderDate();

             
            if ((d.isAfter(first_date) || d.isEqual(first_date)) &&
                (d.isBefore(second_date) || d.isEqual(second_date))) {

                orders.add(currentOrder);
                currentOrder.display();
                found = true;
            }

            if (orderList.last()) break;
            orderList.findnext();
        }

        if (!found) {
            System.out.println("No orders between these two dates.");
        }

        return orders;
    }


    // ---------------------------------------------------
    public void displayAllOrders() {

        if (orderList.empty()) {
            System.out.println("No orders available!");
            return;
        }

        orderList.findfirst();
        for (int i = 0; i < orderList.size(); i++) {
            orderList.retrieve().display();

            if (orderList.last()) break;
            orderList.findnext();
        }
    }

}
