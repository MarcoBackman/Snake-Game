
public class BodyCell {
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

