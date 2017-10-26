package javaexercise.interview.basis;

/**
 * 使用final关键字修饰一个变量时，是指引用变量不能变，引用变量所指向的对象中的内容还是可以改变的
 * @author rxh
 */
public class FinalTest
{
    public static void main(String[] args)
    {
//        finalVariableTest();
//        finalMyClassTest();
//        finalStaticTest();
        finalMethodTest();
    }
    
    private static void finalReferenceTest() {
        final StringBuffer buffer = new StringBuffer("immutable");
        // buffer = new StringBuffer("");
        buffer.append(" broken!");
        
    }
    
    private static void finalVariableTest() {
        
        String a = "hello2";
        final String b = "hello";
        final String f = getHello();
        String d = "hello";
        
        String c = b + 2;
        String e = d + 2;
        String g = f + 2;
        
        System.out.println((a==c));
        System.out.println((a==e));
        System.out.println((a==g));
    }
    
    private static String getHello() {
        return "hello";
    }
    
    private static void finalMyClassTest() {
        
        final MyClass myClass = new MyClass();
        System.out.println(++myClass.i);
    }
    
    private static void finalStaticTest() {
        
        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        
        System.out.println(myClass1.j);
        System.out.println(myClass1.k);
        
        System.out.println(myClass2.j);
        System.out.println(myClass2.k);
    }
    
    private static void finalMethodTest() {
        
        MyClass myClass = new MyClass();
        StringBuffer buffer = new StringBuffer("hello");
        myClass.changeValue(buffer);
        System.out.println(buffer.toString());
    }
}

class MyClass {
    public int i = 0;
    public final double j = Math.random();
    public static double k = Math.random();

    void changeValue(final StringBuffer buffer) {
        buffer.append(" world");
    }
}