package javaexercise.interview.basis;

public class TryFinallyReturn
{
    static int test()
    {
        int i = 1;
        try
        {
            System.out.println("try里面的i:" + i);
            return i;
        } finally
        {
            System.out.println("进入finally...");
            ++i;
            System.out.println("finally里面的i : " + i);
        }
    }
    
    @SuppressWarnings("finally")
    static int test1()
    {
        int i = 1;
        try
        {
            System.out.println("try里面的i:" + i);
            return i;
        } finally
        {
            System.out.println("进入finally...");
            ++i;
            System.out.println("finally里面的i : " + i);
            return i;
        }
    }
    
    public static void main(String [] args) {
//        System.out.println("结果: " + test());
        System.out.println("结果: " + test1());
    }
}
