package ninja.harmless.functional;

import java.util.function.Function;

/**
 * @author benjamin.krenn@leftshift.one - 8/5/18.
 * @since 1.0.0
 */
public class Stopwatch {
    public static <T> Function<T,T> start(Function<T,T> toBeWrapped) {
        return input -> {
            long start = System.currentTimeMillis();
            T result = toBeWrapped.apply(input);
            System.out.println("Time passed: " + (System.currentTimeMillis() - start) + " ms");
            return result;
        };
    }
}
