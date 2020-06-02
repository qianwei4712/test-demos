package setsources;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;

/**
 * һ���������ظ�Ԫ�أ�������e1.equals(e2)������
 * �������null��ֻ�ɴ���һ�� null Ԫ��
 * @author shiva   2020/4/25 16:38
 */
public interface Set<E> extends Collection<E> {
    // ��ѯ����

    //���ش˼����е�Ԫ����������˼��������� Integer.MAX_VALUE���򷵻� Integer.MAX_VALUE��
    int size();

    //������ϲ������κ�Ԫ�أ��򷵻�true
    boolean isEmpty();

    //����˼��ϰ���ָ����Ԫ�أ��򷵻� true��
    //����ʽ�ؽ������ҽ����˼��ϰ���һ��Ԫ�� e��ʹ�ã�o == null��e == null��o ʱ���ŷ��� true����
    boolean contains(Object o);

    //���ش˼�����Ԫ�صĵ�������Ԫ���Բ��ض���˳�򷵻أ����Ǵ˼������ṩ��֤��ĳЩ���ʵ������
    Iterator<E> iterator();

    //ת����
    Object[] toArray();

    //ת��ָ������
    <T> T[] toArray(T[] a);

    // �޸Ĳ���

    //���ָ����Ԫ���в����ڣ�������ӵ��ü��ϣ���ѡ��������
    //����ʽ�ؽ�����������в������κ�Ԫ�� e2 ����e == null��e2 == null ��e.equals��e2��
    //����˼����Ѿ�����Ԫ�أ�����ý�ʹ�ü��ϱ��ֲ��䣬������ false��
    //��Ϲ��캯���ϵ����ƣ�����ȷ��������Զ��������ظ�Ԫ�ء�
    boolean add(E e);

    //�������ָ����Ԫ�أ���Ӹü�����ɾ������ѡ��������
    //����ʽ�ؽ���ɾ��Ԫ�� e������o == null��e == null��o.equals��e��
    //����˼��ϰ���Ԫ�أ��򷵻� true�����ߵ�Ч�أ�����˼�����Ϊ���ý�����ģ���
    //��һ�����÷��أ��˼��Ͻ���������Ԫ�ء���
    boolean remove(Object o);

    // ��������

    //����ȫ��
    boolean containsAll(Collection<?> c);

    //�������
    boolean addAll(Collection<? extends E> c);

    //ɾ���
    boolean retainAll(Collection<?> c);

    //ɾ������
    boolean removeAll(Collection<?> c);

    //ɾ������Ԫ�أ������󼯺�Ϊ��
    void clear();

    // �ȽϺ͹�ϣ

    //�Ƚ�ָ������������õ�����ԡ�
    //���ָ������Ҳ��һ�����ϣ����������Ͼ�����ͬ�Ĵ�С������ָ�����ϵ�ÿ����Ա�������ڴ˼����У����Ч�أ��ü��ϵ�ÿ����Ա�����򷵻� true��
    //�˶���ȷ�� equals��������set�ӿڵĲ�ͬʵ��������������
    boolean equals(Object o);

    //���ش˼��ϵĹ�ϣ��ֵ��
    //���ϵĹ�ϣ�붨��Ϊ������Ԫ�صĹ�ϣ��֮�ͣ����� nullԪ�صĹ�ϣ�붨��Ϊ�㡣
    //��ȷ�� s1.equals��s2����ζ�� s1.hashCode����== s2.hashCode����,������������ s1 �� s2������hashCode��һ��Լ����Ҫ��ġ�
    int hashCode();

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, Spliterator.DISTINCT);
    }
}

