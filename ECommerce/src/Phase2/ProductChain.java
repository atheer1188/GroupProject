/*package Phase1;

import java.io.File;
import java.util.Scanner;

public class ProductChain {
	
	public LinkedList<Products> ProductChain;
	
	//--------------------------------------------------------------------------------
	public ProductChain(String fileName) {
	 ProductChain = new LinkedList<Products>();

	    try {
	        File f = new File(fileName);
	        Scanner scan = new Scanner(f);
	        String line =scan.nextLine(); // skip header

	        while (scan.hasNextLine()) {
	            line = scan.nextLine();
	            String [] data = line.split(",");
	            if(data.length >= 4) {
	                int productId =Integer.parseInt(data[0].trim());
	                String name = data[1].trim();
	                double price = Double.parseDouble(data[2].trim());
	                int stock = Integer.parseInt(data[3].trim());
	                

	            

	            Products p = new Products(productId , name , price , stock);
	            ProductChain.add(p);
	        }
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
		
<<<<<<< HEAD
			for(int i = 0 ; i<ProductChain.size() ; i++) {
=======
			while(!ProductChain.last()) {
>>>>>>> branch 'master' of https://github.com/atheer1188/GroupProject.git
				if(ProductChain.retrieve().getProductId()==id) 
					return ProductChain.retrieve();
				if(ProductChain.last())
					break;
				ProductChain.findnext();
			}
			
			if(ProductChain.retrieve().getProductId()==id) 
				return ProductChain.retrieve();
			
		return null;
	    
		}	
	
	
	public Products search(String name) {
		if(ProductChain.empty())
			return null;
		
		ProductChain.findfirst();
		
		while(!ProductChain.last()) {
			if((ProductChain.retrieve().getName()).equals(name)) 
					return ProductChain.retrieve();
				ProductChain.findnext();
			}
		if((ProductChain.retrieve().getName()).equals(name)) 
			return ProductChain.retrieve();
		
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








	}//end class*/
package Phase2;

import java.io.File;
import java.util.Scanner;

import Phase2.AVLTree.AVLNode;


public class ProductChain {
	
	private AVLTree<Products> ProductChain;
	
	 public ProductChain() {
	        ProductChain = new AVLTree<Products>();
	    }
	
	 //constructer
	 public ProductChain(String fileName) {
	        ProductChain = new AVLTree<Products>();
	        if (fileName != null && !fileName.trim().isEmpty()) {
	            readProductsFromFile(fileName);
	        }
	    }
	 
	 public AVLTree<Products> getProductChain(){
		 return ProductChain;
	 }
	 
	 
	 public static Products convertLineToProduct(String line) {
	        try {
	            String[] data = line.split(",");
	            if (data.length < 4) return null;

	            int productId = Integer.parseInt(data[0].trim().replace("\"", ""));
	            String name    = data[1].trim().replace("\"", "");
	            double price   = Double.parseDouble(data[2].trim());
	            int stock      = Integer.parseInt(data[3].trim());

	            return new Products(productId, name, price, stock);
	        } catch (Exception e) {
	            System.out.println("Error in product line: " + line);
	            return null;
	        }
	    }

	 
	 
	    public void readProductsFromFile(String fileName) {
	        try {
	            File f = new File(fileName);
	            Scanner scan = new Scanner(f);

	            if (scan.hasNextLine()) scan.nextLine(); // skip header

	            while (scan.hasNextLine()) {
	                String line = scan.nextLine().trim();
	                if (line.equals("")) continue;

	                Products p = convertLineToProduct(line);
	                if (p != null) {
	                    addProduct(p); 
	                }
	            }

	            scan.close();
	            System.out.println("Products loaded successfully!");

	        } catch (Exception e) {
	            System.out.println("Error while loading products: " + e.getMessage());
	        }
	    }
	    
	    
	    
	    
	    
	    //-------------------------------------------------------------------------------

	    public boolean addProduct(Products p) {
	        if (p == null) 			
	        	return false;
	        int id = p.getProductId();

	       
	        
	        if(ProductChain.insert(id, p)) {      //already checks for duplicates
	        	//System.out.println("Product added successfully!");
	        	return true; }
	        	else {
		        	//System.out.println("Couldn't add your product :( try again");
		        	return false; }
	        		
	        	}
	    
	    //-------------------------------------------------------------------------------
	    
	    public boolean remove(int id) { //assuming it exsists (Check in main)
	    			
	    	if(ProductChain.removeKey(id)) {
	    		System.out.print("Product deleted successfully!");
	    		return true;
	    	}
	    	else return false;
	    	
	    }
	    
	    //-------------------------------------------------------------------------------
	    
	    public boolean updateProduct(int id, String newName, double newPrice, int newStck) {
	        Products p = search(id);
	        if (p == null) {
	            System.out.println("Product not found");
	            return false;
	        }
	        p.setName(newName);
	        p.setPrice(newPrice);
	        p.setStock(newStck);
	        System.out.println("Product updated successfully");
	        return true;
	    }
	    
	    //-------------------------------------------------------------------------------
	    //needs to be checked
	    public Products search(int id) {
	        if (ProductChain.empty()) {
	        	System.out.println("There is No Products");
	        	return null;
	        	}
	        
	        Products p = ProductChain.ExtractKeyData(id);
	        return p;
	      
	    }
	    
	    //-------------------------------------------------------------------------------
	    public Products search(String name) {
	    	 if (ProductChain.empty()) {
		        	System.out.println("There is No Products");
		        	return null;
		        	}
	    	 
	    	 //list of all products O(p)
	    	 LinkedList<Products> Products = ProductChain.inOrderTrverse();
	    	 
	    	 Products p = null;
	    	 Products.findfirst();
	    	 while(!Products.last()) {
	    		 if(Products.retrieve().getName().equalsIgnoreCase(name)) {
	    			 p = Products.retrieve();
	    			 break; 
	    			 }
	    		 Products.findnext();
	    	 }
	    	 // one more time for last
	    	 if(Products.retrieve().getName().equalsIgnoreCase(name)) 
    			 p = Products.retrieve();
	    	 
	    	 return p;	 
	    }
	    //-------------------------------------------------------------------------------
	    
	    public boolean searchProductId(int id) {
	        if (ProductChain.empty()) {
	        	return false;
	        	}
	        
	         return ProductChain.findkey(id);
	    }
	    
	    //-------------------------------------------------------------------------------

	    public void addReviewToProduct(int Pid, Reviews r) {
	        Products p = search(Pid);
	        if (p != null) {
	            p.addReview(r);
	        }
	    }
	    
	    //-------------------------------------------------------------------------------

	    public LinkedList<Products> TrackOutOfStock() {
	    	 if (ProductChain.empty()) {
		        	System.out.println("There is No Products");
		        	return null;
		        	}
	    	 
	    	 LinkedList<Products> Products = ProductChain.inOrderTrverse();
	         LinkedList<Products> out = new LinkedList<Products>();
	         
	         Products.findfirst();
	         for (int i = 0; i < Products.size(); i++) {
	             Products p = Products.retrieve();
	             if (p.getStock() == 0) 
	            	 out.add(p);
	             Products.findnext();
	             
	         }
	         
	         if (out.empty()) 
	             System.out.println("No products are out of stock :)");
	         
	         return out;
	    }
	    // better option
	    public LinkedList<Products> TrackOUTOfStock(){
	    	return ProductChain.inOrderOOS();
	    }
	
	    //-------------------------------------------------------------------------------

	    public Reviews[] SortReviewsByRating(int ProductID){
	        Products p = search(ProductID);
	        if (p == null) {
	            return new Reviews[0]; // Return empty array if product not found
	        }
	        
	        LinkedList<Reviews> reviews = p.getReviews();
	        
	        // Check if reviews are empty
	        if (reviews.empty()) {
	            return new Reviews[0];
	        }
	        
	        Reviews[] RevArray = new Reviews[reviews.size()];  // convert to array for sorting
	        
	        reviews.findfirst();
	        for(int i = 0; i < reviews.size(); i++) {
	            RevArray[i] = reviews.retrieve();
	            
	            if(reviews.last())
	                break;
	            reviews.findnext();
	        } //end conversion
	        
	        mergeSortByRating(RevArray, 0, RevArray.length - 1);
	        return RevArray;
	    }

	    // Merge sort for reviews by rating (descending - highest first)
	    public void mergeSortByRating(Reviews[] A, int l, int r) {
	        if (l >= r) return;
	        
	        int m = (l + r) / 2;
	        mergeSortByRating(A, l, m);
	        mergeSortByRating(A, m + 1, r);
	        mergeByRating(A, l, m, r);
	    }

	    private void mergeByRating(Reviews[] A, int l, int m, int r) {
	        Reviews[] B = new Reviews[r - l + 1];
	        int i = l, j = m + 1, k = 0;
	        
	        while (i <= m && j <= r) {
	            // Descending order: higher ratings first
	            if (A[i].getRating() >= A[j].getRating()) {
	                B[k++] = A[i++];
	            } else {
	                B[k++] = A[j++];
	            }
	        }
	        
	        while (i <= m) B[k++] = A[i++];
	        while (j <= r) B[k++] = A[j++];
	        
	        for (k = 0; k < B.length; k++) {
	            A[l + k] = B[k];
	        }
	    }
	    

	    
	    
	    //-------------------------------------------------------------------------------
	    
	    public LinkedList<Products> rangeQueryByPrice(double minPrice, double maxPrice) {
	    	
	    	 LinkedList<Products> Products = ProductChain.inOrderTrverse();
	        LinkedList<Products> results = new LinkedList<Products>();
	        
	        Products.findfirst();
	        for (int i = 0; i < Products.size(); i++) {
	            Products p = Products.retrieve();
	            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
	                results.add(p);
	            }
	            if (Products.last())
	            	break;
	            Products.findnext();
	        }
	        if(results.empty())
	        	System.out.println("There are No products that fall in that range of price");
	        return results;
	    }	    
	    
	    public double getAverageRating(Products pro) {
	          

	         LinkedList<Reviews> ReveiwsForProduct = pro.getReviews();
	          if(ReveiwsForProduct.empty()) return 0;
	          double sum = 0;//1
	          int count =0;//1
	          ReveiwsForProduct.findfirst();
	          for(int i=0; i<ReveiwsForProduct.size(); i++){
	              {
	                  sum += ReveiwsForProduct.retrieve().getRating();
	                  count++;
	                 }
	                /*  if(i<ReveiwsForProduct.size()-1)
	                	  ReveiwsForProduct.findnext(); */ 
	                       
	              }  
	           if(count == 0) return 0;
	          return sum / count;
	      }
	    
	    //-------------------------------------------------------------------------------

//******************convert List of Products to Array***************************
	    
	    private Products[] convertListToArray(LinkedList<Products> list) {
	    	int size = list.size();//size for the array
	    	Products [] array = new Products[size]; // initialize the array
	    	list.findfirst();
	    	for(int i = 0 ; i< size ; i++) {
	    		array[i]= list.retrieve();//print value of node in the array
	    		if(!list.last())//to make sure it doesnt point to null
	    			list.findnext();
	    	}	
	    	return array;	
	    }
	    
	 //******************Sort By Merge Sort******************************************
	    public static void mergeSort(Products[] A , int l , int r) {
	    	if(l >= r)
	    		return;
	    	int m = (l + r) /2;
	    	mergeSort(A , l , m); // sort first half (left side)
	    	mergeSort(A , m+1 , r); // sort second half (right side)
	    	merge(A, l , m , r);//merge
	    }
	    
	    private static void merge(Products[] A , int l , int m , int r) {
	    	Products [] P = new Products [r -l + 1];
	    	int i = l , j =m+1 , k=0;
	    	while(i <= m && j<=r) {
	    		if(A[i].reviews.size() >= A[j].reviews.size()) {//sorts from most reviewed to least
	    			P[k++] = A[i++];
	    		}
	    		else {
	    			P[k++] = A[j++];
	    		}
	    	}
	    	if(i <= m)
	    	while(j <= r)
    			P[k++] = A[j++];
	    	else
	    	while(i <= m)
    			P[k++] = A[i++];
	    	
	    	for(k =0 ; k < P.length ; k++)
	    		A[k+1] = P[k];
	    	
	    }
		
	  //******************Top 3 Most Rated Products************************************
	    public void Top3MostRatedProducts() {
	    	LinkedList<Products> p = ProductChain.inOrderOOS();//list all nodes from tree to linked list
	    	Products[] pA = convertListToArray(p);//Convert list of products to Array 
	    	 int r =pA.length-1;
	    	mergeSort(pA , 0 , r);
	    	
	    	//Print First Three elements of array with are the highest
	    	System.out.println("Top Three Most Reviewed Products:");
	    	for(int b=0; b<3 ; b++) {
		    	System.out.println(pA[b]);
	    	}
	    		
	    	
	    }


	    
	    
	    
	    
	    
	    }// end class
	    


	 


	



