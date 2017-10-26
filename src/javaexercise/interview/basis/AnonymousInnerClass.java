package javaexercise.interview.basis;

class A
{
    public void a()
    {
        System.out.println("AAA");
    }

    public void z()
    {
        System.out.println("ZZZ");
    }
}

class B
{
    public static A getA()
    {
        return new A()
        {
            public void a()
            {
                System.out.println("BBB");
            }
        };
    }
}

public class AnonymousInnerClass
{
    public static void main(String[] args)
    {
        A myA = B.getA();
        myA.a(); // 输出：BBB
        myA.z(); // 输出：ZZZ
    }
}
