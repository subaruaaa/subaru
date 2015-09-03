package subaru.com.types;

import java.util.Comparator;

public class Tuple3 <T1, T2, T3> extends Tuple2<T1, T2> {
    public T3 _3;

    public Tuple3(T1 o1, T2 o2, T3 o3) {
        super(o1, o2);
        _3 = o3;
    }

    @Override
    protected String _string() {
        return super._string() + ", " + _3.toString();
    }

    public static class _3rdComparator<T extends Tuple3<?, ?, ? extends Comparable>> implements Comparator<T> {
        private int order = 1;

        public _3rdComparator() {
            this(1);
        }

        public _3rdComparator(int order) {
            this.order = order;
        }

        public int compare(T o1, T o2) {
            return order * o1._3.compareTo(o2._3);
        }
    }
}
