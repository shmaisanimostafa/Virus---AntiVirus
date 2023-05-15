import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AntiVirus extends JFrame implements ActionListener {

    private JTextField directoryField;
    private JTextArea resultArea;
    private JPanel resultsPanel;

    public AntiVirus() {

        super("Mostafa's AntiVirus");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create directory input field and search button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel directoryLabel = new JLabel("Enter directory path:");
        directoryField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.green);
        searchButton.addActionListener(this);

        topPanel.add(directoryLabel);
        topPanel.add(directoryField);
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);

        // Create panel to show results
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        add(scrollPane, BorderLayout.CENTER);

        resultArea = new JTextArea();
        add(resultArea, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            String dirPath = directoryField.getText().trim();
            if (!dirPath.isEmpty()) {
                search(dirPath);
            }
        } else if (e.getActionCommand().equals("Delete")) {
            File fileToDelete = (File) ((JButton) e.getSource()).getClientProperty("fileToDelete");
            boolean success = fileToDelete.delete();
            if (success) {
                resultArea.append(fileToDelete.getName() + " deleted successfully\n");
            } else {
                resultArea.append("Error deleting file " + fileToDelete.getName() + "\n");
            }
            ((JButton) e.getSource()).setEnabled(false);
        }
    }

    public void search(String dirPath) {
        File dir = new File(dirPath);
        File[] filesList = dir.listFiles();
        if (filesList == null) {
            resultArea.append("Invalid directory path\n");
            return;
        }
        String[] words = { "Security", "dormant", "payload", "trigger", "//ComputerSecurity", "Virus" };

        for (File file : filesList) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    boolean fileMatched = false;
                    while ((line = br.readLine()) != null) {
                        for (String string : words) {
                            if (line.contains(string)) {
                                resultArea.append("Found " + string + " in file: " + file.getName() + "\n");

                                if (!fileMatched) {
                                    addSearchResult(file);
                                    fileMatched = true;
                                }
                                break;
                            }
                        }
                    }
                } catch (Exception ex) {
                    resultArea.append("Error searching file " + file.getName() + "\n");
                    ex.printStackTrace();
                }
            } else if (file.isDirectory()) {
                search(file.getAbsolutePath());
            }
        }
        resultArea.setForeground(Color.red);

    }

    private void addSearchResult(File file) {
        JPanel resultPanel = new JPanel(new BorderLayout());
        JLabel resultLabel = new JLabel(file.getName());
        resultPanel.add(resultLabel, BorderLayout.WEST);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.red);
        deleteButton.setForeground(Color.white);
        deleteButton.putClientProperty("fileToDelete", file);
        deleteButton.addActionListener(this);
        resultPanel.add(deleteButton, BorderLayout.EAST);
        resultsPanel.add(resultPanel);
        revalidate();
    }

    public static void main(String[] args) {

        new AntiVirus();
    }
}
