package javaexercise.interview.thread;

import java.lang.Thread.UncaughtExceptionHandler;

class Task implements Runnable
{

    @Override
    public void run()
    {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("234"));
        System.out.println(Integer.parseInt("345"));
        // This will cause NumberFormatException
        System.out.println(Integer.parseInt("XYZ"));
        System.out.println(Integer.parseInt("456"));

    }

}

class ExceptionHandler implements UncaughtExceptionHandler
{

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {

        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getId());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n", t.getState());
        new Thread(new Task()).start();
    }

}

public class UncaughtExceptionHandlerTest
{
    

    private static void withUncaughtException()
    {
        Task task = new Task();
        Thread th = new Thread(task);
        th.start();
    }

    public static void main(String[] args)
    {
        withUncaughtException();
    }
}
