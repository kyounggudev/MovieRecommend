package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.movieManagementsGUI.BackwardListener;
import Movie.Movie;
import Movie.MovieBook;
import People.User;
import Review.Review;
import Review.ReviewBook;

/* Admin - ���ο� ��ȭ �߰� */

public class reviseReviewGUI extends JFrame {
	private JScrollPane scroll = new JScrollPane(); 
	private JLabel rateLabel = new JLabel("����");					// �ڵ�
	private JTextField rateField = new JTextField(19);			// �ڵ� �Է� �ʵ�

	private JLabel contentLabel = new JLabel("����");					// �ٰŸ�
	private JTextArea contentField = new JTextArea();		// �ٰŸ� �Է� �ʵ�

	private JButton addBtn = new JButton("���");
	private JButton backBtn = new JButton("�ڷΰ���");
	private MovieBook mb;
	private ReviewBook rb;
	private Movie movie;
	private User user;
	private Review review;

	//	public static void main(String[] args) {
	//		new addMovieGUI();
	//	}
	//	
	public reviseReviewGUI(Review review, Movie movie, User user, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.review=review;
		this.movie=movie;
		this.user=user;
		this.mb=mb;
		this.rb=rb;
		rateField.setText(Double.toString(review.rating));
		contentField.setText(review.content);
		contentField.setLineWrap(true);
		setTitle("��ȭ �߰�");
		setVisible(true);				// �����츦 ȭ�鿡 ǥ��
		setSize(400,320);				// ũ��
		setLocationRelativeTo(null);	// �����찡 ȭ�� �߾ӿ��� ����
		setResizable(false);			// ������ ũ�� ����
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// ����
		addListeners();				// ��ư ��� ����
		createWindow();				// ������Ʈ�� �����쿡 ��ġ
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(20,30,20,30));
		panel.add(create());

		scroll.setBounds(90, 50, 200, 120);
		scroll.setViewportView(contentField); 

		rateLabel.setBounds(20, 10, 50, 30);
		rateField.setBounds(90, 10, 200, 30);

		contentLabel.setBounds(20, 50, 50, 30);
		contentField.setBounds(90, 50, 200, 120);


		addBtn.setBounds(90, 200, 90, 30);
		backBtn.setBounds(200, 200, 90, 30);

		add(panel);
	}

	private JPanel create() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(rateLabel);
		panel.add(rateField);

		panel.add(contentLabel);
		panel.add(contentField);
		panel.add(scroll);

		panel.add(addBtn);
		panel.add(backBtn);

		return panel;
	}

	private void addListeners() {
		addBtn.addActionListener(new addMovieListener());			// ��ȭ ��� ��ư
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
	}
	// ��ȭ ��� ��ư �̺�Ʈ
	class addMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ��ȭ�� ����ϴ� �̺�Ʈ �߰��ϱ�
			String pattern = "^[0-5]\\.[0-9]";
			if(!rateField.getText().matches(pattern)||Double.parseDouble(rateField.getText())>5.0) {
				JOptionPane.showMessageDialog( null , "������ 0.5~5.0���� �Է����ּ���.");
			}else if(rateField.getText().equals("")||contentField.getText().equals("")){
				JOptionPane.showMessageDialog( null , "�ۼ����� ���� �׸��� �ֽ��ϴ�.");
			} else {
				double rating = Double.parseDouble(rateField.getText());
				Date date = new Date();
				SimpleDateFormat sdformat = new SimpleDateFormat("YYYY-MM-dd");
				String writtenDate = sdformat.format(date);
				review.rating = rating;
				review.content = contentField.getText();
				dispose();
			}

		}
	}
	// �ڷΰ��� ��ư
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();					// ���� â�� �ݱ�
		}
	}
}
