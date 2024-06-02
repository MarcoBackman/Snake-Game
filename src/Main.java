import config.ConfigReader;
import config.GameConfig;
import ui.MainFrame;

public class Main {

    public static void main (String[] args) {

        //Read config
        GameConfig gameConfig = ConfigReader.getGameConfig();

        //Initialize components
        MainFrame mainFrame = new MainFrame(gameConfig);

        //Run UI

    }
}