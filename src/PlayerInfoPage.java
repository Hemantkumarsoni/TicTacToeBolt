import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerInfoPage extends JFrame implements ActionListener {
    String xName, oName;
    JTextField xField, oField;
    JButton startBtn;
    public PlayerInfoPage() {
        setTitle("Player Setup");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel xLabel = new JLabel("Enter Player X Name:");
        xLabel.setBounds(50, 50, 250, 30);
        xLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(xLabel);

        xField = new JTextField();
        xField.setFont(new Font("Raleway", Font.BOLD, 20));
        xField.setBounds(350, 50, 180, 30);
        add(xField);

        JLabel oLabel = new JLabel("Enter Player O Name:");
        oLabel.setBounds(50, 100, 250, 30);
        oLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(oLabel);

        oField = new JTextField();
        oField.setFont(new Font("Raleway", Font.BOLD, 20));
        oField.setBounds(350, 100, 180, 30);
        add(oField);

        startBtn = new JButton("Start Game");
        startBtn.setBounds(180, 170, 140, 40);
        startBtn.setBackground(new Color(0, 123, 255));
        startBtn.setForeground(Color.WHITE);
        startBtn.setFocusPainted(false);
        startBtn.addActionListener(this);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(startBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(xField.getText().isEmpty() || oField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter the Player's Name");
            return;
        }
        else if(e.getSource() == startBtn) {
            xName = xField.getText().trim();
            oName = oField.getText().trim();
            new FirstStart(xName, oName, 0, 0);
            dispose();
        }
    }

    public static void main(String[] args) {
        new PlayerInfoPage();
    }
}
