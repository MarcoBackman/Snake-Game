import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GameScreen extends JPanel implements ActionListener {
    static JProgressBar scoreBar;
    static JLabel scoreBoard;
    JButton backButton, endButton;
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
        scoreBoard.setText("Score: " + GameData.score);
        frame.validate();
    }

    private void addPanels() {
        this.setLayout(new BorderLayout());

        scoreBar = new JProgressBar();

        GameData.autoInit();
        JPanel lowerPanel = new JPanel();
        backButton = new JButton("To Main");
        endButton = new JButton("End Game");
        snakeGame = new SnakeGame(this);
        scoreBoard = new JLabel("Score: " + GameData.score);

        frame.requestFocusInWindow();
        frame.setFocusable(true);
        frame.addKeyListener(snakeGame);
        frame.requestFocus();

        backButton.setBackground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(120, 30));
        endButton.setBackground(Color.WHITE);
        endButton.setPreferredSize(new Dimension(120, 30));

        scoreBoard.setBackground(Color.WHITE);
        scoreBoard.setPreferredSize(new Dimension(80, 30));
        scoreBar.setPreferredSize(new Dimension(getWidth(), 20));
        lowerPanel.setBackground(Color.GRAY);
        lowerPanel.add(backButton);
        lowerPanel.add(endButton);
        lowerPanel.add(scoreBoard);
        lowerPanel.setPreferredSize(new Dimension(getWidth(), 40));

        this.add(scoreBar, BorderLayout.NORTH);
        this.add(snakeGame, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        backButton.addActionListener(this);
        endButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(backButton)) {
            //Save process
            snakeGame.stopSnake();
            frame.removeKeyListener(snakeGame);
            GameData.loadData = true;
            MainMenu menu = new MainMenu(frame);
            frame.remove(this);
            frame.add(menu);
            frame.validate();
        } else if (obj.equals(endButton)) {
            //Save points and flush game
            frame.removeKeyListener(snakeGame);
            GameData.loadData = false;
            MainMenu menu = new MainMenu(frame);
            frame.remove(this);
            frame.add(menu);
            frame.validate();
            new SaveData().saveToFile("", "Anonymous",GameData.score);
        }
    }
}