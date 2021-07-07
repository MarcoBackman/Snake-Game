import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameScreen extends JPanel implements ActionListener {
    static JProgressBar scoreBar;
    JButton backButton;
    static JLabel scoreBoard;
    SnakeGame snakeGame;
    MainFrame frame;

    protected GameScreen (MainFrame frame) {
        super();
        this.frame = frame;
        focusSetting();
        addPanels();
    }

    protected GameScreen() {
        super();
        addPanels();
    }

    private void focusSetting() {
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    public void refreshScore() {
        scoreBoard.setText("Score: " + snakeGame.score);
        frame.validate();
    }

    private void addPanels() {
        this.setLayout(new BorderLayout());

        scoreBar = new JProgressBar();
        snakeGame = new SnakeGame(this);
        JPanel lowerPanel = new JPanel();
        backButton = new JButton("To Main");
        scoreBoard = new JLabel("Score: " + snakeGame.score);

        frame.requestFocusInWindow();
        frame.setFocusable(true);
        frame.addKeyListener(snakeGame);

        backButton.setBackground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(80, 30));
        scoreBoard.setBackground(Color.WHITE);
        scoreBoard.setPreferredSize(new Dimension(80, 30));
        scoreBar.setPreferredSize(new Dimension(getWidth(), 20));
        lowerPanel.setBackground(Color.GRAY);
        lowerPanel.add(backButton);
        lowerPanel.add(scoreBoard);
        lowerPanel.setPreferredSize(new Dimension(getWidth(), 40));

        this.add(scoreBar, BorderLayout.NORTH);
        this.add(snakeGame, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(backButton)) {
            MainMenu menu = new MainMenu(frame);
            frame.remove(this);
            frame.add(menu);
            frame.validate();
        }
    }
}