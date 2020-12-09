package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;


public class FilterIterable implements Iterator<Integer> {

    private final Iterator<Integer> previous;
    private int val;
    private final IntPredicate predicate;

    public FilterIterable(Iterator<Integer> iter, IntPredicate pr) {

        previous = iter;
        predicate = pr;
    }

    @Override
    public Integer next() {
        return val;
    }

    @Override
    public boolean hasNext() {

        while (previous.hasNext()) {

            val = previous.next();

            if (predicate.test(val)) {

                return true;
            }
        }

        return false;
    }
}