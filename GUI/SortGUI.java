package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import GUI.RCMovieListGUI.BackwardListener;
import GUI.RCMovieListGUI.DetailedListener;
import Movie.MovieBook;
import People.User;
import Review.ReviewBook;

/* User - 영화를 정렬하는 화면 */

public class SortGUI extends JFrame {

	private String [] menu = {"제목","개봉일","평점","리뷰수","관객수"};		// 콤보박스 아이템
	private JComboBox<String> strCombo = new JComboBox<String>(menu);
	private JTextArea ML = new JTextArea();	
	JScrollPane scrollPane = new JScrollPane(ML);
	private JButton backBtn = new JButton("뒤로가기");			// 뒤로가기 버튼
	private JButton detailBtn = new JButton("영화 세부정보");	// 영화 세부정보 버튼
	private User user;
	private MovieBook mb;
	private ReviewBook rb;
	
//	public static void main(String[] args) {
//		new SortGUI();
//	}
	
	public SortGUI(User user, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.user=user;
		this.mb=mb;
		this.rb=rb;
		setTitle("영화 리뷰 & 추천");					// 제목
		setSize(500,500);							// 크기
		createWindow();
		addListeners();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(30,30,30,30));
		panel.add(createTop(), BorderLayout.NORTH);
		panel.add(createCenter());
		panel.add(createBottom(), BorderLayout.SOUTH);
		add(panel);
	}
	
	private JPanel createTop() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(strCombo);
		return panel;
	}
	
	private JPanel createCenter() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ML);						// 영화 목록
		panel.add(new JScrollPane(ML));		// 스크롤
		ML.setEditable(false);				// TextArea에 글 입력 X
		ML.setLineWrap(true);
		ML.append(mb.MovieList_GUI());	
		return panel;
	}
	
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(detailBtn);	// 영화 세부정보 버튼
		panel.add(backBtn);		// 뒤로가기 버튼
		return panel;
	}
	// 버튼 눌렀을 때 이벤트 처리
	private void addListeners() {
		strCombo.addActionListener(new ComboBoxListener());
		backBtn.addActionListener(new BackwardListener());			// 뒤로가기 버튼
		detailBtn.addActionListener(new DetailedListener());		// 영화 세부정보 버튼
	}
	// 콤보박스 이벤트
	class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(strCombo.getSelectedItem().toString()=="제목") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "1"));
			}
			if(strCombo.getSelectedItem().toString()=="개봉일") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "2"));
			}
			if(strCombo.getSelectedItem().toString()=="평점") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "3"));
			}
			if(strCombo.getSelectedItem().toString()=="리뷰수") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "4"));
			}
			if(strCombo.getSelectedItem().toString()=="관객수") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "5"));
			}
		}
	}
	// 뒤로가기 버튼 이벤트
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();				// 현재 창 닫기
		}
	}
	// 영화 세부정보 버튼 이벤트
	class DetailedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new detailedGUI(user, mb, rb);
		}
	}
}
