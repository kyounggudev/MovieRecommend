package Review;
import Movie.Movie;
import People.User;

public class Review {
	public User id;
	public Movie movie;
	public double rating;
	public String content;
	protected String writtenDate;
	/*����Ǿ��ִ� ���� */
	public Review(User id, Movie movie, double rating, String content, String writtenDate) {
		this.id = id;
		this.movie = movie;
		this.rating = rating;
		this.content = content;
		this.writtenDate = writtenDate;
	}
	/*���ο� ���� */

	public String printReview_GUI(User user, ReviewBook rb) {
		String a= (id.name+"("+id.loadGrade(user, rb)+") /����: "+ rating+" /�ۼ����� :"+writtenDate+"\n"+content+"\n");
		return a;
	}
}
