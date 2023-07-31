import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FeesManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FeeManagementApp app = new FeeManagementApp();
            app.run();
        });
    }
}

class FeeManagementApp {
    private List<Student> students = new ArrayList<>();
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField tuitionFeeField;
    private JTextField paidFeeField;
    private JButton addButton;
    private JButton displayButton;

    public void run() {
        frame = new JFrame("Fee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        studentIdField = new JTextField(20);
        nameField = new JTextField(20);
        tuitionFeeField = new JTextField(20);
        paidFeeField = new JTextField(20);

        addButton = new JButton("Add Student");
        displayButton = new JButton("Display Student Info");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudentInfo();
            }
        });

        mainPanel.add(new JLabel("Student ID:"));
        mainPanel.add(studentIdField);
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Tuition Fee:"));
        mainPanel.add(tuitionFeeField);
        mainPanel.add(new JLabel("Paid Fee:"));
        mainPanel.add(paidFeeField);
        mainPanel.add(addButton);
        mainPanel.add(displayButton);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void addStudent() {
        String studentId = studentIdField.getText();
        String name = nameField.getText();
        double tuitionFee = Double.parseDouble(tuitionFeeField.getText());
        double paidFee = Double.parseDouble(paidFeeField.getText());
        double remainingFee = tuitionFee - paidFee;

        students.add(new Student(studentId, name, tuitionFee, paidFee, remainingFee));
        JOptionPane.showMessageDialog(frame, "Student added successfully!");
        clearFields();
    }

    private void displayStudentInfo() {
        String studentId = JOptionPane.showInputDialog(frame, "Enter Student ID:");
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                JOptionPane.showMessageDialog(frame, student.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Student not found.");
    }

    private void clearFields() {
        studentIdField.setText("");
        nameField.setText("");
        tuitionFeeField.setText("");
        paidFeeField.setText("");
    }
}

class Student {
    private String studentId;
    private String name;
    private double tuitionFee;
    private double paidFee;
    private double remainingFee;

    public Student(String studentId, String name, double tuitionFee, double paidFee, double remainingFee) {
        this.studentId = studentId;
        this.name = name;
        this.tuitionFee = tuitionFee;
        this.paidFee = paidFee;
        this.remainingFee = remainingFee;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
                "\nName: " + name +
                "\nTuition Fee: $" + tuitionFee +
                "\nPaid Fee: $" + paidFee +
                "\nRemaining Fee: $" + remainingFee;
    }
}
