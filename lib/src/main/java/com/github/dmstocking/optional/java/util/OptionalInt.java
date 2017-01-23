package com.github.dmstocking.optional.java.util;

import com.github.dmstocking.optional.java.util.function.IntConsumer;
import com.github.dmstocking.optional.java.util.function.IntSupplier;
import com.github.dmstocking.optional.java.util.function.Supplier;

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
public final class OptionalInt {

    private static final OptionalInt EMPTY = new OptionalInt(false, 0);

    /**
     * Returns an empty {@code Optional} instance. No value is present for this Optional.
     *
     * Though it may be tempting to do so, avoid testing if an object is empty by comparing with
     * {@code ==} against instances returned by {@code Optional.empty()}. There is no guarantee that
     * it is a singleton. Instead, use {@link #isPresent()}
     *
     * @return an empty {@code Optional}
     */
    public static OptionalInt empty() {
        return EMPTY;
    }

    private boolean isPresent;
    private final int value;

    private OptionalInt(boolean isPresent, int value) {
        this.isPresent = isPresent;
        this.value = value;
    }

    /**
     * Returns an Optional with the specified present non-null value.
     *
     * @param value the value to be present, which must be non-null
     * @return an Optional with the value present
     */
    public static OptionalInt of(int value) {
        return new OptionalInt(true, value);
    }

    /**
     * If a value is present in this {@code Optional}, returns the value, otherwise throws {@code
     * NoSuchElementException}.
     *
     * @return the non-null value held by this {@code Optional}
     * @throws NoSuchElementException if there is no value present
     * @see OptionalInt#isPresent()
     */
    public int getAsInt() {
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
    public void ifPresent(IntConsumer consumer) {
        if (isPresent()) {
            consumer.accept(value);
        }
    }

    /**
     * If a value is present, invoke the specified consumer with the value, otherwise performs the
     * given empty-based aciton.
     *
     * @param consumer    block to be executed if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if value is present and {@code consumer} is null, or no value is
     *                              present and the given empty-based action is null.
     */
    public void ifPresentOrElse(IntConsumer consumer, Runnable emptyAction) {
        if (isPresent()) {
            consumer.accept(value);
        } else {
            emptyAction.run();
        }
    }

    public boolean isPresent() {
        return isPresent;
    }

    /**
     * Return the value if present, otherwise return {@code other}.
     *
     * @param other the value to be returned if there is no value present, may be null
     * @return the value, if present, otherwise {@code other}
     */
    public int orElse(int other) {
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
    public int orElseGet(IntSupplier other) {
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
    public <X extends Throwable> int orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
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

        if (!(o instanceof OptionalInt)) {
            return false;
        }

        if (isPresent()) {
            OptionalInt other = (OptionalInt) o;
            return value == other.value;
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
            return Integer.hashCode(value);
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
            return "OptionalInt[" + value + "]";
        }

        return "OptionalInt.empty";
    }
}
