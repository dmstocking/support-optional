package com.github.dmstocking.optional.java.util;

import com.github.dmstocking.optional.java.util.function.LongConsumer;
import com.github.dmstocking.optional.java.util.function.LongSupplier;
import com.github.dmstocking.optional.java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class OptionalLongTest {

    @Test
    public void emptyIsSameInstanceEveryTime() {
        Assert.assertSame(OptionalLong.empty(), OptionalLong.empty());
    }

    @Test
    public void getAsIntOfValueReturnsValue() {
        OptionalLong of = OptionalLong.of(1L);
        Assert.assertEquals(1L, of.getAsLong());
    }

    @Test(expected = NoSuchElementException.class)
    public void getOfEmptyThrowsNoSuchElementException() {
        OptionalLong.empty().getAsLong();
    }

    @Test
    public void ifPresentOfEmptyDoesNothing() {
        final long[] value = {0L};
        OptionalLong.empty().ifPresent(new LongConsumer() {
            @Override
            public void accept(long o) {
                value[0] = 1L;
            }
        });
        Assert.assertEquals(0L, value[0]);
    }

    @Test
    public void ifPresentOfValueDoesSomething() {
        final long[] value = {0L};
        OptionalLong.of(0L).ifPresent(new LongConsumer() {
            @Override
            public void accept(long o) {
                value[0] = 1L;
            }
        });
        Assert.assertEquals(1L, value[0]);
    }

    @Test
    public void ifPresentOrElseDoesActionWhenPresent() {
        final long[] value = {0L};
        OptionalLong.of(0L).ifPresentOrElse(new LongConsumer() {
            @Override
            public void accept(long o) {
                value[0] = 1L;
            }
        }, new Runnable() {
            @Override
            public void run() {
            }
        });
        Assert.assertEquals(1L, value[0]);
    }

    @Test
    public void ifPresentOrElseDoesEmptyActionWhenEmpty() {
        final long[] value = {0L};
        OptionalLong.empty().ifPresentOrElse(new LongConsumer() {
            @Override
            public void accept(long o) {
            }
        }, new Runnable() {
            @Override
            public void run() {
                value[0] = 1L;
            }
        });
        Assert.assertEquals(1L, value[0]);
    }

    @Test
    public void isPresentOfEmptyReturnsFalse() {
        Assert.assertFalse(OptionalLong.empty().isPresent());
    }

    @Test
    public void isPresentOfValueReturnsTrue() {
        Assert.assertTrue(OptionalLong.of(0L).isPresent());
    }

    @Test
    public void orElseOfEmptyReturnsElse() {
        Assert.assertEquals(1L, OptionalLong.empty().orElse(1L));
    }

    @Test
    public void orElseOfValueReturnsValue() {
        Assert.assertEquals(1L, OptionalLong.of(1L).orElse(2L));
    }


    @Test
    public void orElseGetOfEmptyReturnsElse() {
        long actual = OptionalLong.empty().orElseGet(new LongSupplier() {
            @Override
            public long get() {
                return 1L;
            }
        });
        Assert.assertEquals(1L, actual);
    }

    @Test
    public void orElseGetOfValueReturnsValue() {
        long actual = OptionalLong.of(1L).orElseGet(new LongSupplier() {
            @Override
            public long get() {
                return 2L;
            }
        });
        Assert.assertEquals(1L, actual);
    }

    @Test
    public void orElseThrowOfEmptyThrowsException() throws Exception {
        final Exception exception = new Exception();
        try {
            long actual = OptionalLong.empty().orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    return exception;
                }
            });
            Assert.fail();
            Assert.assertEquals(0L, actual);
        } catch (Exception e) {
            Assert.assertEquals(exception, e);
        }
    }

    @Test
    public void orElseThrowOfValueReturnsValue() throws Exception {
        long actual = OptionalLong.of(1L).orElseThrow(new Supplier<Exception>() {
            @Override
            public Exception get() {
                return new Exception();
            }
        });
        Assert.assertEquals(1L, actual);
    }

    @Test
    public void equalsOfValueDoesNotEqualsAnother() throws Exception {
        Assert.assertNotEquals(OptionalLong.of(1L), OptionalLong.of(2L));
    }

    @Test
    public void equalsSameReference() throws Exception {
        OptionalLong value = OptionalLong.of(1L);
        Assert.assertEquals(value, value);
    }

    @Test
    public void equalsOfValueEqualsItself() throws Exception {
        Assert.assertEquals(OptionalLong.of(1L), OptionalLong.of(1L));
    }

    @Test
    public void equalsOfValueDoesNotEqualDifferentType() throws Exception {
        Assert.assertNotEquals(OptionalLong.of(1L), "a");
    }

    @Test
    public void equalsOfEmptyDoesNotEqualValue() throws Exception {
        Assert.assertNotEquals(OptionalLong.empty(), OptionalLong.of(1L));
    }

    @Test
    public void hashCodeOfEmptyIsZero() {
        Assert.assertEquals(0L, OptionalLong.empty().hashCode());
    }

    @Test
    public void hashCodeOfValueIsValuesHashCode() {
        Assert.assertEquals(Long.hashCode(1L), OptionalLong.of(1L).hashCode());
    }

    @Test
    public void toStringOfEmpty() {
        Assert.assertEquals("OptionalLong.empty", OptionalLong.empty().toString());
    }

    @Test
    public void toStringOfValue() {
        Assert.assertEquals("OptionalLong[0]", OptionalLong.of(0L).toString());
    }
}
