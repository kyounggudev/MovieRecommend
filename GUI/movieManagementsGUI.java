package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import GUI.detailedGUI.BackwardListener;
import Movie.Movie;
import Movie.MovieBook;
import Movie.UnknownMovieException;
import People.Admin;
import Review.ReviewBook;
import Review.UnknownReviewException;

/* Admin - 영화 리스트를 관리하는 화면 */

public class movieManagementsGUI extends JFrame implements ActionListener{

	private JScrollPane scroll = new JScrollPane();
	private JLabel label = new JLabel("영화 관리");
	private JComboBox cb = new JComboBox();				//영화를 선택할 수 있는 콤보박스, 코드와 타이틀만 나오도록.
	private JTextArea movieInfo = new JTextArea();		//영화 정보를 보여주는 공간
	// 버튼들
	private JButton addMovie = new JButton("영화 추가");
	private JButton editMovie = new JButton("영화 수정");
	private JButton deleteMovie = new JButton("영화 삭제");
	private JButton backBtn = new JButton("뒤로가기");
	private Admin admin;
	private MovieBook mb;
	private ReviewBook rb;
	private Movie movie;

	//	public static void main(String[] args) {
	//		// TODO Auto-generated method stub
	//		new movieManagementsGUI();
	//	}

	public movieManagementsGUI(Admin admin, MovieBook mb, ReviewBook rb) {
		mb.newAverage(mb, rb);
		mb.newCount(mb, rb);
		this.admin=admin;
		this.mb=mb;
		this.rb=rb;
		setTitle("영화 리스트 관리");
		setSize(500,500);
		addListeners();								// 버튼에 대한 기능을 배정
		createWindow();								// 컴포넌트를 윈도우에 배치
		setVisible(true);							// 윈도우를 화면에 표시
		setResizable(false);						// 윈도우 크기 고정
		setLocationRelativeTo(null);				// 윈도우가 화면 중앙에서 열림
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,0));

		panel.setBorder(new EmptyBorder(0,0,0,0));
		panel.add(create());

		// 라벨
		label.setBounds(30, 14, 60, 15);
		// 콤보박스
		cb.setBounds(30,40,310,20);
		Collection v = mb.movieMap.values();
		Iterator<Movie> itrv = v.iterator();
		while(itrv.hasNext()){
			cb.addItem(itrv.next().title);

		}
		scroll.setBounds(30, 80, 310, 330);
		scroll.setViewportView(movieInfo); 
		// textArea
		movieInfo.setEditable(false);			// TextArea에 글 입력 X
		movieInfo.setBounds(30,80,310,330);
		movieInfo.setLineWrap(true);
		
		// 버튼
		addMovie.setBounds(360, 40, 97, 32);
		editMovie.setBounds(360, 92, 97, 32);
		deleteMovie.setBounds(360, 144, 97, 32);
		backBtn.setBounds(360, 196, 97, 32);
		add(panel);
	}

	private JPanel create() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(null);


		panel.add(label);
		panel.add(cb);
		panel.add(movieInfo);
		panel.add(scroll); 
		panel.add(addMovie);
		panel.add(editMovie);
		panel.add(deleteMovie);
		panel.add(backBtn);
		return panel;
	}
	private void addListeners() {
		// TODO Auto-generated method stub
		cb.addActionListener(new ComboBoxListener());
		addMovie.addActionListener(new addMovieListener());
		editMovie.addActionListener(new editMovieListener());
		deleteMovie.addActionListener(this);
		backBtn.addActionListener(new BackwardListener());			// 뒤로가기 버튼
	}

	class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			movieInfo.setText("");
			String a = cb.getSelectedItem().toString();
			try {
				String detailedCode = (String)mb.getMovieCode(a);
				Movie detailedMovie = mb.getMovie(detailedCode);
				movie=detailedMovie;
				movieInfo.append(mb.detailedMovie_GUI(a, mb, rb, null));
			} catch (UnknownMovieException e1) {
				movieInfo.append("해당 제목의 영화는 없습니다.");
			}
		}
	}
	
	class addMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new addMovieGUI(mb,rb);
		}
	}
	class editMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new reviseMovieGUI(movie,mb,rb);
		}
	}

	// 뒤로가기 버튼
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();					// 현재 창 닫기
			new AdminBtnGUI(admin,mb,rb);
		}
	}
	public void actionPerformed(ActionEvent e) {
		String btnVal = e.getActionCommand();
		if (btnVal.equals("영화 삭제")) {
			mb.movieMap.remove(mb.getMovieCode(movie.title));
			JOptionPane.showMessageDialog( null , "정상처리됐습니다.");
			
		}
	}
}
