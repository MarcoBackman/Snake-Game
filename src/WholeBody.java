import java.awt.Color;
import java.awt.Graphics;

public class WholeBody {

    public BodyCell head;
    public BodyCell tail;
    public int bodyLen;

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
        GameData.grid[oldTail.y][oldTail.x] = false;
    }

    public void trueGrid(BodyCell newTail) {
        GameData.grid[newTail.y][newTail.x] = true;
    }

    public void drawCells (Graphics g, int newX, int newY) {
        System.out.println("Drawing cells");
        GameData.snake.shiftCell (newX, newY);
        BodyCell link = GameData.snake.head;
        while (link != null) {
            g.setColor(Color.RED);
            g.fillRect(link.x * GameData.squareSize, link.y * GameData.squareSize,
              GameData.squareSize, GameData.squareSize);
            link = link.next;
        }
    }
}