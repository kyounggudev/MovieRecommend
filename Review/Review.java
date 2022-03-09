package Review;
import Movie.Movie;
import People.User;

public class Review {
	public User id;
	public Movie movie;
	public double rating;
	public String content;
	protected String writtenDate;
	/*내재되어있는 리뷰 */
	public Review(User id, Movie movie, double rating, String content, String writtenDate) {
		this.id = id;
		this.movie = movie;
		this.rating = rating;
		this.content = content;
		this.writtenDate = writtenDate;
	}
	/*새로운 리뷰 */

	public String printReview_GUI(User user, ReviewBook rb) {
		String a= (id.name+"("+id.loadGrade(user, rb)+") /평점: "+ rating+" /작성일자 :"+writtenDate+"\n"+content+"\n");
		return a;
	}
}
