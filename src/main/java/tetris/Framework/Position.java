package tetris.Framework;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != Position.class) { return false; }
        Position other = (Position) o;
        return row == other.getRow() && col == other.getCol();
    }


    public int hashCode() {
        return 4000 * row + col;
    }
}
