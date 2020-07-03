package Lib_management;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

/**
 * edit button issue(Done)
 * spinner is not updtaing (done)
 * --> replace the edit button with the JCheckBox to toggle  or use a toggle button (Done)
 * 
 * -->remove the genrate button and make the automatic generation of the book id
 * -->add the delete button to delete the book from db make sure that the book is not issued to any student
 * -->don't update the old book which is already issued
 * 		-Add the codition in generate button for not updating the books ID for books which has already been issued
 */
public class add_book implements ActionListener,ItemListener{

	JFrame f;
	JLabel l,l1,l2,l3,l4,l5,l6,l7,l8;
	JButton b1,b2,b3,b4,b5;
	JTextField t1,t2,t3,t4,t5;
	JRadioButton r1,r2;
	JComboBox<String> c1;
	JSpinner spinner;
	JCheckBox cb1;
	
	
	
	public add_book() {
		// TODO Auto-generated constructor stub
		
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");

		l=new JLabel("Add Books");
		l.setFont(new Font("serif", Font.ITALIC, 40));
		l.setBounds(180,10,300,50);
		f.add(l);
		
		
		r1=new JRadioButton("Edit Old Book");		//radio button for the old and new books Gui
		r1.setBounds(280, 80, 150, 30);
		r1.setFocusable(false);
		r2=new JRadioButton("Add New Book");
		r2.setBounds(140, 80, 120, 30);
//		r2.setSelected(true);
		r2.setFocusable(false);
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);bg.add(r2);
		r1.addActionListener(this);
		r2.addActionListener(this);
		r1.setBackground(Color.white);
		r2.setBackground(Color.white);

		l5=new JLabel("Category:");		//search book gui
		l5.setBounds(10, 120, 60, 20);
		String category[]= {"Select Category","Book ID","Name"};
		c1=new JComboBox<String>(category);
		c1.setBounds(75, 120, 150, 20);
		c1.setEnabled(false);
		l1=new JLabel("Enter Value:");
		l1.setBounds(245, 120, 100, 20);
		t1=new JTextField();
		t1.setBounds(320, 120, 140, 20);
		t1.setEnabled(false);
		b1=new JButton("Search");
		b1.setBounds(465, 120, 80, 20);
		b1.addActionListener(this);
		b1.setEnabled(false);
		b1.setFocusable(false);	
		
		l2 =new JLabel("Book ID:");
		l2.setBounds(50, 190,60, 20);
		t5=new JTextField();
		t5.setBounds(170, 190, 60, 20);
		t5.setEnabled(false);
		t5.setDisabledTextColor(Color.black);
		b2=new JButton("Generate");
		b2.setBounds(240, 190, 90, 20);
		b2.setEnabled(false);
		b2.setFocusable(false);
		b2.addActionListener(this);
		
		l3 =new JLabel("Name:");
		l3.setBounds(50, 220, 50, 20);
		t2=new JTextField();
		t2.setBounds(170, 220, 200, 20);
		t2.setEnabled(false);
		t2.setDisabledTextColor(Color.black);
		
		l4 =new JLabel("Author's Name");
		l4.setBounds(50, 250, 100, 20);
		t3=new JTextField();
		t3.setBounds(170, 250, 200, 20);
		t3.setEnabled(false);
		t3.setDisabledTextColor(Color.black);
		
		l6=new JLabel("Quantity :");
		l6.setBounds(50, 280, 60, 20);
		spinner=new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		spinner.setBounds(170, 280, 50, 20);
		spinner.setEnabled(false);
		spinner.setForeground(Color.black);
		
		l7=new JLabel("** to edit the content of Old book the edit button **");
		l7.setBounds(40, 165, 280, 15);
		l7.setForeground(Color.blue.brighter());
		l7.setEnabled(false);
		cb1=new JCheckBox("Edit");
		cb1.setForeground(Color.blue.brighter());
		cb1.setBounds(330, 165, 60, 15);
		cb1.addItemListener(this);
		cb1.setBackground(Color.white);
		cb1.setEnabled(false);
		cb1.setFocusable(false);
			
		b4=new JButton("Update");
		b4.setBounds(170, 320, 75, 20);
		b4.addActionListener(this);
		b4.setFocusable(false);
	
		b5=new JButton("Back");
		b5.setBounds(255, 320, 70,20);
		b5.addActionListener(this);
		b5.setFocusable(false);
		
		f.add(b1);f.add(b2);f.add(b4);f.add(b5);
		f.add(l1);f.add(l3);f.add(l4);f.add(l5);f.add(l2);f.add(l6);f.add(l7);
		f.add(r1);f.add(r2);
		f.add(t1);f.add(t2);f.add(t3);f.add(t5);f.add(c1);
		f.add(spinner);f.add(cb1);
		
		f.setSize(600,400);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.white);
		f.setLayout(null);
		f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==cb1) {
				if(e.getStateChange()==1) {
					b2.setEnabled(true);
					t3.setEnabled(true);
					t2.setEnabled(true);
					spinner.setEnabled(true);
				}
			else {
				b2.setEnabled(false);
				t3.setEnabled(false);
				t2.setEnabled(false);
				spinner.setEnabled(false);
			}
			
		}
	}
	int book_qty;		//for stock calcuation
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource()==r1) {  //add old book JRadioButton
			
			t1.setEnabled(true);
			b1.setEnabled(true);
			c1.setEnabled(true);
			l5.setEnabled(true);
			l1.setEnabled(true);
			
			
			t5.setEnabled(false);
			t5.setText("");
			b2.setEnabled(false);
			t3.setEnabled(false);
			t3.setText("");
			t2.setEnabled(false);
			t2.setText("");
			spinner.setEnabled(false);	
			spinner.setValue(0);
		}
		if (e.getSource()==r2) {  // add new book JRadioButton
			
			b2.setEnabled(true);
			t3.setEnabled(true);
			t2.setEnabled(true);
			spinner.setEnabled(true);
			
			t1.setEnabled(false);
			b1.setEnabled(false);
			c1.setEnabled(false);
			l5.setEnabled(false);
			l1.setEnabled(false);
			cb1.setEnabled(false);
			l7.setEnabled(false);
		}
		if (e.getSource()==b1) {    // search button   
			String category_selected=(String)c1.getSelectedItem();
			category_selected=category_selected.replaceAll("\\s+", "_").toLowerCase();
			if (category_selected.equalsIgnoreCase("select_Category") || t1.getText().trim().length()==0 ) {
				JOptionPane.showMessageDialog(f, "Select Proper Search Filter and Enter Proper Value");
			}
			else {
				try {
					db_connection display =new db_connection();
					String sql="SELECT * FROM books WHERE "+category_selected+"=?";
					PreparedStatement pstmt=display.con.prepareStatement(sql);
					pstmt.setString(1,t1.getText());
					ResultSet rs=pstmt.executeQuery();
					if (rs.next()) {
						t5.setText(rs.getString(1));
						t2.setText(rs.getString(2));
						t3.setText(rs.getString(3));
						spinner.setValue(rs.getInt(5));
						l7.setEnabled(true);
						cb1.setEnabled(true);
						book_qty=(int)spinner.getValue();
					}
					else {
						JOptionPane.showMessageDialog(f, "NO SUCH BOOK EXISTS");
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		if (e.getSource()==b2) {  // Genrate button for serial number
			Random random_num=new Random();
			String number="SIT"+String.valueOf(random_num.nextInt(10000));
			t5.setText(number);
			
		}
		if (e.getSource()==b4) { // UPDATE button execute as per the radio button selected
			
			if (r2.isSelected()) { // ADD NEW BOOK radio button
				try {	
					db_connection display =new db_connection();
					String sql_statement="INSERT INTO books(book_id,name,`author's_name`,quantity,stock) VALUES(?,?,?,?,?)";
					PreparedStatement pstmt=display.con.prepareStatement(sql_statement);
					pstmt.setString(1,t5.getText());
					pstmt.setString(2, t2.getText());
					pstmt.setString(3, t3.getText());
					pstmt.setInt(4,(int)spinner.getValue());
					pstmt.setInt(5,(int)spinner.getValue());		//stock
					int i=pstmt.executeUpdate();
					if(i>0)
					{
						JOptionPane.showMessageDialog(f,"Successfully Updated");
						t5.setText("");		// to Empty the content
						t2.setText("");
						t3.setText("");
						spinner.setValue(0);
					}
					else {
						JOptionPane.showMessageDialog(f, "Wrong input");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(f, "Enter Proper Value");
					System.out.println(e2);
				}
				
			}
			int qty_changed,stock;
			if(r1.isSelected() && cb1.isSelected())  //update button selected when the Old BOOK and EDIT bothe are selected
			{
				try {
					db_connection display =new db_connection();
					String category_selected=(String)c1.getSelectedItem();
					category_selected=category_selected.replaceAll("\\s+", "_");
					
					qty_changed=(int)spinner.getValue();
					stock=qty_changed-book_qty;
					System.out.println(book_qty+" "+qty_changed+" "+stock);
					
					String sql_statement="UPDATE books SET book_id=?,name=?,`author's_name`=?,quantity=? ,stock=stock+"+stock+" "
							+ "WHERE "+category_selected+" =?";
					PreparedStatement pstmt=display.con.prepareStatement(sql_statement);
					pstmt.setString(1,t5.getText());
					pstmt.setString(2, t2.getText());
					pstmt.setString(3, t3.getText());
					pstmt.setInt(4, (int) spinner.getValue());  //spinner is not updated
					pstmt.setString(5, t1.getText());
					
					int i=pstmt.executeUpdate();
					if (i>0) {
						JOptionPane.showMessageDialog(f, "Succefully Updated");
		
							String sql="SELECT * FROM books WHERE "+category_selected+"=?";
							pstmt=display.con.prepareStatement(sql);
							pstmt.setString(1,t1.getText());
//							System.out.println(t1.getText());
							ResultSet rs=pstmt.executeQuery();
							if (rs.next()) {
//								System.out.println("got the data updating");
								t5.setText(rs.getString(1));
								t2.setText(rs.getString(2));
								t3.setText(rs.getString(3));
								spinner.setValue(rs.getInt(5));
								
								l7.setEnabled(true);
								cb1.setEnabled(true);
								cb1.setSelected(false);	
								book_qty=(int)spinner.getValue();
							}
							else {
								JOptionPane.showMessageDialog(f, "NO SUCH BOOK EXISTS");
							}	
						}
					else {
						JOptionPane.showMessageDialog(f, "Unable to update");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2);
					
				}
			}
		}
		if (e.getSource()==b5) {   // Back button to return to library
			new library().f.setVisible(true);
			f.setVisible(false);	
		}	
	}	
	public static void main(String[] args) {
		add_book add=new add_book();
	}
}
	
