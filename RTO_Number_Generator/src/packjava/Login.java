package packjava;

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

public class Login extends JFrame {

    private static final long serialVersionUID = 1;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btn_rto,btn_owner;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 500, 500);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        label = new JLabel("RTO Vehicle Number Generator and Status");
        label.setBounds(50, 35, 500, 20);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));

        btn_rto = new JButton("RTO Officer");
        btn_rto.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btn_rto.setBounds(150, 150, 200, 80);
        btn_rto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              new RtoLogin().setVisible(true);
              dispose();
            }
        });
        
        btn_owner = new JButton("Owner(User)");
        btn_owner.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btn_owner.setBounds(150,240, 200, 80);
        btn_owner.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new OwnerLogin().setVisible(true);
                dispose();
            }
        });
        

        contentPane.add(btn_rto);
        contentPane.add(btn_owner);
        contentPane.add(label);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}
