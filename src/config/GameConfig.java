package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameConfig {

    @JsonProperty("WINDOW_WIDTH")
    int windowWidth;
    @JsonProperty("WINDOW_HEIGHT")
    int windowHeight;
    @JsonProperty("MENU_BG_COLOR")
    String menuBgColor;
    @JsonProperty("MENU_TITLE_COLOR")
    String menuTitleColor;
    @JsonProperty("GOAL_SCORE")
    int goalScore;
    @JsonProperty("SPEED_UP_INTERVAL")
    int speedupInterval;

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public String getMenuTitleColor() {
        return menuTitleColor;
    }

    public String getMenuBgColor() {
        return menuBgColor;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public int getSpeedupInterval() {
        return speedupInterval;
    }
}
