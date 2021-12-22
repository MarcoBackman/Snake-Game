import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenu extends JPanel implements ActionListener {
    final int SWING_CENTER = SwingConstants.CENTER;
    final Color MENU_BG_COLOR = Color.decode("#6365ff");
    final Color MENU_TITLE_COLOR = Color.decode("#4445b3");

    JLabel mainTitle = new JLabel("Snake Game");
    JButton startButton = new JButton("Start");
    JButton scoreButton = new JButton("Score");
    JButton exitButton = new JButton("Exit");
    final String userName = "user01";
    final String filePath = "./log/logfile.log";

    MainFrame frame;

    protected MainMenu(MainFrame frame) {
        this.frame = frame;
        addComponentsToPanel();
    }

    public void addComponentsToPanel() {
        this.setLayout(new GridLayout (2, 1, 0, 10));

        startButton.setBackground(Color.WHITE);
        scoreButton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);

        mainTitle.setHorizontalAlignment(SWING_CENTER);
        mainTitle.setVerticalAlignment(SWING_CENTER);
        mainTitle.setHorizontalTextPosition(SWING_CENTER);
        mainTitle.setVerticalTextPosition(SWING_CENTER);
        mainTitle.setBackground(MENU_TITLE_COLOR);
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setOpaque(true);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(3, 1));
        upperPanel.setBackground(MENU_BG_COLOR);
        upperPanel.add(mainTitle);
        upperPanel.add(new JPanel() {
            @Override
            public void setBackground(Color bg) {
                super.setBackground(MENU_BG_COLOR);
            }
        });
        upperPanel.add(startButton);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(3, 1, 0, 10));
        lowerPanel.setBackground(MENU_BG_COLOR);
        lowerPanel.add(scoreButton);
        lowerPanel.add(exitButton);
        lowerPanel.add(new JPanel() {
            @Override
            public void setBackground(Color bg) {
                super.setBackground(MENU_BG_COLOR);
            }
        });
        this.setBackground(MENU_BG_COLOR);
        this.add(upperPanel);
        this.add(lowerPanel);
        this.validate();

        addPanelToFrame(this, this);
        addListeners();
        disableFocuses();
    }

    private void addPanelToFrame(JPanel newPanel, JPanel oldPanel) {
        frame.remove(oldPanel);
        frame.add(newPanel);
        frame.validate();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(startButton)) {
            GameScreen snakeGameScreen = new GameScreen(frame);
            addPanelToFrame(snakeGameScreen, this);
        } else if (obj.equals(scoreButton)) {
            new LoadData().readPointsFromFile("");
        } else if (obj.equals(exitButton)) {
            System.exit(0);
        }
    }
}