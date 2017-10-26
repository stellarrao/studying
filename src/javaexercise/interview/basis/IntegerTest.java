package javaexercise.interview.basis;

/**
 * ①无论如何，Integer与new Integer不会相等。不会经历拆箱过程，d1的引用指向堆，而d2指向专门存放他的内存（常量池），他们的内存地址不一样，所以为false
    ②两个都是非new出来的Integer，如果数在-128到127之间，则是true,否则为false
    java在编译Integer c2= 128的时候,被翻译成-> Integer c2= Integer.valueOf(128);而valueOf()函数会对-128到127之间的数进行缓存
    ③两个都是new出来的,都为false
    ④int和integer(无论new否)比，都为true，因为会把Integer自动拆箱为int再去比
 * @author rxh
 */
public class IntegerTest
{
public static void main(String [] args) {
    testInteger();
    testCacheInteger();
    testNewInteger();
    
}

private static void testInteger() {
    int a1 = 128;
    Integer a2 = 128;
    Integer a3 = new Integer(128);
    
    //Integer会自动拆箱为int，所以为true
    System.out.println("a1 == a2 : " + (a1 == a2));
    System.out.println("a1 == a3 : " + (a1 == a3));
}

private static void testCacheInteger() {
    
    Integer c1 = 128;
    Integer c2 = 128;
    System.out.println("c1 == c2 : " + (c1 == c2));

    Integer b1 = 127;
    Integer b2 = 127;
    System.out.println("b1 == b2 : " + (b1 == b2));
    
}

private static void testNewInteger() {
    
    Integer d1 = new Integer(127);
    Integer d2 = 127;
    System.out.println("d1 == d2 : " + (d1 == d2));
    
    Integer e1 = new Integer(128);
    Integer e2 = new Integer(123);
    System.out.println("e1 == e2 : " + (e1 == e2));
    
}
}
