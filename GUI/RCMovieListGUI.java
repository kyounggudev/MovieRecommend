package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import GUI.MovieListGUI.BackwardListener;
import GUI.MovieListGUI.DetailedListener;
import GUI.MovieListGUI.SortListener;
import Movie.MovieBook;
import People.User;
import Recommend.Recommend;
import Review.ReviewBook;

/* User - ��ȭ ��õ ����Ʈ ȭ�� */

public class RCMovieListGUI extends JFrame {

	// Top
	private JTextArea RCML = new JTextArea();				// ��ȭ��õ ����Ʈ �����ִ� ����
	// Bottom
	private JButton backBtn = new JButton("�ڷΰ���");			// �ڷΰ��� ��ư
	private JButton detailBtn = new JButton("��ȭ ��������");	// ��ȭ �������� ��ư
	private User user;
	private MovieBook mb;
	private ReviewBook rb;
	
//	public static void main(String[] args) {
//		new RCMovieListGUI();
//	}
	
	public RCMovieListGUI(User user, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.user=user;
		this.mb=mb;
		this.rb=rb;
		setTitle("��ȭ ���� & ��õ - ��õ ��ȭ ����Ʈ");						// ����
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
		panel.add(RCML);					// list ��µǴ� ����
		panel.add(new JScrollPane(RCML));	// ��ũ��
		RCML.setEditable(false);			// TextArea�� �� �Է� X
		RCML.setLineWrap(true);
		RCML.append(Recommend.recommendList_GUI(user,mb,rb));
		return panel;
	}
		
	// �Ʒ��ʿ� ��ư ���� (�ڷΰ���, ��ȭ ��������)
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(detailBtn);	// ��ȭ �������� ��ư
		panel.add(backBtn);		// �ڷΰ��� ��ư
		return panel;
	}

	// ��ư ������ �� �̺�Ʈ ó��
	private void addListeners() {
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
		detailBtn.addActionListener(new DetailedListener());		// ��ȭ �������� ��ư
	}
		
	// �ڷΰ��� ��ư �̺�Ʈ
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();				// ���� â �ݱ�
			new UserBtnGUI(user,mb,rb);
		}
	}
	// ��ȭ �������� ��ư �̺�Ʈ
	class DetailedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new detailedGUI(user,mb,rb);
		}
	}
}
