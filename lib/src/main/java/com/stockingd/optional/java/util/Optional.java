package com.stockingd.optional.java.util;

import com.stockingd.optional.java.util.function.Consumer;
import com.stockingd.optional.java.util.function.Function;
import com.stockingd.optional.java.util.function.Predicate;
import com.stockingd.optional.java.util.function.Supplier;

import java.util.NoSuchElementException;

/**
 * A container object which may or may not contain a non-null value. If a value is present, {@code
 * isPresent()} will return {@code true} and {@code get()} will return the value.
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
     * @param <T>   the class of the value
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
     * @param <T>   the class of the value
     * @return an Optional with a present value if the specified value is non-null, otherwise an
     * empty Optional
     */
    public static <T> Optional<T> ofNullable(T value) {
        if (value != null) {
            return new Optional<T>(value);
        }

        return empty();
    }

    /**
     * If a value is present, and the value matches the given predicate, return an {@code Optional}
     * describing the value, otherwise return an empty {@code Optional}.
     *
     * @param predicate a predicate to apply to the value, if present
     * @return an Optional describing the value of this {@code Optional} if a value is present and
     * the value matches the given predicate, otherwise an empty {@code Optional}.
     * @throws NullPointerException if the predicate is null
     */
    public Optional<T> filter(Predicate<? super T> predicate) {
        if (isPresent() && predicate.test(value)) {
            return this;
        }

        return empty();
    }

    /**
     * If a value is present, apply the provided {@code Optional}-bearing mapping function to it,
     * return that result, otherwise return an empty {@code Optional}. This method is similar to
     * {@link #map(Function)}, but the provided mapper is one whose result is already an {@code
     * Optional}, and if invoked, {@code flatMap} does not wrap it with an additional {@code
     * Optional}.
     *
     * @param <U>    The type parameter to the {@code Optional} returned by
     * @param mapper a mapping function to apply to the value, if present the mapping function
     * @return the result of applying an {@code Optional}-bearing mapping function to the value of
     * this {@code Optional}, if a value is present, otherwise an empty {@code Optional}
     * @throws NullPointerException if the mapping function is null or returns a null result
     */
    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        if (isPresent()) {
            return mapper.apply(value);
        }

        return empty();
    }

    /**
     * If a value is present in this {@code Optional}, returns the value, otherwise throws {@code
     * NoSuchElementException}.
     *
     * @return the non-null value held by this {@code Optional}
     * @throws NoSuchElementException if there is no value present
     * @see Optional#isPresent()
     */
    public T get() {
        if (isPresent()) {
            return value;
        }

        throw new NoSuchElementException("No value present");
    }

    /**
     * If a value is present, invoke the specified consumer with the value, otherwise do nothing.
     *
     * @param consumer block to be executed if a value is present
     * @throws NullPointerException if value is present and {@code consumer} is null
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent()) {
            consumer.accept(value);
        }
    }

    public boolean isPresent() {
        return value != null;
    }

    /**
     * If a value is present, apply the provided mapping function to it, and if the result is
     * non-null, return an {@code Optional} describing the result.  Otherwise return an empty {@code
     * Optional}. <p> This method supports post-processing on optional values, without the need to
     * explicitly check for a return status.  For example, the following code traverses a stream of
     * file names, selects one that has not yet been processed, and then opens that file, returning
     * an {@code Optional<FileInputStream>}:
     *
     * <pre>{@code
     *     Optional<FileInputStream> fis =
     *         names.stream().filter(name -> !isProcessedYet(name))
     *                       .findFirst()
     *                       .map(name -> new FileInputStream(name));
     * }</pre>
     *
     * Here, {@code findFirst} returns an {@code Optional<String>}, and then {@code map} returns an
     * {@code Optional<FileInputStream>} for the desired file if one exists. </p>
     *
     * @param <U>    The type of the result of the mapping function
     * @param mapper a mapping function to apply to the value, if present
     * @return an {@code Optional} describing the result of applying a mapping function to the value
     * of this {@code Optional}, if a value is present, otherwise an empty {@code Optional}
     * @throws NullPointerException if the mapping function is null
     */
    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        if (isPresent()) {
            return Optional.ofNullable(mapper.apply(value));
        }

        return empty();
    }

    /**
     * Return the value if present, otherwise return {@code other}.
     *
     * @param other the value to be returned if there is no value present, may be null
     * @return the value, if present, otherwise {@code other}
     */
    public T orElse(T other) {
        if (isPresent()) {
            return value;
        }

        return other;
    }

    /**
     * Return the value if present, otherwise invoke {@code other} and return the result of that
     * invocation.
     *
     * @param other a {@code Supplier} whose result is returned if no value is present
     * @return the value if present otherwise the result of {@code other.get()}
     * @throws NullPointerException if value is not present and {@code other} is null
     */
    public T orElseGet(Supplier<? extends T> other) {
        if (isPresent()) {
            return value;
        }

        return other.get();
    }

    /**
     * Return the contained value, if present, otherwise throw an exception to be created by the
     * provided supplier.
     *
     * <p> A method reference to the exception constructor with an empty argument list can be used
     * as the supplier. For example, {@code IllegalStateException::new} </p>
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier The supplier which will return the exception to be thrown
     * @return the present value
     * @throws X                    if there is no value present
     * @throws NullPointerException if no value is present and {@code exceptionSupplier} is null
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            return value;
        }

        throw exceptionSupplier.get();
    }

    /**
     * Indicates whether some other object is "equal to" this Optional. The other object is
     * considered equal if: <ul> <li>it is also an {@code Optional} and; <li>both instances have no
     * value present or; <li>the present values are "equal to" each other via {@code equals()}.
     * </ul>
     *
     * @param o an object to be tested for equality
     * @return {code true} if the other object is "equal to" this object otherwise {@code false}
     */
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

    /**
     * Returns the hash code value of the present value, if any, or 0 (zero) if no value is
     * present.
     *
     * @return hash code value of the present value or 0 if no value is present
     */
    @Override
    public int hashCode() {
        if (isPresent()) {
            return value.hashCode();
        }

        return 0;
    }

    /**
     * Returns a non-empty string representation of this Optional suitable for debugging. The exact
     * presentation format is unspecified and may vary between implementations and versions.
     *
     * <p> If a value is present the result must include its string representation in the result.
     * Empty and present Optionals must be unambiguously differentiable. </p>
     *
     * @return the string representation of this instance
     */
    @Override
    public String toString() {
        if (isPresent()) {
            return "Optional[" + value.toString() + "]";
        }

        return "Optional.empty";
    }
}
