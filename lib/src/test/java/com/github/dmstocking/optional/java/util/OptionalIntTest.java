package com.github.dmstocking.optional.java.util;

import com.github.dmstocking.optional.java.util.function.IntConsumer;
import com.github.dmstocking.optional.java.util.function.IntSupplier;
import com.github.dmstocking.optional.java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class OptionalIntTest {

    @Test
    public void emptyIsSameInstanceEveryTime() {
        Assert.assertSame(OptionalInt.empty(), OptionalInt.empty());
    }

    @Test
    public void getAsIntOfValueReturnsValue() {
        OptionalInt of = OptionalInt.of(1);
        Assert.assertEquals(1, of.getAsInt());
    }

    @Test(expected = NoSuchElementException.class)
    public void getOfEmptyThrowsNoSuchElementException() {
        OptionalInt.empty().getAsInt();
    }

    @Test
    public void ifPresentOfEmptyDoesNothing() {
        final int[] value = {0};
        OptionalInt.empty().ifPresent(new IntConsumer() {
            @Override
            public void accept(int o) {
                value[0] = 1;
            }
        });
        Assert.assertEquals(0, value[0]);
    }

    @Test
    public void ifPresentOfValueDoesSomething() {
        final int[] value = {0};
        OptionalInt.of(0).ifPresent(new IntConsumer() {
            @Override
            public void accept(int o) {
                value[0] = 1;
            }
        });
        Assert.assertEquals(1, value[0]);
    }

    @Test
    public void ifPresentOrElseDoesActionWhenPresent() {
        final int[] value = {0};
        OptionalInt.of(0).ifPresentOrElse(new IntConsumer() {
            @Override
            public void accept(int o) {
                value[0] = 1;
            }
        }, new Runnable() {
            @Override
            public void run() {
            }
        });
        Assert.assertEquals(1, value[0]);
    }

    @Test
    public void ifPresentOrElseDoesEmptyActionWhenEmpty() {
        final int[] value = {0};
        OptionalInt.empty().ifPresentOrElse(new IntConsumer() {
            @Override
            public void accept(int o) {
            }
        }, new Runnable() {
            @Override
            public void run() {
                value[0] = 1;
            }
        });
        Assert.assertEquals(1, value[0]);
    }

    @Test
    public void isPresentOfEmptyReturnsFalse() {
        Assert.assertFalse(OptionalInt.empty().isPresent());
    }

    @Test
    public void isPresentOfValueReturnsTrue() {
        Assert.assertTrue(OptionalInt.of(0).isPresent());
    }

    @Test
    public void orElseOfEmptyReturnsElse() {
        Assert.assertEquals(1, OptionalInt.empty().orElse(1));
    }

    @Test
    public void orElseOfValueReturnsValue() {
        Assert.assertEquals(1, OptionalInt.of(1).orElse(2));
    }


    @Test
    public void orElseGetOfEmptyReturnsElse() {
        int actual = OptionalInt.empty().orElseGet(new IntSupplier() {
            @Override
            public int get() {
                return 1;
            }
        });
        Assert.assertEquals(1, actual);
    }

    @Test
    public void orElseGetOfValueReturnsValue() {
        int actual = OptionalInt.of(1).orElseGet(new IntSupplier() {
            @Override
            public int get() {
                return 2;
            }
        });
        Assert.assertEquals(1, actual);
    }

    @Test
    public void orElseThrowOfEmptyThrowsException() throws Exception {
        final Exception exception = new Exception();
        try {
            int actual = OptionalInt.empty().orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    return exception;
                }
            });
            Assert.fail();
            Assert.assertEquals(0, actual);
        } catch (Exception e) {
            Assert.assertEquals(exception, e);
        }
    }

    @Test
    public void orElseThrowOfValueReturnsValue() throws Exception {
        int actual = OptionalInt.of(1).orElseThrow(new Supplier<Exception>() {
            @Override
            public Exception get() {
                return new Exception();
            }
        });
        Assert.assertEquals(1, actual);
    }

    @Test
    public void equalsOfValueDoesNotEqualsAnother() throws Exception {
        Assert.assertNotEquals(OptionalInt.of(1), OptionalInt.of(2));
    }

    @Test
    public void equalsSameReference() throws Exception {
        OptionalInt value = OptionalInt.of(1);
        Assert.assertEquals(value, value);
    }

    @Test
    public void equalsOfValueEqualsItself() throws Exception {
        Assert.assertEquals(OptionalInt.of(1), OptionalInt.of(1));
    }

    @Test
    public void equalsOfValueDoesNotEqualDifferentType() throws Exception {
        Assert.assertNotEquals(OptionalInt.of(1), "a");
    }

    @Test
    public void equalsOfEmptyDoesNotEqualValue() throws Exception {
        Assert.assertNotEquals(OptionalInt.empty(), OptionalInt.of(1));
    }

    @Test
    public void hashCodeOfEmptyIsZero() {
        Assert.assertEquals(0, OptionalInt.empty().hashCode());
    }

    @Test
    public void hashCodeOfValueIsValuesHashCode() {
        Assert.assertEquals(Integer.hashCode(1), OptionalInt.of(1).hashCode());
    }

    @Test
    public void toStringOfEmpty() {
        Assert.assertEquals("OptionalInt.empty", OptionalInt.empty().toString());
    }

    @Test
    public void toStringOfValue() {
        Assert.assertEquals("OptionalInt[0]", OptionalInt.of(0).toString());
    }
}
