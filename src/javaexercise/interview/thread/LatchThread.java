package javaexercise.interview.thread;

import java.util.concurrent.CountDownLatch;

public class LatchThread extends Thread
{
    private final CountDownLatch startLatch;

    public LatchThread(CountDownLatch startLatch)
    {
        this.startLatch = startLatch;
    }

    @Override
    public void run()
    {
        try
        {
            startLatch.await();
            // 执行任务
            System.out.println("线程：" + Thread.currentThread().getName());
        } catch (InterruptedException e)
        {
        }
    }

    public static void startExecute()
    {
        CountDownLatch startLatch = new CountDownLatch(1);
        for (int i = 0; i < 4; i++)
        {
            LatchThread thread = new LatchThread(startLatch);
            thread.start();
        }

        try
        {
            // 线程开始执行时，可以执行一些初始化代码
            System.out.println("所有线程开始同时执行");
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        startLatch.countDown();
    }

    public static void main(String[] args)
    {
        // startExecute();
        StopLatchedThread.performParallelTask();
    }
}

class StopLatchedThread extends Thread
{
    private final CountDownLatch stopLatch;

    public StopLatchedThread(CountDownLatch stopLatch)
    {
        this.stopLatch = stopLatch;
    }

    @Override
    public void run()
    {
        try
        {
            // 执行任务
            System.out.println("线程名称:" + Thread.currentThread().getName());
        } finally
        {
            stopLatch.countDown();
        }
    }

    public static void performParallelTask()
    {
        System.out.println("开始执行");
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++)
        {
            Thread t = new StopLatchedThread(cdl);
            t.start();
        }

        try
        {

            cdl.await();
            System.out.println("结束执行");

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
