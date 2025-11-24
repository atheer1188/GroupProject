package Phase2;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderChain {

    private AVLTree<Order> orderTree;
    private LinkedList<Customers> customerList;

    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Scanner read = new Scanner(System.in);

    public OrderChain() {
        orderTree = new AVLTree<Order>();
        customerList = CustomerChain.customers; 
    }

    public AVLTree<Order> getOrderTree() {
        return orderTree;
    }

    // ---------------------------------------
    public Order findOrderById(int orderId) {
        if (orderTree.findkey(orderId)) {
            return orderTree.retrieve();
        }
        return null;
    }

    // -------------------- --------------------
    public void changeStatus(int orderId, String statusName) {
        Order target = findOrderById(orderId);
        if (target == null) {
            System.out.println("Order ID not found!");
            return;
        }

        target.setStatus(statusName);
        System.out.println("Status updated for order " + orderId);
    }

    // ----------------------------------------
    public void cancelOrder(int orderId) {
        Order target = findOrderById(orderId);
        if (target == null) {
            System.out.println("Order ID not found!");
            return;
        }

        if ("Cancelled".equalsIgnoreCase(target.getStatus())) {
            System.out.println("Order " + orderId + " was already cancelled.");
            return;
        }

        target.setStatus("Cancelled");
        System.out.println("Order " + orderId + " has been cancelled.");
    }

    // ---------------------------------------
    private void linkOrderToCustomer(Order orderObj) {
        if (customerList == null || customerList.empty()) return;

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

    // ----------------------------------------
    public void insertOrder(Order orderObj) {
        if (findOrderById(orderObj.getOrderId()) != null) {
            System.out.println("Order already exists!");
            return;
        }

        orderTree.insert(orderObj.getOrderId(), orderObj);
        linkOrderToCustomer(orderObj);
    }

    // ------------------- --------------------
    public static Order convertLineToOrder(String line) {
        try {
            String[] fields = line.split(",");

            int orderId    = Integer.parseInt(fields[0].trim().replace("\"", ""));
            int customerId = Integer.parseInt(fields[1].trim().replace("\"", ""));
            String productIdList = fields[2].trim().replace("\"", "");
            double price   = Double.parseDouble(fields[3].trim());
            LocalDate date = LocalDate.parse(fields[4].trim(), dateFormat);
            String status  = fields[5].trim();

            Order obj = new Order(orderId, customerId, price, date, status);
            obj.addIds(productIdList);

            return obj;

        } catch (Exception e) {
            System.out.println("Error in line: " + line);
            return null;
        }
    }

    // ---------------------------------------
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

    // ----------------------------------------
    public LinkedList<Order> AllOrdersBetweenDates(LocalDate first_date, LocalDate second_date) {

        LinkedList<Order> orders = new LinkedList<Order>();

        if (orderTree.empty()) {
            System.out.println("No orders in the system.");
            return orders;
        }

        if (second_date.isBefore(first_date)) {
            LocalDate tmp = first_date;
            first_date = second_date;
            second_date = tmp;
        }

        final LocalDate f = first_date;
        final LocalDate s = second_date;

      
        orderTree.inOrder(new AVLTree.Visitor<Order>() {
            @Override
            public void visit(int key, Order o) {
                LocalDate d = o.getOrderDate();
                if ((d.isAfter(f) || d.isEqual(f)) &&
                    (d.isBefore(s) || d.isEqual(s))) {
                    orders.add(o);
                    o.display();
                }
            }
        });

        if (orders.size() == 0) {
            System.out.println("No orders between these two dates.");
        }

        return orders;
    }

    // --------------------------------------
    public void displayAllOrders() {
        if (orderTree.empty()) {
            System.out.println("No orders available!");
            return;
        }

        orderTree.inOrder(new AVLTree.Visitor<Order>() {
            @Override
            public void visit(int key, Order o) {
                o.display();
            }
        });
    }
}

