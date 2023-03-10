package deque;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    /** Adds a few things to the array, checking isEmpty() and size() are correct,
     * finally printing the results
     */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", ad.isEmpty());
        ad.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad.size());
        assertFalse("lld1 should now contain 1 item", ad.isEmpty());

        ad.addLast("middle");
        assertEquals(2, ad.size());

        ad.addLast("back");
        assertEquals(3, ad.size());

        System.out.println("Printing out deque: ");
        ad.printDeque();
    }

    @Test
    /* Adds an item, then removes an item, and ensures that ad is empty afterwards */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ad should be empty upon initialization", ad.isEmpty());

        ad.addFirst(10);
        // should not be empty
        assertFalse("ad should contain 1 item", ad.isEmpty());

        ad.removeFirst();
        // should be empty
        assertTrue("ad should be empty after removal", ad.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(1);

        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();

        int size = ad.size();
        assertEquals(0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types */
    public void multipleParamTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<>();

        ad1.addFirst(1);
        ad2.addFirst("Hello!");
        ad3.addFirst(false);

        ad1.removeFirst();
        ad2.removeFirst();
        ad3.removeFirst();
    }

    @Test
    /* Check if null is returned when removing from empty ArrayDeque */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertNull(ad.removeFirst());
        assertNull(ad.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct */
    public void bigADequeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 1000000; i++) {
            ad.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad.removeLast(), 0.0);
        }
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            ad.addLast(i);
        }

        for (double i = 0; i < 100; i++) {
            assertEquals(i, (double) ad.get((int) i), 0.0);
        }

        assertNull(ad.get(101));

        for (int i = 0; i < 100; i++) {
            ad.removeLast();
        }
    }

    @Test
    public void testIterator() {
        ArrayDeque<String> deque = new ArrayDeque<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        Iterator<String> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("b", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRandomCalls() {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            int operation = random.nextInt(4);

            if (operation == 0) {
                int value = random.nextInt();
                deque.addFirst(value);
            } else if (operation == 1) {
                int value = random.nextInt();
                deque.addLast(value);
            } else if (operation == 2 && !deque.isEmpty()) {
                int expectedValue = deque.get(0);
                int actualValue = deque.removeFirst();
                assertEquals(expectedValue, actualValue);
            } else if (operation == 3 && !deque.isEmpty()) {
                int expectedValue = deque.get(deque.size() - 1);
                int actualValue = deque.removeLast();
                assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Test
    public void testEquals() {
        ArrayDeque<String> deque1 = new ArrayDeque<String>();
        ArrayDeque<String> deque2 = new ArrayDeque<String>();

        // Test that two empty deques are equal
        assertTrue(deque1.equals(deque2));

        deque1.addFirst("Hello");
        deque1.addLast("World");

        deque2.addFirst("Hello");
        deque2.addLast("World");

        // Test that two deques with same elements in same order are equal
        assertTrue(deque1.equals(deque2));

        deque2.addFirst("Greetings");

        // Test that two deques with same elements in different order are not equal
        assertFalse(deque1.equals(deque2));

        ArrayDeque<Integer> deque3 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> deque4 = new ArrayDeque<Integer>();

        deque3.addFirst(1);
        deque3.addLast(2);

        deque4.addFirst(1);
        deque4.addLast(3);

        // Test that two deques with different elements are not equal
        assertFalse(deque3.equals(deque4));
    }
}

