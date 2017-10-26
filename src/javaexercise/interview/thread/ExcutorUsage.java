package javaexercise.interview.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class TestOne implements Runnable
{

    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("Excuting task one");
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}

class TestTwo implements Runnable
{
    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("Excuting task two");
            try
            {
                Thread.sleep(1000);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

class TestThree implements Runnable
{
    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("Excuting task Three");
            try
            {
                Thread.sleep(1000);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

class MultiRunnable implements Runnable
{
    private final List<Runnable> runnables;

    public MultiRunnable(List<Runnable> runnables)
    {
        this.runnables = runnables;
    }

    @Override
    public void run()
    {
        for (Runnable runnable : runnables)
        {
            new Thread(runnable).start();
        }
    }

}

class RejectedExecutionHandlerImpl implements RejectedExecutionHandler
{

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor)
    {
        System.out.println(runnable.toString() + " : I've been rejected! ");
    }

}

public class ExcutorUsage
{
    private static ExecutorService executor = null;

    private static volatile Future taskOneResults = null;

    private static volatile Future taskTwoResults = null;

    private static void checkTasks() throws Exception
    {
        if (taskOneResults == null || taskOneResults.isDone() || taskOneResults.isCancelled())
        {
            taskOneResults = executor.submit(new TestOne());
        }

        if (taskTwoResults == null || taskTwoResults.isDone() || taskTwoResults.isCancelled())
        {
            taskTwoResults = executor.submit(new TestTwo());
        }
    }

    private static void testDurationTasks()
    {
        executor = Executors.newFixedThreadPool(2);

        while (true)
        {
            try
            {
                checkTasks();
                Thread.sleep(1000);
            } catch (Exception e)
            {
                System.out.println("Caught exception: " + e.getMessage());
            }
        }

    }

    private static void testMultiTaskExecutor()
    {
        BlockingQueue<Runnable> worksQueue = new ArrayBlockingQueue<Runnable>(10);
        RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandlerImpl();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS, worksQueue, rejectionHandler);
        executor.prestartAllCoreThreads();

        List<Runnable> taskGroup = new ArrayList<Runnable>();
        taskGroup.add(new TestOne());
        taskGroup.add(new TestTwo());
        taskGroup.add(new TestThree());

        worksQueue.add(new MultiRunnable(taskGroup));
    }

    public static void main(String[] args)
    {
        // testDurationTasks();
        
        testMultiTaskExecutor();
    }

}
