import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final int n;
    private int[][] board;
    private int freeRow;
    private int freeColumn ;

    public Board(int[][] blocks) {
        n = blocks[0].length;
        copyBoard(blocks);
    }

    private void copyBoard(int[][] blocks) {
        board = new int[n][];
        for (int i = 0; i < blocks.length; i++) {
            board[i] = new int[blocks[i].length];
            for (int j = 0; j < blocks[i].length; j++) {
                board[i][j] = blocks[i][j];
                if (board[i][j] == 0) {
                    freeRow = i;
                    freeColumn = j;
                }
            }
        }
    }

    @Override
    public boolean equals(Object that) {
        if ((that == null) || (getClass() != that.getClass()) )
            return false;
        Board other = (Board) that;
        return Arrays.deepEquals(this.board, other.board);
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == n - 1) && (j == n - 1))
                    break;
                if (board[i][j] != findTargetValueOf(i, j))
                    hamming++;
            }
        }
        return hamming;
    }

    private int findTargetValueOf(int i, int j) {
        return i * n + j + 1;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                manhattan += manhattanDistanceOf(i, j);
            }
        }
        return manhattan;
    }

    private int manhattanDistanceOf(int i, int j) {
        int currentVal = board[i][j];
        if (currentVal == 0)
            return 0;

        int verticalMoves = Math.abs(findCorrectRowOf(currentVal) - i);
        int horizontalMoves = Math.abs(findCorrectColumnOf(currentVal) - j);

        return verticalMoves + horizontalMoves;
    }

    private int findCorrectRowOf(int currentVal) {
        int row = 0;
        if  (currentVal != 0)
            row = (currentVal - 1) / n;
        return row;
    }

    private int findCorrectColumnOf(int currentVal) {
        int column = 0;
        if  (currentVal != 0) {
            int row = findCorrectRowOf(currentVal);
            if (row != 0)
                column = currentVal % (row * n) - 1;
            else
                column = currentVal - 1;
        }
        if (column < 0)
            column = n - 1;
        return column;
    }

    public Board twin() {
        int[][] twinBoard = Arrays.copyOf(board, board.length);
        for (int i = 0; i < n; i++)
            twinBoard[i] = Arrays.copyOf(board[i], board[i].length);
        int toBeChanged = twinBoard[0][0];
        if (toBeChanged != 0) {
            if (twinBoard[0][1] != 0) {
                twinBoard[0][0] = twinBoard[0][1];
                twinBoard[0][1] = toBeChanged;
            }
            else {
                twinBoard[0][0] = twinBoard[1][0];
                twinBoard[1][0] = toBeChanged;
            }
        }
        else {
            toBeChanged = twinBoard[1][0];
            twinBoard[1][0] = twinBoard[1][1];
            twinBoard[1][1] = toBeChanged;
        }

        return new Board(twinBoard);
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>(4);
        if ((freeColumn - 1) >= 0) {
            exchangeWithBlank(freeRow, freeColumn - 1);
            neighbors.add(new Board(board));
            exchangeWithBlank(freeRow, freeColumn + 1);
        }
        if ((freeColumn + 1) < n) {
            exchangeWithBlank(freeRow, freeColumn + 1);
            neighbors.add(new Board(board));
            exchangeWithBlank(freeRow, freeColumn - 1);
        }
        if ((freeRow + 1) < n) {
            exchangeWithBlank(freeRow + 1, freeColumn);
            neighbors.add(new Board(board));
            exchangeWithBlank(freeRow - 1, freeColumn);
        }
        if ((freeRow - 1) >= 0) {
            exchangeWithBlank(freeRow - 1, freeColumn);
            neighbors.add(new Board(board));
            exchangeWithBlank(freeRow + 1, freeColumn);
        }
        return neighbors;
    }

    private void exchangeWithBlank(int i, int j) {
        board[freeRow][freeColumn] = board[i][j];
        board[i][j] = 0;
        freeColumn = j;
        freeRow = i;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) { }
}