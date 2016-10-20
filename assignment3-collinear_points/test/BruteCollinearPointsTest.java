import org.junit.Test;

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

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_RepeatedPointInArray5_IllegalArgumentThrown() {
        Point[] points = {new Point(19743, 5156), new Point(10048,23908),
                new Point(28893,10331), new Point(27181,19616), new Point(27181,19616)};

        new BruteCollinearPoints(points);
    }

    @Test
    public void Constructor_Input8txt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("input8.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        for (LineSegment ls: bc.segments()) {
            System.out.println(ls);
        }
        assertEquals(2, bc.numberOfSegments());
     }

    @Test
    public void Constructor_Input40txt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("input40.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        assertEquals(4, bc.numberOfSegments());
        for (LineSegment ls: bc.segments()) {
            System.out.println(ls);
        }
    }

    @Test
    public void Constructor_Input48txt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("input48.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        assertEquals(6, bc.numberOfSegments());
        for (LineSegment ls: bc.segments()) {
            System.out.println(ls);
        }
    }

    @Test
    public void Constructor_Equidistanttxt_ReturnsTwoCollinearPointS() {
        Point[] points = PointTest.readPointsFromFile("equidistant.txt");

        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        assertEquals(4, bc.numberOfSegments());
        for (LineSegment ls: bc.segments()) {
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