import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by mike on 10/19/16.
 */
public class FastCollinearPointsTest {
    @Test(expected = NullPointerException.class)
    public void Constructor_NullPointsPassed_ExceptionThrown() {
        new FastCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void Constructor_NullPointInArray_ExceptionThrown() {
        Point[] points = {new Point(0, 0), new Point(1,0), null};

        new FastCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_RepeatedPointInArray_IllegalArgumentThrown() {
        Point[] points = {new Point(0, 0), new Point(0,0), new Point(1,0)};

        new FastCollinearPoints(points);
    }

    @Test
    public void Constructor_Input8txt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("input8.txt");

        FastCollinearPoints fc = new FastCollinearPoints(points);
        assertEquals(2, fc.numberOfSegments());
        for (LineSegment ls: fc.segments()) {
            System.out.println(ls);
        }
    }

    @Test
    public void Constructor_Input6txt_ReturnsOneCollinearPoint() {
        Point[] points = PointTest.readPointsFromFile("input6.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        for (LineSegment ls: bc.segments()) {
            System.out.println(ls);
        }
        assertEquals(1, bc.numberOfSegments());
    }




}