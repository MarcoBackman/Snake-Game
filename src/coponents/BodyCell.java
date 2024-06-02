package coponents;

public class BodyCell {
    int x;
    int y;
    BodyCell next, previous;
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

    public BodyCell getPreviois() {
        return previous;
    }

    public void setTail() {
        isTail = true;
    }
}

