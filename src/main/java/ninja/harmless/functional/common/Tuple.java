package ninja.harmless.functional.common;

import java.util.Objects;

/**
 * @author bnjm@harmless.ninja - 1/30/18.
 */
public class Tuple<L, R> {

    public final L left;
    public final R right;

    public Tuple(L left, R right) {
        this.left = Objects.requireNonNull(left, "Tuple does not allow null values");
        this.right = Objects.requireNonNull(right, "Tuple does not allow null values");
    }

    public Tuple<R, L> swap() {
        return new Tuple<>(right, left);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (!left.equals(tuple.left)) return false;
        return right.equals(tuple.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
