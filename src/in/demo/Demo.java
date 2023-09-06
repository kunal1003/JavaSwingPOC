package in.demo;

import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mysql.cj.protocol.Resultset;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Demo {

	private JFrame frame;
	private JTextField textEdition;
	private JTextField textBookname;
	private JTextField textPrice;
	private JTable table;
	private JTextField text_bid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo window = new Demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Demo() {
		initialize();
		connect() ;
		table_load() ;
		
		
	}
	PreparedStatement pst;
	Connection con;
	ResultSet rs ; 
	
	public void connect() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","root");  
			
			Statement stmt=con.createStatement();  
			
			System.out.println("Connected to Database.");
			
			
			
			
		}
		catch(Exception e) {
			
		}
	}
	
	 public void table_load() { 
		  try { 
			  pst = con.prepareStatement("select * from book"); 
			   rs = pst.executeQuery();
			   table.setModel(DbUtils.resultSetToTableModel(rs)); 
			  }
	  catch (SQLException e) { e.printStackTrace(); } }
	 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1025, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Store");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(194, 31, 296, 61);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(22, 102, 507, 286);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(248, 5, 10, 10);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(49, 41, 100, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(49, 143, 100, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Edition");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(49, 97, 100, 22);
		panel.add(lblNewLabel_1_1_1);
		
		textEdition = new JTextField();
		textEdition.setBounds(172, 98, 185, 28);
		panel.add(textEdition);
		textEdition.setColumns(10);
		
		textBookname = new JTextField();
		textBookname.setColumns(10);
		textBookname.setBounds(172, 46, 185, 28);
		panel.add(textBookname);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(172, 148, 185, 28);
		panel.add(textPrice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname = textBookname.getText();
				edition = textEdition.getText();
				price = textPrice.getText();
				
				try {
			        pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
			        pst.setString(1, bname);
			        pst.setString(2, edition);
			        pst.setString(3, price);
			        pst.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
			        table_load();
			                       
			        textBookname.setText("");
			        textEdition.setText("");
			        textPrice.setText("");
			        textBookname.requestFocus();
			       }
			    catch (SQLException e1) 
			        {            
			       e1.printStackTrace();
			    }
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(64, 218, 100, 39);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				System.exit(0);
				
			}
			
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(185, 218, 100, 39);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textBookname.setText("");
		        textEdition.setText("");
		        textPrice.setText("");
		        textBookname.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClear.setBounds(313, 218, 100, 39);
		panel.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(541, 101, 460, 287);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1_2 = new JLabel("search by book id");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(22, 441, 150, 22);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		text_bid = new JTextField();
		text_bid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String id = text_bid.getText();
				
				try {
					pst = con.prepareStatement("select name,edition,price from book where id=?");
					
					pst.setString(1, id);
					ResultSet rst = pst.executeQuery();
					
					
					if(rst.next()==true) {
						
						String name = rst.getString(1);
						String edition = rst.getString(2);
						String price = rst.getString(3);
						
						textBookname.setText(name);
						textEdition.setText(edition);
						textPrice.setText(price);
						
					}
					
					else {
						
						textBookname.setText("");
						textEdition.setText("");
						textPrice.setText("");
						
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		text_bid.setColumns(10);
		text_bid.setBounds(182, 442, 185, 28);
		frame.getContentPane().add(text_bid);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,price;
				bname = textBookname.getText();
				edition = textEdition.getText();
				price = textPrice.getText();
				String id = text_bid.getText();
				
				try {
			        pst = con.prepareStatement("update book set name=?,edition=?,price=? where id=?");
			        pst.setString(1, bname);
			        pst.setString(2, edition);
			        pst.setString(3, price);
			        pst.setString(4, id);
			        
			        pst.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Record updated!!!!!");
			        table_load();
			                       
			        textBookname.setText("");
			        textEdition.setText("");
			        textPrice.setText("");
			        textBookname.requestFocus();
			       }
			    catch (SQLException e1) 
			        {            
			       e1.printStackTrace();
			    }
				
				
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(623, 417, 100, 39);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = text_bid.getText();
				try {
					pst = con.prepareStatement("DELETE FROM book WHERE id=?");
					
					pst.setString(1, id);
					
					 pst.executeUpdate();
				      JOptionPane.showMessageDialog(null, "Record deleted!!!!!");
				      table_load();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(806, 417, 100, 39);
		frame.getContentPane().add(btnDelete);
	}
}

