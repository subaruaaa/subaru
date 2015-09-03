package subaru.com.types;

import java.util.Comparator;

public class Tuple5<T1, T2, T3, T4, T5> extends Tuple4<T1, T2, T3, T4> {
    public T5 _5;

    public Tuple5(T1 o1, T2 o2, T3 o3, T4 o4, T5 o5) {
        super(o1, o2, o3, o4);
        _5 = o5;
    }

    @Override
    protected String _string() {
        return super._string() + ", " + _5.toString();
    }

    public static class _5thComparator<T extends Tuple5<?, ?, ?, ?, ? extends Comparable>> implements Comparator<T> {
        private int order = 1;

        public _5thComparator() {
            this(1);
        }

        public _5thComparator(int order) {
            this.order = order;
        }

        public int compare(T o1, T o2) {
            return order * o1._5.compareTo(o2._5);
        }
    }
}
