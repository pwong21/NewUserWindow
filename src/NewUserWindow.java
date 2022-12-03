package MyPackage;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;

public class NewUserWindow extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtEmail;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewUserWindow frame = new NewUserWindow();
                frame.setVisible(true);
            }
        });
    }


    public NewUserWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 454, 343);
        setTitle("New User Window");
        getContentPane().setLayout(null);
        JLabel hUsername = new JLabel("Enter a new Username :");
        hUsername.setBounds(78, 50, 150, 14);
        getContentPane().add(hUsername);

        JLabel hPassword = new JLabel("Enter a new Password :");
        hPassword.setBounds(78, 80, 150, 14);
        getContentPane().add(hPassword);

        JLabel hEmail = new JLabel("Enter a Email :");
        hEmail.setBounds(78, 116, 150, 14);
        getContentPane().add(hEmail);



        txtUsername = new JTextField("");
        txtUsername.setBounds(220, 47, 120, 20);
        getContentPane().add(txtUsername);

        // Password
        txtPassword = new JPasswordField();
        txtPassword.setBounds(220, 77, 120, 20);
        getContentPane().add(txtPassword);

        // Email
        txtEmail = new JTextField("");
        txtEmail.setBounds(220, 112, 120, 20);
        getContentPane().add(txtEmail);

        // Save Button
        JButton btnsubmit = new JButton("Submit");
        btnsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(InsertInformation()) {
                    JOptionPane.showMessageDialog(null,
                            "New User Successfully Added");
                }
            }
        });
        btnsubmit.setBounds(161, 227, 89, 23);
        getContentPane().add(btnsubmit);

    }

    private Boolean InsertInformation()
    {

        String strUsername = txtUsername.getText();
        String strPassword = new String(txtPassword.getPassword());
        String strEmail = txtEmail.getText();

        if(strUsername.equals("")) // Username
        {
            JOptionPane.showMessageDialog(null,
                    "Please Enter (Username)");
            txtUsername.requestFocusInWindow();
            return false;
        }
        if(strPassword.equals("")) // Password
        {
            JOptionPane.showMessageDialog(null,
                    "Please Enter (Password)");
            txtPassword.requestFocusInWindow();
            return false;
        }


        if(strEmail.equals("")) // Email
        {
            JOptionPane.showMessageDialog(null,
                    "Please Enter (Email)");
            txtEmail.requestFocusInWindow();
            return false;
        }

        Connection connect = null;
        Statement s = null;
        Boolean status = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(""
                    + "jdbc:mysql://localhost/mydatabase"
                    + "?user=root&password=root");

            s = connect.createStatement();

            // SQL Insert
            String sql = "INSERT INTO userinfo "
                    + "(Username,Password,Email) "
                    + "VALUES ('" + strUsername + "','"
                    + strPassword + "','"
                    + strEmail + "') ";
            s.execute(sql);


            // Reset Text Fields
            txtUsername.setText("");
            txtPassword.setText("");
            txtEmail.setText("");

            status  = true;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        try {
            if (s != null) {
                s.close();
                connect.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return status;

    }
}