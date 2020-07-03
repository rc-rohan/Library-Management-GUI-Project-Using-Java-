package Lib_management;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

/**
 * -->add the functionality that if student had already borrowed 5 books then don't allow the student to issue
 * 
 * -->in issuing the book make sure to reduce the book from the stock column of the books table		(Done)
 * -->Update the stock column while issuing the books			(Done)
 * -implement the details in the jpanel 	(Done)
 * -seprate students and books section with auto load feature with submit button and issue button	(Done)
 * -mysql taking the data with current date		(Done)
 * -Same book will not be issued to the student twice		(Done)
 *
 */
public class issue_new_books implements ActionListener {
	JFrame f;
	JButton b1,b2,b3,b4;
	JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
	JPanel p1,p2;
	JComboBox<String> c1;
	
	issue_new_books(){
		f=new JFrame("LIBRARY MANAGEMENT SYSTEM");
		
		l=new JLabel("Borrow Book");
		l.setBounds(272, 10, 220, 50);
		l.setFont(new Font("serif",Font.ITALIC, 40));
		f.add(l);
		
		l12=new JLabel("Category :");
		l12.setBounds(10, 40, 60, 20);
		String category[]= {"--Select--","BOOK ID","Name"};
		c1=new JComboBox<String>(category);
		c1.setBounds(85, 40, 150, 20);
		l13=new JLabel("Enter Value:");
		l13.setBounds(10, 70, 70, 20);
		t12=new JTextField();
		t12.setBounds(90,70, 140, 20);
		b2=new JButton("Search");		//while searching check the availability of the book if not avialble then show in the joptionpane
		b2.setBounds(240, 70, 80, 20);
		b2.addActionListener(this);
		
		l1=new JLabel("Books ID :"); 	//BOOKS SHOULD BE FIRST SEARCHED
		l1.setBounds(10, 100, 60, 20);		
		t1=new JTextField();
		t1.setBounds(130, 100, 80, 20);
		t1.setEnabled(false);
		t1.setDisabledTextColor(Color.black);
		
		l2=new JLabel("Books Name :");
		l2.setBounds(10, 130, 80, 20);		
		t2=new JTextField();
		t2.setBounds(130, 130, 200, 20);
		t2.setEnabled(false);
		t2.setDisabledTextColor(Color.black);

		l3=new JLabel("Author's Name :");
		l3.setBounds(10, 160, 100, 20);		
		t3=new JTextField();
		t3.setBounds(130, 160, 200, 20);
		t3.setEnabled(false);
		t3.setDisabledTextColor(Color.black);
		
		l4=new JLabel("Publisher Name :");
		l4.setBounds(10,190, 100, 20);		
		t4=new JTextField();
		t4.setBounds(130, 190, 200, 20);
		t4.setEnabled(false);
		t4.setDisabledTextColor(Color.black);

		l5=new JLabel("Date Of Issue :");
		l5.setBounds(10,220, 90, 20);		
		t5=new JTextField();
		LocalDate curDate=LocalDate.now();
		t5.setText(String.valueOf(curDate));
		t5.setDisabledTextColor(Color.black.brighter());
		t5.setBounds(130, 220, 80, 20);
		t5.setEnabled(false);
		t5.setDisabledTextColor(Color.black);

		
		l14=new JLabel("Return Date :");
		l14.setBounds(10, 250, 80, 20);		
		LocalDate returnDate=curDate.plusMonths(1);
		t13=new JTextField(String.valueOf(returnDate));
		t13.setBounds(130, 250, 80, 20);
		t13.setEnabled(false);
		t13.setDisabledTextColor(Color.black);
		
		p1=new JPanel();
		p1.setBounds(370, 80, 350, 290);
		p1.setBorder(new TitledBorder(new LineBorder(Color.black.brighter(), 2, true), "Book-Details", TitledBorder.LEADING	, 
				TitledBorder.TOP,null,Color.gray.darker()));
		p1.setLayout(null);
		p1.setBackground(Color.white);
		p1.add(l1);p1.add(l2);p1.add(l3);p1.add(l4);p1.add(l5);p1.add(l12);p1.add(l13);p1.add(l14);
		p1.add(c1);
		p1.add(t1);p1.add(t2);p1.add(t3);p1.add(t4);p1.add(t5);p1.add(t12);p1.add(t13);
		p1.add(b2);
		
		f.add(p1);
		
		
		l6=new JLabel("USN :"); // SEARCH STUDENT USING USN IF DOESNOT EXISTS THEN REGISTER THE STUDENT
		l6.setBounds(60, 40, 30, 20);		
		t6=new JTextField();
		t6.setBounds(95,40,80,20);
		b1=new JButton("Search");	//check for how many books students has issued if it is 5 then show error in the JOptionPane
		b1.setBounds(190, 40, 75, 20);
		b1.addActionListener(this);
		
		l7=new JLabel("NAME :");
		l7.setBounds(10, 80, 50, 20);		
		t7=new JTextField();
		t7.setBounds(65, 80, 170, 20);
		t7.setEnabled(false);
		t7.setDisabledTextColor(Color.black);

		l8=new JLabel("BRANCH :");
		l8.setBounds(10, 120, 60, 20);		
		t8=new JTextField();
		t8.setBounds(70, 120, 40, 20);
		t8.setEnabled(false);
		t8.setDisabledTextColor(Color.black);

		l9=new JLabel("YEAR :");
		l9.setBounds(140, 120, 40, 20);		
		t9=new JTextField();
		t9.setBounds(185, 120, 50, 20);
		t9.setEnabled(false);
		t9.setDisabledTextColor(Color.black);
		
		l10=new JLabel("SEM :");
		l10.setBounds(250,120, 40, 20);		
		t10=new JTextField();
		t10.setBounds(290, 120, 40, 20);
		t10.setEnabled(false);
		t10.setDisabledTextColor(Color.black);
		
		l11=new JLabel("EMAIL-ID :");
		l11.setBounds(10, 160, 60, 20);		
		t11=new JTextField();
		t11.setBounds(75, 160, 200, 20);
		t11.setEnabled(false);
		t11.setDisabledTextColor(Color.black);
		
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBounds(10, 80, 350,200);
		p2.setBackground(Color.white);
		p2.setBorder(new TitledBorder(new LineBorder(Color.black.brighter(), 2, true),"Student-Details",TitledBorder.LEADING,TitledBorder.TOP,
				null,Color.gray.darker()));
		
		p2.add(l6);p2.add(l7);p2.add(l8);p2.add(l9);p2.add(l10);p2.add(l11);
		p2.add(t6);p2.add(t7);p2.add(t8);p2.add(t9);p2.add(t10);p2.add(t11);
		p2.add(b1);
		
		f.add(p2);
		
		b3=new JButton("Issue");
		b3.setBounds(80, 320, 80, 25);
		b3.addActionListener(this);
		
		b4=new JButton("Back");
		b4.setBounds(190, 320, 80, 25);
		b4.addActionListener(this);
		
		f.add(b3);f.add(b4);
				
		f.setSize(750,430);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.getContentPane().setBackground(Color.white);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==b1) {    // search button STUDENT 
				try {
					db_connection display =new db_connection();
					String sql="SELECT * FROM students WHERE usn='"+t6.getText()+"' ";
					ResultSet rs=display.stmt.executeQuery(sql);
					if (rs.next()) {
						
						t7.setText(rs.getString(2));
						t8.setText(rs.getString(3));
						t9.setText(rs.getString(4));
						t10.setText(rs.getString(5));
						t11.setText(rs.getString(6));
						//check for the number of books already issued if then 5 then allow else error
					}
					else {
						JOptionPane.showMessageDialog(f, "USN is not registered");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2);
				}
			
		}
		
		if (e.getSource()==b2) {    // search button for books:check for the availabilty of the book 
			String category_selected=(String)c1.getSelectedItem();
			category_selected=category_selected.replaceAll("\\s+", "_");
			if (category_selected.equalsIgnoreCase("select_Category")) {
				JOptionPane.showMessageDialog(f, "Select the category for proper search result");
			}
			else {
				try {		//add the comdition to check for the string not to be empty
					db_connection display =new db_connection();
					String sql="SELECT * FROM books WHERE "+category_selected+"='"+t12.getText()+"' ";
					ResultSet rs=display.stmt.executeQuery(sql);
					if (rs.next()) {
						t1.setText(rs.getString(1));
						t2.setText(rs.getString(2));
						t3.setText(rs.getString(3));
						t4.setText("-x-x-x-x-x-x-");
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
		if (e.getSource()==b3) {		//ISSUE button
			if (t6.getText().trim().length()==0 || t12.getText().trim().length()==0) {
				JOptionPane.showMessageDialog(f,"Enter Valid Data To Issue Book");
			}
			else {
				try {
					db_connection update_stock=new db_connection();
					String sqlStock="UPDATE books SET stock=stock-1 WHERE book_id='"+t1.getText()+"' AND books.stock > 0";
					int result=update_stock.stmt.executeUpdate(sqlStock);
					if(result>0) {
						try {
							System.out.println(" issue book is functional");
							db_connection insertData=new db_connection();
							String sql="INSERT INTO issued_books(usn,book_id,date_of_issue,return_date)VALUES(?,?,?,?)";
							PreparedStatement pstmt=insertData.con.prepareStatement(sql);
							pstmt.setString(1, t6.getText());
							pstmt.setString(2, t1.getText());
							pstmt.setString(3,t5.getText());
							pstmt.setString(4, t13.getText());
							int i=pstmt.executeUpdate();
							if (i>0) {
								JOptionPane.showMessageDialog(f, "Successfully Issued Book");
							
							}
						
						} catch (Exception e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(f, "This Book has already been issued to the student");
							System.out.println(e2);
						}
					}
					else {
						JOptionPane.showMessageDialog(f,"This Book Is Out Of Stock Currently"	);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
				
			}
		if (e.getSource()==b4) {
			new library().f.setVisible(true);			
			f.setVisible(false);
		}
		
	}
	
	public static void main(String[] args) {
		issue_new_books isuueBooks=new issue_new_books();
	}
	
}
