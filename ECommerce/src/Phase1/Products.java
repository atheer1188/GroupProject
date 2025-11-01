package Product;

public class Products {

	//productId, name, price, stock, list of reviews.
	
	private int productId;
	private String name;
	private double price;
	private int stock;
	private LinkedList<Reviews> reviews;
	
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
	
	 
	 
	
}
