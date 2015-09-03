package subaru.com.types;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopK<T> {
    private final int k;
    private final T[] heap;
    private final Comparator<? super T> comparator;
    private int size;

    public TopK(int k, Comparator<? super T> comparator) {
        Assert.isTrue(k > 0, "Could not create TopK heap with size 0");

        this.k = k;
        this.comparator = comparator;

        size = 0;
        heap = (T[])new Object[k + 1];
    }

    public static <T extends Comparable> TopK<T> create(int k) {
        return new TopK<T>(k, new Comparator<T>() {
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void add(T element) {
        if (size+1 > k) {
            T min = heap[1];
            if (lessThan(element, min)) {
                return;
            }
            pop();
            add(element);
        }
        else {
            size++;
            heap[size] = element;
            upHeap();
        }
    }

    public T pop() {
        if (size > 0) {
            T result = heap[1];
            heap[1] = heap[size];
            heap[size] = null;
            size --;
            downHeap();
            return result;
        }
        else {
            return null;
        }
    }


    public void addAll(List<T> elements) {
        for(T e : elements) add(e);
    }

    public void clear() {
        while(size > 0) heap[size--] = null;
    }

    public List<T> toTopKList() {
        List<T> topkList = new ArrayList<T>();
        while(size > 0) {
            topkList.add(this.pop());
        }
        Collections.reverse(topkList);
        return topkList;
    }

    public int size() {
        return size;
    }

    public Comparator<? super T> comparator() {
        return comparator;
    }

    private void downHeap() {
        int parent = 1;
        T node = heap[parent];
        int child = parent * 2;
        int child2 = child + 1;
        if (child2 <= size && lessThan(heap[child2], heap[child])) {
            child = child2;
        }
        while (child <= size && lessThan(heap[child], node)) {
            heap[parent] = heap[child]; // shift up child
            parent = child;
            child = parent * 2;
            child2 = child + 1;
            if (child2 <= size && lessThan(heap[child2], heap[child])) {
                child = child2;
            }
        }
        heap[parent] = node;
    }

    private void upHeap() {
        int child = size;
        T node = heap[child];
        int parent = child / 2;
        while (parent > 0 && lessThan(node, heap[parent])) {
            heap[child] = heap[parent]; // shift parent down
            child = parent;
            parent = child / 2;
        }
        heap[child] = node;
    }

    private boolean lessThan(T a, T b) {
        return comparator.compare(a, b) < 0;
    }

}
