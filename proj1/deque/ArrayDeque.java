package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;

        public ArrayDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T itemToReturn = get(position);
            position++;
            return itemToReturn;
        }
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
        nextFirst = (items.length + nextFirst - 1) % items.length;
        size++;
    }
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
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
        if (isEmpty()) {
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
        if (isEmpty()) {
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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other instanceof ArrayDeque) {
            ArrayDeque deque = (ArrayDeque) other;
            if (deque.size != this.size) {
                return false;
            }

            for (int i = 0; i < this.items.length; i++) {
                if (this.get(i) != deque.get(i)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
