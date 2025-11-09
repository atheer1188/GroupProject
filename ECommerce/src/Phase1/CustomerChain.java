package Phase1;
import java.io.File;
import java.util.Scanner;

public class CustomerChain {

public static Scanner read = new Scanner(System.in);
public static LinkedList<Customers> customers = new LinkedList<Customers>();
 
public LinkedList<Customers> customersInfo()
{
	return customers;
}

//do File here ====================================================!!!!!!!!!!!!!!!!!!!!!!

public CustomerChain(String fileName) {
customers = new LinkedList<Customers>();
    try {
        File f = new File(fileName);
        Scanner scan = new Scanner(f);
        String line =scan.nextLine(); // skip header

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String [] data = line.split(",");
            if(data.length >= 3) {
                int customerId =Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();

            Customers c = new Customers(customerId , name , email);
            customers.add(c);
        }
        }
        scan.close();
        System.out.println("Customers loaded successfully!");

    } catch (Exception e) {
        System.out.println("Error while loading Custeomers: " + e.getMessage());
    }
}

//=============================================================================
public void registerCustomer() {
	Customers newcustomer = new Customers();
	
	System.out.println("Please enter the customers ID:");
	newcustomer.setCustomersId(read.nextInt());
	
	while(searchCustomerId(newcustomer.getCustomersId())) {
	    System.out.println("Customer ID: " + newcustomer.getCustomersId()+" already exists, Please enter a new Id");
	    newcustomer.setCustomersId(read.nextInt());
	}
	System.out.println("Enter customers name: ");
	String name = read.nextLine();
	 read.nextLine();
	newcustomer.setName(name);
	
	System.out.println("Enter customers Email : ");
	String email = read.nextLine();
	read.nextLine();
	newcustomer.setName(name);
	newcustomer.setEmail(email);
	
	if(customers.empty())
		customers.findfirst();
	else {
		customers.findfirst();
	while(!customers.last())
		customers.findnext();
	}
	customers.add(newcustomer);
	}
//=============================================================================


//==============================================================================

public void viewOrderHistory() {
	if(customers.empty())
	
		System.out.println("No customer Data");
		else
		{
			System.out.println("Enter Customer ID:");	
			int id = read.nextInt();
		
		if(searchCustomerId(id))
		{
			LinkedList<Integer> Orders = customers.retrieve().getOrders();
			if(Orders.empty())
				System.out.println("There is no past order from Customer ID: "+customers.retrieve().getCustomersId());
			else
			{
			System.out.println("Order History:");
			Orders.findfirst();
			
			for(int j = 0 ; j<Orders.size(); j++)
			{
			System.out.println(Orders.retrieve());
			Orders.findnext();
			}
			}
		}
		else
			System.out.println("Customer ID doesn't Exist");
	
		}
	}
//==============================================================================

//==============================================================================
	public boolean searchCustomerId(int id) {
		boolean found = false;
		if(customers.empty())
			return found;
		
		else {
			customers.findfirst();
			for(int i = 0 ; i<customers.size() ; i++) {
				if(customers.retrieve().getCustomersId()==id)
				{
					found=true;
					break;
				}
				customers.findnext();
			}
				return found;	
		}
	}
//==============================================================================	
	public Customers findCustomer(int id) {
		if(customers.empty())
			System.out.println("The customer has no data");
		
		else {
			customers.findfirst();
			for(int i = 0 ; i<customers.size() ; i++) {
				if(searchCustomerId(id))
					return customers.retrieve();
				customers.findnext();	
			}

		}
		return null;
	}



 }