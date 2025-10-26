import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    private String[] questions = {
        "1. What is the capital of France?",
        "2. Which language runs in a web browser?",
        "3. Who developed Java?",
        "4. What is 5 + 3 * 2?",
        "5. Which company created Windows?"
    };

    private String[][] options = {
        {"Paris", "London", "Rome", "Berlin"},
        {"C++", "Python", "JavaScript", "Java"},
        {"James Gosling", "Guido van Rossum", "Dennis Ritchie", "Bjarne Stroustrup"},
        {"11", "16", "10", "13"},
        {"Apple", "Microsoft", "Google", "IBM"}
    };

    private int[] answers = {0, 2, 0, 3, 1};
    private int index = 0, correct = 0;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private ButtonGroup group;

    public QuizApp() {
        setTitle("Quiz App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        optionButtons = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText(questions[index]);
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[index][i]);
            }
            group.clearSelection();
        } else {
            showResult();
        }
    }

    private void showResult() {
        double score = ((double) correct / questions.length) * 100;
        JOptionPane.showMessageDialog(this,
                "Quiz Over!\nCorrect: " + correct + "/" + questions.length +
                        "\nScore: " + (int) score + "%");
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) selected = i;
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        if (selected == answers[index]) correct++;
        index++;
        loadQuestion();
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
