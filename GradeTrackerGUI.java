import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GradeTrackerGUI extends JFrame {
    private ArrayList<Integer> grades;
    private JTextField gradeField;
    private JTextArea resultArea;

    public GradeTrackerGUI() {
        grades = new ArrayList<>();

        // Create components
        JLabel gradeLabel = new JLabel("Enter Grade:");
        gradeField = new JTextField(5);
        JButton addButton = new JButton("Add Grade");
        JButton computeButton = new JButton("Compute Stats");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add action listeners
        addButton.addActionListener(new AddGradeListener());
        computeButton.addActionListener(new ComputeStatsListener());

        // Set up the layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(computeButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
        container.add(scrollPane, BorderLayout.SOUTH);

        // Set frame properties
        setTitle("Student Grade Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class AddGradeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int grade = Integer.parseInt(gradeField.getText());
                grades.add(grade);
                gradeField.setText("");
                resultArea.append("Added grade: " + grade + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GradeTrackerGUI.this, "Please enter a valid integer grade.");
            }
        }
    }

    private class ComputeStatsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (grades.isEmpty()) {
                JOptionPane.showMessageDialog(GradeTrackerGUI.this, "No grades entered yet.");
                return;
            }
            double average = computeAverage(grades);
            int highestGrade = Collections.max(grades);
            int lowestGrade = Collections.min(grades);

            resultArea.append("Average grade: " + average + "\n");
            resultArea.append("Highest grade: " + highestGrade + "\n");
            resultArea.append("Lowest grade: " + lowestGrade + "\n");
        }
    }

    public double computeAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeTrackerGUI());
    }
}
