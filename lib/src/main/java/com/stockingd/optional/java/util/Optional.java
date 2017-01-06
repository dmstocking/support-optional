package com.stockingd.optional.java.util;

import com.stockingd.optional.java.util.function.Consumer;
import com.stockingd.optional.java.util.function.Function;
import com.stockingd.optional.java.util.function.Predicate;
import com.stockingd.optional.java.util.function.Supplier;

import java.util.NoSuchElementException;

/**
 * A container object which may or may not contain a non-null value. If a value is present,
 * {@code isPresent()} will return {@code true} and {@code get()} will return the value.
 *
 * Additional methods that depend on the presence or absence of a contained value are provided, such
 * as {@code orElse()} (return a default value if value not present) and {@code ifPresent()}
 * (execute a block of code if the value is present).
 */
@SuppressWarnings("WeakerAccess")
public final class Optional<T> {

    @SuppressWarnings("unchecked")
    private static final Optional<?> EMPTY = new Optional(null);

    /**
     * Returns an empty {@code Optional} instance. No value is present for this Optional.
     *
     * Though it may be tempting to do so, avoid testing if an object is empty by comparing with
     * {@code ==} against instances returned by {@code Optional.empty()}. There is no guarantee that
     * it is a singleton. Instead, use {@link #isPresent()}
     *
     * @param <T> Type of the non-existent value
     * @return an empty {@code Optional}
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    private final T value;

    private Optional(T value) {
        this.value = value;
    }

    /**
     * Returns an Optional with the specified present non-null value.
     *
     * @param value the value to be present, which must be non-null
     * @param <T> the class of the value
     * @return an Optional with the value present
     * @throws NullPointerException if value is null
     */
    public static <T> Optional<T> of(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        return new Optional<T>(value);
    }

    /**
     * Returns an Optional describing the specified value, if non-null otherwise returns an empty
     * Optional.
     *
     * @param value the possible-null value to describe
     * @param <T> the class of the value
     * @return an Optional with a present value if the specified value is non-null, otherwise an
     * empty Optional
     */
    public static <T> Optional<T> ofNullable(T value) {
        if (value != null) {
            return new Optional<T>(value);
        }

        return empty();
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (isPresent() && predicate.test(value)) {
            return this;
        }

        return empty();
    }

    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        if (isPresent()) {
            return mapper.apply(value);
        }

        return empty();
    }

    public T get() {
        if (isPresent()) {
            return value;
        }

        throw new NoSuchElementException("No value present");
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent()) {
            consumer.accept(value);
        }
    }

    public boolean isPresent() {
        return value != null;
    }


    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        if (isPresent()) {
            return Optional.ofNullable(mapper.apply(value));
        }

        return empty();
    }

    public T orElse(T other) {
        if (isPresent()) {
            return value;
        }

        return other;
    }

    public T orElseGet(Supplier<? extends T> other) {
        if (isPresent()) {
            return value;
        }

        return other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            return value;
        }

        throw exceptionSupplier.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Optional)) {
            return false;
        }

        if (isPresent()) {
            Optional<?> other = (Optional<?>) o;
            return value.equals(other.value);
        }

        return false;
    }

    @Override
    public int hashCode() {
        if (isPresent()) {
            return value.hashCode();
        }

        return 0;
    }

    @Override
    public String toString() {
        if (isPresent()) {
            return "Optional[" + value.toString() + "]";
        }

        return "Optional.empty";
    }
}
