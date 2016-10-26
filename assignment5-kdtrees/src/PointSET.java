import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by Mike Milonakis on 25/10/2016.
 */
public class PointSET {

    private TreeSet<Point2D> pointSet = new TreeSet<>();

    public boolean isEmpty() {
        return pointSet.size() == 0;
    }

    public int size() {
        return pointSet.size();
    }

    public void insert(Point2D point) {
        validateArg(point);
        pointSet.add(point);
    }

    public boolean contains(Point2D point) {
        validateArg(point);
        return pointSet.contains(point);
    }

    public Iterable<Point2D> range(RectHV rectHV) {
        validateArg(rectHV);
        return pointSet.stream().filter(rectHV::contains).collect(Collectors.toList());
    }

    private void validateArg(Object arg) {
        if (arg == null)
            throw new NullPointerException();
    }

    public Point2D nearest(Point2D point2D) {
        Point2D ret = pointSet.first();
        double min = point2D.distanceSquaredTo(ret);
        for (Point2D point: pointSet)
            if (point2D.distanceTo(point) < min) {
                min = point2D.distanceTo(point);
                ret = point;
            }
        return ret;
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        pointSet.forEach(Point2D::draw);
    }
}
