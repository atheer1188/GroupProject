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
	
	System.out.println("Please enter the customers ID:");
	int cusid =read.nextInt();
	//check if ID exists
	while(searchCustomerId(cusid)) {
	    System.out.println("Customer ID: " + cusid+" already exists, Please enter a new Id");
	    cusid =read.nextInt();
	}
	read.nextLine();//clear
	System.out.println("Enter customers name: ");
	String name = read.nextLine();
	
	
	System.out.println("Enter customers Email : ");
	String email = read.nextLine();
	
	Customers newcustomer = new Customers(cusid , name , email);

	
	if(customers.empty())
		customers.add(newcustomer);
	else {
		customers.findfirst();
	while(!customers.last())
		customers.findnext();
		customers.add(newcustomer);
	}
	System.out.println("Registeration complete!");
	}
//=============================================================================


//==============================================================================

public void viewOrderHistory() {
	if(customers.empty())
	
		System.out.println("No customer data is available");
		else
		{
			System.out.println("Enter Customer ID:");	
			int id = read.nextInt();
		
		if(searchCustomerId(id))
		{   Customers customer = customers.retrieve();
			LinkedList<Integer> Orders = customers.retrieve().getOrders();
			if(Orders.empty())
				System.out.println("There is no past order from Customer ID: "+customer.getCustomersId());
			else
			{
			System.out.println("Order History for Customer:");
			Orders.findfirst();
			
			for(int j = 0 ; j<Orders.size(); j++)
			{
			System.out.println(Orders.retrieve());
			if(!Orders.last())
				break;
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