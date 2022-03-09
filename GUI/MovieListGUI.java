package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Movie.Movie;
import Movie.MovieBook;
import Movie.UnknownMovieException;
import People.Admin;
import People.User;
import Review.ReviewBook;

/* User -> ��ȭ ����Ʈ ȭ�� */

public class MovieListGUI extends JFrame{
	
	// Top
	private JTextArea ML = new JTextArea();					//��ȭ ����Ʈ �����ִ� ����
	// Bottom
	JScrollPane scrollPane = new JScrollPane(ML);
	private JButton backBtn = new JButton("�ڷΰ���");			// �ڷΰ��� ��ư
	private JButton detailBtn = new JButton("��ȭ ��������");	// ��ȭ �������� ��ư
	private JButton sortBtn = new JButton("�����ϱ�");			// ��ȭ �����ϱ�
	private int result=0;
	private User user;
	private MovieBook mb;
	private ReviewBook rb;
//	public static void main(String[] args) {
//		new MovieListGUI();
//	}
	
	public MovieListGUI(User user, MovieBook mb, ReviewBook rb) {
		result=1;
		this.user=user;
		this.mb = mb;
		this.rb = rb;
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		
		setTitle("��ȭ ���� & ��õ - ��ȭ ����Ʈ");		// ����
		setSize(500,500);							// ũ��
		//setDefaultCloseOperation(EXIT_ON_CLOSE);	// â ������ ���α׷� ����
		addListeners();								// ��ư�� ���� ����� ����
		createWindow();								// ������Ʈ�� �����쿡 ��ġ
		setVisible(true);							// �����츦 ȭ�鿡 ǥ��
		setResizable(false);						// ������ ũ�� ����
		setLocationRelativeTo(null);				// �����찡 ȭ�� �߾ӿ��� ����
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(30,30,10,30));
		//panel.add(createTop(), BorderLayout.NORTH);
		panel.add(createTop());
		panel.add(createBottom(), BorderLayout.SOUTH);
		add(panel);
	}
	
	// ���ʿ� ��ȭ ����Ʈ ��� (��ȭ ����� ���̴� â ����)
	private JPanel createTop() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ML);						// ��ȭ ���
		panel.add(new JScrollPane(ML));		// ��ũ��
		ML.setEditable(false);				// TextArea�� �� �Է� X
		ML.setLineWrap(true);
		ML.append(mb.MovieList_GUI());	
		return panel;
	}
	
	// �Ʒ��ʿ� ��ư ���� (�ڷΰ���, ��ȭ ��������, �����ϱ�)
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(sortBtn);		// �����ϱ� ��ư
		panel.add(detailBtn);	// ��ȭ �������� ��ư
		panel.add(backBtn);		// �ڷΰ��� ��ư
		return panel;
	}

	// ��ư ������ �� �̺�Ʈ ó��
	private void addListeners() {
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
		detailBtn.addActionListener(new DetailedListener());		// ��ȭ �������� ��ư
		sortBtn.addActionListener(new SortListener());				// �����ϱ� ��ư
	}
	
	// �ڷΰ��� ��ư �̺�Ʈ
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();				// ���� â �ݱ�
			
			new UserBtnGUI(user, mb, rb);
		}
	}
	// ��ȭ �������� ��ư �̺�Ʈ
	class DetailedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			new detailedGUI(user, mb, rb);		// �������� ȭ������ �̵�
		}
	}
	// �����ϱ� ��ư �̺�Ʈ
	class SortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new SortGUI(user, mb, rb);
		}
	}
}
