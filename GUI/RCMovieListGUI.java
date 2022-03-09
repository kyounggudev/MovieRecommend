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

/* User - 영화 추천 리스트 화면 */

public class RCMovieListGUI extends JFrame {

	// Top
	private JTextArea RCML = new JTextArea();				// 영화추천 리스트 보여주는 공간
	// Bottom
	private JButton backBtn = new JButton("뒤로가기");			// 뒤로가기 버튼
	private JButton detailBtn = new JButton("영화 세부정보");	// 영화 세부정보 버튼
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
		setTitle("영화 리뷰 & 추천 - 추천 영화 리스트");						// 제목
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
		panel.add(RCML);					// list 출력되는 공간
		panel.add(new JScrollPane(RCML));	// 스크롤
		RCML.setEditable(false);			// TextArea에 글 입력 X
		RCML.setLineWrap(true);
		RCML.append(Recommend.recommendList_GUI(user,mb,rb));
		return panel;
	}
		
	// 아래쪽에 버튼 생성 (뒤로가기, 영화 세부정보)
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(detailBtn);	// 영화 세부정보 버튼
		panel.add(backBtn);		// 뒤로가기 버튼
		return panel;
	}

	// 버튼 눌렀을 때 이벤트 처리
	private void addListeners() {
		backBtn.addActionListener(new BackwardListener());			// 뒤로가기 버튼
		detailBtn.addActionListener(new DetailedListener());		// 영화 세부정보 버튼
	}
		
	// 뒤로가기 버튼 이벤트
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();				// 현재 창 닫기
			new UserBtnGUI(user,mb,rb);
		}
	}
	// 영화 세부정보 버튼 이벤트
	class DetailedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new detailedGUI(user,mb,rb);
		}
	}
}
