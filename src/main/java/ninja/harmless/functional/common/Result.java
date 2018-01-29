package ninja.harmless.functional.common;

import java.util.function.Consumer;

/**
 * Represents the result of a computation and complies with the functional paradigm.
 * <p>
 * A computation can either be successful {@link Success}
 * or a failure {@link Failure} in case of an error.
 *
 * @author bnjm@harmless.ninja - 1/29/18.
 */
public interface Result<T> {

    /**
     * Creates a new {@link Success} instance holding a specific success value
     *
     * @param value
     * @param <T>
     * @return a new {@link Success} instance
     */
    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * Creates a new {@link Failure} instance holding a specific success value
     *
     * @param errorMessage
     * @param <T>
     * @return a new {@link Failure} instance
     */
    static <T> Failure<T> failure(String errorMessage) {
        return new Failure<>(errorMessage);
    }

    /**
     * Depending on the concrete implementation either the success consumer or the failure consumer is triggered.
     *
     * @param success consumer containing the function called on a successful computation
     * @param failure consumer containing the function called on a failed computation
     */
    void consumeOn(Consumer<T> success, Consumer<String> failure);

    /**
     * Represents a successful computation and passes the value of the successful computation to a {@link Consumer}
     *
     * @param <T>
     */
    class Success<T> implements Result<T> {
        private final T value;

        Success(T value) {
            this.value = value;
        }

        @Override
        public void consumeOn(Consumer<T> success, Consumer<String> failure) {
            success.accept(value);
        }
    }

    /**
     * Represents a failed computation and passes an error message to a consumer
     *
     * @param <T>
     */
    class Failure<T> implements Result<T> {
        private final String errorMessage;

        Failure(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void consumeOn(Consumer<T> success, Consumer<String> failure) {
            failure.accept(errorMessage);
        }
    }
}
