package Phase1;

import java.io.File;
import java.util.Scanner;

public class ProductChain {
	
	public LinkedList<Products> ProductChain;
	
	//--------------------------------------------------------------------------------
	public ProductChain(String fileName) {

	    try {
	        File f = new File(fileName);
	        Scanner scan = new Scanner(f);
	        String line =scan.nextLine(); // skip header

	        while (scan.hasNext()) {
	            line = scan.nextLine();
	            String [] data = line.split(",");
	            int productid = Integer.parseInt(data[0]);
	            String name = data[1].trim();
	            double price = Double.parseDouble(data[2]);
	            int stock = Integer.parseInt(data[3]);

	            

	            Products p = new Products(productid , name , price , stock);
	            ProductChain.add(p);
	        }

	        scan.close();
	        System.out.println("Products loaded successfully!");

	    } catch (Exception e) {
	        System.out.println("Error while loading products: " + e.getMessage());
	    }
	}

	//--------------------------------------------------------------------------------

	public ProductChain() {
		ProductChain = new LinkedList<Products>();
	}


	

	public LinkedList<Products> getProductChain(){
		 return ProductChain ;
	}
	
	
	public boolean addProduct(Products p) {
		//product already exist
				if(search(p.getProductId())!=null) {
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
		if(search(id)==null) {
			System.out.println("this product does not exsist");
			return false;
		}
		else {
			search(id); //sets current to the desired product
			ProductChain.remove();
			System.out.println("product was removed successfully");
			return true;

		}
		
	}
	
public boolean updateProduct(int id, String newName, double newPrice, int newStck) {

	    Products p = search(id);

	    if(p == null) {
	        System.out.println("Product not found");
	        return false;
	    }

	    p.setName(newName);
	    p.setPrice(newPrice);
	    p.setStock(newStck);

	    System.out.println("Product updated successfully");
	    return true;
	}
	
	
	
	public Products search(int id) {
		if(ProductChain.empty())
			return null;
		
		ProductChain.findfirst();
		
			while((ProductChain.retrieve()) != null) {
				if(ProductChain.retrieve().getProductId()==id) 
					return ProductChain.retrieve();
				ProductChain.findnext();
			}
		return null;
	    
		}	
	
	
	public Products search(String name) {
		if(ProductChain.empty())
			return null;
		
		ProductChain.findfirst();
		
			while((ProductChain.retrieve()) != null) {
				if((ProductChain.retrieve().getName()).equals(name)) 
					return ProductChain.retrieve();
				ProductChain.findnext();
			}
		return null;
	    
		}	
	
	public LinkedList<Products> TrackOutOfStock() {
	    
	    LinkedList<Products> out = new LinkedList<Products>();

	    ProductChain.findfirst();
	    while(ProductChain.retrieve() != null) {
	        if(ProductChain.retrieve().getStock() == 0) {
	            out.add(ProductChain.retrieve());
	        }
	        ProductChain.findnext();
	    }

		if(out.size() == 0) {
	    	System.out.println("No products are out of stock :)");
	    	return null;
	    }
	    return out;
	}	

	public void addReviewToProduct(int Pid,Reviews r) {
		
	    Products p = search(Pid);
	    p.addReview(r);
	}

public boolean addReviewToProduct(int rvwId,int Pid,int Cid, int rate, String cmnt) {
	
    Products p = search(Pid);

    if(p == null) {
        System.out.println("Product not found");
        return false;
    }

    Reviews r = new Reviews(rvwId,Cid,Pid,rate, cmnt);
    p.addReview(r);
    System.out.println("Review added successfully");
    
    return true;
}


	
public double getAverageRating(int productId) {
	    Products p = search(productId);
	   if(p == null) {
	        System.out.println("Product not found");
	        return -1;
	    }

	    LinkedList<Reviews> rs = p.getReviews();
	   if(rs.empty()) return 0;

	    double sum = 0;
	    int count =0;
	    rs.findfirst();
	    for(int i=0; i<rs.size(); i++){
	        if(rs.retrieve().getProductID() == productId)
	        {
	    	sum += rs.retrieve().getRating();
	        count++;
	        }
	        if(i<rs.size()-1)
	        rs.findnext();
	    }
	    return sum / count;
	}

//edit review left

public boolean searchProductId(int id) {
	ProductChain.findfirst();
	
		if(!ProductChain.empty()){
			while(!ProductChain.last()) {
			if(ProductChain.retrieve().getProductId()==id) 
				return true;
			ProductChain.findnext();
		}
			if(ProductChain.retrieve().getProductId()==id) 
				return true;// for last
			}
	return false;
    
	}	


//---------------------------------------------------------------------------------------------------
public LinkedList<Products> top3Products() {
	Products first = null , second = null , third = null ;
	double max1 = 0 , max2 = 0 , max3 = 0;
     if(ProductChain.empty()) {
    	 System.out.println("There are no available products");
		return null;
     }
     
		ProductChain.findfirst();
     for(int i = 0 ; i<ProductChain.size(); i++) {
	
		Products p = ProductChain.retrieve();
		double avg =getAverageRating(p.getProductId());
		
		if(avg > max1) 
		{
			third = second; max3 = max2;
			second = first ; max2 = max1;
			first = p; max1 = avg;
		}//first if
		
		
		else if(avg > max2){
			third = second ; max3 = max2;
			second = p; max2 = avg;
		}//second if
		
		else if(avg > max3){
			third = p ; max3 = avg;
		}//third if
		ProductChain.findnext();
	}
     LinkedList<Products> top3products = new LinkedList<Products>();
     if(first!=null)top3products.add(first); 
     if(second!=null)top3products.add(second); 
     if(third!=null)top3products.add(third);
return top3products;
}
//---------------------------------------------------------------------------------------------------








	}//end class
	
	


