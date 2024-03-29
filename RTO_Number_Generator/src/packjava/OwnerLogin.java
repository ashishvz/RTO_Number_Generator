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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OwnerLogin extends JFrame {
	 private JTextField textField;
	    private JPasswordField passwordField;
	    private JButton btnNewButton;
	    private JLabel label;
	 JPanel contentPane = new JPanel();
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
		
		public OwnerLogin()
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(450, 190, 1014, 597);
	        setResizable(false);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JLabel lblNewLabel = new JLabel("RTO Number Status");
	        lblNewLabel.setForeground(Color.BLACK);
	        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
	        lblNewLabel.setBounds(300, 13, 1000, 93);
	        contentPane.add(lblNewLabel);
	        

	        textField = new JTextField();
	        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        textField.setBounds(481, 170, 281, 68);
	        contentPane.add(textField);
	        textField.setColumns(10);

	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        passwordField.setBounds(481, 286, 281, 68);
	        contentPane.add(passwordField);

	        JLabel lblUsername = new JLabel("Username");
	        lblUsername.setBackground(Color.BLACK);
	        lblUsername.setForeground(Color.BLACK);
	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
	        lblUsername.setBounds(250, 166, 193, 52);
	        contentPane.add(lblUsername);

	        JLabel lblPassword = new JLabel("Password");
	        lblPassword.setForeground(Color.BLACK);
	        lblPassword.setBackground(Color.CYAN);
	        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
	        lblPassword.setBounds(250, 286, 193, 52);
	        contentPane.add(lblPassword);

	        btnNewButton = new JButton("Login");
	        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
	        btnNewButton.setBounds(545, 392, 162, 73);
	        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/rto",
                        "root", "AShish*%34");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select username, passwords from owner_cred where username=? and passwords=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        
                        OwnerCheck ob = new OwnerCheck();
                        ob.setVisible(true);
                        dispose();
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
        

        contentPane.add(btnNewButton);
		}
}
