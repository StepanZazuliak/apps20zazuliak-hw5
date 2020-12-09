package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterable implements Iterator<Integer> {

    private final Iterator<Integer> previous;
    private final IntUnaryOperator fun;

    @Override
    public boolean hasNext() {

        return previous.hasNext();
    }

    public MapIterable(Iterator<Integer> iter, IntUnaryOperator oper) {

        previous = iter;
        fun = oper;
    }

    @Override
    public Integer next() {

        return fun.apply(previous.next());
    }
}