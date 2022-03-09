package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import Movie.UnknownMovieException;
import Review.ReviewBook;

/* Admin - 영화 수정 */

public class reviseMovieGUI extends JFrame {
	private JScrollPane scroll = new JScrollPane(); 
	private JScrollPane scroll2 = new JScrollPane(); 
	private JLabel titleLabel = new JLabel("제목");					// 제목
	private JTextField titleField = new JTextField(19);		// 제목 입력 필드

	private JLabel directorLabel = new JLabel("감독");				// 감독
	private JTextField directorField = new JTextField(19);	// 감독 입력 필드

	private JLabel actorLabel = new JLabel("배우");					// 배우
	private JTextArea actorField = new JTextArea();		// 배우 입력 필드

	
	private JLabel releaseLabel = new JLabel("개봉일");				// 개봉일
	private JTextField releaseField = new JTextField(19);	// 개봉일 입력 필드

	private JLabel genreLabel = new JLabel("장르");					// 장르
	private JTextField genreField = new JTextField(19);		// 장르 입력 필드

	private JLabel plotLabel = new JLabel("줄거리");					// 줄거리
	private JTextArea plotField = new JTextArea();		// 줄거리 입력 필드

	
	private JLabel audienceLabel = new JLabel("관객수");				// 관객수
	private JTextField audienceField = new JTextField(19);	// 관객수 입력 필드

	private JButton addBtn = new JButton("등록");
	private JButton backBtn = new JButton("뒤로가기");
	private MovieBook mb;
	private ReviewBook rb;
	private Movie movie;

	//	public static void main(String[] args) {
	//		new addMovieGUI();
	//	}
	//	
	public reviseMovieGUI(Movie movie, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.movie=movie;
		this.mb=mb;
		this.rb=rb;
		String a="";
		for(int i=0; i<movie.actor.size();i++) {
			a=a+(movie.actor.get(i)+"\n");
		};
		plotField.setLineWrap(true);
		titleField.setText(movie.title);
		directorField.setText(movie.director);
		actorField.setText(a);
		releaseField.setText(movie.release);
		genreField.setText(movie.genre);
		plotField.setText(movie.plot);
		audienceField.setText(Integer.toString(movie.audience));
		setTitle("영화 수정");
		setVisible(true);				// 윈도우를 화면에 표시
		setSize(400,600);				// 크기
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

		scroll.setBounds(90, 280, 200, 120);
		scroll.setViewportView(plotField); 
		
		scroll2.setBounds(90, 130, 200, 60);      
		scroll2.setViewportView(actorField);

		titleLabel.setBounds(20, 50, 50, 30);
		titleField.setBounds(90, 50, 200, 30);

		directorLabel.setBounds(20, 90, 50, 30);
		directorField.setBounds(90, 90, 200, 30);

		actorLabel.setBounds(20, 130, 50, 30);
		actorField.setBounds(90, 130, 200, 60);

		releaseLabel.setBounds(20, 200, 50, 30);
		releaseField.setBounds(90, 200, 200, 30);

		genreLabel.setBounds(20, 240, 50, 30);
		genreField.setBounds(90, 240, 200, 30);

		plotLabel.setBounds(20, 280, 50, 30);
		plotField.setBounds(90, 280, 200, 120);

		audienceLabel.setBounds(20, 410, 50, 30);
		audienceField.setBounds(90, 410, 200, 30);

		addBtn.setBounds(90, 460, 90, 30);
		backBtn.setBounds(200, 460, 90, 30);

		add(panel);
	}

	private JPanel create() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(titleLabel);
		panel.add(titleField);

		panel.add(directorLabel);
		panel.add(directorField);

		panel.add(actorLabel);
		panel.add(actorField);
		panel.add(scroll2);
		
		panel.add(releaseLabel);
		panel.add(releaseField);

		panel.add(genreLabel);
		panel.add(genreField);

		panel.add(plotLabel);
		panel.add(plotField);
		panel.add(scroll);
		

		panel.add(audienceLabel);
		panel.add(audienceField);

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
			// 영화를 등록하는 이벤트 수정하기
			String pattern = "^[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9]$"; // ^시작,$끝
			String pattern2 = "^$|^[0-9]$|^[1-9][0-9]*$"; //정수 

			if(titleField.getText().contentEquals("")||directorField.getText().contentEquals("")||genreField.getText().equals("")||actorField.getText().equals("")||plotField.getText().equals("")) {
				JOptionPane.showMessageDialog( null , "입력하지 않은 값이 있습니다.");
			} else if(!releaseField.getText().matches(pattern)) {// (년도-월-일) 의 패턴으로 넘어오는지 체크
				JOptionPane.showMessageDialog( null , "출시일이 (YYYY-MM-dd)패턴이 아닙니다. 다시 입력해주세요.");

			} else if(!audienceField.getText().matches(pattern2)||audienceField.getText().equals("")) {
				JOptionPane.showMessageDialog( null , "관객수가 정수가 아닙니다. 다시 입력해주세요.");
			} else{
				ArrayList a = new ArrayList();
				String[] str = actorField.getText().split("\\n");
				for(int i=0; i<str.length; i++){
					a.add(str[i]);
				}
				int audience=Integer.parseInt(audienceField.getText());
				JOptionPane.showMessageDialog( null , "성공적으로 수정됐습니다.");
					try {
						Movie reMovie = mb.getMovie(mb.getMovieCode(movie.title));
						reMovie.title=titleField.getText();
						reMovie.director=directorField.getText();
						reMovie.release=releaseField.getText();
						reMovie.genre=genreField.getText();
						reMovie.plot=plotField.getText();
						reMovie.audience=audience;
					} catch (UnknownMovieException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				dispose();					// 현재 창을 닫기
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
