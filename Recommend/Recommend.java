package Recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import Movie.MovieBook;
import Movie.Movie;
import People.User;
import Review.Review;
import Review.ReviewBook;

public class Recommend {
	public static String recommendList_GUI(User user,MovieBook mb,ReviewBook rb) {
		String a=("추천장르: "+mostGenre(user, mb, rb)+"/ 추천감독: "+mostDirector(user, mb, rb)+"/ 추천배우: "+mostActor(user,mb,rb)+"\n\n");
		HashSet<Movie> recommends = new HashSet<Movie>();
		for(Movie m : mb.movieMap.values()) {
			if(m.genre.equals(mostGenre(user,mb,rb))) {
				recommends.add(m);
			}
		}
		for(Movie m : mb.movieMap.values()) {
			if(m.director.equals(mostDirector(user,mb,rb))) {
				recommends.add(m);
			}
		}
		for(Movie m : mb.movieMap.values()) {
			if(m.actor.contains(mostActor(user,mb,rb))) {
				recommends.add(m);
			}
		}
		for(Movie m : mb.movieMap.values()) {
			for(Review r : rb.userReview(user)) {
				if(r.movie==m)
					recommends.remove(m);
			}
		}
		Iterator<Movie> iter = recommends.iterator();
		while(iter.hasNext()) {
			String b= iter.next().getMovieList1_GUI(null);
			a=a+b;
		}
		return a;
	}
	
	
	public static String mostGenre(User user, MovieBook mb,ReviewBook rb) {
		String mostGenre=null;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Review> temp_list=rb.userReview(user);
		for(int i=0; i<temp_list.size();i++) {
			list.add(temp_list.get(i).movie.genre);
		}
		HashMap<String, Integer> duplicate_count = new HashMap<String, Integer>();
		for(int i=0;i<list.size(); i++) {
			if(duplicate_count.containsKey(list.get(i))) { //같은 장르가 이미 존재
				duplicate_count.put(list.get(i), duplicate_count.get(list.get(i))+1);
			}else {  //존재하지 않는다
				duplicate_count.put(list.get(i), 1);
			}
		}
		List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(duplicate_count.entrySet());
		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
			{
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		mostGenre=list_entries.get(0).getKey();
		return mostGenre;
	}
	public static String mostDirector(User user, MovieBook mb,ReviewBook rb) {
		String mostDirector=null;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Review> temp_list=rb.userReview(user);
		for(int i=0; i<temp_list.size();i++) {
			list.add(temp_list.get(i).movie.director);
		}
		HashMap<String, Integer> duplicate_count = new HashMap<String, Integer>();
		for(int i=0;i<list.size(); i++) {
			if(duplicate_count.containsKey(list.get(i))) { //같은 장르가 이미 존재
				duplicate_count.put(list.get(i), duplicate_count.get(list.get(i))+1);
			}else {  //존재하지 않는다
				duplicate_count.put(list.get(i), 1);
			}
		}
		List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(duplicate_count.entrySet());
		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
			{
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		mostDirector=list_entries.get(0).getKey();
		return mostDirector;
	}
	public static String mostActor(User user, MovieBook mb,ReviewBook rb) {
		String mostActor=null;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Review> temp_list=rb.userReview(user);
		for(int i=0; i<temp_list.size();i++) {
			for(int j=0; j<temp_list.get(i).movie.actor.size();j++)
			list.add((String) temp_list.get(i).movie.actor.get(j));
		}
		HashMap<String, Integer> duplicate_count = new HashMap<String, Integer>();
		for(int i=0;i<list.size(); i++) {
			if(duplicate_count.containsKey(list.get(i))) { //같은 장르가 이미 존재
				duplicate_count.put(list.get(i), duplicate_count.get(list.get(i))+1);
			}else {  //존재하지 않는다
				duplicate_count.put(list.get(i), 1);
			}
		}
		List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(duplicate_count.entrySet());
		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
			{
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		mostActor=list_entries.get(0).getKey();
		return mostActor;
	}
}
