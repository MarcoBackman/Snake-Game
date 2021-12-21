import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    final int SNAKE_SPEED_FASTER = 90;
    final int SNAKE_SPEED_FASTEST = 80;
    final int SNAKE_SPEED_WEE = 50;
    final int SNAKE_SPEED_REEE = 30;
    final int INITIAL_TIME = 60;
    final int PAUSE_KEY = 80; //Keycode for 'P'
    final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;

    Timer speed = new Timer(GameData.snakeSpeed, this);

    GameScreen parentPanel;

    boolean gameOver, gameStarted, keyTyped;
    long beforeTime = 0;

    protected SnakeGame(final GameScreen parentPanel) {
        setDoubleBuffered(true);
        this.parentPanel = parentPanel;
        parentPanel.addKeyListener(this);
        GameData.grid[GameData.currentPositionY][GameData.currentPositionX] = true;
        genAppleLoc();
        validate();
    }

    public void stopSnake() {
        speed.stop();
        System.out.println("Stopped");
    }

    private void gameOver() {
        System.out.println("Game Over");
        GameData.loadData = false;
        speed.stop();
        gameOver = true;
    }

    private void genAppleLoc() {
        GameData.applePosX.set(0, (int) ((Math.random() * (GameData.gridSize - 3)) + 1));
        GameData.applePosY.set(0, (int) ((Math.random() * (GameData.gridSize - 3)) + 1));
    }

    private boolean snakeIsDead () {
        //bumps into a wall
        if (GameData.currentPositionX == 0
         || GameData.currentPositionX == GameData.gridSize - 2
         || GameData.currentPositionY == 0
         || GameData.currentPositionY == GameData.gridSize - 2) {
            System.out.println("Hits wall!");
            return true;
         }
        //if snake has eaten it self
        if (GameData.grid[GameData.currentPositionY][GameData.currentPositionX]) {
            System.out.println("Eaten itself!");
            return true;
        }
        return false;
    }

    private boolean eaten() {
        if (GameData.currentPositionX == GameData.applePosX.get(0)
         && GameData.currentPositionY == GameData.applePosY.get(0)) {
            ++GameData.score;
            return true;
        }
        return false;
    }

    private void drawInitialBoard(final Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < GameData.gridSize - 1; i++) {
            for (int j = 0; j < GameData.gridSize - 1; j++) {
                if (i == 0)
                    g.fillRect(j * GameData.squareSize, 0, GameData.squareSize, GameData.squareSize);
                else if (j == 0)
                    g.fillRect(0, i * GameData.squareSize, GameData.squareSize, GameData.squareSize);
                else if (i == GameData.gridSize - 2)
                    g.fillRect(j * GameData.squareSize, i * GameData.squareSize,
                     GameData.squareSize, GameData.squareSize);
                else if (j == GameData.gridSize - 2)
                    g.fillRect((GameData.gridSize - 2) * GameData.squareSize, i * GameData.squareSize,
                     GameData.squareSize, GameData.squareSize);
            }
        }
        GameData.snake.drawCells(g);
        g.setColor(Color.YELLOW);
        g.fillRect(GameData.applePosX.get(0) * GameData.squareSize,
                    GameData.applePosY.get(0) * GameData.squareSize,
                    GameData.squareSize, GameData.squareSize);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g); //overwrites parent's graphics
        setBackground(Color.BLACK);
        drawInitialBoard(g);
        GameData.snake.drawCells(g);
        validate();
    }

    @Override
    public void actionPerformed (final ActionEvent e) {
        Toolkit.getDefaultToolkit().sync(); //This prevents repaint desync on Linux OS
        Object obj = e.getSource();
        if (obj.equals(speed) && !gameOver) {
            // do all drawing
            keyTyped = false;

            if (GameData.direction == UP) {
                GameData.snake.shiftCell(GameData.currentPositionX,
                                         --GameData.currentPositionY);
            } else if (GameData.direction == DOWN) {
                GameData.snake.shiftCell(GameData.currentPositionX,
                                         ++GameData.currentPositionY);
            } else if (GameData.direction == LEFT) {
                GameData.snake.shiftCell(--GameData.currentPositionX,
                                         GameData.currentPositionY);
            } else if (GameData.direction == RIGHT) {
                GameData.snake.shiftCell(++GameData.currentPositionX,
                                         GameData.currentPositionY);
            }

            if (eaten()) {
                System.out.println("Eaten");
                if (GameData.score % 10 == 0) {
                    if (GameData.snakeSpeed > 10) {
                        System.out.print("Speeding up");
                        GameData.snakeSpeed -= 10;
                    }
                    speed.setDelay(GameData.snakeSpeed);
                }
                parentPanel.refreshScore();

                GameData.snake.addCell(new BodyCell(GameData.currentPositionX, GameData.currentPositionY));
                genAppleLoc();
            }
            //Calls this after shifting snake cell
            if (snakeIsDead()) {
                gameOver();
                GameData.init();
            }

            if (!gameOver) {
                repaint();
            }
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        int key = e.getKeyCode();
        if (key == PAUSE_KEY) { // 'P' pressed
            System.out.println("Pause");
            if (speed.isRunning()) {
                speed.stop();
                gameStarted = true;
            }
        }

        if (key == KeyEvent.VK_UP && GameData.direction != 2 && !keyTyped) {
            keyTyped = true;
            GameData.direction = UP;
            if (!speed.isRunning()) {
                speed.start();
                gameStarted = true;
            }
        } else if (key == KeyEvent.VK_DOWN && GameData.direction != 1 && !keyTyped) {
            keyTyped = true;
            GameData.direction = DOWN;
            if (!speed.isRunning()) {
                speed.start();
                gameStarted = true;
            }
        } else if (key == KeyEvent.VK_LEFT && GameData.direction != 4 && !keyTyped) {
            keyTyped = true;
            GameData.direction = LEFT;
            if (!speed.isRunning()) {
                speed.start();
                gameStarted = true;
            }
        } else if (key == KeyEvent.VK_RIGHT && GameData.direction != 3 && !keyTyped) {
            keyTyped = true;
            GameData.direction = RIGHT;
            if (!speed.isRunning()) {
                speed.start();
                gameStarted = true;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}