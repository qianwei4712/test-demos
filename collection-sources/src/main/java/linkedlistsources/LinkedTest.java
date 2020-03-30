package linkedlistsources;


import java.util.Arrays;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * @author shiva   2020/3/18 19:46
 */
class LinkedTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add(1,"sss");


        System.out.println(linkedList);
    }
}
