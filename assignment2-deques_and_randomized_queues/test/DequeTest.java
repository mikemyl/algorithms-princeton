import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mike on 10/13/16.
 */
public class DequeTest {

    @Test
    public void IsEmpty_EmptyDeque_ReturnsTrue() {
        Map<String, String> map = new HashMap<>();
        String b = "asd";
        map.put("a", b);
        System.out.println(map.get("a"));
        b = "asdsad";
        System.out.println(map.get("a"));

        Deque<Integer> deq = new Deque();

        assertTrue(deq.isEmpty());
    }

    @Test
    public void Size_EmptyDeque_ReturnsZero() {
        Deque<Integer> deq = new Deque();

        assertEquals(deq.size(), 0);
    }

    @Test
    public void AddFirst_OnEmptyDeque_SizeIncreased() {
        Deque<Integer> deq = new Deque();

        deq.addFirst(2);
    }

}