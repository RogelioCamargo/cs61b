package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextLast = 0;
        nextFirst = items.length - 1;
    }

    private void resize(int capacity) {
        T[] newItems = (T []) new Object[capacity];
        int j = 0;
        int start = (nextFirst + 1) % items.length;
        for (int i = start; i < start + size; i = (i + 1) % items.length) {
            if (j >= size) break;
            newItems[j] = items[i];
            j++;
        }

        items = newItems;
        nextLast = size;
        nextFirst = items.length - 1;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst--;
        size++;
    }
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast++;
        size++;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        int start = nextFirst + 1;
        for (int i = start; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }

        int end = nextLast;
        for (int i = 0; i < end; i++) {
            System.out.print(items[i] + " ");
        }

        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (items.length >= 16 && ((double) size / items.length) < 0.25) {
            resize(items.length / 4);
        }

        int firstIndex = (nextFirst + 1) % items.length;
        T itemToRemove = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;

        size--;
        return itemToRemove;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (items.length >= 16 && ((double) size / items.length) < 0.25) {
            resize(items.length / 4);
        }

        int lastIndex = (items.length + nextLast - 1) % items.length;
        T itemToRemove = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;

        size--;
        return itemToRemove;
    }
    @Override
    public T get(int index) {
        int indexFromFirst = (nextFirst + 1 + index) % items.length;
        return items[indexFromFirst];
    }
}
