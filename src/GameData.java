import java.util.LinkedList;

public class GameData {
    //returns if the data is saved data or not
    final public static int INIT_GRID_SIZE = 40;
    final public static int INIT_SNAKE_SPEED = 100;
    final public static int INIT_SQUARE_SIZE = 5;

    public static boolean loadData;

    //Snake information
    public static int currentPositionX, currentPositionY;
    public static int previousPositionX, previousPositionY;
    public static WholeBody snake;

    //Target information
    public static LinkedList<Integer> applePosX, applePosY;

    //Game environment
    public static boolean[][] grid;
    public static int snakeSpeed, squareSize, gridSize, score, direction;

    //Rum this on user requesst
    //minumum size 8, max to 100
    public static void init() {
        squareSize = INIT_SQUARE_SIZE;
        currentPositionX = (INIT_GRID_SIZE - 2) / 2;
        currentPositionY = (INIT_GRID_SIZE - 2) / 2;
        previousPositionX = 0;
        previousPositionY = 0;
        direction = 0;
        applePosX = new LinkedList<Integer>();
        applePosY = new LinkedList<Integer>();
        initialApple();
        snakeSpeed = INIT_SNAKE_SPEED;
        grid = new boolean[INIT_GRID_SIZE][INIT_GRID_SIZE];
        gridSize = INIT_GRID_SIZE;
        snake = new WholeBody(currentPositionX, currentPositionY);
        score = 0;
    }

    private static void initialApple() {
        applePosX.add((int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
        applePosY.add((int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));

        while (applePosX.get(0) == currentPositionX && applePosY.get(0) == currentPositionY) {
            applePosX.set(0, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
            applePosY.set(0, (int) ((Math.random() * (INIT_GRID_SIZE - 3)) + 1));
        }
    }

    //Rum this on game load
    public static void autoInit() {
        if (loadData == false) {
            init();
        }
    }
}
