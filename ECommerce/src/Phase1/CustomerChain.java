package Phase1;
import java.util.Scanner;

public class CustomerChain {

public static Scanner read = new Scanner(System.in);
public static LinkedList<Customers> customers = new LinkedList<Customers>();
 
public LinkedList<Customers> getcustomersInfo()
{
	return customers;
}

//do File here ====================================================!!!!!!!!!!!!!!!!!!!!!!



//=============================================================================
public void registerCustomer(int id , String n , String e) {
	Customers customer = new Customers();
	
	System.out.println("Please enter the customers ID:");
	customer.setCustomersId(read.nextInt());
	//do a method for search
	//make sure this is right!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	if(searchCustomerId(customer.getCustomersId())) {
	    System.out.println("Customer ID: " + customer.getCustomersId()+" already exists, Please enter a new Id");
		customer.setCustomersId(read.nextInt());
	}
	System.out.println("Enter customers name: ");
	String name = read.nextLine();
	name = read.nextLine();//for garbage
	customer.setName(name);
	
	System.out.println("Enter customers Email : ");
	String email = read.nextLine();
	email = read.nextLine();//for garbage
	customer.setEmail(email);//+=====check for mistake
	customers.add(customer);
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