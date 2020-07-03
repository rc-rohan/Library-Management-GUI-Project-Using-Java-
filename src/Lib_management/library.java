package Lib_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * -->issued Books section and sarch book section to books and implement the function there
 * -button for issued books showing the students usn took the with due date and due amount
 * -issue books section consisting with book id auto search and student search id and autofill section
 * -use some png in the back button internet explorer format
 * -return book showing the due date
 * -in add books section if the book is already there then select the prefered option to search the book and increase the book qty
 */
public class library implements ActionListener{

	JFrame f;
	JButton b1,b2,b3,b4,b6,b7;
	JLabel l,l1;
	
	public library() {
		// TODO Auto-generated constructor stub
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/library.png"));
	    Image i2 = i1.getImage().getScaledInstance(200,250,Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    l1 = new JLabel(i3);
	    l1.setBounds(420, 20, 150, 250);
		
		l=new JLabel("LIBRARY");
		l.setBounds(200, 10, 300, 50);
		l.setFont(new Font("serif",Font.ITALIC, 40));
		
		b1=new JButton("View Books");
		b1.addActionListener(this);
		b1.setBounds(20, 120, 115, 40);
		b1.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.gray.brighter()));
		b1.setFocusable(false);
		
		b2=new JButton("Student Details");
		b2.setBounds(80, 180, 120, 40);
		b2.addActionListener(this);
		b2.setFocusable(false);
		
		b3=new JButton("Issued Books");
		b3.setBounds(155, 120, 115, 40);
		b3.addActionListener(this);
		b3.setFocusable(false);
		
		b4=new JButton("Borrow Books");
		b4.setBounds(230, 180, 115, 40);
		b4.addActionListener(this);
		b4.setFocusable(false);
		
		b6=new JButton("Add Books");
		b6.setBounds(295, 120, 115, 40);
		b6.addActionListener(this);
		b6.setFocusable(false);
		
		b7=new JButton("Back");
		b7.setBounds(170, 240, 80, 40);
		b7.addActionListener(this);
		b7.setFocusable(false);
		
		f.add(b1);f.add(b2);f.add(b3);f.add(b4);f.add(b6);f.add(b7);
		f.add(l);f.add(l1);
		
		f.getContentPane().setBackground(Color.white);
		f.setSize(600, 350);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==b1) {		// view books to check the availability
			new books().f.setVisible(true);
			
		}
		if (e.getSource()==b2) {		//student_details 
			new student_details().f.setVisible(true);
			f.setVisible(false);
		}
		if (e.getSource()==b3) {		//issued books
			new issued_books().f.setVisible(true);
			f.setVisible(false);
		}
		if (e.getSource()==b4) {		//borrow Books
			new issue_new_books().f.setVisible(true);
			f.setVisible(false);
		}
		
		if (e.getSource()==b6) {		//add books
			new add_book().f.setVisible(true);
			f.setVisible(false);
		}
		if (e.getSource()==b7) {		//back button
			new admin_portal().f.setVisible(true);
			f.setVisible(false);
		}
	}
	public static void main(String[] args) {
		library lib=new library();
	}
}
