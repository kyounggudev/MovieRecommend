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
			System.out.println("���̵� �Է��ϼ���.");
			String login_id= scanner.next();
			System.out.println("��й�ȣ�� �Է��ϼ���.");
			String login_pw= scanner.next();
			for(People currentId : accounts) {
				if(currentId.id.equals(login_id)) {
					login_account = currentId;
				}
			}
			if(login_account!=null) {
				if(login_account.pw.equals(login_pw)) {
				} else {
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �α��� ���ּ���.");
					login_account = null;
				}
			}else {
				System.out.println("�������� �ʴ� ���̵��Դϴ�. �ٽ� �α��� ���ּ���.");
			}
		}
		System.out.println("�α��ο� �����߽��ϴ�. "+login_account.name+"�� ȯ���մϴ�.");
		return login_account;
	}
}
