package Movie;
import java.util.ArrayList;

public class Movie {
	public String title;
	public String director;
	public ArrayList actor;
	public String release;
	public String genre;
	public String plot;
	public int reviewCount;
	public double average;
	public int audience;
	
	public Movie(String title, String director, ArrayList actor, String release
			, String genre, String plot, int audience) {
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.release = release;
		this.genre = genre;
		this.plot = plot;
		this.audience = audience;
	}

	public String getMovieList1_GUI(String code) {
		String b="";
		b = b+("타이틀: " + title + " /감독: " + director + "\n배우: " + actor + " /개봉일 : " + release + "\n장르 : " + genre + " /평점: " + average + " /관객 수: " + audience);
		b= b+("\n----------------------------------------\n");
		return b;
	}
	public String getMovieList2_GUI(String code) {
		String a="";
		if(code!=null)
			a=a+("코드: "+code+"\n");  //관리자모드시 코드 추가 출력
		a = a+ ("타이틀: " + title+"\n감독: " + director+"\n배우: " + actor+"\n개봉일 : " + release+"\n장르 : " + genre+"\n줄거리: " + plot+"\n리뷰 개수 : " + reviewCount+"\n평점: " + average+"\n관객 수: " + audience);
		return a;
	}
	
}
