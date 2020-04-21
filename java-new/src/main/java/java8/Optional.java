package java8;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author shiva   2020/4/21 21:48
 */
public final class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();

    //存储值，如果非空则为原值，如果是null，不存在任何值
    private final T value;

    //无参构造方法。默认值null
    private Optional() {
        this.value = null;
    }

    //返回一个空Optional类
    public static<T> Optional<T> empty() {
        @SuppressWarnings("unchecked")
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }

    //带参构造方法，要求value非空
    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    //根据非空值创建Optional
    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }


    //静态构造
    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    //返回值，如果值为空，抛出没有节点异常，不是空指针哦
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    //存在值，返回true,否则返回false
    public boolean isPresent() {
        return value != null;
    }

    //如果存在值，则使用该值调用指定的使用者，否则为不执行。
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    //如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    //如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
    public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    //如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的Optional
    public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    //如果不为空返回当前值，否则返回传入值
    public T orElse(T other) {
        return value != null ? value : other;
    }


    /**
     *      Optional<User> opt = Optional.ofNullable(user);
     *         opt.orElseGet(() -> {
     *             User user1 = new User();
     *             user1.setNo("2");
     *             return user1;
     *      });
     */
    //判空，否则提供一个新对象值
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional<?> other = (Optional<?>) obj;
        return Objects.equals(value, other.value);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
    @Override
    public String toString() {
        return value != null
                ? String.format("Optional[%s]", value)
                : "Optional.empty";
    }
}
