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
package Phase1;

import java.io.File;
import java.util.Scanner;

public class ProductChain {


    private LinkedList<Products> ProductChain;


    public ProductChain(String fileName) {
        ProductChain = new LinkedList<Products>();
        if (fileName != null && !fileName.trim().isEmpty()) {
            readProductsFromFile(fileName);
        }
    }

    public ProductChain() {
        ProductChain = new LinkedList<Products>();
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



    public LinkedList<Products> getProductChain() {
        return ProductChain;
    }



    public boolean addProduct(Products p) {
        if (p == null) return false;

        // product already exists
        if (search(p.getProductId()) != null) {
            System.out.println("A product with the same ID already exists");
            return false;
        } else {

            if (ProductChain.empty()) {
                ProductChain.add(p);
            } else {
                ProductChain.findfirst();
                while (!ProductChain.last()) ProductChain.findnext();
                ProductChain.add(p);
            }
            return true;
        }
    }

    public boolean remove(int id) {
        if (search(id) == null) {
            System.out.println("this product does not exist");
            return false;
        } else {
           
            ProductChain.remove();
            return true;
        }
    }

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

    

    public Products search(int id) {
        if (ProductChain.empty()) return null;//1

        ProductChain.findfirst();//1
        for (int i = 0; i < ProductChain.size(); i++) {//p+1
            if (ProductChain.retrieve().getProductId() == id)//1
                return ProductChain.retrieve();//1
            if (ProductChain.last()) break;//1
            ProductChain.findnext();//1
        }//
        return null;//1
    }

    public Products search(String name) {
        if (ProductChain.empty()) return null;

        ProductChain.findfirst();
        for (int i = 0; i < ProductChain.size(); i++) {
            if (ProductChain.retrieve().getName().equals(name))
                return ProductChain.retrieve();
            if (ProductChain.last()) break;
            ProductChain.findnext();
        }
        return null;
    }

    public boolean searchProductId(int id) {
        if (ProductChain.empty()) //1
        	return false;//1

        ProductChain.findfirst();//1
        for (int i = 0; i < ProductChain.size(); i++) {//p+1
            if (ProductChain.retrieve().getProductId() == id)//1(p)
                return true;//1(p)
            if (ProductChain.last()) //1(p)
            	break;//1(p)
            ProductChain.findnext();//1(p)
        }
        return false;//1
    }

 

    public LinkedList<Products> TrackOutOfStock() {
        LinkedList<Products> out = new LinkedList<Products>();
        if (ProductChain.empty()) {
            System.out.println("No products are out of stock :)");
            return out;
        }

        ProductChain.findfirst();
        for (int i = 0; i < ProductChain.size(); i++) {
            Products p = ProductChain.retrieve();
            if (p.getStock() == 0) {
                // أضف للنهاية
                if (out.empty()) out.add(p);
                else {
                    out.findfirst();
                    while (!out.last()) out.findnext();
                    out.add(p);
                }
            }
            if (ProductChain.last()) break;
            ProductChain.findnext();
        }

        if (out.empty()) {
            System.out.println("No products are out of stock :)");
        }
        return out;
    }

    public void addReviewToProduct(int Pid, Reviews r) {
        Products p = search(Pid);
        if (p != null) {
            p.addReview(r);
        }
    }

    public boolean addReviewToProduct(int rvwId, int Pid, int Cid, int rate, String cmnt) {
        Products p = search(Pid);
        if (p == null) {
            System.out.println("Product not found");
            return false;
        }
        Reviews r = new Reviews(rvwId, Pid, Cid, rate, cmnt); 
        p.addReview(r);
        System.out.println("Review added successfully");
        return true;
    }

    public double getAverageRating(int productId) {
        Products p = search(productId);//O(p)
        if(p == null) {//1
            System.out.println("Product not found");//1
            return -1;//1
        }

        LinkedList<Reviews> rs = p.getReviews();//1
        if(rs.empty()) //1
        	return 0;//1

        double sum = 0;//1
        int count =0;//1
        rs.findfirst();//1
        for(int i=0; i<rs.size(); i++){//r+1
            /*if(rs.retrieve().getProductID() == productId)//1(r)
            {*/
                sum += rs.retrieve().getRating();//1(r)
                count++;//1(r)
              if(i<rs.size()-1)//r(r)
            rs.findnext();//1(r-1)(r)   
                
            }
           
            if(count == 0) return 0;
        return sum / count;//1
    }



    public LinkedList<Products> top3Products() {//-
        Products first = null, second = null, third = null;//1
        double max1 = -1, max2 = -1, max3 = -1;//1

        if (ProductChain.empty()) {//1
            System.out.println("There are no available products");//1
            return new LinkedList<Products>();//1
        }

        ProductChain.findfirst();//1
        for (int i = 0; i < ProductChain.size(); i++) {//p+1
            Products p = ProductChain.retrieve();//1(p)
            double avg = getAverageRating(p.getProductId());//O(p+r)*O(p)

            if (avg > max1) {//1p
                third = second; max3 = max2;//2p
                second = first; max2 = max1;//2p
                first = p;      max1 = avg;//2p
            } else if (avg > max2) {//1p
                third = second; max3 = max2;//2p
                second = p;     max2 = avg;//2p
            } else if (avg > max3) {//1p
                third = p;   max3 = avg;//2p
            }

            if (ProductChain.last()) //p
            	break;//p
            ProductChain.findnext();//p
        }

        LinkedList<Products> top3products = new LinkedList<Products>();//1
        if (first  != null) top3products.add(first);//1
        if (second != null) top3products.add(second);//1
        if (third  != null) top3products.add(third);//1
        return top3products;//1
    }

}//O(p^2 +p*r)//O(1)

	
	


