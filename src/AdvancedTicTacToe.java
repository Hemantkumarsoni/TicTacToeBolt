import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.NoRouteToHostException;
import java.util.*;

public class AdvancedTicTacToe extends JFrame implements ActionListener {

    private JButton[][] board = new JButton[3][3];
    private Queue<Point> xMoves = new LinkedList<>();
    private Queue<Point> oMoves = new LinkedList<>();
    private boolean xTurn = true;

    private JLabel statusLabel, scoreLabel;
    private int xScore = 0, oScore = 0;

    public AdvancedTicTacToe() {
        setTitle("Modern Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 250, 250));
        setVisible(true);

        JLabel title = new JLabel("Tic Tac Toe Bolt", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
        title.setForeground(new Color(55, 71, 79));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel(new GridLayout(3, 3, 10, 10));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gamePanel.setBackground(new Color(230, 230, 230));

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 48);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setFont(buttonFont);
                btn.setFocusPainted(false);
                btn.setBackground(new Color(255, 255, 255));
                btn.setForeground(new Color(44, 62, 80));
                btn.addActionListener(this);

                final JButton ref = btn;
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (ref.getText().equals(""))
                            ref.setBackground(new Color(245, 245, 245));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (ref.getText().equals(""))
                            ref.setBackground(Color.WHITE);
                    }
                });

                board[i][j] = btn;
                gamePanel.add(btn);
            }
        }

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        bottomPanel.setBackground(new Color(250, 250, 250));

        scoreLabel = new JLabel("Score - X: 0 | O: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        scoreLabel.setForeground(new Color(33, 33, 33));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        statusLabel.setForeground(new Color(26, 35, 126));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resetButton.setBackground(new Color(63, 81, 181));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resetButton.addActionListener(e -> resetGame());

        bottomPanel.add(scoreLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(statusLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(resetButton);

        add(gamePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) return;

        Point location = getButtonPosition(btn);

        if (xTurn) {
            btn.setText("X");
            btn.setForeground(new Color(30, 136, 229)); // Blue
            xMoves.add(location);
            if (xMoves.size() > 3) {
                Point p = xMoves.poll();
                board[p.x][p.y].setText("");
            }
        } else {
            btn.setText("O");
            btn.setForeground(new Color(244, 81, 30)); // Orange
            oMoves.add(location);
            if (oMoves.size() > 3) {
                Point p = oMoves.poll();
                board[p.x][p.y].setText("");
            }
        }

        if (checkWin(xTurn ? "X" : "O")) {
            if (xTurn) xScore++; else oScore++;
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + " wins!");
            updateScore();
            disableBoard();
            return;
        }

        xTurn = !xTurn;
        statusLabel.setText("Player " + (xTurn ? "X" : "O") + "'s Turn");
    }

    private Point getButtonPosition(JButton btn) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == btn)
                    return new Point(i, j);
        return null;
    }

    private boolean checkWin(String player) {
        for (int i = 0; i < 3; i++)
            if (board[i][0].getText().equals(player) &&
                    board[i][1].getText().equals(player) &&
                    board[i][2].getText().equals(player)) return true;

        for (int j = 0; j < 3; j++)
            if (board[0][j].getText().equals(player) &&
                    board[1][j].getText().equals(player) &&
                    board[2][j].getText().equals(player)) return true;

        if (board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player)) return true;

        if (board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player)) return true;

        return false;
    }

    private void disableBoard() {
        for (JButton[] row : board)
            for (JButton btn : row)
                btn.setEnabled(false);
    }

    private void updateScore() {
        scoreLabel.setText("Score - X: " + xScore + " | O: " + oScore);
    }

    private void resetGame() {
        for (JButton[] row : board)
            for (JButton btn : row) {
                btn.setText("");
                btn.setEnabled(true);
                btn.setBackground(Color.WHITE);
            }
        xMoves.clear();
        oMoves.clear();
        xTurn = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        new AdvancedTicTacToe();
    }
}
