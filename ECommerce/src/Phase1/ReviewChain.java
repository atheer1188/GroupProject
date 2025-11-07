package Phase1;

import java.io.File;
import java.util.Scanner;

public class ReviewChain {
public static LinkedList<Reviews> reviews = new LinkedList<Reviews>() ;
public static Scanner read = new Scanner(System.in);

public static LinkedList<Reviews> getReviews() {
	return reviews;
}
//------------------------------------------------------------------
public ReviewChain(String fileName) {

    try {
        File f = new File(fileName);
        Scanner scan = new Scanner(f);
        String line =scan.nextLine(); // skip header

        while (scan.hasNext()) {
            line = scan.nextLine();
            String [] data = line.split(",");
            int productid = Integer.parseInt(data[0]);
            int customerid = Integer.parseInt(data[1]);
            int rating = Integer.parseInt(data[2]);
            String comment = data[4];

            Reviews r = new Reviews(productid , customerid , rating , comment);
            reviews.add(r);
        }

        scan.close();
        System.out.println("Customers loaded successfully!");

    } catch (Exception e) {
        System.out.println("Error while loading Custeomers: " + e.getMessage());
    }
}

//------------------------------------------------------------------
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
//--------------------------------------------------------------------



}
