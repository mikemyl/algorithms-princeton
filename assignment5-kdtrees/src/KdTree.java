import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        size++;
        put(root, point, Orientation.HORIZONTAL);
    }

    private void put(Node node, Point2D point, Orientation orientation) {
        if (root == null) {
            root = new Node(point, new RectHV(0.0, 0.0, 1.0, 1.0));
            return;
        }
        Comparator<Point2D> orientationComparator =
                getCurrentLevelComparator(orientation);
        int cmp = orientationComparator.compare(point, node.p);
        if (cmp < 0) {
            if (node.lb == null)
                node.lb = new Node(point, createLbRectangle(node, orientation));
            else
                put(node.lb, point, orientation.next());
        }
        else {
            if (node.p.compareTo(point) == 0) {
                size--;
                return;
            }
            if (node.rt == null)
                node.rt = new Node(point, createRtRectangle(node, orientation));
            else
                put(node.rt, point, orientation.next());
        }
    }

    private RectHV createLbRectangle(Node node, Orientation orientation) {
        if (orientation == Orientation.VERTICAL)
            return new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
        else
            return new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
    }

    private RectHV createRtRectangle(Node node, Orientation orientation) {
        if (orientation == Orientation.VERTICAL)
            return new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
        else
            return new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
    }

    public boolean contains(Point2D point2D) {
        Objects.requireNonNull(point2D);
        return get(root, point2D, Orientation.HORIZONTAL);
    }

    private boolean get(Node node, Point2D point2D, Orientation orientation) {
        if (node == null)
            return false;
        Comparator<Point2D> orientationComparator =
                getCurrentLevelComparator(orientation);
        int cmp = orientationComparator.compare(point2D, node.p);
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


    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> pointList = new ArrayList<>();
        Queue<Node> nodeQueue = new Queue<>();
        nodeQueue.enqueue(root);
        int nodesInCurrentLevel = 1;
        int nodesInNextLevel = 0;
        Orientation orientation = Orientation.VERTICAL;
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.dequeue();
            nodesInCurrentLevel--;
            if (rect.contains(node.p))
                pointList.add(node.p);
            if ((node.lb != null) && (node.lb.rect.intersects(rect))) {
                nodeQueue.enqueue(node.lb);
                nodesInNextLevel++;
            }
            if ((node.rt != null) && (node.rt.rect.intersects(rect))) {
                nodeQueue.enqueue(node.rt);
                nodesInNextLevel++;
            }
            if (nodesInCurrentLevel == 0) {
                nodesInCurrentLevel = nodesInNextLevel;
                nodesInNextLevel = 0;
                orientation = orientation.next();
            }
        }
        return pointList;
    }

    public Point2D nearest(Point2D point) {
        Objects.requireNonNull(point);
        return nearest(point, root);
    }

    private Point2D nearest(Point2D point, Node current) {
        if (current == null)
            return null;
        Point2D nearest = current.p;
        if (point.compareTo(current.p) > 0) {
            if (current.rt != null) {
                Point2D rtNearest = nearest(point, current.rt);
                nearest = rtNearest.distanceTo(point) > nearest.distanceTo(point) ?
                        nearest : rtNearest;
            }
            if ((current.lb != null) && (point.distanceTo(nearest) > current.lb.rect.distanceTo(point))) {
                Point2D lbNearest = nearest(point, current.lb);
                nearest = lbNearest.distanceTo(point) > nearest.distanceTo(point) ?
                        nearest : lbNearest;
            }
        }
        else {
            if (current.lb != null) {
                Point2D lbNearest = nearest(point, current.lb);
                nearest = lbNearest.distanceTo(point) > nearest.distanceTo(point) ?
                        nearest : lbNearest;
            }
            if ((current.rt != null) && (point.distanceTo(nearest) > current.rt.rect.distanceTo(point))) {
                Point2D rtNearest = nearest(point, current.rt);
                nearest = rtNearest.distanceTo(point) > nearest.distanceTo(point) ?
                        nearest : rtNearest;
            }
        }
        return nearest;
    }

    public void draw() {}

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        Node(Point2D point, RectHV rect) {
            this.p = point;
            this.rect = rect;
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
