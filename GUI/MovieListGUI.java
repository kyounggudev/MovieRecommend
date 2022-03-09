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

/* User -> 영화 리스트 화면 */

public class MovieListGUI extends JFrame{
	
	// Top
	private JTextArea ML = new JTextArea();					//영화 리스트 보여주는 공간
	// Bottom
	JScrollPane scrollPane = new JScrollPane(ML);
	private JButton backBtn = new JButton("뒤로가기");			// 뒤로가기 버튼
	private JButton detailBtn = new JButton("영화 세부정보");	// 영화 세부정보 버튼
	private JButton sortBtn = new JButton("정렬하기");			// 영화 정렬하기
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
		
		setTitle("영화 리뷰 & 추천 - 영화 리스트");		// 제목
		setSize(500,500);							// 크기
		//setDefaultCloseOperation(EXIT_ON_CLOSE);	// 창 닫으면 프로그램 종료
		addListeners();								// 버튼에 대한 기능을 배정
		createWindow();								// 컴포넌트를 윈도우에 배치
		setVisible(true);							// 윈도우를 화면에 표시
		setResizable(false);						// 윈도우 크기 고정
		setLocationRelativeTo(null);				// 윈도우가 화면 중앙에서 열림
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(30,30,10,30));
		//panel.add(createTop(), BorderLayout.NORTH);
		panel.add(createTop());
		panel.add(createBottom(), BorderLayout.SOUTH);
		add(panel);
	}
	
	// 위쪽에 영화 리스트 출력 (영화 목록이 보이는 창 생성)
	private JPanel createTop() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ML);						// 영화 목록
		panel.add(new JScrollPane(ML));		// 스크롤
		ML.setEditable(false);				// TextArea에 글 입력 X
		ML.setLineWrap(true);
		ML.append(mb.MovieList_GUI());	
		return panel;
	}
	
	// 아래쪽에 버튼 생성 (뒤로가기, 영화 세부정보, 정렬하기)
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(sortBtn);		// 정렬하기 버튼
		panel.add(detailBtn);	// 영화 세부정보 버튼
		panel.add(backBtn);		// 뒤로가기 버튼
		return panel;
	}

	// 버튼 눌렀을 때 이벤트 처리
	private void addListeners() {
		backBtn.addActionListener(new BackwardListener());			// 뒤로가기 버튼
		detailBtn.addActionListener(new DetailedListener());		// 영화 세부정보 버튼
		sortBtn.addActionListener(new SortListener());				// 정렬하기 버튼
	}
	
	// 뒤로가기 버튼 이벤트
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();				// 현재 창 닫기
			
			new UserBtnGUI(user, mb, rb);
		}
	}
	// 영화 세부정보 버튼 이벤트
	class DetailedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			new detailedGUI(user, mb, rb);		// 세부정보 화면으로 이동
		}
	}
	// 정렬하기 버튼 이벤트
	class SortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new SortGUI(user, mb, rb);
		}
	}
}
