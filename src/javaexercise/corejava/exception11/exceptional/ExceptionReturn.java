package javaexercise.corejava.exception11.exceptional;

public class ExceptionReturn
{
    public static void main(String[] args)
    {
        // System.out.println(Test());
        System.out.println(ReturnResultTest());
    }

    public static int Test()
    {
        int x = 1;

        try
        {
            x++;
            return x;
        } finally
        {
            ++x;
        }
    }

    @SuppressWarnings("finally")
    public static int ReturnResultTest()
    {
        /*
         * break can only be used in switch & loops (for/while/do-while)
         * if(true) {
            break; //break cannot be used outside of a loop or a switch
        }*/ 
        
        /* try中的return语句调用的函数先于finally中调用的函数执行，
         * 也就是说return语句先执行，finally语句后执行，所以，返回的结果是2。
         * Return并不是让函数马上返回，而是return语句执行后，
         * 将把返回结果放置进函数栈中，此时函数并不是马上返回，
         * 它要执行finally语句后才真正开始返回。
         */
        try
        {
            System.out.println("try");
            
            return 1;
        } finally
        {
            System.out.println("finally");
            return 2;
        }
    }
}
