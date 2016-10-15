import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*----------------------------------------------------------------
        *  Author:        Mike Milonakis
        *  Written:       15/10/2016
        *  Last updated:  15/10/2016
        *
        *
        *
        *  This is the second programming assignment of
        *  princeton - algorithms course in coursera
        *
        *----------------------------------------------------------------*/

/**
 * The RandomizedQueue is a collection of items that get added as usual, but
 * get removed uniformly at random
 *
 * @author Mike Milonakis
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int current;

    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    /**
     * Checks if the Queue is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return current == 0;
    }

    /**
     * Returns the size of the Items that the queue holds
     * @return
     */
    public int size() {
        return current;
    }

    /**
     * Adds an item on the queue. Runs in constant time in most cases, but
     * may introduce a linear cost if an array-resizing is needed.
     * @param item the item to be added
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        array[current++] = item;
        if (current == array.length)
            resizeArray(array.length * 2);
    }

    /**
     * Dequeues a random item from the queue
     * @return the random item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randIndex = StdRandom.uniform(current);
        Item ret = array[randIndex];
        array[randIndex] = array[--current];
        array[current] = null;
        if (current == array.length / 4)
            resizeArray(array.length / 2);
        return ret;
    }

    private void resizeArray(int newLength) {
        Item[] newArr = (Item[]) new Object[newLength];
        System.arraycopy(array, 0, newArr, 0, current);
        array = newArr;
    }

    /**
     * "Peeks" a random item from the queue, without removing it
     * @return the random item
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return array[StdRandom.uniform(current)];
    }

    /**
     * Main method - currently empty, cause unittests were written on seperate
     * classes. This method stub was introduced just to conform to the API.
     * @param args
     */
    public static void main(String[] args) {

    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Item[] arr = array.clone();
            private int curr = current;

            @Override
            public boolean hasNext() {
                return curr != 0;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                int randIndex = StdRandom.uniform(curr);
                Item ret = arr[randIndex];
                arr[randIndex] = arr[--curr];
                arr[curr] = null;
                return ret;
            }
        };
    }
}
