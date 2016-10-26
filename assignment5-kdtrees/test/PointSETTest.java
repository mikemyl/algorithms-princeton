import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Mike Milonakis on 25/10/2016.
 */
public class PointSETTest {

    private PointSET pointSET;

    @Before
    public void setUp() {
        pointSET = new PointSET();
    }

    @Test
    public void IsEmpty_OnNewPointSET_ReturnsTrue() {
        assertTrue(pointSET.isEmpty());
    }

    @Test
    public void Size_OnNewPointSET_ReturnsZero() {
        assertEquals(0, pointSET.size());
    }

    @Test(expected = NullPointerException.class)
    public void Insert_NullArgumentPassed_ThrowsException() {
        pointSET.insert(null);
    }

    @Test(expected = NullPointerException.class)
    public void Contains_NullArgumentPassed_ThrowsException() {
        pointSET.contains(null);
    }

    @Test
    public void Contains_AfterInsert_ReturnsTrue() {
        pointSET.insert(new Point2D(0, 0));

        assertTrue(pointSET.contains(new Point2D(0, 0)));
    }

    @Test
    public void Range_WithPointsBetweenTheRange_ReturnsContainedPoints() {
        pointSET.insert(new Point2D(0.5, 0.5));
        pointSET.insert(new Point2D(1.5, 0.5));
        pointSET.insert(new Point2D(0.7, 0.8));

        int size = 0;
        for (Point2D ignored : pointSET.range(new RectHV(0, 0, 1, 1)))
            size++;

        assertEquals(2, size);
    }

    @Test(expected = NullPointerException.class)
    public void Range_NullArgumentPassed_ExceptionThrown() {
        pointSET.range(null);
    }

    @Test
    public void Nearest_WithPointsBetweenTheRange_ReturnsContainedPoints() {
        pointSET.insert(new Point2D(0.5, 0.5));
        pointSET.insert(new Point2D(0.51, 0.51));
        pointSET.insert(new Point2D(1.5, 0.5));
        pointSET.insert(new Point2D(0.7, 0.8));

        assertEquals(new Point2D(0.51, 0.51), pointSET.nearest(new Point2D(0.52, 0.52)));
    }
}