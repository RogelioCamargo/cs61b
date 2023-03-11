package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class TNode {
        public T item;
        public TNode prev;
        public TNode next;
        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        TNode oldFirst = sentinel.next;
        TNode newNode = new TNode(item, sentinel, oldFirst);
        sentinel.next = newNode;
        oldFirst.prev = newNode;

        size++;
    }

    @Override
    public void addLast(T item) {
        TNode oldLast = sentinel.prev;
        TNode newNode = new TNode(item, oldLast, sentinel);
        sentinel.prev = newNode;
        oldLast.next = newNode;

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
    public  void printDeque() {
        TNode current = sentinel.next;
        while (current.item != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;

        TNode nodeToRemove = sentinel.next;
        sentinel.next = sentinel.next.next;
        nodeToRemove.next.prev = nodeToRemove.prev;

        nodeToRemove.next = null;
        nodeToRemove.prev = null;

        size--;
        return nodeToRemove.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;

        TNode nodeToRemove = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        nodeToRemove.prev.next = nodeToRemove.next;

        nodeToRemove.next = null;
        nodeToRemove.prev = null;

        size--;
        return nodeToRemove.item;
    }

    @Override
    public T get(int index) {
        return null;
    }
}