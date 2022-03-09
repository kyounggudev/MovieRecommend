package GUI;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Movie.Movie;
import Movie.MovieBook;
import Movie.UnknownMovieException;
import People.User;
import Review.Review;
import Review.ReviewBook;

/* User - ��ȭ �������� �����ִ� â */

public class detailedGUI extends JFrame implements ActionListener{
	// Top
	//private JLabel titleLabel = new JLabel("��ȭ ���� ");			// ��ȭ ����
	private JTextField titleField = new JTextField();				// ��ȭ ���� �Է� �ʵ�
	private JButton EnterBtn = new JButton("Enter");				// �Է� ��ư
	// Center
	private JTextArea movieInfo = new JTextArea();						// ��ȭ �������� �����ִ� ����
	// Right
	private JButton rewriteReview = new JButton("���� ����");			// ���� ���� ��ư
	private JButton writeReview = new JButton("���� �ۼ�");
	private JButton deleteReview = new JButton("���� ����");			// ���� ���� ��ư
	private JButton backBtn = new JButton("�ڷΰ���");					// �ڷΰ��� ��ư
	private User user;
	private MovieBook mb;
	private ReviewBook rb;
	private Movie movie;

	//	public static void main(String[] args) {
	//		new detailedGUI();
	//	}

	public detailedGUI(User user, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.user=user;
		this.mb=mb;
		this.rb=rb;
		setTitle("��ȭ ��������");			// ����
		setVisible(true);				// �����츦 ȭ�鿡 ǥ��
		setSize(500, 500);				// ũ��
		setLocationRelativeTo(null);	// �����찡 ȭ�� �߾ӿ��� ����
		setResizable(false);			// ������ ũ�� ����
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// ����
		addListeners();				// ��ư ��� ����
		createWindow();				// ������Ʈ�� �����쿡 ��ġ
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,20));
		panel.setBorder(new EmptyBorder(20,35,20,30));
		panel.add(createTop(), BorderLayout.NORTH);
		panel.add(createBottom(), BorderLayout.SOUTH);
		panel.add(createCenter());
		movieInfo.setEditable(false);			// TextArea�� �� �Է� X
		movieInfo.setLineWrap(true);
		add(panel);
	}

	// ������ ���� - ���� �Է�
	private JPanel createTop() {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		JLabel label = new JLabel("���� ");
		panel.add(label, BorderLayout.WEST);
		panel.add(titleField);		// ��ȭ ���� �Է�
		panel.add(EnterBtn, BorderLayout.EAST);		// �Է� ��ư
		return panel;
	}

	// ��� - ��ȭ ��������
	private JPanel createCenter() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(movieInfo);
		panel.add(new JScrollPane(movieInfo));
		return panel;
	}

	// �Ʒ��� - �������, �������, �ڷΰ��� ��ư
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(writeReview);
		panel.add(rewriteReview);		// ������� ��ư
		panel.add(deleteReview);		// ������� ��ư
		panel.add(backBtn);				// �ڷΰ��� ��ư
		return panel;
	}

	// ��ư ������ �� �̺�Ʈ ó��
	private void addListeners() {
		// TODO Auto-generated method stub
		EnterBtn.addActionListener(this);
		writeReview.addActionListener(new WriteReviewAction());
		rewriteReview.addActionListener(new RewriteReviewAction());	// ������� ��ư
		deleteReview.addActionListener(new DeleteReveiewAction());	// ������� ��ư
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
	}
	// ������� ��ư �̺�Ʈ
	class WriteReviewAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Review review=null;
			for(Review r : rb.reviewList) {
				if(r.id == user) {
					if(r.movie==movie) {
						review=r;
						break;
					}
				}
			}
			if(movie==null) {
				JOptionPane.showMessageDialog( null , "��ȭ�� ���õ��� �ʾҽ��ϴ�.");
			}else if(review!=null){
				JOptionPane.showMessageDialog( null , "�̹� ���並 �ۼ��߽��ϴ�.");
			}
			else {
				new addReviewGUI(movie,user, mb,rb);
			}
		}
	}
	class RewriteReviewAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Review review=null;
			for(Review r : rb.reviewList) {
				if(r.id == user) {
					if(r.movie==movie) {
						review=r;
						break;
					}
				}
			}
			if(movie==null) {
				JOptionPane.showMessageDialog( null , "��ȭ�� ���õ��� �ʾҽ��ϴ�.");
			}else if(review==null){
				JOptionPane.showMessageDialog( null , "�ۼ��� ���䰡 �����ϴ�.");
			}else {
				new reviseReviewGUI(review, movie, user, mb,rb);
			}
		}
	}
	// ������� ��ư
	class DeleteReveiewAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(movie!=null) {
				rb.deleteReview_GUI(movie, user);
			}else {
				JOptionPane.showMessageDialog( null , "��ȭ�� ���õ��� �ʾҽ��ϴ�.");
			}
		}
	}
	// �ڷΰ��� ��ư
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();					// ���� â �ݱ�
		}
	}
	public void actionPerformed(ActionEvent e) {
		String btnVal = e.getActionCommand();

		if (btnVal.equals("Enter")) {
			movieInfo.setText("");
			String a = titleField.getText();

			try {
				String detailedCode = (String)mb.getMovieCode(a);
				Movie detailedMovie = mb.getMovie(detailedCode);
				this.movie=detailedMovie;
				movieInfo.append(mb.detailedMovie_GUI(a, mb, rb, user));
			} catch (UnknownMovieException e1) {
				this.movie=null;
				movieInfo.append("�ش� ������ ��ȭ�� �����ϴ�.");
			}
		}
	}
}
