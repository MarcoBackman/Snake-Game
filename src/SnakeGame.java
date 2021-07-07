import java.awt.*;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeGame extends JPanel implements KeyListener, ActionListener {
    final int SQUARE_SIZE = 10;
    final int TOTAL_GRID_SIZE = 40;
    final int BLACK_GRID_SIZE = 38;
    final int REAL_TIME_TICK = 1000; //1sec
    final int SNAKE_SPEED = 100;
    final int SNAKE_SPEED_FASTER = 90;
    final int SNAKE_SPEED_FASTEST = 80;
    final int SNAKE_SPEED_WEE = 50;
    final int SNAKE_SPEED_REEE = 30;
    final int INITIAL_TIME = 60;
    final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;

    int adjustableSpeed = 100;
    
    Timer timer = new Timer(REAL_TIME_TICK, this);
    Timer speed = new Timer(SNAKE_SPEED, this);

    int score, timeRemaining, direction;
    int applePosX, applePosY,
        applePosX2, applePosY2,
        applePosX3, applePosY3;
    int snakeHeadX = (BLACK_GRID_SIZE) / 2,
        snakeHeadY = (BLACK_GRID_SIZE) / 2;
    int previousHeadX, previousHeadY;

    GameScreen parentPanel;

    WholeBody snake = new WholeBody(snakeHeadX, snakeHeadY);
    boolean[][] grid = new boolean[TOTAL_GRID_SIZE][TOTAL_GRID_SIZE];
    boolean gameOver, gameStarted, keyTyped;

    class BodyCell {
        int x;
        int y;
        BodyCell next;
        boolean isTail;
        public BodyCell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setNext(BodyCell cell) {
            next = cell;
        }

        public BodyCell getNext() {
             return next;
        }

        public void updatePos(BodyCell previous) {
            this.x = previous.x;
            this.y = previous.y;
        }
    
        public void setTail() {
            isTail = true;
        }
    }

    class WholeBody {
        BodyCell head;
        BodyCell tail;
        int bodyLen;
        WholeBody (int x, int y) {
            head = new BodyCell(x, y);
            tail = head;
            bodyLen++;
        }
        
        public void addCell(int x, int y) {
            BodyCell newCell = new BodyCell(x, y);
            if (bodyLen == 1) {
                newCell.next = head;
                tail = head;
                head = newCell;
                bodyLen++;
            } else {
                newCell.next = head;
                head = newCell;
                bodyLen++;
            }
        }

        public void shiftCell(int newPosX, int newPosY) {
            BodyCell newPos = new BodyCell(newPosX, newPosY);
            if (bodyLen == 1) {
                falseGrid(tail);
                tail = head = newPos;
                trueGrid(tail);
            } else if (bodyLen == 2) {
                falseGrid(tail);
                newPos.next = head;
                tail = head;
                tail.next = null;
                head = newPos;
                trueGrid(head);
                trueGrid(tail);
            } else {
                falseGrid(tail);
                newPos.next = head;
                BodyCell walk = head;
                while (walk.next != null) {
                    trueGrid(walk);
                    if (walk.next == tail) {
                        tail = walk;
                        tail.next = null;
                        break;
                    }
                    walk = walk.next;
                }
                head = newPos;
            }
        }

        public void falseGrid(BodyCell oldTail) {
            grid[oldTail.y][oldTail.x] = false;
        }

        public void trueGrid(BodyCell newTail) {
            grid[newTail.y][newTail.x] = true;
        }

        public void drawCells (Graphics g, int newX, int newY) {
            shiftCell (newX, newY);
            BodyCell link = head;
            while (link != null) {
                g.setColor(Color.RED);
                g.fillRect(link.x * SQUARE_SIZE, link.y * SQUARE_SIZE,
                   SQUARE_SIZE, SQUARE_SIZE);
                link = link.next;
            }
        }
    }

    protected SnakeGame(GameScreen parentPanel) {
        setDoubleBuffered(true);
        this.parentPanel = parentPanel;
        timeRemaining = INITIAL_TIME;

        addKeyListener(this);

        grid[snakeHeadY][snakeHeadX] = true;
        genAppleLoc();
        validate();
    }

    private void gameOver() {
        System.out.println("Game Over");
        speed.stop();
        timer.stop();
        gameOver = true;
        this.removeKeyListener(this);
    }

    private void genAppleLoc() {
        applePosX = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        applePosY = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        applePosX2 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        applePosY2 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        applePosX3 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        applePosY3 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);

        while (grid[applePosY][applePosX]) {
            applePosX = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
            applePosY = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        }

        while (grid[applePosY2][applePosX2] && (applePosX == applePosX2 && applePosY == applePosY2)) {
            applePosX2 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
            applePosY2 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        }

        while (grid[applePosY3][applePosX3] && (applePosX == applePosX3 && applePosY == applePosY3)) {
            applePosX3 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
            applePosY3 = (int) ((Math.random() * (BLACK_GRID_SIZE - 2)) + 1);
        }
    }

    private boolean snakeIsDead () {
        if (snakeHeadX == 0 || snakeHeadX == TOTAL_GRID_SIZE - 2 || snakeHeadY == 0 || snakeHeadY == TOTAL_GRID_SIZE - 2)
            return true;
        if (grid[snakeHeadY][snakeHeadX])
            return true;
        return false;
    }

    private boolean eaten() {
        if (snakeHeadX == applePosX && snakeHeadY == applePosY) {
            ++score;
            return true;
        } else if (snakeHeadX == applePosX2 && snakeHeadY == applePosY2) {
            ++score;
            return true;
        } else if (snakeHeadX == applePosX3 && snakeHeadY == applePosY3) {
            ++score;
            return true;
        }
        return false;
    }

    private void drawInitialBoard(Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < TOTAL_GRID_SIZE - 1; i++) {
            for (int j = 0; j < TOTAL_GRID_SIZE - 1; j++) {
                if (i == 0)
                    g.fillRect(j * SQUARE_SIZE, 0, SQUARE_SIZE, SQUARE_SIZE);
                else if (j == 0)
                    g.fillRect(0, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                else if (i == TOTAL_GRID_SIZE - 2)
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                else if (j == TOTAL_GRID_SIZE - 2)
                    g.fillRect((TOTAL_GRID_SIZE - 2) * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        g.setColor(Color.YELLOW);
        g.fillRect(applePosX * SQUARE_SIZE, applePosY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        g.fillRect(applePosX2 * SQUARE_SIZE, applePosY2 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        g.fillRect(applePosX3 * SQUARE_SIZE, applePosY3 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        drawInitialBoard(g);
        snake.drawCells(g, snakeHeadX, snakeHeadY);
        validate();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP && direction != 2 && !keyTyped) {
            keyTyped = true;
            direction = UP;
            if (!speed.isRunning()) {
                speed.start();
                timer.start();
                gameStarted = true;
            }
        } else if (key == KeyEvent.VK_DOWN && direction != 1 && !keyTyped) {
            keyTyped = true;
            direction = DOWN;
            if (!speed.isRunning()) {
                speed.start();
                timer.start();
                gameStarted = true;
            }
        } else if (key == KeyEvent.VK_LEFT && direction != 4 && !keyTyped) {
            keyTyped = true;
            direction = LEFT;
            if (!speed.isRunning()) {
                speed.start();
                timer.start();
                gameStarted = true;
            }
        } else if (key == KeyEvent.VK_RIGHT && direction != 3 && !keyTyped) {
            keyTyped = true;
            direction = RIGHT;
            if (!speed.isRunning()) {
                speed.start();
                timer.start();
                gameStarted = true;
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed (ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(timer) && !gameOver) {
          //--timeRemaining;
        }

        if (obj.equals(speed) && !gameOver) {
            keyTyped = false;
            previousHeadX = snakeHeadX;
            previousHeadY = snakeHeadY;
            if (direction == UP) {
                --snakeHeadY;
            } else if (direction == DOWN) {
                ++snakeHeadY;
            } else if (direction == LEFT) {
                --snakeHeadX;
            } else if (direction == RIGHT) {
                ++snakeHeadX;
            }

            if (eaten()) {
                if (score % 10 == 0) {
                    if (adjustableSpeed > 10) {
                        adjustableSpeed -= 10;
                        System.out.println("speed decreased " + adjustableSpeed);
                    }
                    speed.setDelay(adjustableSpeed);
                }
                parentPanel.refreshScore();
                snake.addCell(previousHeadX, previousHeadY);
                genAppleLoc();
            }

            if (snakeIsDead())
                gameOver();
            if (!gameOver)
                repaint();
        }
    }
}