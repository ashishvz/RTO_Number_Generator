package packjava;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OwnerCheck extends JFrame {
	JPanel contentPane = new JPanel();
	JTextField textField;
	JButton statusbtn;
	JLabel onamelabel;
	String reg_no,oname,ownerName;
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public OwnerCheck()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 500, 500);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
		 JLabel lblNewLabel = new JLabel("RTO Number Status");
	        lblNewLabel.setForeground(Color.BLACK);
	        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	        lblNewLabel.setBounds(50, 13,500 , 93);
	        contentPane.add(lblNewLabel);
	        
	        onamelabel = new JLabel("Owner Name");
	        onamelabel.setForeground(Color.BLACK);
	        onamelabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        onamelabel.setBounds(50, 100, 200, 93);
	        contentPane.add(onamelabel);
	        
	        textField = new JTextField();
	        textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        textField.setBounds(165,125,180,30);
	        contentPane.add(textField);
	        textField.setColumns(10);
	        
	        statusbtn=new JButton("Check Status");
	        statusbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        statusbtn.setBounds(140, 320, 300, 30);
	        statusbtn.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	        	  ownerName = textField.getText();
	              if(textField.getText().isEmpty())
	              {
	            	  JOptionPane.showMessageDialog(statusbtn, "Enter the owner Name!");
	                
	              }
	              else
	              {
	            	  try {
		                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/rto",
		                        "root", "AShish*%34");
		                    
		                    PreparedStatement st = (PreparedStatement) connection
		                        .prepareStatement("Select reg_no, owner_name from reg_no where owner_name=?");

		                    st.setString(1, ownerName);
		                   // st.setString(2, password);
		                    ResultSet rs = st.executeQuery();
		                    if (rs.next()) {
		                      
		                        JOptionPane.showMessageDialog(statusbtn, "Your Registration Number is:"+rs.getString("reg_no"));
		                    } else {
		                        JOptionPane.showMessageDialog(statusbtn, "Registration Number not generated!");
		                    }
		                } catch (SQLException sqlException) {
		                    sqlException.printStackTrace();
		                }
	              }
	            }
	        });
	        

	        contentPane.add(statusbtn);
	}

}
