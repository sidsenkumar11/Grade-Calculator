import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Creates a Grade Calculator for use by high schoolers
 * at West Windsor-Plainsboro High School North.
 * Adapted in part from:
 * www.java-forums.org/awt-swing/58196-empty-space-between-jlabels-jframe.html
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class GradeCalculator extends JFrame {

    // Panel to be put on JFrame
    private JPanel displayPanel = new JPanel();

    // Inside panels for displayPanel
    private JPanel labelsPanel = new JPanel(new GridLayout(4, 1));
    private JPanel textsPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    // 6 JTextFields
    private JTextField MP1 = new JTextField();
    private JTextField MP2 = new JTextField();
    private JTextField MP3 = new JTextField();
    private JTextField MP4 = new JTextField();
    private JTextField finalWantedField = new JTextField();
    private JTextField resultField = new JTextField();

    // 6 JLabels
    private JLabel MP1Label = new JLabel("MP 1 Grade: ");
    private JLabel MP2Label = new JLabel("MP 2 Grade: ");
    private JLabel MP3Label = new JLabel("MP 3 Grade: ");
    private JLabel MP4Label = new JLabel("MP 4 Grade: ");
    private JLabel finalWantedLabel = new JLabel("Year Grade Desired: ");
    private JLabel resultFieldLabel = new JLabel("% Needed On Final Exam: ");

    // Font
    private Font font = new Font("Times New Roman", Font.BOLD, 15);
    // Border
    private TitledBorder border = new TitledBorder("Grade Percentages");
    // Layout of Labels & TextBoxes

    private GridLayout grid = new GridLayout(7, 1);
    /**
     * Creates the grade calculator by setting
     * up and adding components to this frame.
     */
    public GradeCalculator() {

        // General Frame Initialization
        super("Grade Calculator - Created by Sid S.");
        initialize();

        // Creates & Initializes Labels, Text Boxes, and Button
        addLabels();
        addTextBoxes();
        addButton();

        // Add labelsPanel, textsPanel, and button into displayPanel in new
        // GridLayout w/border
        addToDisplayPanel();

        // Adds displayPanel to jFrame
        add(displayPanel);
    }

    /**
     * Sets up general characteristics of this JFrame
     */
    public void initialize() {
        setSize(600, 300);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(0, 0, 0));
        getContentPane().setBackground(Color.GRAY);
        displayPanel.setPreferredSize(new Dimension(600, 300));
    }

    /**
     * Creates labels and places them onto a label panel.
     */
    public void addLabels() {
        // Align Labels to left
        MP1Label.setHorizontalAlignment(JLabel.LEFT);
        MP2Label.setHorizontalAlignment(JLabel.LEFT);
        MP3Label.setHorizontalAlignment(JLabel.LEFT);
        MP4Label.setHorizontalAlignment(JLabel.LEFT);
        finalWantedLabel.setHorizontalAlignment(JLabel.LEFT);
        resultFieldLabel.setHorizontalAlignment(JLabel.LEFT);

        // Set Labels' fonts
        MP1Label.setFont(font);
        MP2Label.setFont(font);
        MP3Label.setFont(font);
        MP4Label.setFont(font);
        finalWantedLabel.setFont(font);
        resultFieldLabel.setFont(font);

        // Add Labels to labelsPanel in grid formation
        labelsPanel.setLayout(grid);
        labelsPanel.add(MP1Label);
        labelsPanel.add(MP2Label);
        labelsPanel.add(MP3Label);
        labelsPanel.add(MP4Label);
        labelsPanel.add(finalWantedLabel);
        labelsPanel.add(resultFieldLabel);
    }

    /**
     * Adds Textboxes to a textbox panel.
     */
    public void addTextBoxes() {
        // Add Text Boxes to textsPanel in grid formation
        textsPanel.setLayout(grid);
        textsPanel.add(MP1);
        textsPanel.add(MP2);
        textsPanel.add(MP3);
        textsPanel.add(MP4);
        textsPanel.add(finalWantedField);
        textsPanel.add(resultField);
    }

    /**
     * Adds a calculate button to a button panel.
     * The button's function is to calculate the desired grade
     * and display the result in a textfield.
     */
    public void addButton() {

        // Creates Button, Adds Action Event, and Adds Button to buttonPanel
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (isFull(MP1, MP2, MP3, MP4, finalWantedField)) {
                    if (isNumber(MP1.getText()) && isNumber(MP2.getText())
                            && isNumber(MP3.getText())
                            && isNumber(MP4.getText())
                            && isNumber(finalWantedField.getText())) {
                        String firstMP = MP1.getText();
                        String secondMP = MP2.getText();
                        String thirdMP = MP3.getText();
                        String fourthMP = MP4.getText();
                        String finalWantedString = finalWantedField.getText();

                        // Get rid of extra spaces
                        firstMP = firstMP.trim();
                        secondMP = secondMP.trim();
                        thirdMP = thirdMP.trim();
                        fourthMP = fourthMP.trim();
                        finalWantedString = finalWantedString.trim();

                        // Convert Strings to Doubles
                        double first = Double.parseDouble(firstMP);
                        double second = Double.parseDouble(secondMP);
                        double third = Double.parseDouble(thirdMP);
                        double fourth = Double.parseDouble(fourthMP);
                        double finalWantedGrade = Double
                                .parseDouble(finalWantedString);

                        // Calculate grade and display
                        double gradeNeeded = calculate(first, second, third,
                                fourth, finalWantedGrade);
                        resultField.setText(String.format("%.2f", gradeNeeded)
                                + "%");
                    } else {
                        resultField.setText("Please only enter numbers.");
                    }
                } else {
                    resultField.setText("Please fill in all fields.");
                }
            }
        });
        buttonPanel.add(button);
    }

    /**
     * Adds all existing panels into main panel in a grid layout.
     */
    public void addToDisplayPanel() {
        displayPanel.setLayout(new GridLayout(1, 3));
        displayPanel.add(labelsPanel);
        displayPanel.add(textsPanel);
        displayPanel.add(buttonPanel);
        displayPanel.setBorder(border);
    }

    /**
     * Performs calculations on the inputs to output the desired grade
     * @param mp1 The percentage grade received in the first quarter
     * @param mp2 The percentage grade received in the second quarter
     * @param mp3 The percentage grade received in the third quarter
     * @param mp4 The percentage grade received in the fourth quarter
     * @param finalGradeWanted The grade desired for the year
     * @return The percentage grade needed on the final to
     *         achieve the desired final grade.
     */
    public double calculate(double mp1, double mp2, double mp3, double mp4,
            double finalGradeWanted) {
        double x = 9 * finalGradeWanted - 2 * (mp1 + mp2 + mp3 + mp4);
        return ((int) ((x * 100) + .5)) / 100;
    }

    /**
     * Checks whether input is a number.
     * @param input A string potentially representing a number.
     * @return Is the input a number?
     */
    public boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks to see if all desired textfields are filled in
     * before the calculations can be computed.
     * @param box1 Textbox with first quarter grade
     * @param box2 Textbox with second quarter grade
     * @param box3 Textbox with third quarter grade
     * @param box4 Textbox with fourth quarter grade
     * @param box5 Textbox with desired final grade
     * @return Do all of these textboxes have something written in them?
     */
    public boolean isFull(JTextField box1, JTextField box2,
                          JTextField box3, JTextField box4, JTextField box5) {
        return box1.getText().length() > 0 && box2.getText().length() > 0
               && box3.getText().length() > 0 && box4.getText().length() > 0
               && box5.getText().length() > 0;
    }

    /**
     * Runs the program and displays the GradeCalculator frame.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new GradeCalculator().setVisible(true);
    }
}
