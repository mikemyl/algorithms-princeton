import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mike Milonakis on 26/10/2016.
 */
public class KdTreeTest {

    private KdTree kdTree;

    @Before
    public void setUp() {
        this.kdTree = new KdTree();
    }

    @Test
    public void IsEmpty_OnNewKdTree_ReturnsTrue() {
        assertTrue(kdTree.isEmpty());
    }

    @Test
    public void Size_OnNewKdTree_IsZero() {
        assertEquals(0, kdTree.size());
    }

    @Test(expected = NullPointerException.class)
    public void Insert_WithNullArgumentPassed_ExceptionThrown() {
        kdTree.insert(null);
    }

    @Test
    public void Contains_PreviouslyInsertedPoint_ReturnsTrue() {
        kdTree.insert(new Point2D(0.0 ,0.1));

        assertFalse(kdTree.contains(new Point2D(0.2, 0.1)));
        assertTrue(kdTree.contains(new Point2D(0.0, 0.1)));
    }

    @Test
    public void Contains_ManyPointsInserted_ReturnsCorrectResults() {
        kdTree.insert(new Point2D(0.0 ,0.1));
        kdTree.insert(new Point2D(0.1 ,0.2));
        kdTree.insert(new Point2D(0.3 ,0.3));
        kdTree.insert(new Point2D(0.2 ,0.4));
        kdTree.insert(new Point2D(0.0 ,0.5));
        kdTree.insert(new Point2D(0.1 ,0.1));

        assertTrue(kdTree.contains(new Point2D(0.1, 0.2)));
    }

    @Test
    public void Range_RandomPointsInserted_ReturnsCorrectResults() {
        kdTree.insert(new Point2D(0.0 ,0.1));
        kdTree.insert(new Point2D(0.1 ,0.2));
        kdTree.insert(new Point2D(0.3 ,0.3));
        kdTree.insert(new Point2D(0.2 ,0.4));
        kdTree.insert(new Point2D(0.0 ,0.5));
        kdTree.insert(new Point2D(0.1 ,0.1));

        Iterable<Point2D> ret = kdTree.range(new RectHV(0.0, 0.0, 0.2, 0.2));
        int size = 0;
        for (Point2D ignored : ret)
            size++;
        assertEquals(3, size);
    }

    @Test
    public void Nearest_RandomPointsInserted_ReturnsCorrectResults() {
        kdTree.insert(new Point2D(0.1 ,0.2));
        kdTree.insert(new Point2D(0.0 ,0.1));
        kdTree.insert(new Point2D(0.3 ,0.3));
        kdTree.insert(new Point2D(0.2 ,0.4));
        kdTree.insert(new Point2D(0.0 ,0.5));
        kdTree.insert(new Point2D(0.1 ,0.1));

        assertEquals(new Point2D(0.0, 0.1), kdTree.nearest(new Point2D(0.01, 0.12)));
    }


}