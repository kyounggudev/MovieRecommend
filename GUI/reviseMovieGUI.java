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

/* Admin - ��ȭ ���� */

public class reviseMovieGUI extends JFrame {
	private JScrollPane scroll = new JScrollPane(); 
	private JScrollPane scroll2 = new JScrollPane(); 
	private JLabel titleLabel = new JLabel("����");					// ����
	private JTextField titleField = new JTextField(19);		// ���� �Է� �ʵ�

	private JLabel directorLabel = new JLabel("����");				// ����
	private JTextField directorField = new JTextField(19);	// ���� �Է� �ʵ�

	private JLabel actorLabel = new JLabel("���");					// ���
	private JTextArea actorField = new JTextArea();		// ��� �Է� �ʵ�

	
	private JLabel releaseLabel = new JLabel("������");				// ������
	private JTextField releaseField = new JTextField(19);	// ������ �Է� �ʵ�

	private JLabel genreLabel = new JLabel("�帣");					// �帣
	private JTextField genreField = new JTextField(19);		// �帣 �Է� �ʵ�

	private JLabel plotLabel = new JLabel("�ٰŸ�");					// �ٰŸ�
	private JTextArea plotField = new JTextArea();		// �ٰŸ� �Է� �ʵ�

	
	private JLabel audienceLabel = new JLabel("������");				// ������
	private JTextField audienceField = new JTextField(19);	// ������ �Է� �ʵ�

	private JButton addBtn = new JButton("���");
	private JButton backBtn = new JButton("�ڷΰ���");
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
		setTitle("��ȭ ����");
		setVisible(true);				// �����츦 ȭ�鿡 ǥ��
		setSize(400,600);				// ũ��
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
		addBtn.addActionListener(new addMovieListener());			// ��ȭ ��� ��ư
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
	}
	// ��ȭ ��� ��ư �̺�Ʈ
	class addMovieListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ��ȭ�� ����ϴ� �̺�Ʈ �����ϱ�
			String pattern = "^[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9]$"; // ^����,$��
			String pattern2 = "^$|^[0-9]$|^[1-9][0-9]*$"; //���� 

			if(titleField.getText().contentEquals("")||directorField.getText().contentEquals("")||genreField.getText().equals("")||actorField.getText().equals("")||plotField.getText().equals("")) {
				JOptionPane.showMessageDialog( null , "�Է����� ���� ���� �ֽ��ϴ�.");
			} else if(!releaseField.getText().matches(pattern)) {// (�⵵-��-��) �� �������� �Ѿ������ üũ
				JOptionPane.showMessageDialog( null , "������� (YYYY-MM-dd)������ �ƴմϴ�. �ٽ� �Է����ּ���.");

			} else if(!audienceField.getText().matches(pattern2)||audienceField.getText().equals("")) {
				JOptionPane.showMessageDialog( null , "�������� ������ �ƴմϴ�. �ٽ� �Է����ּ���.");
			} else{
				ArrayList a = new ArrayList();
				String[] str = actorField.getText().split("\\n");
				for(int i=0; i<str.length; i++){
					a.add(str[i]);
				}
				int audience=Integer.parseInt(audienceField.getText());
				JOptionPane.showMessageDialog( null , "���������� �����ƽ��ϴ�.");
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
				dispose();					// ���� â�� �ݱ�
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
