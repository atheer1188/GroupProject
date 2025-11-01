package Phase1;

public class ProductChain {
	
	private LinkedList<Products> ProductChain;
	
	ProductChain(){
		ProductChain = new LinkedList();
	}
	
	
	public boolean addProduct(Products p) {
		//product already exist
				if(ProductChain.searchId(p.getProductId())!=null) {
			System.out.println("A product with the same ID already exsists");
			return false;
		}
		else {
			ProductChain.add(p);
			System.out.println("Product Added Successfully!");
			return true;
		}
	}
	
	public boolean remove(int id) {
		if(ProductChain.searchId(id)!=null) {
			System.out.println("this product does not exsist");
			return false;
		}
		else {
			ProductChain.searchId(id); //sets current to the desired product
			ProductChain.remove();
			System.out.println("product was removed successfully");
			return true;

		}
		
	}
	
	//public boolean update()
	
	
	
	
	public Products searchId(int id) {
		ProductChain.findfirst();
		
			while((ProductChain.retrieve()) != null) {
				if(ProductChain.retrieve().getProductId()==id) 
					return ProductChain.retrieve();
				ProductChain.findnext();
			}
		return null;
	    
		}			
	
	
	public Products searchId(String name) {
		ProductChain.findfirst();
		
			while((ProductChain.retrieve()) != null) {
				if((ProductChain.retrieve().getName()).equals(name)) 
					return ProductChain.retrieve();
				ProductChain.findnext();
			}
		return null;
	    
		}			
	}
	
	


