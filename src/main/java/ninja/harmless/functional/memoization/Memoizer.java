package ninja.harmless.functional.memoization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author benjamin.krenn@leftshift.one - 8/5/18.
 * @since 1.0.0
 */
public class Memoizer<T,U> {

    private final Map<T, U> cache = new ConcurrentHashMap<>();

    private Memoizer() { }

    public static <T,U> Function<T,U> memoize(Function<T,U> function) {
        return new Memoizer<T, U>().applyMemoize(function);
    }

    private Function<T,U> applyMemoize(Function<T,U> function) {
        return input -> cache.computeIfAbsent(input, function::apply);
    }
}
