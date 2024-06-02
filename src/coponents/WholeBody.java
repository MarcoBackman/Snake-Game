package coponents;

import data.GameData;

import java.awt.Color;
import java.awt.Graphics;

public class WholeBody {

    public BodyCell head;
    public BodyCell tail;
    public int bodyLen;

    public WholeBody(int x, int y) {
        head = new BodyCell(x, y);
        tail = head;
        bodyLen++;
    }

    public void setSafeGrid(BodyCell oldTail) {
        GameData.grid[oldTail.y][oldTail.x] = false;
    }

    public void setDangerGrid(BodyCell newTail) {
        GameData.grid[newTail.y][newTail.x] = true;
    }

    public void addCell(BodyCell newHead) {
        if (bodyLen == 1) {
            newHead.next = head;
            tail = newHead.next;
            head = newHead;
            tail.previous = head;
        } else {
            newHead.next = head;
            head.previous = newHead;
            head = newHead;
        }
        ++bodyLen;
    }

    //remove tail and set new tail to previous one
    public void removeCell(BodyCell oldTail) {
        setSafeGrid(oldTail); //set that location as a free cell
        //bodyLen cannot be 1 at this point
        if (bodyLen == 2) {
            tail = head;
            head.next = null;
        } else {
            tail = oldTail.previous;
            tail.next = null;
        }
        --bodyLen;
    }

    public void shiftCell(int newPosX, int newPosY) {
        BodyCell newHead = new BodyCell(newPosX, newPosY);
        BodyCell oldTail = tail;
        setDangerGrid(head);
        addCell(newHead);
        removeCell(oldTail);
    }

    public void drawCells (Graphics g) {
        BodyCell link = GameData.snake.head;
        g.setColor(Color.GREEN);
        g.fillRect(link.x * GameData.squareSize, link.y * GameData.squareSize,
              GameData.squareSize, GameData.squareSize);
        link = link.next;
        while (link != null) {
            g.setColor(Color.RED);
            g.fillRect(link.x * GameData.squareSize, link.y * GameData.squareSize,
              GameData.squareSize, GameData.squareSize);
            if (link.next != null) {
                link = link.next;
                g.setColor(Color.GRAY);
                g.fillRect(link.x * GameData.squareSize, link.y * GameData.squareSize,
                  GameData.squareSize, GameData.squareSize);
            }
            link = link.next;
        }
    }
}