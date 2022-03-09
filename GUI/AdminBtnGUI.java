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

/* Admin 로그인 후 영화 리스트, 영과 관리 버튼 나오는 창 */
public class AdminBtnGUI extends JFrame {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new AdminBtnGUI(null);
//	}

	// Top
	private JButton MList = new JButton("모든 영화"); 		// 영화 리스트 출력하는 버튼
	private JButton Mmng = new JButton("영화 관리");		// 영화 관리 버튼
	private Admin admin;
	private MovieBook mb;
	private ReviewBook rb;
			
			
	public AdminBtnGUI(Admin admin, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.admin=admin;
		this.mb=mb;
		this.rb=rb;
		setTitle("영화 리뷰 & 추천 (관리자)");			// 제목
		setSize(500,250);							// 크기
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// 창 닫으면 프로그램 종료
		addListeners();								// 버튼에 대한 기능을 배정
		createWindow();								// 컴포넌트를 윈도우에 배치
		setVisible(true);							// 윈도우를 화면에 표시
		setResizable(false);						// 윈도우 크기 고정
		setLocationRelativeTo(null);				// 윈도우가 화면 중앙에서 열림
	}
			
	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.setBorder(new EmptyBorder(60,10,20,10));
		panel.add(create());
		MList.setPreferredSize(new Dimension(120,50));		// 영화 리스트 버튼 크기
		Mmng.setPreferredSize(new Dimension(120,50));		// 영화 관리 버튼 크기
		add(panel);
	}
			
	// 버튼 생성
	private JPanel create() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));	// 버튼 간격
		panel.add(MList);		// 영화 리스트 버튼
		panel.add(Mmng);		// 영화 관리 버튼
		return panel;
	}
			
	// 버튼 눌렀을 때 이벤트 처리
	private void addListeners() {
		MList.addActionListener(new PrintMovieListListener());		// 영화 리스트 출력 버튼
		Mmng.addActionListener(new ManagementsMovieListener());			// 영화 관리 버튼
	}
	// 영화 리스트 출력 버튼 이벤트
	class PrintMovieListListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new AdminMovieListGUI(admin, mb,rb);
			dispose();
		}
	}
	// 영화 관리 버튼 이벤트
	class ManagementsMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(admin.right==1) {
				new movieManagementsGUI(admin,mb,rb);
				dispose();
			}else {
				JOptionPane.showMessageDialog( null , "권한이 없습니다.");
			}
		}
	}
}
