package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void testConstructor() {
        Comparator<Integer> c = MaxArrayDeque.getIntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(c);
        assertEquals(0, deque.size());
    }

    @Test
    public void testMax() {
        Comparator<Integer> c = MaxArrayDeque.getIntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(c);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(2);
        deque.addLast(1);
        assertEquals((Integer) 3, deque.max());
    }

    @Test
    public void testMaxWithComparator() {
        Comparator<Integer> c1 = MaxArrayDeque.getIntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(c1);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(2);
        deque.addLast(1);
        Comparator<Integer> c2 = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        };
        assertEquals((Integer) 1, deque.max(c2));
    }

    @Test
    public void testEmptyDeque() {
        Comparator<Integer> c = MaxArrayDeque.getIntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(c);
        assertNull(deque.max());
        assertNull(deque.max(c));
    }

    @Test
    public void testMaxWithStrings() {
        Comparator<String> c = MaxArrayDeque.getStringComparator();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(c);
        deque.addLast("hello");
        deque.addLast("world");
        deque.addLast("foo");
        deque.addLast("bar");
        deque.addLast("baz");
        assertEquals("world", deque.max());
    }
}
