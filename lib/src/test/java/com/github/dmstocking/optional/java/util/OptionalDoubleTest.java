package com.github.dmstocking.optional.java.util;

import com.github.dmstocking.optional.java.util.function.DoubleConsumer;
import com.github.dmstocking.optional.java.util.function.DoubleSupplier;
import com.github.dmstocking.optional.java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class OptionalDoubleTest {

    @Test
    public void emptyIsSameInstanceEveryTime() {
        Assert.assertSame(OptionalDouble.empty(), OptionalDouble.empty());
    }

    @Test
    public void getAsIntOfValueReturnsValue() {
        OptionalDouble of = OptionalDouble.of(1.0);
        Assert.assertEquals(1.0, of.getAsDouble(), 0.0);
    }

    @Test(expected = NoSuchElementException.class)
    public void getOfEmptyThrowsNoSuchElementException() {
        OptionalDouble.empty().getAsDouble();
    }

    @Test
    public void ifPresentOfEmptyDoesNothing() {
        final double[] value = {0.0};
        OptionalDouble.empty().ifPresent(new DoubleConsumer() {
            @Override
            public void accept(double o) {
                value[0] = 1.0;
            }
        });
        Assert.assertEquals(0.0, value[0], 0.0);
    }

    @Test
    public void ifPresentOfValueDoesSomething() {
        final double[] value = {0.0};
        OptionalDouble.of(0.0).ifPresent(new DoubleConsumer() {
            @Override
            public void accept(double o) {
                value[0] = 1.0;
            }
        });
        Assert.assertEquals(1.0, value[0], 0.0);
    }

    @Test
    public void ifPresentOrElseDoesActionWhenPresent() {
        final double[] value = {0.0};
        OptionalDouble.of(0.0).ifPresentOrElse(new DoubleConsumer() {
            @Override
            public void accept(double o) {
                value[0] = 1.0;
            }
        }, new Runnable() {
            @Override
            public void run() {
            }
        });
        Assert.assertEquals(1.0, value[0], 0.0);
    }

    @Test
    public void ifPresentOrElseDoesEmptyActionWhenEmpty() {
        final double[] value = {0.0};
        OptionalDouble.empty().ifPresentOrElse(new DoubleConsumer() {
            @Override
            public void accept(double o) {
            }
        }, new Runnable() {
            @Override
            public void run() {
                value[0] = 1.0;
            }
        });
        Assert.assertEquals(1.0, value[0], 0.0);
    }

    @Test
    public void isPresentOfEmptyReturnsFalse() {
        Assert.assertFalse(OptionalDouble.empty().isPresent());
    }

    @Test
    public void isPresentOfValueReturnsTrue() {
        Assert.assertTrue(OptionalDouble.of(0.0).isPresent());
    }

    @Test
    public void orElseOfEmptyReturnsElse() {
        Assert.assertEquals(1.0, OptionalDouble.empty().orElse(1.0), 0.0);
    }

    @Test
    public void orElseOfValueReturnsValue() {
        Assert.assertEquals(1.0, OptionalDouble.of(1.0).orElse(2.0), 0.0);
    }


    @Test
    public void orElseGetOfEmptyReturnsElse() {
        double actual = OptionalDouble.empty().orElseGet(new DoubleSupplier() {
            @Override
            public double get() {
                return 1.0;
            }
        });
        Assert.assertEquals(1.0, actual, 0.0);
    }

    @Test
    public void orElseGetOfValueReturnsValue() {
        double actual = OptionalDouble.of(1.0).orElseGet(new DoubleSupplier() {
            @Override
            public double get() {
                return 2.0;
            }
        });
        Assert.assertEquals(1.0, actual, 0.0);
    }

    @Test
    public void orElseThrowOfEmptyThrowsException() throws Exception {
        final Exception exception = new Exception();
        try {
            double actual = OptionalDouble.empty().orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    return exception;
                }
            });
            Assert.fail();
            Assert.assertEquals(0.0, actual);
        } catch (Exception e) {
            Assert.assertEquals(exception, e);
        }
    }

    @Test
    public void orElseThrowOfValueReturnsValue() throws Exception {
        double actual = OptionalDouble.of(1.0).orElseThrow(new Supplier<Exception>() {
            @Override
            public Exception get() {
                return new Exception();
            }
        });
        Assert.assertEquals(1.0, actual, 0.0);
    }

    @Test
    public void equalsOfValueDoesNotEqualsAnother() throws Exception {
        Assert.assertNotEquals(OptionalDouble.of(1.0), OptionalDouble.of(2.0));
    }

    @Test
    public void equalsSameReference() throws Exception {
        OptionalDouble value = OptionalDouble.of(1.0);
        Assert.assertEquals(value, value);
    }

    @Test
    public void equalsOfValueEqualsItself() throws Exception {
        Assert.assertEquals(OptionalDouble.of(1.0), OptionalDouble.of(1.0));
    }

    @Test
    public void equalsOfValueDoesNotEqualDifferentType() throws Exception {
        Assert.assertNotEquals(OptionalDouble.of(1.0), "a");
    }

    @Test
    public void equalsOfEmptyDoesNotEqualValue() throws Exception {
        Assert.assertNotEquals(OptionalDouble.empty(), OptionalDouble.of(1.0));
    }

    @Test
    public void hashCodeOfEmptyIsZero() {
        Assert.assertEquals(0, OptionalDouble.empty().hashCode());
    }

    @Test
    public void hashCodeOfValueIsValuesHashCode() {
        Assert.assertEquals(Double.hashCode(1.0), OptionalDouble.of(1.0).hashCode());
    }

    @Test
    public void toStringOfEmpty() {
        Assert.assertEquals("OptionalDouble.empty", OptionalDouble.empty().toString());
    }

    @Test
    public void toStringOfValue() {
        Assert.assertEquals("OptionalDouble[0.0]", OptionalDouble.of(0.0d).toString());
    }
}
