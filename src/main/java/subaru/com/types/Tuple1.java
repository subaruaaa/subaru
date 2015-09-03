package subaru.com.types;

import java.util.Comparator;

public class Tuple1 <T1> extends Tuple0 {
    public T1 _1;

    public Tuple1(T1 o1) {
        super();
        _1 = o1;
    }

    @Override
    protected String _string() {
        return _1.toString();
    }

    public static class _1stComparator<T extends Tuple1<? extends Comparable>> implements Comparator<T> {
        private int order = 1;

        public _1stComparator() {
            this(1);
        }

        public _1stComparator(int order) {
            this.order = order;
        }

        public int compare(T o1, T o2) {
            return order * o1._1.compareTo(o2._1);
        }
    }

}
