package stackandqueue;

import java.util.Collection;

/**
 * @author shiva   2020/3/31 21:17
 */
interface Queue<E> extends Collection<E> {

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
}

