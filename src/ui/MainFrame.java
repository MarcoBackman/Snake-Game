package ui;

import config.GameConfig;

import javax.swing.*;

public class MainFrame extends JFrame {

    private MainMenu mainMenu;
    private final GameConfig gameConfig;

    private void initializePanel(GameConfig gameConfig) {
         //Game panel and label init
        JPanel menuPanel = mainMenu.initializeMainMenuComponents();
        this.add(menuPanel);
        this.validate();
    }

    public MainFrame(GameConfig gameConfig) {
        super("ui.SnakeGame");

        this.setSize(gameConfig.getWindowWidth(), gameConfig.getWindowHeight());
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.mainMenu = new MainMenu(gameConfig, this);
        this.gameConfig = gameConfig;

        initializePanel(gameConfig);
    }

    public void showMenuScreen() {
        this.mainMenu = new MainMenu(gameConfig, this);
        initializePanel(gameConfig);
    }

}
