package data;

import coponents.WholeBody;

import java.util.LinkedList;

public class GameData {
    //returns if the data is saved data or not
    final public static int INIT_GRID_SIZE = 82;
    final public static int INIT_SNAKE_SPEED = 80;
    final public static int INIT_SQUARE_SIZE = 7;
    final public static int INIT_APPLE_NUM = 30;

    @Deprecated
    public static boolean loadData;

    //Snake information
    public static int currentPositionX, currentPositionY;
    public static int previousPositionX, previousPositionY;
    public static WholeBody snake;

    //Target information
    public static LinkedList<Integer> applePosX, applePosY;

    //Game environment
    public static boolean[][] grid;
    public static int snakeSpeed,
                      squareSize,
                      gridSize,
                      score,
                      direction,
                      numOfApple;

    //Rum this on user requesst
    //minumum size 8, max to 100
    public static void init() {
        System.out.println("Initializing Game Data");
        squareSize = INIT_SQUARE_SIZE;
        currentPositionX = (INIT_GRID_SIZE - 2) / 2;
        currentPositionY = (INIT_GRID_SIZE - 2) / 2;
        previousPositionX = 0;
        previousPositionY = 0;
        direction = 0;

        applePosX = new LinkedList<Integer>();
        applePosY = new LinkedList<Integer>();
        numOfApple = INIT_APPLE_NUM;
        initialApple();

        snakeSpeed = INIT_SNAKE_SPEED;
        grid = new boolean[INIT_GRID_SIZE][INIT_GRID_SIZE];
        gridSize = INIT_GRID_SIZE;
        snake = new WholeBody(currentPositionX, currentPositionY);
        score = 0;
    }

    private static void initialApple() {
        for (int i = 0; i < numOfApple; i++) {
            applePosX.add(i, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
            applePosY.add(i, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));

            while (applePosX.get(i) == currentPositionX
             && applePosY.get(i) == currentPositionY) {
                applePosX.set(i, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
                applePosY.set(i, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
            }
        }
    }

    public static void setApplePos(int appleNumber) {
        applePosX.set(appleNumber, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
        applePosY.set(appleNumber, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));

        while (applePosX.get(appleNumber) == currentPositionX
            && applePosY.get(appleNumber) == currentPositionY) {
            applePosX.set(appleNumber, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
            applePosY.set(appleNumber, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
        }
    }

    //Rum this on game load
    public static void autoInit() {
        if (!loadData) {
            init();
        }
    }
}
