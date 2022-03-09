package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.UserBtnGUI.PrintMovieListListener;
import GUI.UserBtnGUI.PrintRMListListener;
import Movie.MovieBook;
import People.Admin;
import Review.ReviewBook;

/* Admin �α��� �� ��ȭ ����Ʈ, ���� ���� ��ư ������ â */
public class AdminBtnGUI extends JFrame {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new AdminBtnGUI(null);
//	}

	// Top
	private JButton MList = new JButton("��� ��ȭ"); 		// ��ȭ ����Ʈ ����ϴ� ��ư
	private JButton Mmng = new JButton("��ȭ ����");		// ��ȭ ���� ��ư
	private Admin admin;
	private MovieBook mb;
	private ReviewBook rb;
			
			
	public AdminBtnGUI(Admin admin, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.admin=admin;
		this.mb=mb;
		this.rb=rb;
		setTitle("��ȭ ���� & ��õ (������)");			// ����
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
		panel.add(create());
		MList.setPreferredSize(new Dimension(120,50));		// ��ȭ ����Ʈ ��ư ũ��
		Mmng.setPreferredSize(new Dimension(120,50));		// ��ȭ ���� ��ư ũ��
		add(panel);
	}
			
	// ��ư ����
	private JPanel create() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));	// ��ư ����
		panel.add(MList);		// ��ȭ ����Ʈ ��ư
		panel.add(Mmng);		// ��ȭ ���� ��ư
		return panel;
	}
			
	// ��ư ������ �� �̺�Ʈ ó��
	private void addListeners() {
		MList.addActionListener(new PrintMovieListListener());		// ��ȭ ����Ʈ ��� ��ư
		Mmng.addActionListener(new ManagementsMovieListener());			// ��ȭ ���� ��ư
	}
	// ��ȭ ����Ʈ ��� ��ư �̺�Ʈ
	class PrintMovieListListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new AdminMovieListGUI(admin, mb,rb);
			dispose();
		}
	}
	// ��ȭ ���� ��ư �̺�Ʈ
	class ManagementsMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(admin.right==1) {
				new movieManagementsGUI(admin,mb,rb);
				dispose();
			}else {
				JOptionPane.showMessageDialog( null , "������ �����ϴ�.");
			}
		}
	}
}
