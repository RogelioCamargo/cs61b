package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }
    public T max() {
        if (this.size() == 0) {
            return null;
        }
        T max = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T current = this.get(i);
            if (c.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        T max = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T current = this.get(i);
            if (c.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }

    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    private static class StringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }
}
