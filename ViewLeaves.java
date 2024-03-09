package humanresource.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ViewLeaves extends JFrame implements ActionListener {

    JTable leaveTable;
    JButton back;

    ViewLeaves() {
        JLabel label = new JLabel("Leaves of All Employees");
        label.setBounds(150, 30, 300, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(label);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM LeaveManagement");
            Vector<String> columnNames = new Vector<>();
            Vector<Vector<String>> data = new Vector<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(resultSet.getString(i));
                }
                data.add(vector);
            }
            leaveTable = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(leaveTable);
            scrollPane.setBounds(30, 80, 520, 250);
            add(scrollPane);
        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(220, 350, 100, 30);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBackground(Color.CYAN);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        setSize(600, 450);
        setLocation(300, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new ViewLeaves();
    }
}