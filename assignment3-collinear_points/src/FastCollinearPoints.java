import java.util.*;


/**
 * Created by Mike Milonakis on 19/10/2016.
 */
public class FastCollinearPoints {

    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();

        validateAndCopyArray(points);
        segments = new ArrayList<>();


        for (Point p : points) {
            for (Point q: points) {
                Point[] sortedByPoint = Arrays.copyOf(points, points.length);
                Arrays.sort(sortedByPoint, p.slopeOrder());
                int continuos = 0;
                Double prevSlope = p.slopeTo(q);
                Point lastPoint = null;
                for (Point aSortedByPoint : sortedByPoint) {
                    double slope = p.slopeTo(aSortedByPoint);
                    if (slope == prevSlope) {
                        continuos++;
                        lastPoint = aSortedByPoint;
                        continue;
                    }
                    if (continuos >= 3) {
                        segments.add(new LineSegment(p, lastPoint));
                    }
                    prevSlope = slope;
                    continuos = 1;
                    lastPoint = aSortedByPoint;
                }
            }

        }
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
        return segments.size();
    }

    private void validateAndCopyArray(Point[] points) {
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        Point prev = null;
        for (Point point: points) {
            if (prev != null && point.compareTo(prev) == 0)
                throw new IllegalArgumentException("Duplicate Point detected");
            prev = point;
        }
    }
}
