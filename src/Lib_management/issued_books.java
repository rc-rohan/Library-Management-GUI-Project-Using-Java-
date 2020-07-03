package Lib_management;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
/**
 * -->there will be 2 tabbed panes		(Done)
 * -->one for currently issued books		(Done)
 * -->add the name of the student also in the Column
 * --> other for all the books issued till today		
 * -->in the history tb make the status value in the 2 colors
 * -->in the status tab while creating set the defult value to not returned
 *--> add the filter button to select the pending status
 *-->instead oh haaving the another tab we can get the entire things of the issued books in the same tab with a column status 
 */
public class issued_books {

	JFrame f;
	JTable table;
	JScrollPane jp;
	JTabbedPane tp1;
	JPanel p1,p2;
	JButton b1;
	
	public issued_books(){
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.white);
		p1.setBounds(10,60,400,400);
		
		DefaultTableModel table_model=new DefaultTableModel();
		table_model.addColumn("S.NO");
		table_model.addColumn("USN");
		table_model.addColumn("Book ID");
		table_model.addColumn("Issue Date");
		table_model.addColumn("Return Date");
		table_model.addColumn("Fine");
		
		table=new JTable(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		
		table.setFillsViewportHeight(true);
		
		jp=new JScrollPane(table);
		jp.setBounds(10,60,460,185);
		p1.add(jp);
		
		try {
			db_connection display_data=new db_connection();
			String sql="SELECT * FROM issued_books ORDER BY date_of_issue ";
			//after updating the issued books tabs add the codition where staus=returned/not..
			ResultSet rs=display_data.stmt.executeQuery(sql);
			int i=0;
			while(rs.next()) {
				table_model.addRow(new Object[0]);
				table_model.setValueAt(i+1,i,0);
				table_model.setValueAt(rs.getString(1), i, 1);
				table_model.setValueAt(rs.getString(2),i,2);
				table_model.setValueAt(rs.getString(3),i,3);
				table_model.setValueAt(rs.getString(4),i,4);
				table_model.setValueAt(rs.getString(5),i,5);
				i++;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBackground(Color.white);
		p2.setBounds(10,60,400,400);
		
		tp1=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
		tp1.addTab("Issued Books", p1);
		tp1.addTab("History", p2);	
		tp1.setBounds(0, 0, 485, 400);
		
		f.add(tp1);
		f.getContentPane().setBackground(Color.white);
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		issued_books show =new issued_books();
	}
}
