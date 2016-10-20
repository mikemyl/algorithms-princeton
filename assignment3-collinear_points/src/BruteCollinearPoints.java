import java.util.*;

/**
 * Created by Mike Milonakis on 19/10/2016.
 */
public class BruteCollinearPoints {

    private final List<LineSegment> segments;
    private Point[] sortedPoints;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();
        sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        Point prev = null;
        for (Point point : sortedPoints) {
            if ((prev != null) && (point.slopeTo(prev) == Double.NEGATIVE_INFINITY))
                throw new IllegalArgumentException();
            prev = point;
        }
        segments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double slope = points[i].slopeTo(points[j]);
                int collinears = 2;
                Point point3 = null;
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeTo(points[k]) == slope) {
                        collinears++;
                        if (collinears == 4) {
                            Point min = findMinPoint(points[i], points[j], point3, points[k]);
                            Point max = findMaxPoint(points[i], points[j], point3, points[k]);
                            segments.add(new LineSegment(min, max));
                            break;
                        }
                        point3 = points[k];
                    }
                }
            }
        }
    }

    private Point findMinPoint(Point... points) {
        Point min = points[0];
        for (Point p : points) {
            if (min.compareTo(p) > 0)
                min = p;
        }
        return min;
    }

    private Point findMaxPoint(Point... points) {
        Point max = points[0];
        for (Point p : points) {
            if (max.compareTo(p) < 0)
                max = p;
        }
        return max;
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
        return segments.size();
    }
}
