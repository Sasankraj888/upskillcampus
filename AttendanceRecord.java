package humanresource.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

public class AttendanceRecord extends JFrame implements ActionListener {

    Choice choiceEMPID;
    JComboBox<String> attendanceOptions;
    JButton markAttendance, viewAttendance, back;
    String empId;

   AttendanceRecord(String empId){
        this.empId = empId;

        JLabel heading = new JLabel("Attendance Record");
        heading.setBounds(250, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 40));
        add(heading);

        JLabel label = new JLabel("Employee ID");
        label.setBounds(120,110,100,30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(250,115,150,30);
        add(choiceEMPID);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while (resultSet.next()){
                choiceEMPID.add(resultSet.getString("empId"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String[] options = {"Present", "Absent"};
        attendanceOptions = new JComboBox<>(options);
        attendanceOptions.setBounds(250, 150, 150, 30);
        add(attendanceOptions);

        markAttendance = new JButton("Mark Attendance");
        markAttendance.setBounds(120,200,180,30);
        markAttendance.setFont(new Font("SAN_SERIF", Font.BOLD,15));
        markAttendance.setBackground(Color.CYAN);
        markAttendance.setForeground(Color.BLACK);
        markAttendance.addActionListener(this);
        add(markAttendance);

        viewAttendance = new JButton("View Attendance");
        viewAttendance.setBounds(360, 200, 180, 30);
        viewAttendance.setFont(new Font("serif", Font.BOLD, 20));
        viewAttendance.setBackground(Color.CYAN);
        viewAttendance.setForeground(Color.BLACK);
        viewAttendance.addActionListener(this);
        add(viewAttendance);

        back = new JButton("Back");
        back.setBounds(260,270,100,30);
        back.setFont(new Font("SAN_SERIF", Font.BOLD,15));
        back.setBackground(Color.CYAN);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        setSize(800,450);
        setLocation(300,100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == markAttendance){
            // Write logic to mark attendance
            try{
                conn c = new conn();

                // Get the selected attendance option
                String attendanceOption = (String) attendanceOptions.getSelectedItem();
                // Write SQL query to mark attendance for selected employee with selected option
                String query = "INSERT INTO attendance (empId, date, status) VALUES ('"+choiceEMPID.getSelectedItem()+"', CURRENT_DATE, '"+attendanceOption+"')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Attendance marked successfully");
            }catch (Exception E){
                E.printStackTrace();
            }
        }else if (e.getSource() == viewAttendance) {
            new ViewAttendance(empId);
            setVisible(false);
        } else if(e.getSource() == back)
        {
            new Main_class();
            setVisible(false);

        }
    }

    public static void main(String[] args) {
        new AttendanceRecord("");
    }
}
