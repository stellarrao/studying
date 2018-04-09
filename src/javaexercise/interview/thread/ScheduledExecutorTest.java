package javaexercise.interview.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class ScheduledTask implements Runnable
{
    private String name;

    public ScheduledTask(String name)
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
            System.out.println("Doing a task during : " + name + " - Time - " + new Date());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

public class ScheduledExecutorTest
{
    private static void scheduledExecutorOperation()
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        ScheduledTask task1 = new ScheduledTask("Demo Task 1");
        ScheduledTask task2 = new ScheduledTask("Demo Task 2");
        System.out.println("The time is : " + new Date());

        executor.schedule(task1, 5, TimeUnit.SECONDS);
        executor.schedule(task2, 10, TimeUnit.SECONDS);
        try
        {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    private static void scheduledExecutorOperationPeriodically()
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledTask task1 = new ScheduledTask("Demo Task 1");
        System.out.println("The time is : " + new Date());

        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 5, 5, TimeUnit.SECONDS);
        try
        {
            TimeUnit.MILLISECONDS.sleep(20000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    public static void main(String[] args)
    {
        // scheduledExecutorOperation();
        scheduledExecutorOperationPeriodically();
    }
}
