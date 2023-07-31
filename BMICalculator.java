import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator {
    private JFrame frame;
    private JPanel panel;
    private JTextField weightField;
    private JTextField heightField;
    private JLabel bmiLabel;
    private JLabel categoryLabel;

    public BMICalculator() {
        frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        weightField = new JTextField(10);
        heightField = new JTextField(10);
        bmiLabel = new JLabel("Your BMI: ");
        categoryLabel = new JLabel("BMI Category: ");

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateListener());

        panel.add(new JLabel("Enter your weight in kilograms: "));
        panel.add(weightField);
        panel.add(new JLabel("Enter your height in meters: "));
        panel.add(heightField);
        panel.add(calculateButton);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(bmiLabel);
        panel.add(categoryLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class CalculateListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());

                double bmi = calculateBMI(weight, height);
                String category = interpretBMI(bmi);

                bmiLabel.setText("Your BMI: " + bmi);
                categoryLabel.setText("BMI Category: " + category);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter valid numbers.");
            }
        }
    }

    public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    public static String interpretBMI(double bmi) {
        if (bmi < 16.0) {
            return "Severely underweight";
        } else if (bmi >= 16.0 && bmi < 16.9) {
            return "Underweight";
        } else if (bmi >= 17.0 && bmi < 18.4) {
            return "Mildly underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi < 29.9) {
            return "Overweight";
        } else if (bmi >= 30.0 && bmi < 34.9) {
            return "Obese Class I (Moderate)";
        } else if (bmi >= 35.0 && bmi < 39.9) {
            return "Obese Class II (Severe)";
        } else {
            return "Obese Class III (Very severe or morbidly obese)";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMICalculator());
    }
}
