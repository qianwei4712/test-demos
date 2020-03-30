package vectorsources;

import java.lang.annotation.Native;

/**
 * @author shiva   2020/3/28 19:10
 */
class VectorTest {
    public static void main(String[] args) {
       VectorTest.test2();
    }



    public static void test2(){
        java.util.Vector<Integer> v = new java.util.Vector<>();
        Integer i = 3;
        v.add(7);
        v.add(i);
        synchronized(v) {
            if (!v.contains(i)) {
                v.add(i);
            }else {
                System.out.println("The vertor is contains the object of " + i.toString());
            }
        }
    }



    public static void test1(){
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
//        vector.insertElementAt(10,-1);
        vector.remove(-1);
        System.out.println(vector);
    }
//Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
//    at java.lang.System.arraycopy(
//    Native Method)
//    at vectorsources.Vector.insertElementAt(Vector.java:91)
//    at vectorsources.VectorTest.main(VectorTest.java:10)


}
