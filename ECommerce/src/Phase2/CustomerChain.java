package Phase2;

import java.io.File;
import java.util.Scanner;

public class CustomerChain {

public static Scanner read = new Scanner(System.in);
public static AVLTree<Customers> customers = new AVLTree<Customers>();
 
public AVLTree<Customers> getcustomers()
{
	return customers;
}



public CustomerChain(String fileName) {

    try {
        File f = new File(fileName);
        Scanner scan = new Scanner(f);
        String line =scan.nextLine(); // skip header

        while (scan.hasNext()) {
            line = scan.nextLine();
            String [] data = line.split(",");
            Customers c = new Customers(Integer.parseInt(data[0]) , data[1] , data[2]);
            customers.insert(c.getCustomersId() , c);
        }
        scan.close();
    } catch (Exception e) {
        System.out.println("Error while loading Custeomers: " + e.getMessage());
    }
}

// File here ====================================================!!!!!!!!!!!!!!!!!!!!!!

/*public CustomerChain(String fileName) {
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
*/
//=============================================================================
public void registerCustomer() {
	
	System.out.println("Please enter the customers ID:");//1
	int cusid =read.nextInt();//1
	//check if ID exists
	while(customers.findkey(cusid)) {//O(c)
	    System.out.println("Customer ID: " + cusid+" already exists, Please enter a new Id");//O(c)
	    cusid =read.nextInt();//O(c)
	}
	read.nextLine();//clear//1
	System.out.println("Enter customers name: ");//1
	String name = read.nextLine();
	
	
	System.out.println("Enter customers Email : ");
	String email = read.nextLine();
	
	Customers newcustomer = new Customers(cusid , name , email);

	customers.insert(cusid, newcustomer);
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
		
		if(customers.findkey(id))
		{   
			if(customers.retrieve().getOrders().empty())
				System.out.println("There is no past order from Customer ID: "+customers.retrieve().getCustomersId());
			else
			{
				System.out.println("Orders History:");
				System.out.println(customers.retrieve().getOrders());
			}
		}
		else
			System.out.println("Customer ID doesn't Exist");
	
		}
	}
//==============================================================================

//==============================================================================
	public Customers searchCustomerById() {
		if(customers.empty()) {
			System.out.println("No customers");
			return null;
		}
		else {
		System.out.println("Enter customers ID");
		int cusID = read.nextInt();
			if(customers.findkey(cusID)) {
				System.out.println(customers.retrieve());
				return customers.retrieve();
			}
		}
		System.out.println("customers ID Doesnt Exist");
		return null;

	}
//==============================================================================	
private Customers[] convertListToArray(LinkedList<Customers> list) {
	int size = list.size();//size for the array
	Customers [] array = new Customers[size]; // initialize the array
	list.findfirst();
	for(int i = 0 ; i< size ; i++) {
		array[i]= list.retrieve();//print value of node in the array
		if(!list.last())//to make sure it doesnt point to null
			list.findnext();
	}	
	return array;	
}
//==============================================================================
//sort the array using bubble 
private void sortNameByBubble(Customers[] array) {
	int size = array.length;
	for(int i=0 ; i < size -1 ; i++) {
		for(int k=0 ; k<size-i-1 ; k++) {
			if(array[k].getName().compareToIgnoreCase(array[k+1].getName()) >0) {
				Customers temp = array[k];
				array[k] = array[k+1];
				array[k+1] = temp;
				
			}
		}
	}
}
//==============================================================================
//method that uses method helpers to print names in alphabetical order
public void ListCustomersInAlphabeticalOrder() {
	//insert all customer nodes from tree to list
	LinkedList<Customers> cusL = customers.inOrderTrverse();
	//convert it to array to sort it
	Customers[] cA =convertListToArray(cusL);
	//sort the array alphabeticcally using bubble
	sortNameByBubble(cA);
	//print all the values of the sorted bubble
	System.out.println("Names of customers in Alphabetical Order:");
	for(int i = 0; i < cA.length ; i++) {
		System.out.println(cA[i].getName());
	}
}


}
