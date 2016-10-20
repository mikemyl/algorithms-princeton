import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();

        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        Point prev = null;
        for (Point point : sortedPoints) {
            if ((prev != null) && (point.slopeTo(prev) == Double.NEGATIVE_INFINITY))
                throw new IllegalArgumentException();
            prev = point;
        }

        this.lineSegments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point[] sortedSlopes = new Point[points.length - (i + 1)];
            System.arraycopy(points, i + 1, sortedSlopes, 0, points.length - (i + 1));
            Arrays.sort(sortedSlopes, 0, sortedSlopes.length, points[i].slopeOrder());
            Point min = points[i];
            Point max = points[i];
            double slope = -1;
            int collinears = 1;
            boolean lineAlreadyAdded = false;
            for (int j = 0; j < sortedSlopes.length; j++) {
                if (slope == Double.NEGATIVE_INFINITY)
                    throw new IllegalArgumentException();
                if (points[i].slopeTo(sortedSlopes[j]) == slope) {
                    collinears++;
                    if (sortedSlopes[j].compareTo(max) >= 0)
                        max = sortedSlopes[j];
                    else if (sortedSlopes[j].compareTo(min) < 0)
                        min = sortedSlopes[j];
                    if (j != sortedSlopes.length - 1)
                        continue;
                }
                if (collinears >= 4) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (points[k].slopeTo(max) == slope) {
                            lineAlreadyAdded = true;
                        }
                    }
                    if (!lineAlreadyAdded)
                        lineSegments.add(new LineSegment(min, max));
                }
                lineAlreadyAdded = false;
                slope = points[i].slopeTo(sortedSlopes[j]);
                collinears = 2;
                if (sortedSlopes[j].compareTo(points[i]) >= 0) {
                    max = sortedSlopes[j];
                    min = points[i];
                } else if (sortedSlopes[j].compareTo(points[i]) < 0) {
                    min = sortedSlopes[j];
                    max = points[i];
                } else {
                    min = points[i];
                    max = points[i];
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[lineSegments.size()];
        ret = lineSegments.toArray(ret);
        return ret;
    }
}