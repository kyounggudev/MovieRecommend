package People;

public class Admin extends People {
	public int right; /*1:���� 2:���� */
	public Admin(String id, String pw, String name, int right) {
		this.id=id;
		this.pw=pw;
		this.name=name;
		this.right=right;
	}
}