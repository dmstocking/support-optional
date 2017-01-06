package com.stockingd.optional.java.util.function;

/**
 * Represents a predicate (boolean-valued function) of one argument.
 *
 * This is a functional interface whose functional method is {@code #test(Object)}.
 *
 * @param <T> the type of the input to the predicate
 */
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    boolean test(T t);
}
