import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Mike Milonakis on 25/10/2016.
 */
public class KdTree {


    private int size;
    private Node root;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        Objects.requireNonNull(point);
        root = put(root, point, Orientation.VERTICAL);
    }

    private Node put(Node node, Point2D point, Orientation orientation) {
        if (node == null)
            return new Node(point);
        Comparator<Point2D> orientationComparator =
                getCurrentLevelComparator(orientation);
        int cmp = orientationComparator.compare(node.p, point);
        if (cmp < 0)
            node.lb = put(node.lb, point, orientation.next());
        else if (cmp > 0)
            node.rt = put(node.rt, point, orientation.next());
        else
            node.p = point;
        return node;
    }

    public boolean contains(Point2D point2D) {
        Objects.requireNonNull(point2D);
        return get(root, point2D, Orientation.VERTICAL);
    }

    private boolean get(Node node, Point2D point2D, Orientation orientation) {
        if (node == null)
            return false;
        Comparator<Point2D> orientationComparator =
                getCurrentLevelComparator(orientation);
        int cmp = orientationComparator.compare(node.p, point2D);
        if (cmp < 0)
            return get(node.lb, point2D, orientation.next());
        else {
            return node.p.equals(point2D) || get(node.rt, point2D, orientation.next());
        }
    }

    private Comparator<Point2D> getCurrentLevelComparator(Orientation orientation) {
        return orientation == Orientation.VERTICAL ?
                Point2D.Y_ORDER : Point2D.X_ORDER;
    }

    public Iterable<Point2D> range(RectHV rectHV) {
        return range(root, rectHV, Orientation.VERTICAL, new ArrayList<Point2D>());
    }

    private Iterable<Point2D> range(Node node, RectHV rectHV, Orientation orientation, ArrayList<Point2D> rangeList) {
        if (node != null) {


        }
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        Node(Point2D point) {
            this.p = point;
        }
    }

    private enum Orientation {
        VERTICAL,
        HORIZONTAL {
            @Override
            public Orientation next() { return VERTICAL;
            }
        };
        public Orientation next() { return HORIZONTAL;}
    }
}
