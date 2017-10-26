package javaexercise.interview.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class DemoThread implements Runnable
{
    private String name = null;

    public DemoThread(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }


    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Executing : " + name);
    }

}

class CustomThreadPoolExecutor extends ThreadPoolExecutor
{

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        super.beforeExecute(t, r);
        System.out.println("Perform beforeExecute() logic");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t)
    {
        super.afterExecute(r, t);
        if (t != null)
        {
            System.out.println("Perform exception handler logic");
        }
        System.out.println("Perform afterExecute() logic");
    }

}

public class DemoExecutor
{
    public static void main(String[] args)
    {
        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(10, 20, 5000, TimeUnit.MICROSECONDS, blockingQueue);

        executor.setRejectedExecutionHandler(new RejectedExecutionHandler()
        {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
            {
                System.out.println("DemoTask Rejected : " + ((DemoThread) r).getName());
                System.out.println("Waiting for a second !!");
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Lets and another time : " + ((DemoThread) r).getName());
                executor.execute(r);
            }
        });

        executor.prestartAllCoreThreads();
        while (true)
        {
            threadCounter++;
            System.out.println("Adding DemoTask : " + threadCounter);
            executor.execute(new DemoThread(threadCounter.toString()));
            if (threadCounter == 100) break;
        }
    }
}