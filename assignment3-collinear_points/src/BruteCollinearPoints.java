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

        validateAndCopyArray(points);
        segments = new ArrayList<>();
        Set<Point> pointsInSegments = new HashSet<>();
        boolean found = false;
        for (Point point1: sortedPoints) {
            if (pointsInSegments.contains(point1))
                continue;
            for (Point point2: sortedPoints) {
                if (point1.compareTo(point2) == 0 || pointsInSegments.contains(point2))
                    continue;
                for (Point point3: sortedPoints) {
                    if (point1.compareTo(point3) == 0 ||
                            point2.compareTo(point3) == 0 ||
                            pointsInSegments.contains(point3))
                        continue;
                    for (Point point4: sortedPoints) {
                        if (point1.compareTo(point4) == 0 ||
                                point2.compareTo(point4) == 0 ||
                                point3.compareTo(point4) == 0 ||
                                pointsInSegments.contains(point4))
                            continue;
                        double slope = point1.slopeTo(point2);
                        if ((point1.slopeTo(point3) == slope) &&
                                (point1.slopeTo(point4) == slope)) {
                            segments.add(new LineSegment(point1, point4));
                            pointsInSegments.add(point1);
                            pointsInSegments.add(point2);
                            pointsInSegments.add(point3);
                            pointsInSegments.add(point4);
                            found = true;
                            break;
                        }
                    }
                    if (found)
                        break;
                }
                if (found)
                    break;
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
        sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        Point prev = null;
        for (Point point: points) {
            if (prev != null && point.compareTo(prev) == 0)
                throw new IllegalArgumentException("Duplicate Point detected");
            prev = point;
        }
    }

}
