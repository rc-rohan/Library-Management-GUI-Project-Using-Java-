package Lib_management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * -add joptionpane showing message about succefull addition of the data else data not entered
 *--check for the already registered student so that there won't be any duplicate usn
 */
public class register_student implements ActionListener{
	
	JFrame f;
	JButton b1,b2,b3;
	JLabel l,l1,l2,l3,l4,l5,l6,l7;
	JComboBox<String> c1,c2,c3;
	JTextField t1,t2,t3,t4;
	
	
	register_student(){
		
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		l=new JLabel("Student Registration!");
		l.setBounds(65, 0,280, 50);
		l.setFont(new Font("serif",Font.ITALIC,30));
		
		l1=new JLabel("USN:");
		l1.setBounds(10, 60,30, 20);
		t1=new JTextField();
		t1.setBounds(50, 60, 80, 20);
		
		l2=new JLabel("Name:");
		l2.setBounds(150, 60,40, 20);
		t2=new JTextField();
		t2.setBounds(200, 60, 170, 20);
		
		l3=new JLabel("Branch:");//JComBox can be used here
		l3.setBounds(10, 100, 50, 20);
		String content[]= {"","CSE","ECE","ISE","MECH","CHEM","ETC","CIVIL"};
		c1=new JComboBox<>(content);
		c1.setBounds(65, 100, 60, 20);
		
		l4=new JLabel("Year:");
		l4.setBounds(150, 100, 30, 20);
		String year[]= {"","First","Second","Third","Fourth"};
		c2=new JComboBox<>(year);
		c2.setBounds(190, 100, 70, 20);
		
		l5=new JLabel("Sem:");
		l5.setBounds(280, 100, 100, 20);
		String sem[]= {"","1st","2nd","3rd","4th","5th","6th","7th","8th"};
		c3=new JComboBox<>(sem);
		c3.setBounds(320, 100, 50, 20);
		
		l7=new JLabel("E-Mail ID:");
		l7.setBounds(10, 140, 70, 20);
		t4=new JTextField();
		t4.setBounds(80, 140, 190, 20);
		
		l6=new JLabel("Password:");
		l6.setBounds(10, 175, 70, 20);
		t3=new JTextField("----click on genrate----");
		t3.setBounds(85, 175, 130, 20);
		t3.setEnabled(false);
		b1=new JButton("Generate");
		b1.setBounds(220, 175, 90, 20);
		b1.addActionListener(this);
		
		b2=new JButton("REGISTER");
		b2.setBounds(75, 210, 100, 30);
		b2.addActionListener(this);
		
		b3=new JButton("CANCEL");
		b3.setBounds(195, 210, 100, 30);
		b3.addActionListener(this);
		
		f.add(l1);f.add(l2);f.add(l3);f.add(l4);f.add(l5);f.add(l6);f.add(l7);f.add(c1);f.add(c2);
		f.add(t1);f.add(t2);f.add(t3);f.add(t4);f.add(l);f.add(c3);f.add(b1);f.add(b2);f.add(b3);
		
		f.setSize(400, 300);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.white);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)		//genrate password button
		{
			String passwordSet ="abcdefghijklmnopqrstuvwxyz0123456789";
			char pswd[]=new char[8];
			for(int i=0;i<8;i++)
			{
				int random = (int)(Math.random()*passwordSet.length());
				pswd[i]=passwordSet.charAt(random);
			}
			String password=String.valueOf(pswd);
//			System.out.println("Student password is:"+password);
			t3.setText(password);
			t3.setDisabledTextColor(Color.black);			
		}	
		if (e.getSource()==b2) {		//Register button	
		if(t1.getText().trim().length()==0 || t2.getText().trim().length()==0 || t3.getText().trim().length()==0 || 
				t4.getText().trim().length()==0	|| ((String)c1.getSelectedItem()).trim().length()==0 ||
				((String)c2.getSelectedItem()).trim().length()==0 || ((String)c3.getSelectedItem()).trim().length()==0	) {
			
			JOptionPane.showMessageDialog(f,"Fill all the Details");
		}
		else{
				try {
				System.out.println("Register button pressed");
				db_connection add=new db_connection();
				String sql="INSERT INTO students(usn,name,branch,year,sem,e_mail,password)VALUES(?,?,?,?,?,?,?)";
				PreparedStatement pstmt=add.con.prepareStatement(sql);
				pstmt.setString(1, t1.getText());
				pstmt.setString(2, t2.getText());
				pstmt.setString(3, (String)c1.getSelectedItem());
				pstmt.setString(4, (String)c2.getSelectedItem());
				pstmt.setString(5, (String)c3.getSelectedItem());
				pstmt.setString(6, t4.getText());
				pstmt.setString(7, t3.getText());
				System.out.println("inserting the data");
				int result=pstmt.executeUpdate();
				if(result>0)
				{
					JOptionPane.showMessageDialog(f,"Successfully registered");
					t1.setText("");
					t2.setText("");
					c1.setSelectedIndex(0);
					c2.setSelectedIndex(0);
					c3.setSelectedIndex(0);
					t4.setText("");
					t3.setText("");
				}
		    	} catch (Exception e2) {
				// TODO: handle exception
		    	JOptionPane.showMessageDialog(f,"Student is already Registered");
				System.out.println(e2);
			}
		}
		}
		if (e.getSource()==b3) {		//cancel button
			new admin_portal();
			f.setVisible(false);
			
		}
		
	}
	
	public static void main(String[] args) {
		register_student rStudent=new register_student();
	}

}
