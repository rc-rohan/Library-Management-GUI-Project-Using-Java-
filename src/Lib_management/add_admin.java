package Lib_management;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author rohan
 *-->add only the username and the password of the librarian
 *-->also add other details of the librarian
 */
public class add_admin implements ActionListener{

	JFrame f;
	JLabel l1;
	JButton b1;
	add_admin()
	{
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		l1=new JLabel("work under progress ");
		l1.setBounds(20, 20, 200, 20);
		b1=new JButton("Back");
		b1.setBounds(200,150,100,50);
		b1.addActionListener(this);
		f.add(b1);f.add(l1);
		
		f.setSize(600,350);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==b1) {
			new admin_portal().f.setVisible(true);
			f.setVisible(false);
			
		}
		
	}
	public static void main(String[] args) {
		add_admin add_admin=new add_admin();
	}
}
