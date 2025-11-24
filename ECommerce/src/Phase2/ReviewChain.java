package Phase2;

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

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String [] data = line.split(",");
            if(data.length >= 5) {
            int rvwId =Integer.parseInt(data[0].trim());
            int productid = Integer.parseInt(data[1].trim());
            int customerid = Integer.parseInt(data[2].trim());
            int rating = Integer.parseInt(data[3].trim());
            String comment = data[4].trim();

            Reviews r = new Reviews(rvwId , customerid , productid , rating , comment);
            reviews.add(r);
        }
        }
        scan.close();
        System.out.println("Reviews loaded successfully!");

    } catch (Exception e) {
        System.out.println("Error while loading Reviews: " + e.getMessage());
    }
}


//------------------------------------------------------------------
public Reviews addReview(int customerID, int productID) {
	
	System.out.println("Enter the Reviews ID: ");//1
	int rvwId = read.nextInt();//1
	
	while(searchreview(rvwId)) {//r
		System.out.println("This id already exists, enter a new one: ");//r
		 rvwId = read.nextInt();//r
	}
	System.out.println("How High would you rate this Product from 1->5:");//1
	int rate = read.nextInt();//1
	if(rate>5 || rate<1) {//1
		while(rate>5 || rate<1) {//?
			System.out.print("Choose a number in the range(1-->5)");//1
			rate = read.nextInt();//1
		}}//check
	System.out.println("Comment what you think about this product:");//1
	String comment = read.nextLine();//1
	comment = read.nextLine();//check garbage //1
    Reviews review = new Reviews(rvwId,productID , customerID ,rate, comment);//?
    reviews.add(review);//1
    return review;//1
		}//O(r)

	//------------------------------------------------------------------
public boolean editReview(int rvwID) {
	
	if(searchreview(rvwID)) {
		
		
		System.out.println("How High would you rate this Product from 1->5:");
		int rate = read.nextInt();
		if(rate>5 || rate<1) {
			while(rate>5 || rate<1) {
				System.out.print("Choose a number in the range(1-->5)");;
				rate = read.nextInt();
			}}//check
		reviews.retrieve().setRating(rate); // rate edited
		
		System.out.println("Comment what you think about this product:");
		String comment = read.nextLine();
		read.nextLine();//check garbage
		reviews.retrieve().setComment(comment);
		
		System.out.println("Review edited succesfully!");
		return true;		
	}
	else {
		System.out.println("Sorry we couldn't find this review");
		return false;
	}
}


//--------------------------------------------------------------------
public boolean searchreview(int id){
boolean found = false;
if(reviews.empty())
	return found;

else {
	reviews.findfirst();
	for(int i = 0 ; i<reviews.size() ; i++) {
		if(reviews.retrieve().getReviewID()==id)
		{
			found=true;
			break;
		}
		reviews.findnext();
	}
		return found;	
}
}
//----------------------------------------------------------shaden

public LinkedList<Reviews> searchReviewsByCustomer(int customerId) {
    LinkedList<Reviews> out = new LinkedList<Reviews>();           // O(1)
    if (reviews.empty()) return out;                              // O(1)
    reviews.findfirst();                                          // O(1)

    for (int i = 0; i < reviews.size(); i++) {                    // O(n)
        Reviews r = reviews.retrieve();                           // O(1)

        if (r.getCustomerID() == customerId)                      // O(1)
            appendToEnd(out, r);                                  // O(m) worst-case, O(1) avg

        if (reviews.last()) break;                                // O(1)
        reviews.findnext();                                       // O(1)
    }

    return out;                                                   // O(1)
}



	//Overall Time Complexity: O(n), where n = total number of reviews in the system.
    //Space Complexity: O(m), where m = number of reviews written by the specified customer.


public LinkedList<Reviews> searchReviewsByProduct(int productId) {
    LinkedList<Reviews> out = new LinkedList<Reviews>();
    if (reviews.empty()) return out;
    reviews.findfirst();
    for (int i = 0; i < reviews.size(); i++) {
        Reviews r = reviews.retrieve();
        if (r.getProductID() == productId) appendToEnd(out, r);
        if (reviews.last()) break;
        reviews.findnext();
    }
    return out;
}

private void appendToEnd(LinkedList<Reviews> list, Reviews r) {
    if (list.empty()) {
        list.add(r);
    } else {
        list.findfirst();
        while (!list.last()) list.findnext();
        list.add(r);
    }
}

}



