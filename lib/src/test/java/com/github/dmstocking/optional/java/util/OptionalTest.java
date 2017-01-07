package com.github.dmstocking.optional.java.util;

import com.github.dmstocking.optional.java.util.function.Consumer;
import com.github.dmstocking.optional.java.util.function.Function;
import com.github.dmstocking.optional.java.util.function.Predicate;
import com.github.dmstocking.optional.java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class OptionalTest {

    @Test
    public void emptyIsSameInstanceEveryTime() {
        Assert.assertSame(Optional.empty(), Optional.empty());
    }

    @Test(expected = NullPointerException.class)
    public void ofWithNullThrowsNPException() {
        Optional.of(null);
    }

    @Test
    public void ofNullableWithNullReturnsEmpty() {
        Assert.assertEquals(Optional.empty(), Optional.ofNullable(null));
    }

    @Test
    public void filterOfEmptyWithFalsePredicateReturnsEmpty() {
        Optional<Object> filtered = Optional.empty().filter(new Predicate<Object>() {
            @Override
            public boolean test(Object object) {
                return false;
            }
        });

        Assert.assertEquals(Optional.empty(), filtered);
    }

    @Test
    public void filterOfEmptyWithTruePredicateReturnsEmpty() {
        Optional<Object> filtered = Optional.empty().filter(new Predicate<Object>() {
            @Override
            public boolean test(Object object) {
                return true;
            }
        });

        Assert.assertEquals(Optional.empty(), filtered);
    }

    @Test
    public void filterOfValueWithFalsePredicateReturnsEmpty() {
        Optional<Integer> filtered = Optional.of(0).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer object) {
                return false;
            }
        });

        Assert.assertEquals(Optional.empty(), filtered);
    }

    @Test
    public void filterOfValueWithTruePredicateReturnsValue() {
        Optional<Integer> filtered = Optional.of(0).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer object) {
                return true;
            }
        });

        Assert.assertEquals(Optional.of(0), filtered);
    }

    @Test
    public void flatMapOfEmptyReturnsValue() {
        Optional<Integer> empty = Optional.empty();
        Optional<String> mapped = empty.flatMap(new Function<Integer, Optional<String>>() {
            @Override
            public Optional<String> apply(Integer integer) {
                return Optional.of("mapped");
            }
        });

        Assert.assertEquals(Optional.empty(), mapped);
    }

    @Test
    public void flatMapOfValueReturnsMappedValue() {
        Optional<String> mapped = Optional.of(0).flatMap(new Function<Integer, Optional<String>>() {
            @Override
            public Optional<String> apply(Integer integer) {
                return Optional.of("mapped");
            }
        });

        Assert.assertEquals(Optional.of("mapped"), mapped);
    }

    @Test
    public void getOfValueReturnsValue() {
        Optional<String> of = Optional.of("value");
        Assert.assertEquals("value", of.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void getOfEmptyThrowsNoSuchElementException() {
        Optional.empty().get();
    }

    @Test
    public void ifPresentOfEmptyDoesNothing() {
        final int[] value = {0};
        Optional.empty().ifPresent(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                value[0] = 1;
            }
        });
        Assert.assertEquals(0, value[0]);
    }

    @Test
    public void ifPresentOfValueDoesSomething() {
        final int[] value = {0};
        Optional.of(0).ifPresent(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                value[0] = 1;
            }
        });
        Assert.assertEquals(1, value[0]);
    }

    @Test
    public void isPresentOfEmptyReturnsFalse() {
        Assert.assertFalse(Optional.empty().isPresent());
    }

    @Test
    public void isPresentOfValueReturnsTrue() {
        Assert.assertTrue(Optional.of(0).isPresent());
    }

    @Test
    public void mapOfEmptyReturnsEmpty() {
        Optional<Integer> empty = Optional.empty();
        Optional<String> mapped = empty.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "mapped";
            }
        });
        Assert.assertEquals(Optional.empty(), mapped);
    }

    @Test
    public void mapOfValueReturnsMappedValue() {
        Optional<String> mapped = Optional.of(0).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "mapped";
            }
        });
        Assert.assertEquals(Optional.of("mapped"), mapped);
    }

    @Test
    public void orElseOfEmptyReturnsElse() {
        Assert.assertEquals("else", Optional.<String>empty().orElse("else"));
    }

    @Test
    public void orElseOfValueReturnsValue() {
        Assert.assertEquals("value", Optional.of("value").orElse("else"));
    }


    @Test
    public void orElseGetOfEmptyReturnsElse() {
        String actual = Optional.<String>empty().orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "else";
            }
        });
        Assert.assertEquals("else", actual);
    }

    @Test
    public void orElseGetOfValueReturnsValue() {
        String actual = Optional.of("value").orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "else";
            }
        });
        Assert.assertEquals("value", actual);
    }

    @Test
    public void orElseThrowOfEmptyThrowsException() throws Exception {
        final Exception exception = new Exception();
        try {
            String actual = Optional.<String>empty().orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    return exception;
                }
            });
            Assert.fail();
            Assert.assertEquals("value", actual);
        } catch (Exception e) {
            Assert.assertEquals(exception, e);
        }
    }

    @Test
    public void orElseThrowOfValueReturnsValue() throws Exception {
        String actual = Optional.of("value").orElseThrow(new Supplier<Exception>() {
            @Override
            public Exception get() {
                return new Exception();
            }
        });
        Assert.assertEquals("value", actual);
    }

    @Test
    public void equalsOfValueDoesNotEqualsAnother() throws Exception {
        Assert.assertNotEquals(Optional.of(1), Optional.of(2));
    }

    @Test
    public void equalsOfValueEqualsItself() throws Exception {
        Assert.assertEquals(Optional.of(1), Optional.of(1));
    }

    @Test
    public void hashCodeOfEmptyIsZero() {
        Assert.assertEquals(0, Optional.empty().hashCode());
    }

    @Test
    public void hashCodeOfValueIsValuesHashCode() {
        Assert.assertEquals("hash".hashCode(), Optional.of("hash").hashCode());
    }

    @Test
    public void toStringOfEmpty() {
        Assert.assertEquals("Optional.empty", Optional.empty().toString());
    }

    @Test
    public void toStringOfValue() {
        Assert.assertEquals("Optional[0]", Optional.of(0).toString());
    }
}
