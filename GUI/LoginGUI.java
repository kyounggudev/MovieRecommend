package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.UserBtnGUI;
import Movie.Movie;
import Movie.MovieBook;
import People.Admin;
import People.People;
import People.User;
import Review.Review;
import Review.ReviewBook;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/* ���� - �α��� ȭ�� */

public class LoginGUI extends JFrame implements ActionListener {

	private JLabel idLabel = new JLabel("���̵� ");				// ���̵�
	private JTextField idField = new JTextField(19);			// ���̵� �Է� �ʵ�
	private JLabel pwLabel = new JLabel("��й�ȣ ");				// ��й�ȣ
	private JPasswordField pwField = new JPasswordField(19);	// ��й�ȣ �Է� �ʵ�
	private JButton logBtn = new JButton("Login");				// �α��� ��ư
	private ArrayList<User> users;
	private ArrayList<Admin> admins;
	private int result=0;
	private MovieBook mb;
	private ReviewBook rb;
	public static User user=null;
	public static Admin admin=null;

	public static void main(String[] args) {
		MovieBook mb = new MovieBook();
		ReviewBook rb = new ReviewBook();
		ArrayList actors1 = new ArrayList();
		ArrayList actors2 = new ArrayList();
		actors1.add("������");
		actors1.add("�����");
		actors2.add("����");
		actors2.add("������");
		actors2.add("������");
		actors2.add("�����");
		Movie m1 = new Movie("���˵���", "������", actors1, "2017-19-03"
				, "�׼�", "2004�� ��� �Ͼ�󿡼� �Ѿ�� �ܼ��� ���� �������� ����ϰ� ���� ������ ������ ������� ���� ��Ȳ����(������ ��)������ �����ϸ� ���� �ϴ��� �ְ��ڷ� �޺λ��� ������������� �Ƕ��� ���� ����þ(����� ��)��. ���ѹα��� ����� ����þ(����� ��)�� �ϴ��� ��� ���� ���� �ָ� �ѹ����� ������ ��ȭ�� ������ �� �������� ��������(������ ��)���� �ΰ��� ��ġ�� ����� ���� �����ϸ�(�ֱ�ȭ ��)�� ������ �̲��� ���¹��� ���� ����� �ѹ濡 ������� ��.¯.��.��. ������ ����µ��� �����ϰ�! ȭ���ϰ�! ����ϰ�! ���� ��� ������� ���¹� ������� ������������������ ���۵ȴ�!", 6800000);
		Movie m2 = new Movie("�λ���", "����ȣ", actors2, "2016-07-20"
				, "�׼�", "��ü�Ҹ��� ���̷����� �������� Ȯ��ǰ� ���ѹα� ����糭�溸���� ������ ���, ������ ���� ���� ������� �� �ϳ��� ������ ���� �λ���� ��ư��� ���� ġ���� ������ ���̰� �ȴ�. ���￡�� �λ������ �Ÿ� 442KM ��Ű�� ����, ���Ѿ߸� �ϴ� ������� ������ ����!", 11570000);
		mb.addMovie("1", m1);
		mb.addMovie("2", m2);
		ArrayList actors3 = new ArrayList();
		actors3.add("�̺���");
		actors3.add("������");
		actors3.add("������");
		actors3.add("������");
		actors3.add("����");
		Movie m3 = new Movie("��λ�", "������", actors3, "2019-12-19"
				,"���", "���ѹα� ���� ����� �ִ� �Ը��� ��λ� ���� �߻�. ���۽����� �糭�� �ѹݵ��� ���İ��� �ƺ��ȯ�� �ǰ�, ���� �� ��θ� �����ų �߰� ������ �����ȴ�. ��� ������ �糭�� ���� ���� �������桯(������)�� ��λ� ������ ������ �� ������ ���� ����������(������)�� �̷п� ���� ������ ��ȹ�ϰ�, ������ �յ� Ư���� EOD ���� ������â��(������)�� ���� ���� ����� �ɸ� ��� ������ ���Եȴ�. ������ Ű�� �� ���� ���º� �Ҽ� �ϱ� �ڿ� ��������(�̺���)�� ������ ������ ����â��. ������ �������� ���� �� �� ���� �ൿ���� ����â���� ����ϰ� �����. ����, ����â���� ���ѿ��� �������� ������ ���Ե� ��ǵ� �� ä ���￡ Ȧ�� ���� ����������(�����)�� �糭�� �¼� ��Ƴ��� ���� �������ϰ� �� ����, ��λ� ������ ���߱����� �ð��� ���� ����� ���µ���!",8250000);
		mb.addMovie("3", m3);
		
		ArrayList actors4 = new ArrayList();
		actors4.add("���·�");
		actors4.add("���ϴ�");
		actors4.add("������");
		actors4.add("�̵���");
		actors4.add("����");
		Movie m4 = new Movie("��������", "�̺���", actors4, "2019-01-23"
				,"�ڹ̵�", "��ö�־� �޸��� �������� ������ �ٴ�, �ޱ�� ��ü ���⸦ �´� �����! �� �̻� ������ ���� ���� ���� ���� ������� ���� ���������� ���� ���� �й��� ��Ȳ�� �����ϰ� ������, ������, ��ȣ, ���Ʊ��� 4���� ������� �Բ� �ẹ ���翡 ������. ������� 24�ð� ���ø� ���� ���������� ����Ʈ �� ġŲ���� �μ��� ���� â���� �ϰ� �ǰ�, ����� ����̰��� ���� �������� ���� ������� ġŲ���� �Ͼ� �������� �Լҹ��� ���� �����Ѵ�. ����� ����, ġŲ���� ���� �� �� ���� �ٺ��� ����ݿ��� ��� �� ��ȣ�� ��ȸ�� ã�ƿ��µ��� ������ ���� ���ΰ�, ���� ���� ���ΰ�!",16260000);
		mb.addMovie("4", m4);

		ArrayList actors5 = new ArrayList();
		actors5.add("������");
		actors5.add("����");
		Movie m5 = new Movie("����Ʈ", "�̻��", actors5, "2019-07-31"
				,"�׼�", "���б� ��� ���Ƹ� ���̽� ��������� ���� �� �� ��° ��� ���з� ��ĩ�丸 �Դ� �볲�� �� ������ ������ ��Ӵ��� ĥ�� ��ġ���� ��ȸ�� �������� ����� ���Ƹ� �Ĺ� ���ָ� ������ ����� ��ȸ�� ���, ĥ�� ��ġ�� �����ʹ� �� �ǹ��� ���Ⱑ �������� �Ǿ� ������ ���� ���� ���� ���İ��� ���� ��ü�� ���������� �ڵ��� �ϴ�ȥ���� �۽��̰� �ȴ�. �볲�� ���ִ� ��� ���Ƹ� ���� �׾� �״� ��� ü�°� ��ų�� ������ Ż���� ���� ������ �����ϱ� �����ϴµ���",9420000);
		mb.addMovie("5", m5);
		
		ArrayList actors6 = new ArrayList();
		actors6.add("������");
		actors6.add("������");
		actors6.add("������");
		actors6.add("�����");
		actors6.add("�赿��");
		Movie m6 = new Movie("�Ű��Բ�-�˿� ��", "���ȭ", actors6, "2017-12-20"
				,"��Ÿ��", "���� ���� ���ϸ�, ��� �ΰ��� ���� 49�� ���� 7���� ������ ���ľ߸� �Ѵ�. ����, ����, ����, ����, ���, ����, õ�� 7���� �������� 7���� ������ ������ ����� ���ڸ��� ȯ���Ͽ� ���ο� ���� ������ �� �ִ�. ������ȫ ������, ���� ���� ��� ������ ����ϼ̽��ϴ١� ȭ�� ��� ���忡�� ���ھ��̸� ���ϰ� ������ ������ �ҹ�� ��ȫ, ���� �տ� �������� �ؿ��ư� ������ ��Ÿ����. �ڽ��� ������ ���� �ϱ����� �ʴµ� ������ ���Ƿο� �������� �����̶�� �׸� ġ�Ѽ����. �������� ���� �Ա�, �ʱ������� �׸� ��ٸ��� �� �� ���� ���� ����, �״� ������� �������� ������ ��ȫ�� �޾�� �� 7���� ���ǿ��� ��ȣ�� �þ��� ��ȣ���̱⵵ �ϴ�. �����տ��� õ�� ���� 49���� ���ڸ� ȯ����Ű�� �ڽŵ� ���� �ΰ����� ȯ������ �ְڴٴ� ����� ���� �������, �׵��� �ڽŵ��� ��ȣ�ϰ� ȣ���ؾ� �ϴ� 48��° �������� 19�� ���� ��Ÿ�� �Ƿο� ���� ��ȫ�� ȯ���� Ȯ��������, �� �������� ��ȫ�� ���Ű� �ϳ� �Ѿ� �巯���鼭 ����ġ ���� ���� �´ڶ߸��µ��� ������ ������ �ƹ��� �� �� ���� ��, ���ο� ������ ���� ������!",14420000);
		mb.addMovie("6", m6);
		
		ArrayList actors7 = new ArrayList();
		actors7.add("�۰�ȣ");
		actors7.add("�����");
		actors7.add("������");
		actors7.add("��γ�");
		actors7.add("��Ƽ�");
		Movie m7 = new Movie("����", "����ȣ", actors7, "2006-07-27"
				,"����", "�޻� ������ ��ȭ�ο� �Ѱ� ��ġ �ƹ���(�����)�� ��ϴ� �Ѱ� ����, �þ����� ���� �ڴ� ����(�۰�ȣ)�� ��ῡ �鸮�� ���ƺ������ �Ҹ��� ���� �Ͼ��. ���� ���л��� �� �� ����(��Ƽ�)�� �ܶ� ȭ�� ���ִ�. �������⵵ â���� ������ �ڵ�����, �кθ� ���� ������ �� ���� ǳ��� �� ����(������)�����̴�. ���δ� ��� ���� ��и��� ��� �� ������ ���� ��� �Ŷ�� �׸��� ���� ���δ�. �׷��� ������ ��ū���� ��, �� ���۵� ���(��γ�)�� ����ü�� ��ð�⿡ ������ ������. �װ����� ������ ��Ÿ����. �Ѱ� ��ġ�� ��¡�� ����� ���� ����, �쿬�� �������� ���ִ� ����� �ӿ��� Ư���� ������ ����ϰ� �ȴ�. ���� ���� ���� ���𰡰� �Ѱ��ٸ��� �Ŵ޷� �����̴� ���̴�. ������� ���� �ű����ϸ� �ڵ���, ��ī�� ���� ���� �����. �׷��� �װ͵� ��á� ��ü�� �� �� ���� ������ ��ġ ���� �ö�� ������� ��ħ���� ��ƹ�����, �������� ������ �����Ѵ�. ���İ��� �Ƽ��������� �����ϴ� �Ѱ���. ���ε� �ڴʰ� �� ������ ������ ���� ���� ����������, ����� ������ ������� ����� �ӿ���, �� ��Ҵ� ������ ���� ��ġ�� ����. �� ���� ������ ��ٷȴٴ� ���� ������ ����ä ������ �Ѱ����� �������. ��򰡿� ���� ������ �ݵ�� ã�ƾ� �Ѵ�. ���۽��� ������ �������� �Ѱ��� ��� ���ǰ�, ���� ��ü�� ����ȴ�. �Ϸ��ħ�� ���� ����, �׸��� ���� ������ �������� ��� ���� �Ұ� �� ���� ������ ���� ���� ���� ���� �׵��� �ƹ��� �������� ������, ���豸������ ������ �Ѱ� ��򰡿� ���� ������ ã�� ������.",10910000);
		mb.addMovie("7", m7);
		
		ArrayList actors8 = new ArrayList();
		actors8.add("�۰�ȣ");
		actors8.add("�̼���");
		actors8.add("������");
		actors8.add("�ֿ��");
		actors8.add("�ڼҴ�");
		Movie m8 = new Movie("�����", "����ȣ", actors8, "2019-05-30"
				,"���", "��������� �� �� ���������� ���̴� ���� ����(�۰�ȣ) ����. �峲 ���(�ֿ��)���� ����� ģ���� ������� �� ��� ���� �ڸ��� ��ó�� ��ư ���������� ����̴�. �� ������ ����� ��� �ӿ� �ڻ���(�̼���) ������ ���ϴ� ���. �۷ι� IT��� CEO�� �ڻ����� ���ÿ� �������� ���� �Ƹ��ٿ� ���� ����(������)�� ��츦 �����Ѵ�. �׷��� �̷��� ���۵� �� ������ ���� �ڷ�, ������ �� ���� ����� ��ٸ��� �־����ϡ�",10310000);
		mb.addMovie("8", m8);
		
		ArrayList actors9 = new ArrayList();
		actors9.add("ũ���� ���ݽ�");
		actors9.add("�۰�ȣ");
		actors9.add("���� �ظ���");
		actors9.add("�� ��Ʈ");
		actors9.add("��Ƽ�");
		Movie m9 = new Movie("��������", "����ȣ", actors9, "2013-08-01"
				,"SF", "��� �̺����� ��� ���� �ǲ� ������ ����. ��Ƴ��� ������� �¿� ���� �� �밡 ������ �˵��� �޸��� �ִ�. ��� ����� ������� �ٱ۴�� ��α� ���� �� ������ ����ĭ, �׸��� ���õ� ������� ���� ������� ���� ȣȭ�ο� ������ �߱��� �ִ� ����ĭ. ���� ���� ������ ���� ������� �ʴ�. ������ �޸��� ������ 17�� °, ����ĭ�� ���� ������ ĿƼ���� �� ���� �غ��� �� ������ ����Ų��. ������ ������ ������ ���, ����ĭ�� �ع��Ű�� ��ħ�� ���� ��ü�� �ع� ��Ű�� ���� ����Ƿ��� �����尡 ���縮�� �ִ� �� ���� ����ĭ�� ���� �����ϴ� ĿƼ���� ����ĭ �����. �׵� �տ� ����ġ ���� ��Ȳ���� ��ٸ��� �ִµ���",9350000);
		mb.addMovie("9", m9);
		
		ArrayList actors10 = new ArrayList();
		actors10.add("���¿�");
		actors10.add("������");
		actors10.add("������");
		actors10.add("������");
		actors10.add("������");
		Movie m10 = new Movie("Ÿ¥", "�ֵ���", actors10, "2006-09-28"
				,"����", "�������忡�� ���ϸ� ������ ���� ��� ��ϴ� ���к��� ������ ����� ���� ���� �켱�� ���� õ������ û��! ��� �� ��ϴ�, �������� �� �ҿ��� �ڹ��� ������ ���̴� ȭ���ǿ� ���� �ȴ�. �������� ȭ���� ���̴� '����' �� ��! ������ ��ϴ� �� �ǿ��� ��� ���� ��Ƶξ��� �� ���θ� ������ ����. �װ��� �������ڲ� Ÿ¥���� ¥�� ģ ���̾��� ����� �ڴʰ� �� ��ϴ� �ڹ��� ������ ã�� ������, �������� �ú� ���� �� â���� �쿬�� �� �ʿ�ó�� ������ Ÿ¥ ������� ������. �׸��� �Ҿ��� ���� �ټ� �踦 ���� ȭ���� �׸��ΰڴ� ����� �ϰ�, �׿� �Բ� �������� �ɽο� ���� ������ ���� ����濡 ������. ���� Ÿ¥�� ��� �� ���! ������ ��������� ���� �� �������� ��, ������ �������� �Ұ� �ް� ���� ���ο��Լ� ����ġ ���� �ºο�� ����� ����� ������ �ȴ�. ��ϴ� �������� �̸� ������ �� �ǿ��� ū ���� ���� �ǰ�, �ᱹ Ŀ�� ���� ����� �̱��� ���� ä �������� ����� ���� ����. ��������� ȭ���� �����λ�, �������� ������� ���� ���. �������� ������ ������ ������ ������ �λ縦 ���� ��ϴ� �� ���������� �ؾǹ����� �������� ������ Ÿ¥�� �Ʊ͸� ��ġ�� ������. ���� ��ϴ�, �������� �������� ������ �� ȭ���ǿ��� ��������� �Դ����� ���� ���� ������ ������, ������ �ܼ��� ���ϴ� �� �׿� �Բ� �������� ������ �ȴ�. ������ ��Ͽʹ� �޸� ���� ���� ��ŭ�� ���� �ȴٴ� ������ ���ε��� �ΰ��� ��ġ�� Ÿ¥! ���� ȯ���� ȣ���� �ڶ��ϸ� ������ ȭ������ �۾���. �Բ� ������ �ٸ� ������ �����λ��� �ٷ����� ��Ͽ� ����. ���� �� �쿬�� �鸰 �� �������� ��ϴ� �������� ȭ���� ������ ���� ù���� ���ο��� �������� �Ѿ��� ������ Ÿ¥�� �λ��� ����� �׸� ���� ���� �ƴϴ�. ����, ��ϴ� �ڽ��� �� ���迡 �� ��װ� �� �庻�� �ڹ����� �׸� �����ϴ� �ι� ��ö���� ã�� �ǰ�, ���� ���� ���� �� �� ������ �����Ѵ�. ������ ��ö���� ���ϴ� ������ ���� ������ ���� �ƱͿ��� ������ û�ϰ�, �Ʊʹ� ��Ͽ��� ������ ���� �������� �̳��� ��Ͽ� ������ ȭ�������� ������δ�. ���������� ���ƴ� �Ʊ͸� ����س��� �װ��� '������ �� ��'�̶� ���� ������ ���. ������ ��ϴ� �̸� �������� �ʴ´�. ������ ������ �Ѹ�ģ ä, �׸��� ó������ ����� ���� �޲ٰ� �� ���� ȭ������ ����� �ڷ� �� ä, ��ϴ� �׷��� ������ ���� ������ �迡 ������ �����µ�.... ������ �� ���� �ɵ��� ����..! ������ ���Ѱ� ���, �׸��� ������ ���, �� ��� ���� �ھ�Ų �� ���� ���۵ȴ�..! \"�̳� �͵�, ����� �͵� ����. ���� �ƴ� ��� ����� �׷��� ��ó��, �������� ���� ��ġ�ų� �״´�. �װ� Ÿ¥�̴ϱ�...",5680000);
		mb.addMovie("10", m10);
		
		ArrayList actors11 = new ArrayList();
		actors11.add("������");
		actors11.add("������");
		actors11.add("�̼���");
		actors11.add("������");
		actors11.add("������");
		Movie m11 = new Movie("�Ϻ��� Ÿ��", "�����", actors11, "2018-10-31"
				,"���", "�츮 ���� �� �� �غ���? �ٵ� �ڵ��� �÷��� ���� �Դ� ���� ���� ��� �� �����ϴ� �ž� ��ȭ, ����, ī��, �̸��� �� �� ���� ��! �������� Ŀ�� ���ӿ��� �� ���� ������ �����Ѵ�. �ٷ� ������ �ڵ����� ���̺� ���� �÷��ΰ� ��ȭ ������� ���ڿ� �̸��ϱ��� ��� �������ڰ� �� ��. ������ ������ �����ϰ� �� �̵��� ����� �ڵ����� ���� ���볪�鼭 ó�� ������ �����ߴ� �Ͱ��� ���� �ٸ� ���ġ ���� �ḻ�� �귯���µ���. ����� ��� ������ ��������!",5290000);
		mb.addMovie("11", m11);
		
		ArrayList actors12 = new ArrayList();
		actors12.add("�ֹν�");
		actors12.add("������");
		actors12.add("������");
		actors12.add("������");
		actors12.add("������");
		Movie m12 = new Movie("���˿��� ����: ���۳�� �����ô�", "������", actors12, "2012-02-02"
				,"����", "�� ���� ������ ������, ���� �����踦 ������! 1982�� �λ�. �ذ�� ���⿡ ó�� �� ������ ������(�ֹν�)�� ���� �� ������ ���λ��� �Ϻ����� �м���, ���������� �� �� �ϱ� ���� �λ� �ִ� ������ ���� ���� ������(������)�� ���� ��´�. �Ӹ� ���� ���� ��� �ָ� ���� ���� ��, �λ��� �����ϴ�! ������ Ź���� �ӱ������� Ư���� ģȭ������ ������ �ŷڸ� ��� �� �����Ѵ�. �ָ� �ѹ��� ����� �κ��� �� ������ �Բ� ���� ���� �λ��� �����ϱ� �����ϰ�, �� ���� �տ� ���� ����� �����ô밡 ��������. �ѹ����� �ǰ� ���� ���� ����� ���� �º�. ���˿��� ���� ������ 1990�� ���˿��� ������ �������� ������ �Ǹ��� ���� ���� �ѹ����� �ǰ� ���� ���� ��� ������ ����� ���۵ȴ�. ��Ƴ��� ���� ���̴� ġ���� ���� �º�, ���Ŀ� ���� �ڴ� ���� ���� �� ���ΰ�?",4720000);
		mb.addMovie("12", m12);
		
		User u1 = new User("id1", "1234", "name1", 23);
		User u2 = new User("id2", "1234", "name2", 25);
		User u3 = new User("id3", "1234", "name3", 25);
		User u4 = new User("id4", "1234", "name4", 25);
		User u5 = new User("id5", "1234", "name5", 25);
		
		Admin a1 = new Admin("aid1", "a1234", "aname1", 1);
		Admin a2 = new Admin("aid2", "a1234", "aname2", 2);

		ArrayList<People> accounts = new ArrayList<People>();
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		
		admins.add(a1);
		admins.add(a2);
		accounts.add(u1);
		accounts.add(u2);
		accounts.add(u3);
		accounts.add(u4);
		accounts.add(u5);
		accounts.add(a1);
		accounts.add(a2);

		Review r1 = new Review(u1, m1, 3.0, "���� ����1", "2020-11-10");
		Review r2 = new Review(u2, m1, 5.0, "���� ����2", "2020-10-10");
		Review r3 = new Review(u3, m2, 1.0, "���� ����3", "2020-11-15");
		Review r4 = new Review(u4, m2, 3.0, "���� ����4", "2020-12-10");
		Review r5 = new Review(u5, m3, 5.0, "���� ����5", "2020-11-22");
		Review r6 = new Review(u1, m3, 1.0, "���� ����6", "2020-12-24");
		Review r7 = new Review(u2, m4, 4.0, "���� ����7", "2020-11-24");
		Review r8 = new Review(u3, m4, 3.0, "���� ����8", "2020-12-30");
		Review r9 = new Review(u4, m5, 2.0, "���� ����9", "2020-11-11");
		Review r10 = new Review(u5, m5, 5.0, "���� ����10", "2020-12-09");
		Review r11 = new Review(u1, m6, 1.0, "���� ����11", "2020-09-02");
		Review r12 = new Review(u2, m6, 2.0, "���� ����12", "2020-10-01");
		Review r13 = new Review(u1, m7, 3.0, "���� ����13", "2020-10-02");
		Review r14 = new Review(u1, m7, 4.0, "���� ����14", "2020-10-03");
		Review r15 = new Review(u1, m8, 5.0, "���� ����15", "2020-10-04");
		Review r16 = new Review(u1, m8, 5.0, "���� ����16", "2020-10-05");
		Review r17 = new Review(u1, m9, 4.0, "���� ����17", "2020-10-06");
		Review r18 = new Review(u1, m9, 3.0, "���� ����18", "2020-10-07");
		Review r19 = new Review(u1, m10, 2.0, "���� ����19", "2020-10-08");
		Review r20 = new Review(u3, m10, 1.0, "���� ����20", "2020-10-09");
		Review r21 = new Review(u4, m11, 2.0, "���� ����21", "2020-10-10");
		Review r22 = new Review(u5, m11, 3.0, "���� ����22", "2020-11-05");
		Review r23 = new Review(u5, m12, 4.0, "���� ����23", "2020-12-05");
		Review r24 = new Review(u4, m12, 5.0, "���� ����24", "2020-01-05");
		
		rb.addReview(r1); rb.addReview(r2); rb.addReview(r3); rb.addReview(r4);rb.addReview(r5);rb.addReview(r6);rb.addReview(r7);rb.addReview(r8);rb.addReview(r9);rb.addReview(r10);
		rb.addReview(r11);rb.addReview(r12);rb.addReview(r13);rb.addReview(r14);rb.addReview(r15);rb.addReview(r16);rb.addReview(r17);rb.addReview(r18);rb.addReview(r19);rb.addReview(r20);
		rb.addReview(r21);rb.addReview(r22);rb.addReview(r23);rb.addReview(r24);
		m1.average=rb.getAverage(mb, m1);
		m2.average=rb.getAverage(mb, m2);
		m3.average=rb.getAverage(mb, m3);
		m4.average=rb.getAverage(mb, m4);
		m5.average=rb.getAverage(mb, m5);
		m6.average=rb.getAverage(mb, m6);
		m7.average=rb.getAverage(mb, m7);
		m8.average=rb.getAverage(mb, m8);
		m9.average=rb.getAverage(mb, m9);
		m10.average=rb.getAverage(mb, m10);
		m11.average=rb.getAverage(mb, m11);
		m12.average=rb.getAverage(mb, m12);
		
		m1.reviewCount=rb.countReview(mb, m1);
		m2.reviewCount=rb.countReview(mb, m2);
		m3.reviewCount=rb.countReview(mb, m3);
		m4.reviewCount=rb.countReview(mb, m4);
		m5.reviewCount=rb.countReview(mb, m5);
		m6.reviewCount=rb.countReview(mb, m6);
		m7.reviewCount=rb.countReview(mb, m7);
		m8.reviewCount=rb.countReview(mb, m8);
		m9.reviewCount=rb.countReview(mb, m9);
		m10.reviewCount=rb.countReview(mb, m10);
		m11.reviewCount=rb.countReview(mb, m11);
		m12.reviewCount=rb.countReview(mb, m12);
		//�ʱⰪ �Է� ��
		int result =0;
		LoginGUI a = new LoginGUI(users, admins,mb,rb);


	}

	public LoginGUI(ArrayList<User> users, ArrayList<Admin> admins, MovieBook mb, ReviewBook rb) {
		this.users=users;
		this.admins=admins;
		this.mb = mb;
		this.rb = rb;
		setTitle("��ȭ ���� & ��õ");		// ����
		setVisible(true);				// �����츦 ȭ�鿡 ǥ��
		setSize(310,200);				// ũ��
		setLocationRelativeTo(null);	// �����찡 ȭ�� �߾ӿ��� ����
		setResizable(false);			// ������ ũ�� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// ����
		logBtn.addActionListener(this);				// ��ư ��� ����
		createWindow();				// ������Ʈ�� �����쿡 ��ġ
	}

	public User getUser() {
		return user;
	}
	public Admin getAdmin() {
		return admin;
	}
	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(20,10,10,30));
		panel.add(createCenter());
		add(panel);
	}

	private JPanel createCenter() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(idLabel);		// ���̵�
		panel.add(idField);		// ���̵� �Է�
		panel.add(pwLabel);		// ��й�ȣ
		panel.add(pwField);		// ��й�ȣ �Է�
		panel.add(logBtn);			// �α��� ��ư
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		for(User log : users) {
			if(log.id.equals(idField.getText()) && log.pw.equals(pwField.getText())) {
				JOptionPane.showMessageDialog( null, "�α��ο� �����߽��ϴ�." );
				setVisible(false);				// ���ο� â�� ���鼭 ���ÿ� �α��� â�� ����
				user=log;
				result=1;
				new UserBtnGUI(user,mb,rb);
				break;
			}
		}
		for(Admin log : admins) {
			if(log.id.equals(idField.getText()) && log.pw.equals(pwField.getText())) {
				JOptionPane.showMessageDialog( null, "������ �α��ο� �����߽��ϴ�." );
				setVisible(false);				// ���ο� â�� ���鼭 ���ÿ� �α��� â�� ����
				admin=log;
				result=2;
				new AdminBtnGUI(admin,mb,rb);
				break;
			}
		}
		if(result==0){
			JOptionPane.showMessageDialog( null , "���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�. �ٽ� �α��� ���ּ���.");
			/* �α��� ���н� �ؽ�Ʈ�ʵ忡 �Է��ߴ� ������ ���� */
			idField.setText(null);	// �ʵ忡 �Է��� ���̵� ����
			pwField.setText(null);	// �ʵ忡 �Է��� �н����� ����
			result=3;
		}
	}
}



