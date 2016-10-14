import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mike on 10/13/16.
 */
public class DequeTest {

    private Deque<Integer> deq;

    @Before
    public void setUp() {
        deq = new Deque();
    }

    @Test
    public void IsEmpty_EmptyDeque_ReturnsTrue() {
        assertTrue(deq.isEmpty());
    }

    @Test
    public void Size_EmptyDeque_ReturnsZero() {
        assertEquals(deq.size(), 0);
    }

    @Test
    public void AddFirst_OnEmptyDeque_SizeIncreased() {
        deq.addFirst(2);

        assertEquals(deq.size(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void AddFirst_NullItem_ExceptionThrown() {
        deq.addFirst(null);
    }

    @Test
    public void AddFirst_DequeNonEmptyAfterEmpty_ItemAdded() {
        deq.addFirst(42);
        deq.removeLast();
        deq.addFirst(32);

        assertEquals(deq.removeFirst(), Integer.valueOf(32));
        assertEquals(deq.size(), 0);
    }

    @Test
    public void AddLast_OnEmptyDeque_SizeIncreased() {
        deq.addLast(42);
        deq.addLast(4);

        assertEquals(deq.size(), 2);
    }

    @Test(expected = NullPointerException.class)
    public void AddLast_NullItem_ExceptionThrown() {
        deq.addLast(null);
    }


    @Test(expected = NoSuchElementException.class)
    public void RemoveLast_EmptyDeque_ExceptionThrown() {
        deq.removeFirst();
    }

    @Test
    public void RemoveLast_ItemsAddedAtTheFront_ReturnsItemInFiFo() {
        deq.addFirst(42);
        deq.addFirst(32);
        deq.addFirst(22);
        deq.addFirst(12);

        assertEquals(deq.removeLast(), Integer.valueOf(42));
        assertEquals(deq.removeLast(), Integer.valueOf(32));
        assertEquals(deq.removeLast(), Integer.valueOf(22));
        assertEquals(deq.removeLast(), Integer.valueOf(12));
    }

    @Test
    public void RemoveLast_ItemsAddedAtTheEnd_ReturnsItemInLiFo() {
        deq.addLast(42);
        deq.addLast(32);
        deq.addLast(22);
        deq.addLast(12);

        assertEquals(deq.removeLast(), Integer.valueOf(12));
        assertEquals(deq.removeLast(), Integer.valueOf(22));
        assertEquals(deq.removeLast(), Integer.valueOf(32));
        assertEquals(deq.removeLast(), Integer.valueOf(42));
    }

    @Test(expected = NoSuchElementException.class)
    public void RemoveFirst_EmptyDeque_ExceptionThrown() {
        deq.removeFirst();
    }

    @Test
    public void RemoveFirst_SingleItem_ItemReturned() {
        deq.addLast(42);

        Integer ret = deq.removeFirst();

        assertEquals(ret, Integer.valueOf(42));
    }

    @Test
    public void RemoveFirst_ItemsAddedAtTheEnd_ReturnsItemInFiFo() {
        deq.addLast(42);
        deq.addLast(32);
        deq.addLast(22);
        deq.addLast(12);

        assertEquals(deq.removeFirst(), Integer.valueOf(42));
        assertEquals(deq.removeFirst(), Integer.valueOf(32));
        assertEquals(deq.removeFirst(), Integer.valueOf(22));
        assertEquals(deq.removeFirst(), Integer.valueOf(12));
        assertEquals(deq.size(), 0);
    }

    @Test
    public void RemoveFirst_ItemsAddedAtTheFront_ReturnsItemInLiFo() {
        deq.addFirst(42);
        deq.addFirst(32);
        deq.addFirst(22);
        deq.addFirst(12);

        assertEquals(deq.removeFirst(), Integer.valueOf(12));
        assertEquals(deq.removeFirst(), Integer.valueOf(22));
        assertEquals(deq.removeFirst(), Integer.valueOf(32));
        assertEquals(deq.removeFirst(), Integer.valueOf(42));
    }

    @Test
    public void Iterator_EmptyDeque_DoesNotHaveNext() {
        Iterator<Integer> it = deq.iterator();

        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void Iterator_NextCalledOnEmptyDeque_ExceptionThrown() {
        Iterator<Integer> it = deq.iterator();

        it.next();
    }

    @Test
    public void Iterator_DequeWithITems_ReturnsItemsFromFirstToLast() {
        deq.addLast(42);
        deq.addLast(32);
        deq.addLast(22);
        deq.addLast(12);

        Iterator<Integer> it = deq.iterator();

        assertEquals(it.next(), Integer.valueOf(42));
        assertEquals(it.next(), Integer.valueOf(32));
        assertEquals(it.next(), Integer.valueOf(22));
        assertEquals(it.next(), Integer.valueOf(12));
        assertEquals(it.hasNext(), false);
    }



}