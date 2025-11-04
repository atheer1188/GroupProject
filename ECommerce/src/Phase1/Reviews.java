package Phase1;

public class Reviews {
	private int rating;
	private String comment;
	
	public Reviews(){
		rating = 0;
		comment = null;
	}
	
	public Reviews(int r,String c){
		rating = r;
		comment = c;
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
