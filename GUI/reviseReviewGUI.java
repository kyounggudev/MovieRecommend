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

/* Admin - 새로운 영화 추가 */

public class reviseReviewGUI extends JFrame {
	private JScrollPane scroll = new JScrollPane(); 
	private JLabel rateLabel = new JLabel("평점");					// 코드
	private JTextField rateField = new JTextField(19);			// 코드 입력 필드

	private JLabel contentLabel = new JLabel("내용");					// 줄거리
	private JTextArea contentField = new JTextArea();		// 줄거리 입력 필드

	private JButton addBtn = new JButton("등록");
	private JButton backBtn = new JButton("뒤로가기");
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
		setTitle("영화 추가");
		setVisible(true);				// 윈도우를 화면에 표시
		setSize(400,320);				// 크기
		setLocationRelativeTo(null);	// 윈도우가 화면 중앙에서 열림
		setResizable(false);			// 윈도우 크기 고정
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 종료
		addListeners();				// 버튼 기능 배정
		createWindow();				// 컴포넌트를 윈도우에 배치
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
		addBtn.addActionListener(new addMovieListener());			// 영화 등록 버튼
		backBtn.addActionListener(new BackwardListener());			// 뒤로가기 버튼
	}
	// 영화 등록 버튼 이벤트
	class addMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 영화를 등록하는 이벤트 추가하기
			String pattern = "^[0-5]\\.[0-9]";
			if(!rateField.getText().matches(pattern)||Double.parseDouble(rateField.getText())>5.0) {
				JOptionPane.showMessageDialog( null , "평점은 0.5~5.0까지 입력해주세요.");
			}else if(rateField.getText().equals("")||contentField.getText().equals("")){
				JOptionPane.showMessageDialog( null , "작성하지 않은 항목이 있습니다.");
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
	// 뒤로가기 버튼
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();					// 현재 창을 닫기
		}
	}
}
