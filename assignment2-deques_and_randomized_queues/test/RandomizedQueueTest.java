import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by Mike Milonakis on 14/10/2016.
 */
public class RandomizedQueueTest {

    RandomizedQueue<Integer> rq;

    @Before
    public void setUp() {
        rq = new RandomizedQueue();
    }

    @Test
    public void IsEmpty_OnNewRandomizedQueue_ReturnsTrue() {
        assertTrue(rq.isEmpty());
    }

    @Test
    public void Size_OnNewRQueue_ReturnsZero() {
        assertEquals(rq.size(), 0);
    }

    @Test
    public void Enqueue_OnNewRQueue_AddsItem() {
        rq.enqueue(42);

        assertEquals(rq.size(), 1);
        assertFalse(rq.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void Enqueue_NullItem_ExceptionThrown() {
        rq.enqueue(null);
    }

    @Test
    public void Sample_OnNonEmptyQueue_ReturnsRandomItem() {
        rq.enqueue(42);
        rq.enqueue(52);
        rq.enqueue(62);
        rq.enqueue(72);
        rq.enqueue(82);
        rq.enqueue(92);
        rq.enqueue(102);

        assertNotEquals(rq.sample(), rq.sample());
    }

    @Test(expected = NoSuchElementException.class)
    public void Sample_OnEmptyQueue_ExceptionThrown() {
        rq.sample();
    }

    @Test
    public void Dequeue_OnNonEmptyQueue_RemovesItems() {
        rq.enqueue(42);
        rq.enqueue(52);
        rq.enqueue(62);

        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        assertTrue(rq.isEmpty());
    }

    @Test
    public void Iterator_OnEmptyQueue_DoesNotHaveNext() {
        Iterator<Integer> it = rq.iterator();

        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void IteratorNext_OnEmptyQueue_ExceptionThrown() {
        Iterator<Integer> it = rq.iterator();

        it.next();
    }

    @Test
    public void Iterator_OnNonEmptyQueue_ReturnsItemsInRandomOrder() {
        rq.enqueue(32);
        rq.enqueue(22);
        rq.enqueue(12);

        Iterator<Integer> it = rq.iterator();

        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

}