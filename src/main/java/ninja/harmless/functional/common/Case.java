package ninja.harmless.functional.common;

import java.util.function.Supplier;

/**
 * @author bnjm@harmless.ninja - 1/30/18.
 */
public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

    private Case(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
        super(booleanSupplier, resultSupplier);
    }

    public static <T> Case<T> matchCondition(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        return new Case<>(condition, value);
    }

    public static <T> DefaultCase<T> defaultCase(Supplier<Result<T>> resultSupplier) {
        return new DefaultCase<>(() -> Boolean.TRUE, resultSupplier);
    }

    @SafeVarargs
    public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... cases) {
        for (Case<T> aCase : cases) {
            if (aCase.left.get())
                return aCase.right.get();
        }
        return defaultCase.right.get();
    }

    private static class DefaultCase<T> extends Case<T> {
        private DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
            super(booleanSupplier, resultSupplier);
        }
    }
}
