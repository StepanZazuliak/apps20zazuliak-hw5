package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterable;
import ua.edu.ucu.iterators.FlatMapIterable;
import ua.edu.ucu.iterators.MapIterable;
import ua.edu.ucu.iterators.StreamIterable;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {

    private final Iterator<Integer> iterator;

    private AsIntStream(Iterator<Integer> iterator) {

        this.iterator = iterator;
    }

    public static IntStream of(int... values) {

        return new AsIntStream(new StreamIterable(values));
    }

    private Iterable<Integer> toIterable() {

        return () -> iterator;
    }

    @Override
    public Double average() {

        checker();
        double sum = 0;
        int len = 0;

        for (int elem : toIterable()) {

            sum += elem;
            len += 1;
        }

        return sum / len;
    }

    private void checker() {

        if (iterator == null || !iterator.hasNext()) {

            throw new IllegalArgumentException("No arguments");
        }
    }

    @Override
    public Integer max() {

        checker();
        int maxVal = Integer.MIN_VALUE;

        for (int elem : toIterable()) {

            if (elem > maxVal) {

                maxVal = elem;
            }
        }

        return maxVal;
    }

    @Override
    public long count() {

        long size = 0;

        for (int elem : toIterable()) {

            size++;
        }

        return size;
    }

    @Override
    public Integer min() {

        checker();
        int minVal = Integer.MAX_VALUE;

        for (int elem : toIterable()) {

            if (elem < minVal) {

                minVal = elem;
            }
        }

        return minVal;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {

        return new AsIntStream(new FilterIterable(iterator, predicate));
    }

    @Override
    public Integer sum() {

        checker();
        int sum = 0;

        for (int elem : toIterable()) {

            sum += elem;
        }

        return sum;
    }

    @Override
    public void forEach(IntConsumer action) {

        for (int elem : toIterable()) {

            action.accept(elem);
        }
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {

        return new AsIntStream(new FlatMapIterable(iterator, func));
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {

        return new AsIntStream(new MapIterable(iterator, mapper));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {

        int res = identity;

        for (int elem : toIterable()) {

            res = op.apply(res, elem);
        }
        return res;
    }



    public Iterator<Integer> getIterator() {

        return iterator;
    }

    @Override
    public int[] toArray() {

        ArrayList<Integer> tmpRes = new ArrayList<>();

        for (int elem : toIterable()) {

            tmpRes.add(elem);
        }

        int[] res = new int[tmpRes.size()];
        int i = 0;

        for (int elem : tmpRes) {

            res[i++] = elem;
        }

        return res;
    }
}
