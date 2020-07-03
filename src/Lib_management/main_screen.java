package Lib_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * --> Close the databse connection from everywhere.
 * -->every time opening a new JFrame might create some delay over the stack of=r might be there 
 *		some high amount of memory usage.  So, check for the data structe and for proper algo of this
 * -->Add the main function only in this class and make sure that whenever the user opens the jar file the db_connections are established 
 * 		automatically
 */

import javax.swing.*;
/**
 *--> check for the memory management and time management
 *-->make sure to update the data in java itself using variables instead of updating in the mysql query 
 *
 *
 *-->can use an tringger function in the database if the user sends the empty data or spaces and rject those values   (optional)
 */
public class main_screen implements ActionListener{
	JFrame f;
	JLabel l,l1,l2,l3,l4,l5;
	JButton b1,b2;
		public main_screen() {
		// TODO Auto-generated constructor stub
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/lib_main_screen.png"));
		Image i2=i1.getImage().getScaledInstance(250, 132, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		l2=new JLabel(i3);
		l2.setBounds(120, 20, 250, 160);
		
		l1=new JLabel("Welcome to Library Management");
		b1=new JButton("Admin Login");
		b1.setBounds(50, 250, 150, 50);
		b1.addActionListener(this);
		
		b2=new JButton("Student Login");
		b2.setBounds(300, 250, 150, 50);
		b2.addActionListener(this);
		
		f.add(b1);f.add(b2);
		f.add(l2);
		
		f.setSize(500,400);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.white);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public static void main(String[] args) {
		main_screen mScreen=new main_screen();
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)
		{
			new admin_login();
			f.setVisible(false);
		}
		if (e.getSource()==b2) {
			new main_screen();
			f.setVisible(false);
		}
	}

}
