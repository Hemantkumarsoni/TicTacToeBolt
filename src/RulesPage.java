import javax.swing.*;
import java.awt.*;

public class RulesPage extends JFrame {
    public RulesPage() {
        setTitle("Tic Tac Toe Rules");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea rules = new JTextArea(
                "--------------------------------Welcome to Tic Tac Toe Bolt!--------------------------\n\n" +
                        " Rules:\n\n" +
                        "1. The game is played on a 3x3 grid.\n" +
                        "2. Players take turns placing their marks (X or O).\n" +
                        "3. Each player can only keep **3 active moves** at a time.\n" +
                        "   - When a 4th move is made, the earliest move disappears.\n" +
                        "4. The first player to align 3 marks in a row, column, or diagonal wins.\n" +
                        "5. The game never ends in a draw — play continues until someone wins.\n" +
                        "6. After each round, you can replay and the scoreboard will keep track of wins."
        );
        rules.setEditable(false);
        rules.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        add(new JScrollPane(rules), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JCheckBox agreeCheck = new JCheckBox("I have read all the rules and want to continue");
        JButton nextBtn = new JButton("Next");

        nextBtn.setEnabled(false);
        agreeCheck.addActionListener(e -> nextBtn.setEnabled(agreeCheck.isSelected()));

        nextBtn.addActionListener(e -> {
            new PlayerInfoPage();
            dispose();
        });

        bottomPanel.add(agreeCheck);
        bottomPanel.add(nextBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RulesPage();
    }
}
