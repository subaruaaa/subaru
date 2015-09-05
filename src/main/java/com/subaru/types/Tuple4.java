package com.subaru.types;

import java.util.Comparator;

public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {
    public T4 _4;

    public Tuple4(T1 o1, T2 o2, T3 o3, T4 o4) {
        super(o1, o2, o3);
        _4 = o4;
    }

    @Override
    protected String _string() {
        return super._string() + ", " + _4.toString();
    }

    public static class _4thComparator<T extends Tuple4<?, ?, ?, ? extends Comparable>> implements Comparator<T> {
        private int order = 1;

        public _4thComparator() {
            this(1);
        }

        public _4thComparator(int order) {
            this.order = order;
        }

        public int compare(T o1, T o2) {
            return order * o1._4.compareTo(o2._4);
        }
    }
}
