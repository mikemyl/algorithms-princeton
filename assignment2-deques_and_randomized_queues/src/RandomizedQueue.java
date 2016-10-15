import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mike on 10/13/16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int current;

    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }


    public boolean isEmpty() {
        return current == 0;
    }

    public int size() {
        return current;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        array[current++] = item;
        if (current == array.length)
            resizeArray(array.length * 2);
    }

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

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return array[StdRandom.uniform(current)];
    }

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
