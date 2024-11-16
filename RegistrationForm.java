import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    // Components
    private JTextField txtName, txtMobile, txtDOB, txtAddress;
    private JRadioButton maleButton, femaleButton;
    private JCheckBox termsCheckbox;
    private JButton submitButton, exitButton;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public RegistrationForm() {
        // Frame properties
        setTitle("Registration Form");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Mobile:"));
        txtMobile = new JTextField();
        inputPanel.add(txtMobile);

        inputPanel.add(new JLabel("Gender:"));
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        inputPanel.add(genderPanel);

        inputPanel.add(new JLabel("DOB:"));
        txtDOB = new JTextField();
        inputPanel.add(txtDOB);

        inputPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        inputPanel.add(txtAddress);

        termsCheckbox = new JCheckBox("Accept Terms and Conditions");
        inputPanel.add(termsCheckbox);

        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        exitButton = new JButton("Exit");
        buttonPanel.add(submitButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Table
        String[] columnNames = {"Name", "Mobile", "Gender", "DOB", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        add(new JScrollPane(dataTable), BorderLayout.SOUTH);

        // Button Actions
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void saveData() {
        if (termsCheckbox.isSelected()) {
            String name = txtName.getText();
            String mobile = txtMobile.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = txtDOB.getText();
            String address = txtAddress.getText();

            // Add to table
            tableModel.addRow(new Object[]{name, mobile, gender, dob, address});

            // Reset fields
            txtName.setText("");
            txtMobile.setText("");
            txtDOB.setText("");
            txtAddress.setText("");
            genderGroup.clearSelection();
            termsCheckbox.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(this, "You must accept the terms and conditions.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationForm().setVisible(true);
        });
    }
}
