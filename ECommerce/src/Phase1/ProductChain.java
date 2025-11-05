package Phase1;

public class ProductChain {
	
	private LinkedList<Products> ProductChain;
	
	ProductChain(){
		ProductChain = new LinkedList();
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
		ProductChain.findfirst();
		
			while((ProductChain.retrieve()) != null) {
				if(ProductChain.retrieve().getProductId()==id) 
					return ProductChain.retrieve();
				ProductChain.findnext();
			}
		return null;
	    
		}	//check if neccecary
	
	
	public Products search(String name) {
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
	    p.reviews.add(r);
	}

public boolean addReviewToProduct(int Pid,int Cid, int rate, String cmnt) {
	
    Products p = search(Pid);

    if(p == null) {
        System.out.println("Product not found");
        return false;
    }

    Reviews r = new Reviews(Cid,Pid,rate, cmnt);
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
	    for(int i=0;i<rs.size();i++){
	        sum += rs.retrieve().getRating();
	    }

	    return sum / rs.size();
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
	}
	
	


