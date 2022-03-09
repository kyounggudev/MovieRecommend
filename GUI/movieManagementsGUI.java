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

/* Admin - ��ȭ ����Ʈ�� �����ϴ� ȭ�� */

public class movieManagementsGUI extends JFrame implements ActionListener{

	private JScrollPane scroll = new JScrollPane();
	private JLabel label = new JLabel("��ȭ ����");
	private JComboBox cb = new JComboBox();				//��ȭ�� ������ �� �ִ� �޺��ڽ�, �ڵ�� Ÿ��Ʋ�� ��������.
	private JTextArea movieInfo = new JTextArea();		//��ȭ ������ �����ִ� ����
	// ��ư��
	private JButton addMovie = new JButton("��ȭ �߰�");
	private JButton editMovie = new JButton("��ȭ ����");
	private JButton deleteMovie = new JButton("��ȭ ����");
	private JButton backBtn = new JButton("�ڷΰ���");
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
		setTitle("��ȭ ����Ʈ ����");
		setSize(500,500);
		addListeners();								// ��ư�� ���� ����� ����
		createWindow();								// ������Ʈ�� �����쿡 ��ġ
		setVisible(true);							// �����츦 ȭ�鿡 ǥ��
		setResizable(false);						// ������ ũ�� ����
		setLocationRelativeTo(null);				// �����찡 ȭ�� �߾ӿ��� ����
	}

	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,0));

		panel.setBorder(new EmptyBorder(0,0,0,0));
		panel.add(create());

		// ��
		label.setBounds(30, 14, 60, 15);
		// �޺��ڽ�
		cb.setBounds(30,40,310,20);
		Collection v = mb.movieMap.values();
		Iterator<Movie> itrv = v.iterator();
		while(itrv.hasNext()){
			cb.addItem(itrv.next().title);

		}
		scroll.setBounds(30, 80, 310, 330);
		scroll.setViewportView(movieInfo); 
		// textArea
		movieInfo.setEditable(false);			// TextArea�� �� �Է� X
		movieInfo.setBounds(30,80,310,330);
		movieInfo.setLineWrap(true);
		
		// ��ư
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
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
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
				movieInfo.append("�ش� ������ ��ȭ�� �����ϴ�.");
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

	// �ڷΰ��� ��ư
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();					// ���� â �ݱ�
			new AdminBtnGUI(admin,mb,rb);
		}
	}
	public void actionPerformed(ActionEvent e) {
		String btnVal = e.getActionCommand();
		if (btnVal.equals("��ȭ ����")) {
			mb.movieMap.remove(mb.getMovieCode(movie.title));
			JOptionPane.showMessageDialog( null , "����ó���ƽ��ϴ�.");
			
		}
	}
}
