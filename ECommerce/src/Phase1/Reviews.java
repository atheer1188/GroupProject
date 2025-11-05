package Phase1;

public class Reviews {
	public int customerID;
	public int ProductID;
	public int rating;
	public String comment;
	
	public Reviews() {
		this.customerID = 0;
		ProductID = 0;
		this.rating = 0;
		this.comment = " ";
	}
	
	
	
	public Reviews(int customerID, int productID, int rating, String comment) {
		this.customerID = customerID;
		ProductID = productID;
		this.rating = rating;
		this.comment = comment;
	}

	
	
	public void display() {
		System.out.println("Customer ID: "+ customerID);
		System.out.println("Product ID: "+ProductID);
		System.out.println("Rating: "+rating);
		System.out.println("Comment: "+ comment);

	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
