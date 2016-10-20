import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        for (LineSegment ls: fc.segments()) {
            System.out.println(ls);
        }
        assertEquals(2, fc.numberOfSegments());
    }

    @Test
    public void Constructor_Input6txt_ReturnsOneCollinearPoint() {
        Point[] points = PointTest.readPointsFromFile("input6.txt");

        FastCollinearPoints fc = new FastCollinearPoints(points);
        for (LineSegment ls: fc.segments()) {
            System.out.println(ls);
        }
        assertEquals(1, fc.numberOfSegments());
    }

    @Test
    public void Constructor_Input40txt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("input40.txt");

        FastCollinearPoints fc = new FastCollinearPoints(points);
        assertEquals(4, fc.numberOfSegments());
        for (LineSegment ls: fc.segments()) {
            System.out.println(ls);
        }
    }

    @Test
    public void Constructor_Input48txt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("input48.txt");

        FastCollinearPoints fc = new FastCollinearPoints(points);
        assertEquals(6, fc.numberOfSegments());
        for (LineSegment ls: fc.segments()) {
            System.out.println(ls);
        }
    }

    @Test
    public void Constructor_Equidistanttxt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("equidistant.txt");

        FastCollinearPoints fc = new FastCollinearPoints(points);
        assertEquals(4, fc.numberOfSegments());
        for (LineSegment ls: fc.segments()) {
            System.out.println(ls);
        }
    }



}