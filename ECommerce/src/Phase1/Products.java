package Phase1;

public class Products {

	//productId, name, price, stock, list of reviews.
	
	public int productId;
	private String name;
	private double price;
	private int stock;
	public LinkedList<Reviews> reviews;
	
	 Products() {
		productId=0;
		name=null;
		price =0;
		stock=0;
		reviews=new LinkedList();
	}
	 
	 Products(int id, String n, double p, int s) {
			productId=id;
			name=n;
			price =p;
			stock=s;
			reviews=new LinkedList();
		}

public void addReview(Reviews r) {
    reviews.add(r);
}


	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public LinkedList<Reviews> getReviews() {
		return reviews;
	}
	
	 public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String toString() {
		
		return "ID: "+productId+", Name: "+name+", Price: "+price+"SR, Stock: "+stock;
				
	}

	
	 
	
}
