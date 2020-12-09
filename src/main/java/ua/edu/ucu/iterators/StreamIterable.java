package ua.edu.ucu.iterators;

import java.util.Iterator;

public class StreamIterable implements Iterator<Integer> {

    private final int[] array;
    private int i = 0;

    public StreamIterable(int... values) {

        array = values;
    }

    @Override
    public Integer next() {

        return array[i++];
    }

    @Override
    public boolean hasNext() {

        return i < array.length;
    }
}