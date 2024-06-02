package ui;

import config.GameConfig;
import data.GameData;
import data.SaveData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main game screen navigated from MainMenu or navigated back to MainMenu.
 * This screen will contain main game graphics
 */
public class GameScreen extends JPanel implements ActionListener {
    private final JLabel scoreBoard;
    private final JButton backButton;
    private final JButton endButton;
    SnakeGame snakeGame;
    MainFrame parentFrame;
    public static GameData gameData;
    private GameConfig gameConfig;

    protected GameScreen (MainFrame parentFrame, GameConfig gameConfig) {
        super();

        this.scoreBoard = new JLabel("Score: " + GameData.score);
        this.backButton = new JButton("To Main");
        this.endButton = new JButton("End Game");

        this.gameConfig = gameConfig;
        this.parentFrame = parentFrame;
        focusSetting();
        addPanels();
    }

    private void focusSetting() {
        parentFrame.setFocusable(true);
        parentFrame.requestFocusInWindow();
    }

    public void refreshScore() {
        scoreBoard.setText("Score: " + GameData.score);
        parentFrame.validate();
    }

    private void addPanels() {
        this.setLayout(new BorderLayout());

        GameData.autoInit();
        JPanel lowerPanel = new JPanel();
        snakeGame = new SnakeGame(this, gameConfig);

        parentFrame.requestFocusInWindow();
        parentFrame.setFocusable(true);
        parentFrame.addKeyListener(snakeGame);
        parentFrame.requestFocus();

        backButton.setBackground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(120, 30));
        endButton.setBackground(Color.WHITE);
        endButton.setPreferredSize(new Dimension(120, 30));

        scoreBoard.setBackground(Color.WHITE);
        scoreBoard.setPreferredSize(new Dimension(80, 30));
        lowerPanel.setBackground(Color.GRAY);
        lowerPanel.add(backButton);
        lowerPanel.add(endButton);
        lowerPanel.add(scoreBoard);
        lowerPanel.setPreferredSize(new Dimension(getWidth(), 40));

        this.add(snakeGame, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        backButton.addActionListener(this);
        endButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        //Todo: implement load & save feature
        //Going back to main menu - pausing game
        if (obj.equals(backButton)) {
            snakeGame.stopSnake();
            parentFrame.removeKeyListener(snakeGame);
            //GameData.loadData = true;
            parentFrame.remove(this);

            //Repaint current screen with menu
            parentFrame.showMenuScreen();
        }
        //Terminate game without save - log will remain
        else if (obj.equals(endButton)) {
            parentFrame.removeKeyListener(snakeGame);
            //GameData.loadData = false;

            parentFrame.remove(this);

            //Save score
            new SaveData().saveToFile("", "Anonymous", GameData.score);

            //Repaint current screen with menu
            parentFrame.showMenuScreen();
        }
    }
}