package javaexercise.interview.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://howtodoinjava.com/core-java/multi-threading/java-thread-pool-executor-example/
 * https://howtodoinjava.com/core-java/multi-threading/java-fixed-size-thread-pool-executor-example/
 * @author rxh
 */
class ExecutorTask implements Runnable
{
    private String name;

    public ExecutorTask(String name)
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
            Long duration = (long) (Math.random() * 10);
            System.out.println("Doing a task during : " + name);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}

public class ThreadPoolExecutorTest
{
    private static void basicThreadPoolExecutorExample()
    {
        // Use the executor created by the newCachedThreadPool() method
        // only when you have a reasonable number of threads
        // or when they have a short duration.
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i <= 5; i++)
        {
            ExecutorTask task = new ExecutorTask("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }
        executor.shutdown();
    }

    private static void fixedThreadPoolExecutorExample()
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++)
        {
            ExecutorTask task = new ExecutorTask("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }

        System.out.println("Maximum threads inside pool " + executor.getMaximumPoolSize());
        executor.shutdown();
    }

    public static void main(String[] args)
    {
        // basicThreadPoolExecutorExample();
        fixedThreadPoolExecutorExample();
    }
}
