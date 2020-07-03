package Lib_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class student_login implements ActionListener{
	
	JFrame f;
	JButton b1,b2;
	JLabel l1,l2,l,l3;
	JTextField t1;
	JPasswordField t2;
	
	student_login()
	{
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		l =new JLabel("STUDENT LOGIN!");
		l.setBounds(120, 20, 250, 30);
		l.setFont(new Font("serif", Font.ITALIC, 30));
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/student_login.png"));
	    Image i2 = i1.getImage().getScaledInstance(200,150,Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    l3 = new JLabel(i3);
	    l3.setBounds(350, 80, 100, 135);		
	    
		l1=new JLabel("Username:");
		l1.setBounds(10,100 ,80, 20);
		
		t1=new JTextField();
		t1.setBounds(90, 100, 200, 20);
		
		l2=new JLabel("Password:");
		l2.setBounds(10, 150, 80, 20);
		
		t2=new JPasswordField();
		t2.setBounds(90, 150, 200, 20);
		
		b1=new JButton("Login");
		b1.setBounds(80, 200,80, 30);
		b1.addActionListener(this);
		
		b2=new JButton("Cancel");
		b2.setBounds(200, 200, 80, 30);
		b2.setBackground(Color.black);
		b2.setForeground(Color.white);
		b2.addActionListener(this);
		
		f.add(b1);f.add(b2);f.add(l1);f.add(l2);f.add(t1);f.add(t2);f.add(l);f.add(l3);
		
		f.getContentPane().setBackground(Color.WHITE);	
		f.setSize(500, 300);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)		// Login Button
		{
			db_connection db=new db_connection();
			try {
				String user=t1.getText();
				String pswd=t2.getText();
				
				String q="SELECT * FROM students WHERE e_mail='"+user+"' AND password='"+pswd+"' ";
				ResultSet rs=db.stmt.executeQuery(q);
				if(rs.next())
				{
					new student_portal(user,pswd).f.setVisible(true);
					f.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(f, "Please enter correct information");
				}
				rs.close();
				db.stmt.close();
				db.con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		if (e.getSource()==b2) {		//Cancel Button
			main_screen ms=new main_screen();
			f.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		student_login sLogin=new student_login();
	}
}


