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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Number_Generator extends JFrame {
	private static final long serialVersionUID = 1;
    private JTextField textField;
    private JButton btn,btnsave;
    private JLabel label;
    JLabel generatednumber = new JLabel();
    static JComboBox statecb,citycb;
    public String num,generated,generated1;
    Random n1 = new Random();
    Random n2=new Random();
    Random n3=new Random();
    Random n4=new Random();
    Random rnd = new Random();
    char ch;
    int fn,sn,tn,fon;
    JPanel contentPane= new JPanel();

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
	
	public Number_Generator()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 600, 500, 500);
        setResizable(false);
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("RTO Number Generator");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel.setBounds(80, 13, 1000, 93);
        contentPane.add(lblNewLabel);
        
        JLabel onamelabel = new JLabel("Owner Name");
        onamelabel.setForeground(Color.BLACK);
        onamelabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        onamelabel.setBounds(50, 100, 200, 93);
        contentPane.add(onamelabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        textField.setBounds(150,120,180,30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel statelabel = new JLabel("State:");
        statelabel.setForeground(Color.BLACK);
        statelabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        statelabel.setBounds(50, 150, 200, 93);
        contentPane.add(statelabel);
        
        String state[] = {"Karnataka","Andhra Pradesh","Tamil Nadu"};
        statecb = new JComboBox(state);
        statecb.setBounds(150, 180, 180, 30);
        contentPane.add(statecb);
        
        JLabel citylabel = new JLabel("City:");
        citylabel.setForeground(Color.BLACK);
        citylabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        citylabel.setBounds(50, 200, 200, 93);
        contentPane.add(citylabel);
        
        String city[] = {"Bangalore","Bagalkot","Mysore"};
        citycb = new JComboBox(city);
        citycb.setBounds(150, 230, 180, 30);
        contentPane.add(citycb);
        
        btn=new JButton("Generate");
        btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn.setBounds(140, 280, 200, 30);
        btn.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            fn=n1.nextInt(9);
            sn=n2.nextInt(9);
            tn=n3.nextInt(9);
            fon=n4.nextInt(9);
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            ch = alphabet.charAt(rnd.nextInt(alphabet.length()));
           String num_con=""+fn+sn+tn+fon;
           String statecode;
           String citycode;
           if(statecb.getSelectedIndex()==0)
           {
        	   statecode="KA";
           }
           else if(statecb.getSelectedIndex()==1)
           {
        	   statecode="AP";
           }
           else 
           {
        	   statecode="TN";
           }
           
           if(citycb.getSelectedIndex()==0)
           {
        	   citycode="01";
           }
           else if(citycb.getSelectedIndex()==1)
           {
        	   citycode="29";
           }
           else
           {
        	   citycode="09";
           }
            generated=""+statecode+" "+citycode+" "+ch+" "+num_con;
            generated1=""+statecode+citycode+ch+num_con;
            generatednumber.setText("Registration Number:"+generated);
            generatednumber.setForeground(Color.BLACK);
            generatednumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            generatednumber.setBounds(50, 350, 400, 93);
            
        }
        
    });
        btnsave=new JButton("Save");
        btnsave.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnsave.setBounds(140, 320, 200, 30);
        btnsave.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        	String readings = textField.getText();
            if(textField.getText().isEmpty())
            {
            	JOptionPane.showMessageDialog(btnsave, "Please Enter the Owner Name");	
            }
            else
            {
                try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/rto",
                    "root", "AShish*%34");
                String query= "insert into reg_no(reg_no, owner_name) values('"+generated1+"','"+readings+"');";
                java.sql.Statement st =connection.createStatement();
                int i=st.executeUpdate(query);
                if(i>0)
                {
                	 JOptionPane.showMessageDialog(btnsave, "Succesfully saved");
                }
                else
                {
                	JOptionPane.showMessageDialog(btnsave, "Error in Saving");
                }
                
                
                
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            }
        }
        
    });
    
       
     contentPane.add(generatednumber);
    contentPane.add(btn);
    contentPane.add(btnsave);
        
    
        
	}
}
