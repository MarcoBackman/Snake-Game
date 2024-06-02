package ui;

import config.GameConfig;
import data.LoadData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
    private final Color menuBgColor;
    private final Color menuTitleColor;
    private final MainFrame mainFrame;
    private final GameConfig gameConfig;
    private final JLabel mainTitle;
    private final JButton startButton;
    private final JButton scoreButton;
    private final JButton exitButton;

    protected MainMenu(GameConfig gameConfig, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.gameConfig = gameConfig;
        menuBgColor = Color.decode(gameConfig.getMenuBgColor());
        menuTitleColor = Color.decode(gameConfig.getMenuTitleColor());
        this.setBackground(menuBgColor);

        //Components initialization
        mainTitle = new JLabel("Snake Game");
        initializeLabels();

        startButton = new JButton("Start");
        scoreButton = new JButton("Score");
        exitButton = new JButton("Exit");
        initializeMenuButtons();
    }

    private void initializeMenuButtons() {
        startButton.setBackground(Color.WHITE);
        scoreButton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);
    }

    private void initializeLabels() {
        mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainTitle.setVerticalAlignment(SwingConstants.CENTER);
        mainTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        mainTitle.setVerticalTextPosition(SwingConstants.CENTER);
        mainTitle.setBackground(menuTitleColor);
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setOpaque(true);
    }

    private JPanel emptyPanel() {
        return new JPanel() {
            @Override
            public void setBackground(Color bg) {
                super.setBackground(menuBgColor);
            }
        };
    }

    private void addComponentsToMenu() {
        this.setLayout(new GridLayout (6, 1, 0, 10));
        this.add(mainTitle);
        this.add(emptyPanel());
        this.add(startButton);
        this.add(scoreButton);
        this.add(exitButton);
    }

    private void addPanelToFrame(JPanel newPanel, JPanel oldPanel) {
        mainFrame.remove(oldPanel);
        mainFrame.add(newPanel);
        mainFrame.validate();
    }

    private void addListeners() {
        startButton.addActionListener(this);
        scoreButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    private void disableFocuses() {
        startButton.setFocusable(false);
        scoreButton.setFocusable(false);
        exitButton.setFocusable(false);
    }

    /*
        Call this to reform menu screen
     */
    public JPanel initializeMainMenuComponents() {
        initializeMenuButtons();
        initializeLabels();
        addComponentsToMenu();
        addListeners();
        disableFocuses();
        this.validate();
        mainFrame.validate();
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(startButton)) {
            GameScreen snakeGameScreen = new GameScreen(mainFrame, gameConfig);
            addPanelToFrame(snakeGameScreen, this);
        } else if (obj.equals(scoreButton)) {
            new LoadData().readPointsFromFile("");
        } else if (obj.equals(exitButton)) {
            System.exit(0);
        }
    }
}