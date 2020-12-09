package ua.edu.ucu;

import ua.edu.ucu.stream.*;

public class StreamApp {

    public static int streamOperations(IntStream intStream) {

        return intStream.filter(x -> x > 0).map(x -> x * x).flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).reduce(0, (sum, x) -> sum += x);
    }

    public static String streamForEach(IntStream intStream) {

        StringBuilder str = new StringBuilder();
        intStream.forEach(str::append);

        return str.toString();
    }

    public static int[] streamToArray(IntStream intStream) {

        return intStream.toArray();
    }
}
