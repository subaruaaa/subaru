package com.subaru.types;

import java.util.Comparator;

public class Tuple2 <T1, T2> extends Tuple1<T1> {
    public T2 _2;

    public Tuple2(T1 o1, T2 o2) {
        super(o1);
        _2 = o2;
    }

    @Override
    protected String _string() {
        return super._string() + ", " + _2.toString();
    }

    public static class _2ndComparator<T extends Tuple2<?, ? extends Comparable>> implements Comparator<T> {
        private int order = 1;

        public _2ndComparator() {
            this(1);
        }

        public _2ndComparator(int order) {
            this.order = order;
        }

        public int compare(T o1, T o2) {
            return order * o1._2.compareTo(o2._2);
        }
    }
    
}
