package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterable implements Iterator<Integer> {

    private final IntToIntStreamFunction fun;
    private final Iterator<Integer> previous;
    private Iterator<Integer> newStream;

    public FlatMapIterable(Iterator<Integer> iter, IntToIntStreamFunction fn) {

        previous = iter;
        fun = fn;

    }

    @Override
    public Integer next() {

        return newStream.next();
    }

    @Override
    public boolean hasNext() {

        if (newStream != null && newStream.hasNext()) {

            return true;
        }

        if (previous.hasNext()) {

            newStream = ((AsIntStream) fun.applyAsIntStream(previous.next())).getIterator();

            return true;
        }

        return false;
    }
}
