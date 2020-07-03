package Lib_management;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *-->will be always loaded with all the books in the database		(Done) 
 *-->search books will be implmented here showing the availability of the book		(Done)
 * --> search button is not functioning properly   (Done)
 *
 *--> remove the search button section and create the fiter section
 *-->create a button for section with the total number of books issued with details of the books
 *-->it will show all the books with a count showing the total number of books at the top corner
 *-->create an databse table where history of all the books issued to the students will be displayed to the admin
 */
public class books implements ActionListener {

	JFrame f;
	JButton b1,b2,b3,b4;
	JLabel l,l1,l2,l3;
	JTable table;
	JComboBox<String> c1;
	JTextField t1,t2,t3;
	DefaultTableModel default_table;

	public books() {
		// TODO Auto-generated constructor stub
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		l=new JLabel("Check Book Availabilty");
		l.setFont(new Font("serif", Font.ITALIC, 40));
		l.setBounds(120,10,390,50);
		f.add(l);
		
		
		l1=new JLabel("Select Category:");
		l1.setBounds(10, 10, 100, 20);
		String category[]= {"select category","Book ID","Name"};
		c1=new JComboBox<String>(category);
		c1.setBounds(120, 10, 150, 20);
		l2=new JLabel("Ente Value :");
		l2.setBounds(290, 10, 70, 20);
		t1=new JTextField();
		t1.setBounds(365, 10, 100, 20);
		b1=new JButton("Search");
		b1.setBounds(480, 10, 80, 20);
		b1.addActionListener(this);
		
		JPanel panel=new JPanel();
		panel.setBounds(10, 90, 570, 40);
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		
		default_table =new DefaultTableModel();
		default_table.addColumn("S.No.");
		default_table.addColumn("ID");
		default_table.addColumn("Name");
		default_table.addColumn("Author");
		default_table.addColumn("Publisher");
		default_table.addColumn("Qty.");	
		default_table.addColumn("Stock");
		
		table=new JTable(default_table);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(35);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(25);
		table.getColumnModel().getColumn(6).setPreferredWidth(25);		
		
		table.setFillsViewportHeight(true);
		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(10, 150, 610, 180);
		f.add(jp);
		
		try {
			db_connection issued_data=new db_connection();
			String sql="SELECT * FROM books  ";
			ResultSet rs= issued_data.stmt.executeQuery(sql);
				int i=0;
			while(rs.next())
			{
				default_table.addRow(new Object[0]);
				default_table.setValueAt(i+1 , i, 0);
				default_table.setValueAt( rs.getString(1), i, 1);
				default_table.setValueAt( rs.getString(2), i, 2);
				default_table.setValueAt(rs.getString(3), i, 3);
				default_table.setValueAt( rs.getString(4), i, 4);
				default_table.setValueAt( rs.getInt(5), i, 5);
				default_table.setValueAt(rs.getInt(5), i, 6);
				i++;				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		panel.add(b1);
		panel.add(t1);
		panel.add(l1);panel.add(l2);
		panel.add(c1);
		
		f.add(panel);
		
		f.setSize(650, 400);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1) {
			String category_selected=(String)c1.getSelectedItem();
			category_selected=category_selected.replaceAll("\\s+", "_").toLowerCase();
			if (category_selected.equalsIgnoreCase("select_Category") || t1.getText().trim().length()==0 ) {
				JOptionPane.showMessageDialog(f, "Select Proper Search Filter and Enter Proper Value");
			}
			else {
				try {
					db_connection display =new db_connection();
					String sql="SELECT * FROM books WHERE "+category_selected+"='"+t1.getText()+"'";
					System.out.println("gettinng the data");
					ResultSet rs=display.stmt.executeQuery(sql);
					int i=0;
					while (rs.next()) {
						
						default_table.addRow(new Object[0]);
						default_table.setValueAt(i+1 , i, 0);
						default_table.setValueAt( rs.getString(1), i, 1);
						default_table.setValueAt( rs.getString(2), i, 2);
						default_table.setValueAt(rs.getString(3), i, 3);
						default_table.setValueAt( rs.getString(4), i, 4);
						default_table.setValueAt( rs.getInt(5), i, 5);
						default_table.setValueAt(rs.getInt(5), i, 6);
						i++;
					
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		
	}
	public static void main(String[] args) {
		books b=new books();
	}

}
