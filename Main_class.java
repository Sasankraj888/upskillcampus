package humanresource.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_class extends JFrame {
    Main_class(){

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1150,630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0,0,1150,630);
        add(img);

        JLabel heading = new JLabel("Human Resource Management System");
        heading.setBounds(310,155,500,40);
        heading.setFont(new Font("Raleway",Font.BOLD,25));
        img.add(heading);

        JButton add = new JButton("Add Employee");
        add.setBounds(335,250,150,40);
        add.setForeground(Color.black);
        add.setBackground(Color.CYAN);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new AddEmployee();
                    setVisible(false);
            }
        });
        img.add(add);

        JButton view = new JButton("View Employee");
        view.setBounds(570,250,150,40);
        view.setForeground(Color.black);
        view.setBackground(Color.CYAN);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployee();
                setVisible(false);
            }
        });
        img.add(view);

        JButton attendance = new JButton("Attendance Record");
        attendance.setBounds(335,310,150,40);
        attendance.setForeground(Color.black);
        attendance.setBackground(Color.CYAN);
        attendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AttendanceRecord("");
                setVisible(false);
            }
        });
        img.add(attendance);

        JButton leave = new JButton("Leave Management");
        leave.setBounds(570,310,150,40);
        leave.setForeground(Color.black);
        leave.setBackground(Color.CYAN);
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LeaveManagement();
                setVisible(false);
            }
        });
        img.add(leave);

        JButton remove = new JButton("Remove Employee");
        remove.setBounds(450,370,150,40);
        remove.setForeground(Color.black);
        remove.setBackground(Color.CYAN);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveEmployee();
            }
        });
        img.add(remove);

        setSize(1150,630);
        setLocation(250,100);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
           new Main_class();
    }
}
