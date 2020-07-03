package Lib_management;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

/**
 * -->this class has to be called through method so needs to be transffered to the method --> Done
 * -->change password section with update section consisting of usn of the user--> 		(Done but Some issue still exists)
 * -->button to check for the avalability of the books with the check box to load a new type of the table
 * --> adding the heading label with stickers of the images
 * --> add the show books button
 */
public class student_portal implements ActionListener {
	
	JFrame f;
	JButton b1,b2,b3,b4;
	JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	JComboBox<String> c1;
	JTable table;
	JScrollPane jp;
	
	
	public student_portal(String user,String pswd) {//
		
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		l=new JLabel("STUDENT PORTAL");
		l.setBounds(10,10,300,50);
//		l.setBorder(BorderFactory.createEtchedBorder(Color.gray, Color.gray.brighter()));
		l.setFont(new Font("serif",Font.ITALIC,40));
		f.add(l);
		
		l1=new JLabel("USN :");
		l1.setBounds(10, 10, 30, 20);
		t1=new JTextField();
		t1.setEnabled(false);
		t1.setDisabledTextColor(Color.black);
		t1.setBounds(50, 10,100, 20);
		b1=new JButton("Search");
			
		l2=new JLabel("NAME :");
		l2.setBounds(180, 10, 40, 20);
		t2=new JTextField();
		t2.setDisabledTextColor(Color.black);
		t2.setBounds(230, 10, 200, 20);
		t2.setEnabled(false);
		
		l3=new JLabel("BRANCH :");
		l3.setBounds(10, 40, 60, 20);
		t3=new JTextField();
		t3.setDisabledTextColor(Color.black);
		t3.setBounds(70, 40, 50, 20);
		t3.setEnabled(false);
		
		l4=new JLabel("YEAR :");
		l4.setBounds(150, 40, 40, 20);
		t4=new JTextField();
		t4.setDisabledTextColor(Color.black);
		t4.setBounds(195, 40, 50, 20);
		t4.setEnabled(false);
		
		l5=new JLabel("SEMESTER :");
		l5.setBounds(275, 40,75, 20);
		t5=new JTextField();
		t5.setDisabledTextColor(Color.black);
		t5.setBounds(350, 40,40, 20);
		t5.setEnabled(false);
		
		l6=new JLabel("E-MAIL ID :");
		l6.setBounds(10, 70, 60, 20);
		t6=new JTextField();
		t6.setDisabledTextColor(Color.black);
		t6.setBounds(75, 70, 200, 20);
		t6.setEnabled(false);
		
		b1=new JButton("UPDATE PASSWORD");
		b1.setBounds(290, 70, 155, 20);
		b1.addActionListener(this);

		l7=new JLabel("**Search book's avaiability**");
		l7.setBounds(10, 100, 200, 15);
		
		l8=new JLabel("Select Category:");
		l8.setBounds(10, 125, 100, 15);
		String category[]= {"select","Book ID","Name","Author Name",};
		c1=new JComboBox<String>(category);
		c1.setBounds(110, 125, 150, 20);
		l9=new JLabel("Ente Value");
		l9.setBounds(280, 125, 60, 20);
		t7=new JTextField();
		t7.setBounds(350, 125, 100, 20);	
		b2=new JButton("search"); 
		b2.setBounds(460, 125, 100, 20);
		b2.addActionListener(this);		
		
		show_student_data(user,pswd);
		
		DefaultTableModel default_table =new DefaultTableModel();
		default_table.addColumn("S.No.");
		default_table.addColumn("ID");
		default_table.addColumn("NAME");
		default_table.addColumn("AUTHOR");
		default_table.addColumn("Issue Date");
		default_table.addColumn("Due Date");
		default_table.addColumn("Fine");	
		
		table=new JTable(default_table);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(35);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		
		table.setFillsViewportHeight(true);
		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(20, 250, 610, 103);
		table.setEnabled(false);
		f.add(jp);
		
		try {
			db_connection issued_data=new db_connection();
			String sql="SELECT issued_books.book_id,name,`author's_name`,date_of_issue,return_date,fine FROM issued_books "
					+ "INNER JOIN books ON issued_books.book_id=books.book_id "
					+ "WHERE issued_books.usn= '"+t1.getText()+"' ";
			ResultSet rs= issued_data.stmt.executeQuery(sql);
			int i=0;
			while(rs.next())
			{
				default_table.addRow(new Object[0]);
				default_table.setValueAt(i+1 , i, 0);
				default_table.setValueAt( rs.getString(1), i, 1);
				default_table.setValueAt( rs.getString(2), i, 2);
				default_table.setValueAt( rs.getString(3), i, 3);
				default_table.setValueAt(rs.getDate(4), i, 4);
				default_table.setValueAt( rs.getDate(5), i, 5);
				default_table.setValueAt( rs.getInt(6), i, 6);
				i++;				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			
		
		b3=new JButton("Logout");
		b3.setBounds(295, 370, 80, 20);
		b3.addActionListener(this);
		
		JPanel panel=new JPanel();
		panel.setBounds(20,80,600,150);
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.add(b1);panel.add(b2);
		panel.add(l1);panel.add(l2);panel.add(l3);panel.add(l4);panel.add(l5);panel.add(l6);panel.add(l7);panel.add(l8);panel.add(l9);
		panel.add(t1);panel.add(t2);panel.add(t3);panel.add(t4);panel.add(t5);panel.add(t6);panel.add(t7);
		panel.add(c1);
										
		
		f.add(panel);
		f.add(b3);
		f.getContentPane().setBackground(Color.white);
		f.setSize(650,450);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void show_student_data(String user,String pswd) {
		// TODO Auto-generated method stub
		try{
		db_connection user_data=new db_connection();
		String sql="SELECT * FROM students WHERE e_mail='"+user+"' AND password='"+pswd+"'";
		ResultSet rSet=user_data.stmt.executeQuery(sql);
		rSet.next();
		t1.setText(rSet.getString(1));
		t2.setText(rSet.getString(2));
		t3.setText(rSet.getString(3));
		t4.setText(rSet.getString(4));
		t5.setText(rSet.getString(5));
		t6.setText(rSet.getString(6));
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
	}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==b1)		//update assword button
		{
			String newPassword=JOptionPane.showInputDialog(f, "Enter new password");
			System.out.println(newPassword);
			if(newPassword==null){
				JOptionPane.showMessageDialog(f, "Enter valid password");
			}
			else {
				String usn=t1.getText();
				try{
					db_connection update=new db_connection();
					String sql="UPDATE students SET password = '"+newPassword+"' WHERE usn='"+usn+"'";
					int rs=update.stmt.executeUpdate(sql);
					if(rs>0) {
						JOptionPane.showMessageDialog(f,"Successfully Updated the Password");
					}
					
					//SHOW HERE UPDATED SUCCESSFULLY JOPTIONPANE MESSAGE
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2);
				}
			}
		}
		
		if (e.getSource()==b2) {    // search button   
			String category_selected=(String)c1.getSelectedItem();
			category_selected=category_selected.replaceAll("\\s+", "_");
			if (category_selected.equalsIgnoreCase("select")) {
				JOptionPane.showMessageDialog(f, "Select Proper Seach Option");
			}
			else {
				try {
					db_connection display =new db_connection();
					String sql="SELECT * FROM books WHERE "+category_selected+"='"+t7.getText()+"'";
					ResultSet rs=display.stmt.executeQuery(sql);
					if (rs.next()) {
						DefaultTableModel default_table1 =new DefaultTableModel();
						default_table1.addColumn("S.No.");
						default_table1.addColumn("ID");
						default_table1.addColumn("NAME");
						default_table1.addColumn("AUTHOR");
						default_table1.addColumn("Publisher");
						default_table1.addColumn("Quantity");
						default_table1.addColumn("Stock");	
						
						table=new JTable(default_table1);
						table.getColumnModel().getColumn(0).setPreferredWidth(0);
						table.getColumnModel().getColumn(1).setPreferredWidth(20);
						table.getColumnModel().getColumn(2).setPreferredWidth(100);
						table.getColumnModel().getColumn(3).setPreferredWidth(100);
						table.getColumnModel().getColumn(4).setPreferredWidth(35);
						table.getColumnModel().getColumn(5).setPreferredWidth(35);
						table.getColumnModel().getColumn(6).setPreferredWidth(20);
						
						table.setFillsViewportHeight(true);
						jp=new JScrollPane(table);
						jp.setBounds(20, 250, 610, 103);
						table.setEnabled(false);
						f.add(jp);
						
						int i=0;
						while(rs.next())
						{
							System.out.println("got the result of database");
							default_table1.addRow(new Object[0]);
							default_table1.setValueAt(i+1 , i, 0);
							default_table1.setValueAt( rs.getString(1), i, 1);
							default_table1.setValueAt( rs.getString(2), i, 2);
							default_table1.setValueAt( rs.getString(3), i, 3);
							default_table1.setValueAt( rs.getDate(4), i, 4);
							default_table1.setValueAt( rs.getDate(5), i, 5);
							default_table1.setValueAt( rs.getInt(6), i, 6);
							i++;				
						}
					}
					else {
						JOptionPane.showMessageDialog(f, "NO SUCH BOOK EXISTS");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2);
				}
			}
		}
		if (e.getSource()==b3) {		//log out button
			new student_login().f.setVisible(true);
			f.setVisible(false);
			
		}
	}
//	public static void main(String[] args) {
//		student_portal portal=new student_portal();
//	}

}
