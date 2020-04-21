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

    //�洢ֵ������ǿ���Ϊԭֵ�������null���������κ�ֵ
    private final T value;

    //�޲ι��췽����Ĭ��ֵnull
    private Optional() {
        this.value = null;
    }

    //����һ����Optional��
    public static<T> Optional<T> empty() {
        @SuppressWarnings("unchecked")
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }

    //���ι��췽����Ҫ��value�ǿ�
    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    //���ݷǿ�ֵ����Optional
    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }


    //��̬����
    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    //����ֵ�����ֵΪ�գ��׳�û�нڵ��쳣�����ǿ�ָ��Ŷ
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    //����ֵ������true,���򷵻�false
    public boolean isPresent() {
        return value != null;
    }

    //�������ֵ����ʹ�ø�ֵ����ָ����ʹ���ߣ�����Ϊ��ִ�С�
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    //���ֵ���ڣ��������ֵƥ������� predicate������һ��Optional�����������ֵ�����򷵻�һ���յ�Optional��
    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    //�����ֵ�������ִ�е���ӳ�亯���õ�����ֵ���������ֵ��Ϊ null���򴴽�����ӳ�䷵��ֵ��Optional��Ϊmap��������ֵ�����򷵻ؿ�Optional��
    public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    //���ֵ���ڣ����ػ���Optional������ӳ�䷽����ֵ�����򷵻�һ���յ�Optional
    public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    //�����Ϊ�շ��ص�ǰֵ�����򷵻ش���ֵ
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
    //�пգ������ṩһ���¶���ֵ
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
