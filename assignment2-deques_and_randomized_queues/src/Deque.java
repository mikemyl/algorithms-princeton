import java.util.Iterator;
import java.util.NoSuchElementException;

/*----------------------------------------------------------------
        *  Author:        Mike Milonakis
        *  Written:       14/10/2016
        *  Last updated:  14/10/2016
        *
        *
        *
        *  This is the second programming assignment of
        *  princeton - algorithms course in coursera
        *
        *----------------------------------------------------------------*/

/**
 * The Deque class implements a combination of the Stack, Queue data structure,
 * e.g one can either add - remove items from the front (or the end), or
 * add items at the front(end) and remove from the end(front)
 *
 * @author Mike Milonakis
 */
public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    /**
     * Adds an @item to the front of the Deque
     * @param item the item to be added as first
     * @throws NullPointerException if the item to be added is null
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Cannot add null item to deque");
        Node oldFirst = first;
        first = new Node(item);
        if (isEmpty())
            last = first;
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    /**
     * Adds @item to the end of the Deque
     * @param item the item to be added as last
     * @throws NullPointerException if the item to be added is null
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Cannot add null item to deque");
        Node oldLast = last;
        last = new Node(item);
        if (size == 0)
            first = last;
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    /**
     * Removes the first element of a Deque
     * @return the first item of the Deque
     * @throws NoSuchElementException if the Deque is empty
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from empty deque");
        Item ret = first.val;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return ret;
    }

    /**
     * Removes the last element of a Deque
     * @return the last item of the Deque
     * @throws NoSuchElementException if the Deque is empty
     */
    public Item removeLast() {
        if (size == 0)
            throw new NoSuchElementException("Cannot remove from empty deque");
        Item ret = last.val;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        }
        return ret;
    }

    private class Node {
        private Node next;
        private Node prev;
        private Item val;

        private Node(Item i) {
            val = i;
        }
    }

    /**
     * Checks whether the Deque is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the Deque
     * @return
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Item ret = current.val;
                current = current.next;
                return ret;
            }
        };
    }
}
