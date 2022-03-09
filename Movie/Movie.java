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
		b = b+("Ÿ��Ʋ: " + title + " /����: " + director + "\n���: " + actor + " /������ : " + release + "\n�帣 : " + genre + " /����: " + average + " /���� ��: " + audience);
		b= b+("\n----------------------------------------\n");
		return b;
	}
	public String getMovieList2_GUI(String code) {
		String a="";
		if(code!=null)
			a=a+("�ڵ�: "+code+"\n");  //�����ڸ��� �ڵ� �߰� ���
		a = a+ ("Ÿ��Ʋ: " + title+"\n����: " + director+"\n���: " + actor+"\n������ : " + release+"\n�帣 : " + genre+"\n�ٰŸ�: " + plot+"\n���� ���� : " + reviewCount+"\n����: " + average+"\n���� ��: " + audience);
		return a;
	}
	
}
