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

  
    public void changeStatus(int orderId, String statusName) {
        Order target = findOrderById(orderId);

        if (target == null) {
            System.out.println("Order ID not found!");
            return;
        }

        target.setStatus(statusName);
        System.out.println("Status updated for order " + orderId);
    }



    public void deleteOrder(int orderId) {

        if (findOrderById(orderId) == null) {
            System.out.println("Order does not exist!");
            return;
        }

        orderList.remove();
        System.out.println("Order " + orderId + " deleted.");
    }

    

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

    

    public static Order convertLineToOrder(String line) {

        try {
            String[] fields = line.split(",");

            int orderId = Integer.parseInt(fields[0].trim().replace("\"", ""));
            int customerId = Integer.parseInt(fields[1].trim().replace("\"", ""));
            String productIdList = fields[2].trim().replace("\"", "");
            double price = Double.parseDouble(fields[3].trim());
            LocalDate date = LocalDate.parse(fields[4].trim(), dateFormat);
            String status = fields[5].trim();

            Order obj = new Order(orderId, customerId,price, date, status);
            obj.addIds(productIdList);

            return obj;

        } catch (Exception e) {
            System.out.println("Error in line: " + line);
            return null;
        }
    }

   

    public void readOrdersFromFile(String fileName) {

        try {
            File f = new File(fileName);
            Scanner scan = new Scanner(f);

            scan.nextLine(); // skip header

            while (scan.hasNextLine()) {
                String row = scan.nextLine().trim();
                Order newOrder = convertLineToOrder(row);
                if (newOrder != null) insertOrder(newOrder);
            }

            scan.close();
            System.out.println("Orders loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error while loading orders: " + e.getMessage());
        }
    }

    public LinkedList<Order> AllOrdersBetweenDates( LocalDate first_date ,LocalDate second_date)
    {
    	LinkedList<Order> orders = new LinkedList<Order>();

    	 if(!orderList.empty()) {
    		 orderList.findfirst();
    		 for(int j = 0 ;  j<orderList.size() ;j++ ) 
    		 {Order currentOrder = orderList.retrieve();
    			 if(orderList.retrieve().getOrderDate().isAfter(first_date) && orderList.retrieve().getOrderDate().isBefore(second_date)) {
    				 orders.add(currentOrder);
    				currentOrder.display();
    			 }
    			 if(orderList.last())
    				 break;
        		 orderList.findnext();
    		 }
    	 }
return orders;
    }
  //-------------------------------------------------------------------------------  
  /*  public void readOrdersFromFile(String fileName) {

        try {
            File f = new File(fileName);
            Scanner scan = new Scanner(f);
            String line = scan.nextLine(); // skip header

            while (scan.hasNext()) {
                line = scan.nextLine(); 
                String [] data = line.split(",");
                int orderid = Integer.parseInt(data[0]);
                int customerid=	Integer.parseInt(data[1]);
                String pp = data[2].replaceAll("\"", "");
                String [] p =pp.split(";");
                Integer [] pids = new Integer [p.length];
                for(int i = 0 ; i<pids.length ; i++)
                	pids[i] = new Integer(Integer.parseInt(p[i].trim()));
                double price =Double.parseDouble(data[3]);
                String date =data[4];
                String status =data[5];
                
                Order o = new Order(orderid, customerid, pids, price, date, status);
                orderList.add(o);


            }

            scan.close();
            System.out.println("Orders loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error while loading orders: " + e.getMessage());
        }
    }
*/
  //------------------------------------------------------------------------------- 
  
  //-------------------------------------------------------------------------------
   

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
