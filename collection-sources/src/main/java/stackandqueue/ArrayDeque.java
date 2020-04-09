package stackandqueue;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author shiva   2020/4/1 20:46
 * @since   1.6
 */
class ArrayDeque<E> extends AbstractCollection<E>
        implements Deque<E>, Cloneable, Serializable
{
    private static final long serialVersionUID = 2340985798034038923L;
    //ArrayDeque��С������������2����
    private static final int MIN_INITIAL_CAPACITY = 8;

    //�ײ�����ʵ�֣��������Ƕ��г���
    transient Object[] elements;
    //����ͷ������
    transient int head;
    //������ӵ�Ԫ�ص�λ������������βָ��
    transient int tail;

    /*
     * **********************************************************************
     * ���췽��
     * **********************************************************************
     */

    //�޲ι���Ĭ�ϳ��� 16
    public ArrayDeque() {
        elements = new Object[16];
    }

    //ָ�������Ĺ�����
    //��ΪҪ�����鳤�ȱ����� 2���ݣ�������Ҫ�Դ���ĳ��Ƚ��м���
    public ArrayDeque(int numElements) {
        allocateElements(numElements);
    }

    //������ӣ���ָ����������ĳ���ָ����ʽ��ͬ
    public ArrayDeque(Collection<? extends E> c) {
        allocateElements(c.size());
        addAll(c);
    }

    /*
     * **********************************************************************
     * public����
     * **********************************************************************
     */

    //������ķ����������������ǵ�����Щ����

    //����ͷ�����Ԫ��
    public void addFirst(E e) {
        //������null
        if (e == null)
            throw new NullPointerException();
        //��ͷ���������ݣ�ͷָ�������ƶ���ÿ�ε���addFirst��head��һ
        elements[head = (head - 1) & (elements.length - 1)] = e;
        //�������ͷ�����ͽ�����ӵ�λ��������ͬ����������
        if (head == tail)
            doubleCapacity();
    }

    //����β�����Ԫ��
    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        //�Ƚ�Ԫ�ؼ�������
        elements[tail] = e;
        //��tail��1��Ȼ�����ж��Ƿ��ͷָ��������ͬ������ͬ������
        if ( (tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
    }

    //����ͷ���Ԫ��
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    //����β���Ԫ��
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    //�Ƴ�����ͷԪ�ز�����
    public E pollFirst() {
        int h = head;
        @SuppressWarnings("unchecked")
        E result = (E) elements[h];
        //��Ϊ���в��������null�����������ؿգ���ʾ�Ѿ��ǿն�����
        if (result == null)
            return null;
        //�Ƴ����
        elements[h] = null;
        //�ƶ� head ָ������
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    //�Ƴ�����βԪ�ز�����
    public E pollLast() {
        //��Ϊ����βָ��ָ���ʵ�ʶ���β��һλ��������Ҫ��ǰ��һ
        int t = (tail - 1) & (elements.length - 1);
        @SuppressWarnings("unchecked")
        E result = (E) elements[t];
        if (result == null)
            return null;
        elements[t] = null;
        tail = t;
        return result;
    }

    //�Ƴ�����ͷԪ��
    public E removeFirst() {
        E x = pollFirst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    //�Ƴ�����βԪ��
    public E removeLast() {
        E x = pollLast();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    //��ö���ͷԪ��
    public E getFirst() {
        @SuppressWarnings("unchecked")
        E result = (E) elements[head];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    //��ö���βԪ��
    public E getLast() {
        @SuppressWarnings("unchecked")
        E result = (E) elements[(tail - 1) & (elements.length - 1)];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    //��ö���ͷԪ��
    @SuppressWarnings("unchecked")
    public E peekFirst() {
        // elements[head] is null if deque empty
        return (E) elements[head];
    }

    //��ö���βԪ��
    @SuppressWarnings("unchecked")
    public E peekLast() {
        return (E) elements[(tail - 1) & (elements.length - 1)];
    }


    //ɾ����һ����ͬԪ�أ���ͷ����
    public boolean removeFirstOccurrence(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = head;
        Object x;
        //����null�жϣ����ڲ��������null
        while ( (x = elements[i]) != null) {
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i + 1) & mask;
        }
        return false;
    }

    //ɾ����һ����ͬԪ�أ���β����
    public boolean removeLastOccurrence(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = (tail - 1) & mask;
        Object x;
        //����null�жϣ����ڲ��������null
        while ( (x = elements[i]) != null) {
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i - 1) & mask;
        }
        return false;
    }


    // *** Queue methods ***

    //����β���Ԫ��
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    //����β���Ԫ��
    public boolean offer(E e) {
        return offerLast(e);
    }

    //�Ƴ�����ͷԪ��
    public E remove() {
        return removeFirst();
    }

    //�Ƴ�����ͷԪ�ز�����
    public E poll() {
        return pollFirst();
    }

    //��ö���ͷԪ��
    public E element() {
        return getFirst();
    }

    //��ö���ͷԪ��
    public E peek() {
        return peekFirst();
    }

    // *** Stack methods ***

    //����ͷ�����Ԫ��
    public void push(E e) {
        addFirst(e);
    }

    //�Ƴ�����ͷԪ��
    public E pop() {
        return removeFirst();
    }

    // *** Object methods ***

    //��¡����ǳ����
    public ArrayDeque<E> clone() {
        try {
            @SuppressWarnings("unchecked")
            ArrayDeque<E> result = (ArrayDeque<E>) super.clone();
            result.elements = Arrays.copyOf(elements, elements.length);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // *** Collection Methods ***

    //���ض��г���
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    //��Ϊ����ͷ��������һ��Ԫ���������ʱ���ض����������
    //���ֻ���ڸչ���ʱ�Ż���� head = tail = 0 ������������ǿ�����
    public boolean isEmpty() {
        return head == tail;
    }

    //������
    public Iterator<E> iterator() {
        return new DeqIterator();
    }

    //���������
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    //�ж��Ƿ����ĳ��Ԫ��
    public boolean contains(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = head;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equals(x))
                return true;
            i = (i + 1) & mask;
        }
        return false;
    }

    //˳�����ɾ��ĳ��Ԫ��
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    //���
    public void clear() {
        int h = head;
        int t = tail;
        if (h != t) { // clear all cells
            head = tail = 0;
            int i = h;
            int mask = elements.length - 1;
            //�����������
            do {
                elements[i] = null;
                i = (i + 1) & mask;
            } while (i != t);
        }
    }

    //ת���飬����תΪ˳�����飬���ص���һ�������顣
    public Object[] toArray() {
        return copyElements(new Object[size()]);
    }

    //ת��ָ������
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int size = size();
        if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        copyElements(a);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public Spliterator<E> spliterator() {
        return new DeqSpliterator<E>(this, -1, -1);
    }

    /*
     * **********************************************************************
     * private����
     * **********************************************************************
     */

    //�������������ڵ���8������ȴ�����������С�� 2����
    //����1���õ�8������8���õ�16������17���õ�32
    private void allocateElements(int numElements) {
        //��С���� 8
        int initialCapacity = MIN_INITIAL_CAPACITY;
        //��������ָ�����ȴ��ڵ��� 8�����������С
        //�㷨���û�������������㣬������ʼ��Ϊ2��n�η�������
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            //������� int ������ 2^31-1����Ҫ���̳���
            if (initialCapacity < 0)
                //��СΪ 2^30
                initialCapacity >>>= 1;
        }
//        ����Է����еļ������ƾ�������ͣ������ʼֵΪ������ 1XXX XXXX XXXX ��
//        1. ��һ������1λ����0���Ͽ�λ��01XX XXXX XXXX ��Ȼ����� ������ �� 11XX XXXX XXXX
//        2. �ڶ�������2λ��0011 XXXX XXXX �������� �� 1111 XXXX XXXX
//        3. ����������4λ����� 1111 1111 XXXX
//        4. .........��Ϊ initialCapacity �������� int ���ö����Ʋ����ʾ��ֵ�����ֵ�� 2^31 - 1 ���� 0x7fffffff �������Ʊ�ʾΪ32λ���������һ������16λ����Բ���32λ
//        5. Ȼ�� initialCapacity++ ���� 1111 1111 1111 ��λΪ 1 0000 0000 0000 ��� 2^12 ����2��12���ݡ�����
//        6. ��� initialCapacity++ ǰ���Ѿ��� int ���ֵ����ôʵ��ֵ���� int ���ֵ����Ϊ int ��Сֵ -2^31  ���� 0x80000000 ����ΪС��0����������һλ���  2^30  ���� 0x40000000 ��
        elements = new Object[initialCapacity];
    }

    //˫�����ݣ����������������ʱ������
    private void doubleCapacity() {
        //���ԣ������Ѿ�������
        assert head == tail;
        //��¼����ͷ����
        int p = head;
        //��¼���г���
        int n = elements.length;
        //����ͷ�������β��Ԫ�ظ�����0---����β---null---����ͷ---����β
        int r = n - p;
        //����1λ�൱�ڳ���2��˫������
        int newCapacity = n << 1;
        //�����ȳ���int���ֵ���׳��쳣��������󳤶� 2^30
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        //�½�����
        Object[] a = new Object[newCapacity];
        //��p��ʼ���ҵ�Ԫ�ظ�ֵ��������
        System.arraycopy(elements, p, a, 0, r);
        //��0��p����Ϊԭ��������������Ԫ�ظ��Ƶ�������
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        //���¶�����head��tail
        head = 0;
        tail = n;
    }



    //д����
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();
        s.writeInt(size());
        int mask = elements.length - 1;
        for (int i = head; i != tail; i = (i + 1) & mask)
            s.writeObject(elements[i]);
    }

    //�����ж�ȡ
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        int size = s.readInt();
        allocateElements(size);
        head = 0;
        tail = size;
        for (int i = 0; i < size; i++)
            elements[i] = s.readObject();
    }


    //�������У������п�����һ��ָ�����顣
    private <T> T[] copyElements(T[] a) {
        if (head < tail) {
            //��� ͷ������β����С������ֱ�Ӹ���
            System.arraycopy(elements, head, a, 0, size());
        } else if (head > tail) {
            //��� ͷ������β������ѭ��������Ҫ�ֶθ���
            int headPortionLen = elements.length - head;
            System.arraycopy(elements, head, a, 0, headPortionLen);
            System.arraycopy(elements, 0, a, headPortionLen, tail);
        }
        //��� ͷ������β����һ���󣨶���0�����ն��в���Ҫ������
        return a;
    }

    //�жϵ�ǰ�����Ƿ��������
    private void checkInvariants() {
        assert elements[tail] == null;
        assert head == tail ? elements[head] == null :
                (elements[head] != null &&
                        elements[(tail - 1) & (elements.length - 1)] != null);
        assert elements[(head - 1) & (elements.length - 1)] == null;
    }

    //ɾ��ָ���±��Ԫ�أ���Ϊ���в��������գ�������Ҫ�ƶ�����
    private boolean delete(int i) {
        checkInvariants();
        final Object[] elements = this.elements;
        final int mask = elements.length - 1;
        final int h = head;
        final int t = tail;
        final int front = (i - h) & mask;
        final int back  = (t - i) & mask;

        // Invariant: head <= i < tail mod circularity
        if (front >= ((t - h) & mask))
            throw new ConcurrentModificationException();

        // Optimize for least element motion
        if (front < back) {
            if (h <= i) {
                System.arraycopy(elements, h, elements, h + 1, front);
            } else { // Wrap around
                System.arraycopy(elements, 0, elements, 1, i);
                elements[0] = elements[mask];
                System.arraycopy(elements, h, elements, h + 1, mask - h);
            }
            elements[h] = null;
            head = (h + 1) & mask;
            return false;
        } else {
            if (i < t) { // Copy the null tail as well
                System.arraycopy(elements, i + 1, elements, i, back);
                tail = t - 1;
            } else { // Wrap around
                System.arraycopy(elements, i + 1, elements, i, mask - i);
                elements[mask] = elements[0];
                System.arraycopy(elements, 1, elements, 0, t);
                tail = (t - 1) & mask;
            }
            return true;
        }
    }



    private class DeqIterator implements Iterator<E> {
        private int cursor = head;
        private int fence = tail;
        private int lastRet = -1;

        public boolean hasNext() {
            return cursor != fence;
        }

        public E next() {
            if (cursor == fence)
                throw new NoSuchElementException();
            @SuppressWarnings("unchecked")
            E result = (E) elements[cursor];
            // This check doesn't catch all possible comodifications,
            // but does catch the ones that corrupt traversal
            if (tail != fence || result == null)
                throw new ConcurrentModificationException();
            lastRet = cursor;
            cursor = (cursor + 1) & (elements.length - 1);
            return result;
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            if (delete(lastRet)) { // if left-shifted, undo increment in next()
                cursor = (cursor - 1) & (elements.length - 1);
                fence = tail;
            }
            lastRet = -1;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            Object[] a = elements;
            int m = a.length - 1, f = fence, i = cursor;
            cursor = f;
            while (i != f) {
                @SuppressWarnings("unchecked") E e = (E)a[i];
                i = (i + 1) & m;
                if (e == null)
                    throw new ConcurrentModificationException();
                action.accept(e);
            }
        }
    }

    private class DescendingIterator implements Iterator<E> {

        private int cursor = tail;
        private int fence = head;
        private int lastRet = -1;

        public boolean hasNext() {
            return cursor != fence;
        }

        public E next() {
            if (cursor == fence)
                throw new NoSuchElementException();
            cursor = (cursor - 1) & (elements.length - 1);
            @SuppressWarnings("unchecked")
            E result = (E) elements[cursor];
            if (head != fence || result == null)
                throw new ConcurrentModificationException();
            lastRet = cursor;
            return result;
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            if (!delete(lastRet)) {
                cursor = (cursor + 1) & (elements.length - 1);
                fence = head;
            }
            lastRet = -1;
        }
    }

    static final class DeqSpliterator<E> implements Spliterator<E> {
        private final ArrayDeque<E> deq;
        private int fence;  // -1 until first use
        private int index;  // current index, modified on traverse/split

        /** Creates new spliterator covering the given array and range */
        DeqSpliterator(ArrayDeque<E> deq, int origin, int fence) {
            this.deq = deq;
            this.index = origin;
            this.fence = fence;
        }

        private int getFence() { // force initialization
            int t;
            if ((t = fence) < 0) {
                t = fence = deq.tail;
                index = deq.head;
            }
            return t;
        }

        public DeqSpliterator<E> trySplit() {
            int t = getFence(), h = index, n = deq.elements.length;
            if (h != t && ((h + 1) & (n - 1)) != t) {
                if (h > t)
                    t += n;
                int m = ((h + t) >>> 1) & (n - 1);
                return new DeqSpliterator<>(deq, h, index = m);
            }
            return null;
        }

        public void forEachRemaining(Consumer<? super E> consumer) {
            if (consumer == null)
                throw new NullPointerException();
            Object[] a = deq.elements;
            int m = a.length - 1, f = getFence(), i = index;
            index = f;
            while (i != f) {
                @SuppressWarnings("unchecked") E e = (E)a[i];
                i = (i + 1) & m;
                if (e == null)
                    throw new ConcurrentModificationException();
                consumer.accept(e);
            }
        }

        public boolean tryAdvance(Consumer<? super E> consumer) {
            if (consumer == null)
                throw new NullPointerException();
            Object[] a = deq.elements;
            int m = a.length - 1, f = getFence(), i = index;
            if (i != fence) {
                @SuppressWarnings("unchecked") E e = (E)a[i];
                index = (i + 1) & m;
                if (e == null)
                    throw new ConcurrentModificationException();
                consumer.accept(e);
                return true;
            }
            return false;
        }

        public long estimateSize() {
            int n = getFence() - index;
            if (n < 0)
                n += deq.elements.length;
            return (long) n;
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED |
                    Spliterator.NONNULL | Spliterator.SUBSIZED;
        }
    }

}

