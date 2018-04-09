package javaexercise.interview.basis;

class SSClass
{
    static
    {
        System.out.println("SSClass");
    }
}

class SuperClass extends SSClass
{
    static
    {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    public SuperClass()
    {
        System.out.println("init SuperClass");
    }
}

class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass()
    {
        System.out.println("init SubClass");
    }
}

public class StaticInitalTest
{
    private static int staticValue = 100;
    
    public static void main(String[] args)
    {
        //System.out.println(SubClass.a);
        new StaticInitalTest().test();
        
    }
    
    public void test() {
        System.out.println(this.staticValue);
    }
}
