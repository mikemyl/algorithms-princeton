import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike Milonakis on 19/10/2016.
 */
public class BruteCollinearPointsTest {

    @Test(expected = NullPointerException.class)
    public void Constructor_NullPointsPassed_ExceptionThrown() {
        new BruteCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void Constructor_NullPointInArray_ExceptionThrown() {
        Point[] points = {new Point(0, 0), new Point(1,0), null};

        new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_RepeatedPointInArray_IllegalArgumentThrown() {
        Point[] points = {new Point(0, 0), new Point(0,0), new Point(1,0)};

        new BruteCollinearPoints(points);
    }

    @Test
    public void Constructor_Input8txt_ReturnsTwoCollinearPointS() {
        Point[] points = readPointsFromFile("input8.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        assertEquals(2, bc.numberOfSegments());
        for (LineSegment ls: bc.segments()) {
            System.out.println(ls);
        }
     }

    @Test
    public void Constructor_Input6txt_ReturnsOneCollinearPoint() {
        Point[] points = readPointsFromFile("input6.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        for (LineSegment ls: bc.segments()) {
            System.out.println(ls);
        }
        assertEquals(1, bc.numberOfSegments());
    }


    private Point[] readPointsFromFile(String testFile) {
        In in = new In(new File(getClass().getResource(testFile).getFile()));
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