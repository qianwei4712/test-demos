package stackandqueue;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * ջ�࣬�� JDK1.0 ��ʼ���ڣ��Ѿ������Ƽ�ʹ��
 * �Ƽ�ʹ�� ArrayDeque ʵ��ջ��
 * @author shiva   2020/3/31 21:16
 */
class Stack<E> extends Vector<E> {
    //�޲ι�����
    public Stack() {
    }
    //��Ԫ��ѹ��ջ�������أ����� Vector����ӷ���
    public E push(E item) {
        addElement(item);
        return item;
    }

    //����ջ����Ԫ��
    public synchronized E pop() {
        E       obj;
        int     len = size();
        obj = peek();
        removeElementAt(len - 1);
        return obj;
    }

    //ֻ����ջ���˵�Ԫ�أ���������Ԫ�أ���ջ���׳��쳣��
    public synchronized E peek() {
        int  len = size();
        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

    //�ж��Ƿ�Ϊ��ջ
    public boolean empty() {
        return size() == 0;
    }

    //����������˵�Ŀ��Ԫ�ص����˵ľ���
    public synchronized int search(Object o) {
        int i = lastIndexOf(o);
        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }
    //���л�
    private static final long serialVersionUID = 1224463164541339165L;
}

