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

/* User - ��ȭ�� �����ϴ� ȭ�� */

public class SortGUI extends JFrame {

	private String [] menu = {"����","������","����","�����","������"};		// �޺��ڽ� ������
	private JComboBox<String> strCombo = new JComboBox<String>(menu);
	private JTextArea ML = new JTextArea();	
	JScrollPane scrollPane = new JScrollPane(ML);
	private JButton backBtn = new JButton("�ڷΰ���");			// �ڷΰ��� ��ư
	private JButton detailBtn = new JButton("��ȭ ��������");	// ��ȭ �������� ��ư
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
		setTitle("��ȭ ���� & ��õ");					// ����
		setSize(500,500);							// ũ��
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
		panel.add(ML);						// ��ȭ ���
		panel.add(new JScrollPane(ML));		// ��ũ��
		ML.setEditable(false);				// TextArea�� �� �Է� X
		ML.setLineWrap(true);
		ML.append(mb.MovieList_GUI());	
		return panel;
	}
	
	private JPanel createBottom() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(detailBtn);	// ��ȭ �������� ��ư
		panel.add(backBtn);		// �ڷΰ��� ��ư
		return panel;
	}
	// ��ư ������ �� �̺�Ʈ ó��
	private void addListeners() {
		strCombo.addActionListener(new ComboBoxListener());
		backBtn.addActionListener(new BackwardListener());			// �ڷΰ��� ��ư
		detailBtn.addActionListener(new DetailedListener());		// ��ȭ �������� ��ư
	}
	// �޺��ڽ� �̺�Ʈ
	class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(strCombo.getSelectedItem().toString()=="����") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "1"));
			}
			if(strCombo.getSelectedItem().toString()=="������") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "2"));
			}
			if(strCombo.getSelectedItem().toString()=="����") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "3"));
			}
			if(strCombo.getSelectedItem().toString()=="�����") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "4"));
			}
			if(strCombo.getSelectedItem().toString()=="������") {
				ML.setText("");
				ML.append(mb.sortMovieList_GUI(mb, "5"));
			}
		}
	}
	// �ڷΰ��� ��ư �̺�Ʈ
	class BackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();				// ���� â �ݱ�
		}
	}
	// ��ȭ �������� ��ư �̺�Ʈ
	class DetailedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new detailedGUI(user, mb, rb);
		}
	}
}
