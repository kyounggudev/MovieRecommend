package Movie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import People.User;
import Review.Review;
import Review.ReviewBook;
import Review.UnknownReviewException;

public class MovieBook implements Serializable{
	Scanner scanner = new Scanner(System.in);
	public static Map<String, Movie> movieMap;
	public MovieBook() {
		movieMap = new HashMap<String, Movie>();
	}

	public void addMovie(String pMovieID, Movie pNewMovie) {
		movieMap.put(pMovieID, pNewMovie);
	}
	public void newAverage(MovieBook mb,ReviewBook rb) {
		for(Entry<String, Movie> elem : movieMap.entrySet()) {
			Movie movie = elem.getValue();
			movie.average = rb.getAverage(mb, movie);
		}
	}
	public void newCount(MovieBook mb,ReviewBook rb) {
		for(Entry<String, Movie> elem : movieMap.entrySet()) {
			Movie movie = elem.getValue();
			movie.reviewCount = rb.countReview(mb, movie);
		}
	}
	
	public static String MovieList_GUI() {
		
		String a = "";
		for(Entry<String, Movie> elem : movieMap.entrySet()) {
			Movie movie = elem.getValue();
			String code=null;
			a=a+movie.getMovieList1_GUI(code);
		}
		return a;
	}
	
	public String MovieList_admin_GUI(MovieBook mb, ReviewBook rb) throws UnknownMovieException {
		String a = "";
		for(Entry<String, Movie> elem : movieMap.entrySet()) {
			Movie movie = elem.getValue();
			String code="1";
			a=a+movie.getMovieList2_GUI(code);
			a= a+("\n----------------------------------------\n");
		}
		  return a;
	}
	public String detailedMovie_GUI(String detailed, MovieBook mb, ReviewBook rb, User user) throws UnknownMovieException {
		  String detailedCode = (String)mb.getMovieCode(detailed);
		  Movie detailedMovie = mb.getMovie(detailedCode);
		  String a= "";
		  String code=null;
		  a=a+detailedMovie.getMovieList2_GUI(code);
		  a=a+ ("\n------댓글 목록------\n");
		  a=a+rb.getReview_GUI(mb, detailedMovie,user, rb);
		  return a;
	}
	public Movie getMovie(String pMovieID) throws UnknownMovieException{
		Movie foundMovie;
		foundMovie = movieMap.get(pMovieID);
		if(foundMovie != null) {
			return foundMovie;
		} else {
			throw new UnknownMovieException(
					"MovieBook.getMovie(): unknown movie ID:" + pMovieID);
		}
	}

	public String getMovieCode(String movieTitle) {
		String code = null;
		for(Entry<String, Movie> elem : movieMap.entrySet()) {
			String a=(String)elem.getValue().title;
			if(a.equals(movieTitle)) {
				code = (String)elem.getKey();
				break;
			}
		}
		return code;
	}

	public String sortMovieList_GUI(MovieBook mb,String menu) {
		Map<String,Movie> temp = movieMap;
		List<Map.Entry<String, Movie>> sortedList = new LinkedList<>(movieMap.entrySet());
		switch(menu) {
		case "1":
			Collections.sort(sortedList, (o1, o2) -> o1.getValue().title.compareTo(o2.getValue().title));
			break;
		case "2":
			Collections.sort(sortedList, (o1, o2) -> o1.getValue().release.compareTo(o2.getValue().release));
			break;
		case "3":
			Collections.sort(sortedList, (o1, o2) -> Double.valueOf(o1.getValue().average).compareTo(o2.getValue().average));
			break;
		case "4":
			Collections.sort(sortedList, (o1, o2) -> Integer.valueOf(o1.getValue().reviewCount).compareTo(o2.getValue().reviewCount));
			break;
		case "5":
			Collections.sort(sortedList, (o1, o2) -> Integer.valueOf(o1.getValue().audience).compareTo(o2.getValue().audience));
			break;
		default:
		  	break;
		}
		LinkedHashMap<String, Movie> result = new LinkedHashMap<>();
	    for (Map.Entry<String, Movie> entry : sortedList) {
	        result.put(entry.getKey(), entry.getValue());
	    }
	    movieMap = result; // 정렬된 리스트 가져오기
	    String a = mb.MovieList_GUI();
	    movieMap = temp; // 다시 돌아오기
	    return a;
	}
	
}
