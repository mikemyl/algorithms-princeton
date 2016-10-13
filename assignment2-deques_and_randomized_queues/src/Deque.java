import java.util.Iterator;

/**
 * Created by mike on 10/13/16.
 */
public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    public void addFirst(Item i) {
        Node oldfirst = first;
        first = new Node(i);
        first.next = oldfirst;
    }

    private class Node {
        private Node next;
        private Item val;

        private Node(Item i) {
            val = i;
        }
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

}
