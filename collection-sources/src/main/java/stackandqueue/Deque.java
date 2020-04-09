package stackandqueue;

import java.util.Iterator;

/**
 * @author shiva   2020/3/31 21:18
 */
public interface Deque<E> extends Queue<E> {

    /**
     * ���� Queue ����
     */

    //��β���Ԫ�أ������׳�IllegalStateException
    boolean add(E e);

    //��β���Ԫ�أ���������false
    boolean offer(E e);

    //�Ƴ���ͷԪ�ز����أ��������Ϊ���׳�NoSuchElementException
    E remove();

    //�Ƴ���ͷԪ�ز����أ��������Ϊ�շ���null
    E poll();

    //���ض�ͷԪ�أ����ǲ�ɾ�����������Ϊ���׳�NoSuchElementException
    E element();

    //���ض�ͷԪ�أ����ǲ�ɾ�����������Ϊ�շ���null
    E peek();


    /**
     * Deque ��������
     */

    //��ͷ���Ԫ�أ������׳�IllegalStateException
    void addFirst(E e);

    //��β���Ԫ�أ������׳�IllegalStateException
    void addLast(E e);

    //��ͷ���Ԫ�أ���������false
    boolean offerFirst(E e);

    //��β���Ԫ�أ���������false
    boolean offerLast(E e);

    //�Ƴ���ͷԪ�ز����أ��������Ϊ���׳�NoSuchElementException
    E removeFirst();

    //�Ƴ���βԪ�ز����أ��������Ϊ���׳�NoSuchElementException
    E removeLast();

    //�Ƴ���ͷԪ�ز����أ��������Ϊ�շ���null
    E pollFirst();

    //�Ƴ���βԪ�ز����أ��������Ϊ�շ���null
    E pollLast();

    //��ö�ͷԪ�أ����ǲ�ɾ�����������Ϊ���׳�NoSuchElementException
    E getFirst();

    //��ö�βԪ�أ����ǲ�ɾ�����������Ϊ���׳�NoSuchElementException
    E getLast();

    //���ض�ͷԪ�أ����ǲ�ɾ�����������Ϊ�շ���null
    E peekFirst();

    //���ض�βԪ�أ����ǲ�ɾ�����������Ϊ�շ���null
    E peekLast();

    //ɾ����һ����ͬԪ�أ���ͷ��ʼ����
    //����������ͺͷ��Ͳ����ݣ��׳�ClassCastException
    //���ָ��Ԫ��Ϊnull,����������в�����Ϊnull���׳� NullPointerException
    boolean removeFirstOccurrence(Object o);

    //ɾ����һ����ͬԪ�أ���β��ʼ����
    //����������ͺͷ��Ͳ����ݣ��׳�ClassCastException
    //���ָ��Ԫ��Ϊnull,����������в�����Ϊnull���׳� NullPointerException
    boolean removeLastOccurrence(Object o);


    /**
     * ջStack �ķ���
     */

    //��Ԫ��ѹ��ջ��������
    //��Υ���������ƣ��׳�IllegalStateException
    //����������ͺͷ��Ͳ����ݣ��׳�ClassCastException
    //���ָ��Ԫ��Ϊnull,����������в�����Ϊnull���׳� NullPointerException
    //������Ԫ�ص�ĳЩ������ֹѹ�룬��ô�׳�IllegalArgumentException
    void push(E e);

    //����ջ����Ԫ��
    E pop();


    /**
     * Collection �ķ���
     */

    //�Ƴ���һ��ָ��Ԫ�أ�˳�����
    boolean remove(Object o);

    //˳���ж��Ƿ����
    boolean contains(Object o);

    //���ض��г���
    public int size();

    //���ص�����
    Iterator<E> iterator();

    //������ǿ������
    Iterator<E> descendingIterator();

}

