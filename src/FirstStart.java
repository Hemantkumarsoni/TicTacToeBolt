import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Random;

public class FirstStart extends JFrame implements ActionListener {
    String xName, oName;
    int scoreX, scoreO;
    JButton startO, startX, toss;
    JLabel resultLabel;
    public FirstStart(String xName, String oName, int scoreX, int scoreO) {
        this.xName = xName;
        this.oName = oName;
        this.scoreO = scoreO;
        this.scoreX = scoreX;

        setTitle("Player Setup");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel scoreLabel = new JLabel("Score - " +xName+" : "+ scoreX + " | "+oName+ " : " + scoreO);
        scoreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        scoreLabel.setBounds(80, 10, 250, 30);
        add(scoreLabel);

        JLabel title = new JLabel("Who Want's to Start First : ");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        title.setBounds(80, 50, 250, 30);
        add(title);

//        startO = new JButton();
//        startO.setText(oName);
//        startO.setBounds(200, 100, 120, 40);
//        startO.setBackground(new Color(0, 123, 255));
//        startO.setForeground(Color.WHITE);
//        startO.setFocusPainted(false);
//        startO.addActionListener(this);
//        add(startO);
//
//        startX = new JButton();
//        startX.setText(xName);
//        startX.setBounds(60, 100, 120, 40);
//        startX.setBackground(new Color(0, 123, 255));
//        startX.setForeground(Color.WHITE);
//        startX.setFocusPainted(false);
//        startX.addActionListener(this);
//        add(startX);

        toss = new JButton();
        toss.setText("Toss");
        toss.setBounds(140, 100, 120, 40);
        toss.setBackground(new Color(0, 123, 255));
        toss.setForeground(Color.WHITE);
        toss.setFocusPainted(false);
        toss.addActionListener(this);
        add(toss);

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBounds(20, 150, 350, 30);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startX) {
            new AdvancedTicTacToe(xName, oName, true, scoreX, scoreO);
            dispose();
        }
        else if(e.getSource() == startO) {
            new AdvancedTicTacToe(xName, oName, false, scoreX, scoreO);
            dispose();
        }
        else if(e.getSource() == toss) {
            Random rand = new Random();
            boolean xStarts = rand.nextBoolean(); // true => X starts, false => O starts

            String winner = xStarts ? xName : oName;
            resultLabel.setText(winner + " won the toss and will start!");

            Timer t = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new AdvancedTicTacToe(xName, oName, xStarts, scoreX, scoreO);
                    dispose();
                }
            });
            t.setRepeats(false);
            t.start();
        }
    }

    public static void main(String[] args) {
        new FirstStart("Alice", "Bob", 0, 0);
    }
}
