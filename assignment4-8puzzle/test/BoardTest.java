import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mike on 10/22/16.
 */
public class BoardTest {

    @Test
    public void Dimension_3X3Board_ReturnsThree() {
        Board board = new Board(new int[3][3]);

        assertEquals(3, board.dimension());
    }

    @Test
    public void Equals_EmptyBoards_ReturnsTrue() {
        Board board1 = new Board(new int[3][3]);
        Board board2 = new Board(new int[3][3]);

        assertTrue(board1.equals(board2));
    }

    @Test
    public void Hamming_OnGoalBoard_ReturnsZero() {
        Board board = new Board(new int[][]{{1,2},{3,0}});

        assertEquals(0,board.hamming());
    }

    @Test
    public void Hamming_OnCustomBoard_ReturnsCorrectDistance() {
        Board board = new Board(new int[][]{{8,1,3},{4,0,2},{7,6,5}});

        assertEquals(5, board.hamming());
    }

    @Test
    public void Manhattan_OnCustomBoard_ReturnsCorrectDistance() {
        Board board = new Board(new int[][]{{8,1,3},{4,0,2},{7,6,5}});

        assertEquals(10, board.manhattan());
    }

    @Test
    public void IsGoal_OnGoalBoard_ReturnsTrue() {
        Board board = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});

        assertTrue(board.isGoal());
    }

    @Test
    public void IsGoal_OnCustomlBoard_ReturnsCorrectResult() {
        Board board = new Board(new int[][]{{1,3,2},{4,5,6},{7,8,0}});

        assertFalse(board.isGoal());
    }

    @Test
    public void Neighbors_OnCustomBoard_ReturnsCoorectNeighbors() {
        Board board = new Board(new int[][]{{2,1},{0,3}});

        Board n1 = new Board(new int[][]{{2,1},{3,0}});
        Board n2 = new Board(new int[][]{{0,1},{2,3}});

        List<Board> neighbors = new ArrayList<>();
        neighbors.add(n1);
        neighbors.add(n2);

        assertEquals(neighbors, board.neighbors());
    }
}