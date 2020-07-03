package Lib_management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class student_details implements ActionListener {

	JFrame f;
	JButton b1, b2, b3, b4;
	JLabel l,l1, l2, l3, l4, l5, l6, l7, l8, l9;
	JTextField t1, t2, t3, t4, t5, t6, t7;
	JComboBox<String> c1;
	JTable table;
	JScrollPane jp;

	/**
	 * -->REturn button not fuctioning properly
	 * 
	 * -->add return books option/button in the table (Done) --> Search button is
	 * not functional (Done)
	 */

	student_details() {

		f = new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		l=new JLabel("Student-Details");
		l.setFont(new Font("serif", Font.ITALIC, 40));
		l.setBounds(180,10,300,50);
		f.add(l);
		
		
		
		l1 = new JLabel("USN           :");
		l1.setBounds(10, 10, 60, 20);
		t1 = new JTextField();
		t1.setBounds(85, 10, 75, 20);
		b1 = new JButton("Search");
		b1.setBounds(180, 10, 80, 20);
		b1.addActionListener(this);

		l2 = new JLabel("NAME        :");
		l2.setBounds(10, 40, 70, 20);
		t2 = new JTextField();
		t2.setBounds(85, 40, 200, 20);
		t2.setEnabled(false);

		l3 = new JLabel("BRANCH   :");
		l3.setBounds(10, 70, 70, 20);
		t3 = new JTextField();
		t3.setBounds(85, 70, 50, 20);
		t3.setEnabled(false);

		l4 = new JLabel("YEAR :");
		l4.setBounds(160, 70, 40, 20);
		t4 = new JTextField();
		t4.setBounds(205, 70, 50, 20);
		t4.setEnabled(false);

		l5 = new JLabel("SEMESTER :");
		l5.setBounds(285, 70, 75, 20);
		t5 = new JTextField();
		t5.setBounds(360, 70, 40, 20);
		t5.setEnabled(false);

		l6 = new JLabel("E-MAIL ID  :");
		l6.setBounds(10, 100, 70, 20);
		t6 = new JTextField();
		t6.setBounds(85, 100, 200, 20);
		t6.setEnabled(false);

		JPanel panel = new JPanel();
		panel.setBounds(10, 90, 550, 130);
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.add(l1);panel.add(l2);panel.add(l3);panel.add(l4);panel.add(l5);panel.add(l6);
		panel.add(t1);panel.add(t2);panel.add(t3);panel.add(t4);panel.add(t5);panel.add(t6);
		panel.add(b1);

		

		b2 = new JButton("Issue Book"); // optional implement it such that as soon as the frame loads it shows the books
		b2.setBounds(170, 350, 100, 20);
		b2.addActionListener(this);

		b3 = new JButton("Back");
		b3.setBounds(300, 350, 100, 20);
		b3.addActionListener(this);

		f.add(panel);f.add(b2);f.add(b3);

		f.setSize(650, 450);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == b1) // Search button for USN search
		{
			System.out.println("search button pressed");
			try {
				db_connection display = new db_connection();
				String sql1 = "SELECT * FROM students WHERE usn ='" + t1.getText() + "'";
				ResultSet rs1 = display.stmt.executeQuery(sql1);
				if (rs1.next()) {
					t2.setText(rs1.getString(2));
					t2.setDisabledTextColor(Color.black);
					t3.setText(rs1.getString(3));
					t3.setDisabledTextColor(Color.black);
					t4.setText(rs1.getString(4));
					t4.setDisabledTextColor(Color.black);
					t5.setText(rs1.getString(5));
					t5.setDisabledTextColor(Color.black);
					t6.setText(rs1.getString(6));
					t6.setDisabledTextColor(Color.black);
					
					
					DefaultTableModel default_table = new DefaultTableModel();
					default_table.addColumn("S.No.");
					default_table.addColumn("ID");
					default_table.addColumn("Name");
					default_table.addColumn("AUTHOR");
					default_table.addColumn("Issue Date");
					default_table.addColumn("Due Date");
					default_table.addColumn("Fine");
					default_table.addColumn("Return");

					table = new JTable(default_table);
					table.getColumnModel().getColumn(0).setPreferredWidth(20);
					table.getColumnModel().getColumn(1).setPreferredWidth(30);
					table.getColumnModel().getColumn(4).setPreferredWidth(55);
					table.getColumnModel().getColumn(5).setPreferredWidth(60);
					table.getColumnModel().getColumn(6).setPreferredWidth(30);
					table.getColumnModel().getColumn(7).setPreferredWidth(50);

					TableCellRenderer buttonRenderer = new JTableButtonRenderer();
					table.getColumnModel().getColumn(7).setCellRenderer(buttonRenderer);
					table.addMouseListener(new JTableButtonMouseListener(table));

					table.setFillsViewportHeight(true);
//					table.setEnabled(false);
					JScrollPane jp = new JScrollPane(table);
					jp.setBounds(10, 230, 610, 103);
					f.add(jp);

					try {
						db_connection issued_data = new db_connection();
						String sql = "SELECT issued_books.book_id,name,`author's_name`,date_of_issue,return_date,fine FROM issued_books "
								+ "INNER JOIN books ON issued_books.book_id=books.book_id"
								+ " WHERE issued_books.usn= '"+t1.getText()+"' ";
						ResultSet rs = issued_data.stmt.executeQuery(sql);
						int i = 0;
						while (rs.next()) {
//							JButton b5=new JButton("Return");
//							b5.setBounds(0, 0, 100, 20);
							default_table.addRow(new Object[0]);
							default_table.setValueAt(i + 1, i, 0);
							default_table.setValueAt(rs.getString(1), i, 1);
							default_table.setValueAt(rs.getString(2), i, 2);
							default_table.setValueAt(rs.getString(3), i, 3);
							default_table.setValueAt(rs.getDate(4), i, 4);
							default_table.setValueAt(rs.getDate(5), i, 5);
							default_table.setValueAt(rs.getInt(6), i, 6);
							default_table.setValueAt(new JButton("Return"), i, 7);
							i++;
						}
					} catch (Exception e3) {
						System.out.println(e3);
					}
					
				} else {
					JOptionPane.showMessageDialog(f, "Enter correct USN");
				}
					
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2);
			}

		}
		if (e.getSource() == b2) { // issue book button

		}
		if (e.getSource() == b3) { // back button
			new library().f.setVisible(true);
			f.setVisible(false);
		}

	}

	public static void main(String[] args) {
		student_details portal = new student_details();
	}

}

class JTableButtonRenderer implements TableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JButton b = (JButton) value;
		b.setOpaque(true);
		System.out.println("");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("sucessfully button clicked");

			}
		});
		if (isSelected) {
			b.setForeground(table.getSelectionForeground());
			b.setBackground(table.getSelectionBackground());
		} else {
			b.setForeground(table.getForeground());
			b.setBackground(UIManager.getColor("b.background"));
		}
//		 b.setText((value != null) ? "" : value.toString());
//		if(hasFocus) {
//			b.setBorder(BorderFactory.createEtchedBorder(0));
//		}
//		else {
//			b.setBorder(BorderFactory.createBevelBorder(1));
//		}
//		b.setText("Return");
		return b;
	}

}

class JTableButtonMouseListener extends MouseAdapter {
	private final JTable table;

	public JTableButtonMouseListener(JTable table) {
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		int row = e.getY() / table.getRowHeight();

		if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
			Object value = table.getValueAt(row, column);
			if (value instanceof JButton) {
				((JButton) value).doClick();
			}
		}
	}
}