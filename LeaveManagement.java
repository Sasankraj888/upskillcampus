package humanresource.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LeaveManagement extends JFrame implements ActionListener, ItemListener {

    Choice choiceEMPID;
    JTextField startDateField, endDateField, reasonField;
    JLabel nameLabel, emailLabel;
    JButton applyLeave,  viewLeaves,  back;

    LeaveManagement() {
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200, 50, 150, 30);
        add(choiceEMPID);

        nameLabel = new JLabel();
        nameLabel.setBounds(50, 80, 300, 30);
        add(nameLabel);

        emailLabel = new JLabel();
        emailLabel.setBounds(50, 110, 300, 30);
        add(emailLabel);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                choiceEMPID.add(resultSet.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(this);

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD)");
        startDateLabel.setBounds(50, 150, 250, 30);
        startDateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(startDateLabel);

        startDateField = new JTextField();
        startDateField.setBounds(270, 150, 150, 30);
        add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD)");
        endDateLabel.setBounds(50, 200, 250, 30);
        endDateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(endDateLabel);

        endDateField = new JTextField();
        endDateField.setBounds(270, 200, 150, 30);
        add(endDateField);

        JLabel reasonLabel = new JLabel("Reason");
        reasonLabel.setBounds(50, 250, 100, 30);
        reasonLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(reasonLabel);

        reasonField = new JTextField();
        reasonField.setBounds(200, 250, 300, 30);
        add(reasonField);

        applyLeave = new JButton("Apply Leave");
        applyLeave.setBounds(80, 300, 150, 30);
        applyLeave.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        applyLeave.setBackground(Color.CYAN);
        applyLeave.setForeground(Color.BLACK);
        applyLeave.addActionListener(this);
        add(applyLeave);

        viewLeaves = new JButton("View Leaves");
        viewLeaves.setBounds(380, 300, 150, 30);
        viewLeaves.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        viewLeaves.setBackground(Color.CYAN);
        viewLeaves.setForeground(Color.BLACK);
        viewLeaves.addActionListener(this);
        add(viewLeaves);

        back = new JButton("Back");
        back.setBounds(250, 300, 100, 30);
        back.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        back.setBackground(Color.CYAN);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        setSize(600, 400);
        setLocation(300, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == applyLeave) {
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String reason = reasonField.getText();
            String empID = choiceEMPID.getSelectedItem();
            try {
                conn c = new conn();
                String query = "INSERT INTO LeaveManagement (empId, start_date, end_date, reason, status) VALUES ('" + empID + "', '" + startDate + "', '" + endDate + "', '" + reason + "', 'Applied')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave applied successfully");
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            // Assuming Main_class is the main class for the application
            new Main_class();
        } else if (e.getSource() == viewLeaves) {
            new ViewLeaves();
            
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == choiceEMPID) {
            try {
                conn c = new conn();
                String empID = choiceEMPID.getSelectedItem();
                String query = "SELECT name, email FROM employee WHERE empId = '" + empID + "'";
                ResultSet resultSet = c.statement.executeQuery(query);
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    nameLabel.setText("Name: " + name);
                    emailLabel.setText("Email: " + email);
                } else {
                    nameLabel.setText("Name: Not Found");
                    emailLabel.setText("Email: Not Found");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new LeaveManagement();
    }
}
