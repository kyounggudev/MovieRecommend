package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.MovieListGUI.BackwardListener;
import Movie.MovieBook;
import People.User;
import Review.ReviewBook;

/* User �α����� �� ���� ��ȭ����Ʈ, ��ȭ��õ����Ʈ ��ư�� ������ â */

public class UserBtnGUI extends JFrame {
	
	// Top
		private JButton MList = new JButton("��� ��ȭ"); 		// ��ȭ ����Ʈ ����ϴ� ��ư
		private JButton RMList = new JButton("��õ ��ȭ");		// ��ȭ ��õ ����Ʈ ����ϴ� ��ư
		private User user;
		private MovieBook mb;
		private ReviewBook rb;
		public static void main(String[] args) {
			new UserBtnGUI(null,null,null);
		}
		
		public UserBtnGUI(User user, MovieBook mb,ReviewBook rb) {
			mb.newAverage(mb, rb);
			mb.newCount(mb, rb);
			this.user=user;
			this.mb = mb;
			this.rb=rb;
			setTitle("��ȭ ���� & ��õ");						// ����
			setSize(500,250);							// ũ��
			setDefaultCloseOperation(EXIT_ON_CLOSE);	// â ������ ���α׷� ����
			addListeners();								// ��ư�� ���� ����� ����
			createWindow();								// ������Ʈ�� �����쿡 ��ġ
			setVisible(true);							// �����츦 ȭ�鿡 ǥ��
			setResizable(false);						// ������ ũ�� ����
			setLocationRelativeTo(null);				// �����찡 ȭ�� �߾ӿ��� ����
			
		}
		
		private void createWindow() {
			JPanel panel = new JPanel(new BorderLayout(10,10));
			panel.setBorder(new EmptyBorder(60,10,20,10));
			//panel.add(createTop(), BorderLayout.NORTH);
			panel.add(create());
			MList.setPreferredSize(new Dimension(120,50));	// ��ȭ ����Ʈ ��ư ũ��
			RMList.setPreferredSize(new Dimension(120,50)); // ��ȭ ��õ ����Ʈ ��ư ũ��
			add(panel);
		}
		
		// ��ư ����
		private JPanel create() {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));	// ��ư ����
			panel.add(MList);		// ��ȭ ����Ʈ ��ư
			panel.add(RMList);		// ��ȭ ��õ ����Ʈ ��ư
			return panel;
		}
		
		// ��ư ������ �� �̺�Ʈ ó��
		private void addListeners() {
			MList.addActionListener(new PrintMovieListListener());		// ��ȭ ����Ʈ ��� ��ư
			RMList.addActionListener(new PrintRMListListener());		// ��ȭ ��õ ����Ʈ ��� ��ư
		}
		// ��ȭ ����Ʈ ��� ��ư �̺�Ʈ
		class PrintMovieListListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				new MovieListGUI(user,mb,rb);
				dispose();
			}
		}
		// ��õ ��ȭ ����Ʈ ��� ��ư �̺�Ʈ
		class PrintRMListListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				new RCMovieListGUI(user,mb,rb);
				dispose();
			}
		}
}
