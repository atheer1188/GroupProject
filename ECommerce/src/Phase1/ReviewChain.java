package Phase1;

import java.util.Scanner;

public class ReviewChain {
public static LinkedList<Reviews> reviews = new LinkedList<Reviews>() ;
public static Scanner read = new Scanner(System.in);

public static LinkedList<Reviews> getReviews() {
	return reviews;
}

public Reviews addReview(int customerID, int productID) {
	System.out.println("How High would you rate this Product from 1->5:");
	int rate = read.nextInt();
	if(rate>5 || rate<1) {
		while(rate>5 || rate<1) {
			System.out.print("Choose a number in the range(1-->5)");;
			rate = read.nextInt();
		}}//check
	System.out.println("Comment what you think about this product:");
	String comment = read.nextLine();
	comment = read.nextLine();//check garbage
    Reviews review = new Reviews(customerID,productID,rate, comment);
    reviews.add(review);
    return review;
		}




}
