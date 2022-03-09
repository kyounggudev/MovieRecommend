package Review;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import Movie.Movie;
import Movie.MovieBook;
import Movie.UnknownMovieException;
import People.User;

public class ReviewBook implements Serializable{
	Scanner scanner = new Scanner(System.in);
	public static ArrayList<Review> reviewList;
	public ReviewBook() {
		reviewList = new ArrayList<Review>();
	}
	
	public void addReview(Review pReview) {
		reviewList.add(pReview);
	}
	

	public String getReview_GUI(MovieBook mb, Movie movie,User user, ReviewBook rb){
		String a="";
		ArrayList<Review> MovieReview = new ArrayList<Review>();
		for(int i=0; i<reviewList.size(); i++) {
			if(reviewList.get(i).movie.equals(movie)) {
				a=a+reviewList.get(i).printReview_GUI(reviewList.get(i).id,rb);
			}
		}
		a=a+("------리뷰 끝 ------");
		return a;
	}
	
	
	public int countReview(MovieBook mb, Movie movie) {
		int count =0;
		for(int i=0; i<reviewList.size(); i++) {
			if(reviewList.get(i).movie.equals(movie)) {
				count++;
			}
		}
		return count;	
	}
	public static int countReview(User user) {
		int count =0;
		for(int i=0; i<reviewList.size(); i++) {
			if(reviewList.get(i).id.equals(user)) {
				count++;
			}
		}
		return count;	
	}
	public ArrayList<Review> userReview(User user) {
		ArrayList<Review> list = new ArrayList<Review>();
		for(int i=0; i<reviewList.size(); i++) {
			if(reviewList.get(i).id.equals(user)) {
				list.add(reviewList.get(i));
			}
		}
		return list;	
	}
	public double getAverage(MovieBook mb, Movie movie) {
		double average=0;
		double count=0;
		for(int i=0; i<reviewList.size(); i++) {
			if(reviewList.get(i).movie.equals(movie)) {
				average += reviewList.get(i).rating;
				count ++;
			}
		}
		if(average!=0) {
			average = average/count;	
		}
		return average;
	}
	
	public void deleteReview_admin_GUI(String Deleted,Movie movie, ReviewBook rb) throws UnknownReviewException{
		Review removedReview=null;
		for(Review r : reviewList) {
			if(r.id.name.equals(Deleted)){
				if(r.movie==movie)
					removedReview=r;
			}
		}
		reviewList.remove(removedReview);
		if (removedReview == null) {
			throw new UnknownReviewException(
					"ReviewBook.deleteReveiw(): unknown Review ID: " + Deleted);
		}
	}
	
	public void deleteReview_GUI(Movie movie, User user) {
		Review review=null;
		for(Review r : reviewList) {
			if(r.id == user) {
				if(r.movie==movie) {
					review=r;
					break;
				}
			}
		}
		deleteReview(review);
	}
	
	public void writeReview(Movie movie, User user) {
		System.out.println("평점을 입력하세요(0~5)");
		double rating = scanner.nextDouble();
		while(0>rating || rating>5) {
			System.out.println("평점 값이 범위에 속하지 않습니다. 다시 입력해주세요.");
			rating = scanner.nextDouble();
		}
		scanner.nextLine();
		System.out.println("리뷰 내용을 작성하세요.");
		String content = scanner.nextLine();
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("YYYY-MM-dd");
		String writtenDate = sdformat.format(date);
		Review a = new Review(user, movie, rating, content, writtenDate);
		addReview(a);
	}
	public void reviseReview(Review review) {
		System.out.println("평점을 입력하세요(0~5)");
		double rating = scanner.nextDouble();
		while(0>rating || rating>5) {
			System.out.println("평점 값이 범위에 속하지 않습니다. 다시 입력해주세요.");
			rating = scanner.nextDouble();
		}
		scanner.nextLine();
		System.out.println("리뷰 내용을 작성하세요.");
		String content = scanner.nextLine();
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("YYYY-MM-dd");
		String writtenDate = sdformat.format(date);
		review.rating=rating;
		review.content=content;
		review.writtenDate=writtenDate;
	}
	public void deleteReview(Review review) {
		reviewList.remove(review);
	}
}
