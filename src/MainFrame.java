import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    final int WIDTH = 405;
    final int HEIGHT = 700;

    private MainFrame() {
        super("SnakeGame");
    }

    private void frameSetting() {
        setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainMenu menu = new MainMenu(this);
        this.add(menu);
        this.setVisible(true);
    }

    public static void main (String[] args) {
        MainFrame main = new MainFrame();
        main.frameSetting();
    }
}