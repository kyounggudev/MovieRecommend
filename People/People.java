package People;
import java.util.ArrayList;
import java.util.Scanner;

import Movie.Movie;

public abstract class People {
	public String id;
	public String pw;
	public String name;
	
	Scanner scanner = new Scanner(System.in);
	  
	public People login(ArrayList<People> accounts) {
		People login_account = null;
	
		while(login_account==null) {
			System.out.println("아이디를 입력하세요.");
			String login_id= scanner.next();
			System.out.println("비밀번호를 입력하세요.");
			String login_pw= scanner.next();
			for(People currentId : accounts) {
				if(currentId.id.equals(login_id)) {
					login_account = currentId;
				}
			}
			if(login_account!=null) {
				if(login_account.pw.equals(login_pw)) {
				} else {
					System.out.println("비밀번호가 틀렸습니다. 다시 로그인 해주세요.");
					login_account = null;
				}
			}else {
				System.out.println("존재하지 않는 아이디입니다. 다시 로그인 해주세요.");
			}
		}
		System.out.println("로그인에 성공했습니다. "+login_account.name+"님 환영합니다.");
		return login_account;
	}
}
