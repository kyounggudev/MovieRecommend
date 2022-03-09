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

/* User - 영화 세부정보 보여주는 창 */

public class detailedGUI extends JFrame implements ActionListener{
	// Top
	//private JLabel titleLabel = new JLabel("영화 제목 ");			// 영화 제목
	private JTextField titleField = new JTextField();				// 영화 제목 입력 필드
	private JButton EnterBtn = new JButton("Enter");				// 입력 버튼
	// Center
	private JTextArea movieInfo = new JTextArea();						// 영화 세부정보 보여주는 공간
	// Right
	private JButton rewriteReview = new JButton("리뷰 수정");			// 리뷰 수정 버튼
	private JButton writeReview = new JButton("리뷰 작성");
	private JButton deleteReview = new JButton("리뷰 삭제");			// 리뷰 삭제 버튼
	private JButton backBtn = new JButton("뒤로가기");					// 뒤로가기 버튼
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
		setTitle("영화 세부정보");			// 제목
		setVisible(true);				// 윈도우를 화면에 표시
		setSize(500, 500);				// 크기
		setLocationRelativeTo(null);	// 윈도우가 화면 중앙에서 열림
		setResizable(false);			// 윈도우 크기 고정
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 종료
		addListeners();				// 버튼 기능 배정
		createWindow();				// 컴포넌트를 윈도우에 배치
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,20));
		panel.setBorder(new EmptyBorder(20,35,20,30));
		panel.add(createTop(), BorderLayout.NORTH);
		panel.add(createBottom(), BorderLayout.SOUTH);
		panel.add(createCenter());
		movieInfo.setEditable(false);			// TextArea에 글 입력 X
		movieInfo.setLineWrap(true);
		add(panel);
	}

	// 윈도우 위쪽 - 제목 입력
	private JPanel createTop() {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		JLabel label = new JLabel("제목 ");
		panel.add(label, BorderLayout.WEST);
		panel.add(titleField);		// 영화 제목 입력
		panel.add(EnterBtn, BorderLayout.EAST);		// 입력 버튼
		return panel;
	}

	// 가운데 - 영화 세부정보
	private JPanel createCenter() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(movieInfo);
		panel.add(new JScrollPane(movieInfo));
		return panel;
	}

	// 아래쪽 - 리뷰수정, 리뷰삭제, 뒤로가기 버튼
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(writeReview);
		panel.add(rewriteReview);		// 리뷰수정 버튼
		panel.add(deleteReview);		// 리뷰삭제 버튼
		panel.add(backBtn);				// 뒤로가기 버튼
		return panel;
	}

	// 버튼 눌렀을 때 이벤트 처리
	private void addListeners() {
		// TODO Auto-generated method stub
		EnterBtn.addActionListener(this);
		writeReview.addActionListener(new WriteReviewAction());
		rewriteReview.addActionListener(new RewriteReviewAction());	// 리뷰수정 버튼
		deleteReview.addActionListener(new DeleteReveiewAction());	// 리뷰삭제 버튼
		backBtn.addActionListener(new BackwardListener());			// 뒤로가기 버튼
	}
	// 리뷰수정 버튼 이벤트
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
				JOptionPane.showMessageDialog( null , "영화가 선택되지 않았습니다.");
			}else if(review!=null){
				JOptionPane.showMessageDialog( null , "이미 리뷰를 작성했습니다.");
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
				JOptionPane.showMessageDialog( null , "영화가 선택되지 않았습니다.");
			}else if(review==null){
				JOptionPane.showMessageDialog( null , "작성한 리뷰가 없습니다.");
			}else {
				new reviseReviewGUI(review, movie, user, mb,rb);
			}
		}
	}
	// 리뷰삭제 버튼
	class DeleteReveiewAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(movie!=null) {
				rb.deleteReview_GUI(movie, user);
			}else {
				JOptionPane.showMessageDialog( null , "영화가 선택되지 않았습니다.");
			}
		}
	}
	// 뒤로가기 버튼
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();					// 현재 창 닫기
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
				movieInfo.append("해당 제목의 영화는 없습니다.");
			}
		}
	}
}
