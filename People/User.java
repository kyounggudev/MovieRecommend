package People;

import Review.ReviewBook;

public class User extends People{
	public String grade;
	public int age;
	public int ratingNum;
	public User(String id, String pw, String name, int age) {
		this.id=id;
		this.pw=pw;
		this.name=name;
		this.age=age;
	}
	public static String loadGrade(User user, ReviewBook rb) {
		int count = ReviewBook.countReview(user);
		if(count>=10) {
			return "우수";
		}else
			return "일반";
	}

}