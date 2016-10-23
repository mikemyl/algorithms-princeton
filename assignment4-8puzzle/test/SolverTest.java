import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by mike on 10/22/16.
 */
public class SolverTest {

    @Test(expected = NullPointerException.class)
    public void Constructor_NullArgumentPassed_ThrowsException() {
        Solver solver = new Solver(null);
    }

    @Test
    public void Constructor_SolvableInput_SolvesWithMinimumMoves() {
        Solver solver = new Solver(readPointsFromFile("puzzle04.txt"));

        assertTrue(solver.isSolvable());
        assertEquals(4, solver.moves());
    }

    @Test
    public void IsSolvable_UnSolvableInput_ReturnsFalse() {
        Solver solver = new Solver(readPointsFromFile("puzzle3x3-unsolvable.txt"));

        assertFalse(solver.isSolvable());
    }

    @Test
    public void IsSolvable_UnSolvableInput4x4_ReturnsFalse() {
        Solver solver = new Solver(readPointsFromFile("puzzle4x4-unsolvable.txt"));

        assertFalse(solver.isSolvable());
    }

    static Board readPointsFromFile(String testFile) {
        In in = new In(new File(SolverTest.class.getResource(testFile).getFile()));
        int n = in.readInt();
        int[][] boardArray = new int[n][];
        for (int i = 0; i < n; i++) {
            boardArray[i] = new int[n];
            for (int j = 0; j < n; j++)
                boardArray[i][j] = in.readInt();
        }
        return new Board(boardArray);
    }
}