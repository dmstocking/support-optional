package com.github.dmstocking.optional.java.util.function;

/**
 * Represents a supplier of results.
 *
 * <p>There is no requirement that a new or distinct result be returned each time the supplier is
 * invoked.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a> whose functional method is
 * {@link #get()}.
 */
public interface IntSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     */
    int get();
}
