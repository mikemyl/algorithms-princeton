import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.io.File;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by mike on 10/18/16.
 */
public class PointTest {

    @Test
    public void CompareTo_LessY_ReturnsNegative() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);

        assertEquals(a.compareTo(b), -1);
    }

    @Test
    public void CompareTo_LessX_ReturnsNegative() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);

        assertEquals(a.compareTo(b), -1);
    }

    @Test
    public void CompareTo_EqualPoints_ReturnsZero() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);

        assertEquals(a.compareTo(b), 0);
    }

    @Test
    public void SlopeTo_EqualPoints_ReturnsNegativeInf() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);

        assertEquals(a.slopeTo(b), Double.NEGATIVE_INFINITY, 1e-15);
    }

    @Test
    public void SlopeTo_VerticalPoints_ReturnsPositiveInf() {
        Point a = new Point(1, 0);
        Point b = new Point(1, 1);

        assertEquals(a.slopeTo(b), Double.POSITIVE_INFINITY, 1e-15);
    }

    @Test
    public void SlopeOrder_EqualPoints_ReturnsZero() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 1);
        Comparator<Point> slopeOrder = a.slopeOrder();

        assertEquals(slopeOrder.compare(a, b), 0);
    }

    @Test
    public void SlopeOrder_CollinearPoints_EqualSlope() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        Point c = new Point(2, 2);
        Comparator<Point> slopeOrder = a.slopeOrder();

        assertEquals(slopeOrder.compare(b, c), 0);
    }

    static Point[] readPointsFromFile(String testFile) {
            In in = new In(new File(PointTest.class.getResource(testFile).getFile()));
            int n = in.readInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                int x = in.readInt();
                int y = in.readInt();
                points[i] = new Point(x, y);
            }
            return points;
    }
}