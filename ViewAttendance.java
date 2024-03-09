package humanresource.management.system;


import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewAttendance extends JFrame implements ActionListener {

    JTable table;
    Choice choice;
    JButton viewAttendancebtn, back;

    ViewAttendance(String empId) {
        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Attendance Record");
        heading.setBounds(320, 30, 200, 30);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel lblEmpId = new JLabel("Employee ID");
        lblEmpId.setBounds(100, 80, 100, 20);
        add(lblEmpId);

        JLabel empIdValue = new JLabel(empId);
        empIdValue.setBounds(250, 80, 100, 20);
        add(empIdValue);

        choice = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                choice.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        choice.setBounds(250, 120, 150, 20);
        add(choice);

        viewAttendancebtn = new JButton("View Attendance");
        viewAttendancebtn.setBounds(150, 170, 150, 30);
        viewAttendancebtn.setBackground(Color.CYAN);
        viewAttendancebtn.setForeground(Color.BLACK);
        viewAttendancebtn.addActionListener(this);
        add(viewAttendancebtn);

        back = new JButton("Back");
        back.setBounds(350, 170, 150, 30);
        back.setBackground(Color.CYAN);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 230, 680, 250);
        add(scroll);

        setSize(720, 600);
        setLocation(300,100);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == viewAttendancebtn) {
        String empId = choice.getSelectedItem();
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM attendance WHERE empId = '" + empId + "'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if (ae.getSource() == back) {
            new AttendanceRecord("").setVisible(true);
            this.setVisible(false);
        }
            
        }

        public static void main(String[] args) {
        new ViewAttendance("");
    }
}