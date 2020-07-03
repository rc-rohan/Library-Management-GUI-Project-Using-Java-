package Lib_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author rohan
 *
 *--> while doing logout move to the main screen
 */
public class admin_portal implements ActionListener {
	
	JFrame f;
	JButton b1,b2,b3,b4,b5;
	JLabel l;
	
	public admin_portal() {
		// TODO Auto-generated constructor stub
		f=new JFrame("LIBRARY MANAGEMENT");
		
		l=new JLabel("ADMIN PORTAL");
		l.setFont(new Font("serif", Font.ITALIC, 30));
		l.setBounds(150, 20, 230, 30);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/add_admin.png"));
	    Image i2 = i1.getImage().getScaledInstance(105,100,Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
		b1=new JButton("Add Admin",i3);
		b1.setBounds(135, 120, 115, 124);
		b1.setHorizontalTextPosition(JButton.CENTER);
		b1.setVerticalTextPosition(JButton.BOTTOM);
		b1.setBackground(Color.white);
		b1.addActionListener(this);
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/student_registrations.png"));
	    Image i5 = i4.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
	    ImageIcon i6 = new ImageIcon(i5);
		b2=new JButton("Register Student",i6);
		b2.setBounds(260, 120, 129, 124);
		b2.setBackground(Color.white);
		b2.setHorizontalTextPosition(JButton.CENTER);
		b2.setVerticalTextPosition(JButton.BOTTOM);
		b2.addActionListener(this);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/admin_library.jpg"));
	    Image i8 = i7.getImage().getScaledInstance(115,100,Image.SCALE_DEFAULT);
	    ImageIcon i9 = new ImageIcon(i8);
		b3=new JButton("Library",i9);
		b3.setBounds(10, 120, 115, 124);
		b3.setHorizontalTextPosition(JButton.CENTER);
		b3.setVerticalTextPosition(JButton.BOTTOM);
		b3.setBackground(Color.white);
		b3.addActionListener(this);
		
//		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("Lib_Management/icons/logout.png"));
//	    Image i11 = i10.getImage().getScaledInstance(70,40,Image.SCALE_DEFAULT);
//	    ImageIcon i12 = new ImageIcon(i11);
		b5=new JButton("Logout");
		b5.setBounds(142, 255, 100, 30);
		b5.setBackground(Color.black);
		b5.setForeground(Color.white);
		b5.addActionListener(this);
		
		
		
		f.add(b1);f.add(b2);f.add(b3);f.add(b5);
		f.add(l);
		
		f.setSize(600, 350);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.white);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==b1) 
		{	
			new add_admin().f.setVisible(true);
			f.setVisible(false);
		}
		if (e.getSource()==b2) {
			new register_student().f.setVisible(true);
			f.setVisible(false);
			
			
		}
		if (e.getSource()==b3) {
			new library().f.setVisible(true);
			f.setVisible(false);
		}
		if (e.getSource()==b5) {
			new admin_login().f.setVisible(true);
			f.setVisible(false);
		}

		
	}
	public static void main(String[] args) {
		admin_portal aPortal=new admin_portal();
	}

}
